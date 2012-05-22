package pl.edu.pw.rso2012.a1.dvcs.model.communication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import com.thoughtworks.xstream.XStream;

public class PersistentBlockingQueue
{
    private BlockingQueue<MailMessage> queue;
    private final XStream xstream;
    private final String name;
    public PersistentBlockingQueue(final String name)
    {
        this.name = name;
        xstream = new XStream();
        loadQueue();
    }
    
    public LinkedList<MailMessage> getAllMessages() throws InterruptedException
    {
        final LinkedList<MailMessage> messagesToSend = new LinkedList<MailMessage>();
        synchronized(queue)
        {
            final MailMessage message = queue.take();
            messagesToSend.add(message);
            queue.drainTo(messagesToSend);
            saveQueue();
        }
        return messagesToSend;
    }
    
    public void put(final MailMessage mailMessage) throws InterruptedException
    {
        synchronized(queue)
        {
           queue.put(mailMessage);
        }
    }
    
    public void put(final LinkedList<MailMessage> mailMessages) throws InterruptedException
    {
        synchronized(queue)
        {
            for (final MailMessage mailMessage : mailMessages)
            {
                queue.put(mailMessage);
            }
            saveQueue();
        }
    }
    
    private void saveQueue()
    {
        FileOutputStream fs;
        try
        {
            fs = new FileOutputStream(name+".xml");
            xstream.toXML(queue, fs);
        }
        catch(final FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    private void loadQueue()
    {
        FileInputStream fis;
        try
        {
            fis = new FileInputStream(name+".xml");
            queue = (BlockingQueue<MailMessage>)xstream.fromXML(fis);
        }
        catch(final FileNotFoundException e)
        {
            queue = new LinkedBlockingDeque<MailMessage>();
            saveQueue();
        }
    }
}
