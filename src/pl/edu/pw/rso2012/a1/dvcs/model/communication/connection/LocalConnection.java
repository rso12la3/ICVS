/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.communication.connection;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingDeque;

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
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowErrorEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.MailMessage;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.exception.NoSuchCommitException;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.RepositoryConfiguration;

/**
 * @author Grzegorz Sancewicz
 * 
 */
public class LocalConnection
{
    private static final String COMMIT_SUBJECT_PREFIX = "commit ";
    private final RepositoryConfiguration repositoryConfiguration;
    private Store store;
    private Session session;
    private final LinkedBlockingDeque<ApplicationEvent> eventQueue;

    public LocalConnection(final RepositoryConfiguration repositoryConfiguration, final LinkedBlockingDeque<ApplicationEvent> eventQueue)
    {
        this.repositoryConfiguration = repositoryConfiguration;
        this.eventQueue = eventQueue;
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

    public MailMessage[] getUnreadMessages() throws MessagingException
    {
        final FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
        return getMessages(ft);
    }
    
    public MailMessage[] getCommitMessages() throws MessagingException
    {
        final SubjectTerm subjectTerm = new SubjectTerm(COMMIT_SUBJECT_PREFIX);
        return getMessages(subjectTerm);
    }
    
    public MailMessage[] getCommitsUpToRevision(final String revision) throws MessagingException
    {
        final Integer versionLimit = new Integer(revision);
        final MailMessage[] result = new MailMessage[versionLimit];
        final SearchTerm subjectTerm = new SubjectTerm(COMMIT_SUBJECT_PREFIX);
        final MailMessage[] messages = getMessages(subjectTerm);
        for (final MailMessage message : messages)
        {
            final String subject = message.getSubject();
            final String[] split = subject.split(" ");
            final String version = split[1];
            final Integer integerVersion = new Integer(version);
            if (integerVersion<=versionLimit)
            {
                result[integerVersion-1] = message;
            }
        }
        return result;
    }
    
    public MailMessage getCommitWithRevision(final String revision) throws MessagingException
    {
        final String subject = COMMIT_SUBJECT_PREFIX + revision;
        //Subject term działa jako substring wiec nalezy sie upewnic czy napewno sie zgadza temat
        final SubjectTerm subjectTerm = new SubjectTerm(subject);
        final MailMessage[] commitMessages = getMessages(subjectTerm);
        for (final MailMessage message : commitMessages)
        {
            if (message.getSubject().equals(subject))
            {
                return message;
            }
        }
        throw new NoSuchCommitException();
    }
    
    private MailMessage[] getMessages(final SearchTerm searchTerm) throws MessagingException
    {
        Message messages[];
        MailMessage result[] = new MailMessage[0];
        synchronized(store)
        {
            if (ensureConnection())
            {
                
            
                final Folder inbox = store.getFolder("Inbox");
                inbox.open(Folder.READ_WRITE);
                messages = inbox.search(searchTerm);
                result = new MailMessage[messages.length];
                int i=0;
                for(final Message message : messages)
                {
                    try
                    {
    
                        final MimeMessage mimeMessage = (MimeMessage)message;
                        final MailMessage mailMessage = new MailMessage("", mimeMessage.getSubject(), mimeMessage.getContent().toString());
                        result[i] = mailMessage;
                        ++i;
                        
                    }
                    catch(final IOException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                
                final Flags flags = new Flags(Flag.SEEN);
                inbox.setFlags(messages, flags, true);
            }
            else
            {
                throw new MessagingException();
            }
        }
        return result;
    }

    public void sendMessages(final MailMessage message) throws AddressException, MessagingException
    {
        synchronized(store)
        {
            if (ensureConnection())
            {
                final Folder inbox = store.getFolder("Inbox");
                inbox.open(Folder.READ_WRITE);
                final Message mailMessage = new MimeMessage(session);
                mailMessage.setText(message.getBody());
                mailMessage.setSubject(message.getSubject());
                mailMessage.addRecipient(RecipientType.TO, new InternetAddress(repositoryConfiguration.getRepositoryAddress()));
                mailMessage.setFrom(new InternetAddress(repositoryConfiguration.getRepositoryAddress()));
                mailMessage.setFlag(Flag.SEEN, true);
                final Message messages[] = {mailMessage};
                inbox.appendMessages(messages);
            }
            else
            {
                throw new MessagingException();
            }
        }
    }

    public void deleteMessages(final List<String> messagesToDelete) throws MessagingException
    {
        synchronized(store)
        {
            if (ensureConnection())
            {
                final Folder inbox = store.getFolder("Inbox");
                inbox.open(Folder.READ_WRITE);
                final Flags flags = new Flags(Flag.DELETED);
                for(final String subject : messagesToDelete)
                {
                    final Message[] messages = inbox.search(new SubjectTerm(subject));
                    inbox.setFlags(messages, flags, true);
                }
            }
            else
            {
                throw new MessagingException();
            }
        }
    }

    private boolean ensureConnection() throws MessagingException
           
    {
        if (!store.isConnected())
        {
            try
            {
                store.connect("imap.gmail.com", repositoryConfiguration.getRepositoryAddress(), repositoryConfiguration.getRepositoryPass());
            }
            catch(final Exception e)
            {
                try
                {
                    eventQueue.put(new ShowErrorEvent("Błędne dane logowania do repozytorium, lub brak połączenia z internetem"));
                    return false;
                }
                catch(final InterruptedException e1)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public Message getNewestCommitMessage() throws MessagingException
    {
        final SubjectTerm subjectTerm = new SubjectTerm(COMMIT_SUBJECT_PREFIX);
        Message messages[] = new Message[0];
        synchronized(store)
        {
            if (ensureConnection())
            {
                final Folder inbox = store.getFolder("Inbox");
                inbox.open(Folder.READ_WRITE);
                messages = inbox.search(subjectTerm);
                final Flags flags = new Flags(Flag.SEEN);
                inbox.setFlags(messages, flags, true);
            }
            else
            {
                throw new MessagingException();
            }
        }
        if (messages.length==0)
            return null;
        return messages[messages.length-1];
    }

    public void clearAll() throws MessagingException
    {
        synchronized(store)
        {
            if (ensureConnection())
            {
                final Folder inbox = store.getFolder("Inbox");
                inbox.open(Folder.READ_WRITE);
                final Flags delete = new Flags(Flag.DELETED);
                final Message[] messages = inbox.getMessages();
                inbox.setFlags(messages, delete, true);
            }
            else
            {
                throw new MessagingException();
            }
        }
    }

}
