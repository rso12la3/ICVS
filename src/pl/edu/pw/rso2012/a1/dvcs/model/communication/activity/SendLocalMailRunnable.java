package pl.edu.pw.rso2012.a1.dvcs.model.communication.activity;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.thoughtworks.xstream.XStream;

import pl.edu.pw.rso2012.a1.dvcs.model.communication.MailMessage;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.PersistentBlockingQueue;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.connection.LocalConnection;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.AbstractOperation;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.CommitOperation;

public class SendLocalMailRunnable implements Runnable
{

    private boolean threadStarted = true;

    private final PersistentBlockingQueue localSendQueue;
    private final LocalConnection localConnection;

    private final XStream xstream;
    
    public SendLocalMailRunnable(final PersistentBlockingQueue localSendQueue,
       final LocalConnection localConnection)
    {
        this.localSendQueue = localSendQueue;
        this.localConnection = localConnection;
        this.xstream = new XStream();
    }

    @Override
    public void run()
    {
        try
        {
            while(isThreadStarted())
            {
                MailMessage message = null;
                try
                {
                    message = localSendQueue.getMessage();
                    prepareCommitVersion(message);
                    localConnection.sendMessages(message);
                }
                catch(final MessagingException e)
                {
                    localSendQueue.putFirst(message);
                }
            }
        }
        catch(final InterruptedException e1)
        {
        }
    }

    private void prepareCommitVersion(final MailMessage message) throws MessagingException
    {
        final String xmlOperation = message.getBody();
        final AbstractOperation operation = (AbstractOperation)xstream.fromXML(xmlOperation);
        if(operation instanceof CommitOperation)
        {
            final String newVersionString = getNewVersionString();
            message.setSubject("commit " + newVersionString);
        }
    }

    /**
     * @return
     * @throws MessagingException
     */
    private String getNewVersionString() throws MessagingException
    {
        final Message newestCommit = localConnection.getNewestCommitMessage();
        final MimeMessage mimeMessage = (MimeMessage)newestCommit;
        if(mimeMessage==null)
        {
            return "1";
        }
        final String[] split = mimeMessage.getSubject().split(" ");
        final String versionString = split[1];
        final Integer version = new Integer(versionString);
        final String newVersionString = new Integer(version+1).toString();
        return newVersionString;
    }

    public void stop()
    {
        setThreadStarted(false);
    }

    public boolean isThreadStarted()
    {
        return threadStarted;
    }

    public void setThreadStarted(final boolean threadStarted)
    {
        this.threadStarted = threadStarted;
    }
}