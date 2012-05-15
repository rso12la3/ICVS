package pl.edu.pw.rso2012.a1.dvcs.model.communication.activity;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

import javax.mail.MessagingException;

import pl.edu.pw.rso2012.a1.dvcs.model.communication.MailMessage;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.connection.LocalConnection;

public class SendLocalMailRunnable implements Runnable
{

    private boolean threadStarted = true;

    private final BlockingQueue<MailMessage> localSendQueue;
    private final LocalConnection localConnection;
    
    public SendLocalMailRunnable(final BlockingQueue<MailMessage> localSendQueue,
       final LocalConnection localConnection)
    {
        this.localSendQueue = localSendQueue;
        this.localConnection = localConnection;
    }

    @Override
    public void run()
    {
        while(isThreadStarted())
        {
            try
            {
                final MailMessage message = localSendQueue.take();
                final LinkedList<MailMessage> messagesToSend = new LinkedList<MailMessage>();
                messagesToSend.add(message);
                localSendQueue.drainTo(messagesToSend);
                localConnection.sendMessages(messagesToSend);
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
}