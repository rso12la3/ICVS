package pl.edu.pw.rso2012.a1.dvcs.model.communication.activity;

import javax.mail.MessagingException;

import pl.edu.pw.rso2012.a1.dvcs.model.communication.MailMessage;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.PersistentBlockingQueue;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.connection.LocalConnection;

public class SendLocalMailRunnable implements Runnable
{

    private boolean threadStarted = true;

    private final PersistentBlockingQueue localSendQueue;
    private final LocalConnection localConnection;
    
    public SendLocalMailRunnable(final PersistentBlockingQueue localSendQueue,
       final LocalConnection localConnection)
    {
        this.localSendQueue = localSendQueue;
        this.localConnection = localConnection;
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
                    localConnection.sendMessages(message);
                }
                catch(final MessagingException e)
                {
                    localSendQueue.putFirst(message);
                }
            }
        }
        catch(InterruptedException e1)
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
}