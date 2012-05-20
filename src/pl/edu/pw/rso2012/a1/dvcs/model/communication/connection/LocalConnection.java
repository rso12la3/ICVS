/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.communication.connection;

import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.search.AndTerm;
import javax.mail.search.DateTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SentDateTerm;
import javax.mail.search.SubjectTerm;

import pl.edu.pw.rso2012.a1.dvcs.model.communication.MailMessage;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.exception.NoSuchCommitException;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.RepositoryConfiguration;

/**
 * @author Grzegorz Sancewicz
 * 
 */
public class LocalConnection
{
    private static final String COMMIT_SUBJECT_PREFIX = "Commit ";
    private final RepositoryConfiguration repositoryConfiguration;
    private Store store;
    private Session session;

    public LocalConnection(final RepositoryConfiguration repositoryConfiguration)
    {
        this.repositoryConfiguration = repositoryConfiguration;
        prepareStore();
    }

    private void prepareStore()
    {
        final Properties properties = System.getProperties();
        properties.setProperty("mail.store.protocol", "imaps");
        session = Session.getDefaultInstance(properties, null);
        try
        {
            store = session.getStore("imaps");
        }
        catch(final NoSuchProviderException e)
        {
            e.printStackTrace();
        }
    }

    public Message[] getUnreadMessages() throws MessagingException
    {
        final FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
        return getMessages(ft);
    }
    
    public Message[] getCommitMessages() throws MessagingException
    {
        final SubjectTerm subjectTerm = new SubjectTerm(COMMIT_SUBJECT_PREFIX);
        return getMessages(subjectTerm);
    }
    
    public Message[] getCommitsUpToRevision(final String revision) throws MessagingException
    {
        final Message commitWithRevision = getCommitWithRevision(revision);
        final Date sentDate = commitWithRevision.getSentDate();
        final DateTerm sentDateTerm = new SentDateTerm(DateTerm.LE, sentDate);
        final SearchTerm subjectTerm = new SubjectTerm(COMMIT_SUBJECT_PREFIX);
        final SearchTerm lessOrEqualCommitTerm = new AndTerm(subjectTerm, sentDateTerm);
        return getMessages(lessOrEqualCommitTerm);
    }
    
    public Message getCommitWithRevision(final String revision) throws MessagingException
    {
        final String subject = COMMIT_SUBJECT_PREFIX + revision;
        //Subject term działa jako substring wiec nalezy sie upewnic czy napewno sie zgadza temat
        final SubjectTerm subjectTerm = new SubjectTerm(subject);
        final Message[] commitMessages = getMessages(subjectTerm);
        for (final Message message : commitMessages)
        {
            if (message.getSubject().equals(subject))
            {
                return message;
            }
        }
        throw new NoSuchCommitException();
    }
    
    private Message[] getMessages(final SearchTerm searchTerm) throws MessagingException
    {
        Message messages[];
        synchronized(store)
        {
            ensureConnection();
            final Folder inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_WRITE);
            messages = inbox.search(searchTerm);
            final Flags flags = new Flags(Flag.SEEN);
            inbox.setFlags(messages, flags, true);
        }
        return messages;
    }

    public void sendMessages(final LinkedList<MailMessage> messagesToSend) throws AddressException, MessagingException
    {
        synchronized(store)
        {
            ensureConnection();
            final Folder inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_WRITE);
            final Message[] messageArray = new Message[messagesToSend.size()];
            final int i=0;
            for (final MailMessage message : messagesToSend)
            {
                final Message mailMessage = new MimeMessage(session);
                mailMessage.setText(message.getBody());
                mailMessage.setSubject(message.getSubject());
                mailMessage.addRecipient(RecipientType.TO, new InternetAddress(repositoryConfiguration.getRepositoryAddress()));
                mailMessage.setFrom(new InternetAddress(repositoryConfiguration.getRepositoryAddress()));
                messageArray[i] = mailMessage;
            }
            if (!(messageArray[0] == null))
            {
                inbox.appendMessages(messageArray);
            }
        }
    }

    public void deleteMessages(final Message[] messagesToDelete) throws MessagingException
    {
        synchronized(store)
        {
            ensureConnection();
            final Folder inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_WRITE);
            final Flags flags = new Flags(Flag.DELETED);
            inbox.setFlags(messagesToDelete, flags, true);
        }
    }

    private void ensureConnection() throws MessagingException
           
    {
        if (!store.isConnected())
        {
            store.connect("imap.gmail.com", repositoryConfiguration.getRepositoryAddress(), repositoryConfiguration.getRepositoryPass());
        }
    }

}
