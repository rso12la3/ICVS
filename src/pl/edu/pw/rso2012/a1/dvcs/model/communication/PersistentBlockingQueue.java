package pl.edu.pw.rso2012.a1.dvcs.model.communication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import com.thoughtworks.xstream.XStream;

public class PersistentBlockingQueue
{
    private BlockingDeque<MailMessage> queue;
    private final XStream xstream;
    private final String name;
    public PersistentBlockingQueue(final String name)
    {
        this.name = name;
        xstream = new XStream();
        loadQueue();
    }
    
    public MailMessage getMessage() throws InterruptedException
    {
        MailMessage message = null;
        message = queue.take();
        saveQueue();
        return message;
    }
    
    public void put(final MailMessage mailMessage) throws InterruptedException
    {
        queue.put(mailMessage);
        saveQueue();
    }
    public void putFirst(final MailMessage mailMessage) throws InterruptedException
    {
        queue.putFirst(mailMessage);
        saveQueue();
    }
    
    public void put(final LinkedList<MailMessage> mailMessages) throws InterruptedException
    {
        for (final MailMessage mailMessage : mailMessages)
        {
            queue.put(mailMessage);
        }
        saveQueue();
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
            queue = (BlockingDeque<MailMessage>)xstream.fromXML(fis);
        }
        catch(final FileNotFoundException e)
        {
            queue = new LinkedBlockingDeque<MailMessage>();
            saveQueue();
        }
    }
}
