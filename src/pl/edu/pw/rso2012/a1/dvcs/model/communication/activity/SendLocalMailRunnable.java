package pl.edu.pw.rso2012.a1.dvcs.model.communication.activity;

import java.util.LinkedList;

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
        while(isThreadStarted())
        {
            try
            {
                final LinkedList<MailMessage> messagesToSend = localSendQueue.getAllMessages();
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