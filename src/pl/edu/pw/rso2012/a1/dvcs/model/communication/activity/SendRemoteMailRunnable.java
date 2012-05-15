package pl.edu.pw.rso2012.a1.dvcs.model.communication.activity;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import javax.mail.MessagingException;

import pl.edu.pw.rso2012.a1.dvcs.model.communication.MailMessage;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.connection.RemoteConnection;


public class SendRemoteMailRunnable implements Runnable
{
    private boolean threadStarted = true;

    private final BlockingQueue<MailMessage> remoteSendQueue;
    private final RemoteConnection remoteConnection;
    
    public SendRemoteMailRunnable(final BlockingQueue<MailMessage> remoteSendQueue,
        final RemoteConnection remoteConnection)
    {
        this.remoteSendQueue = remoteSendQueue;
        this.remoteConnection = remoteConnection;
    }

    @Override
    public void run()
    {
        while(isThreadStarted())
        {
            try
            {
                final MailMessage message = remoteSendQueue.take();
                final LinkedList<MailMessage> messagesToSend = new LinkedList<MailMessage>();
                messagesToSend.add(message);
                remoteSendQueue.drainTo(messagesToSend);
                remoteConnection.sendMail(messagesToSend);
            }
            catch(final InterruptedException e)
            {
                e.printStackTrace();
            }
            catch(final MessagingException e)
            {
                e.printStackTrace();
            }
        }
        
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
};