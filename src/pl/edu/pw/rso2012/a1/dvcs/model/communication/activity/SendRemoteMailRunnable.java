package pl.edu.pw.rso2012.a1.dvcs.model.communication.activity;

import javax.mail.MessagingException;

import pl.edu.pw.rso2012.a1.dvcs.model.communication.MailMessage;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.PersistentBlockingQueue;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.connection.RemoteConnection;


public class SendRemoteMailRunnable implements Runnable
{
    private boolean threadStarted = true;

    private final PersistentBlockingQueue remoteSendQueue;
    private final RemoteConnection remoteConnection;
    
    public SendRemoteMailRunnable(final PersistentBlockingQueue remoteSendQueue,
        final RemoteConnection remoteConnection)
    {
        this.remoteSendQueue = remoteSendQueue;
        this.remoteConnection = remoteConnection;
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
                    message = remoteSendQueue.getMessage();
                    remoteConnection.sendMail(message);
                }
                catch(final MessagingException e)
                {
                    remoteSendQueue.putFirst(message);
                }
            }
        }
        catch(final InterruptedException e)
        {
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