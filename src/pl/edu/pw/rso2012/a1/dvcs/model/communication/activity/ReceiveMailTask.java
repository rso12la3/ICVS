package pl.edu.pw.rso2012.a1.dvcs.model.communication.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

import javax.mail.Message;
import javax.mail.MessagingException;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.EventFactory;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.connection.LocalConnection;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.AbstractOperation;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;

public class ReceiveMailTask extends TimerTask
{
    private final LocalConnection localConnection;
    private final XStream xstream;
    private final EventFactory eventFactory;
    private LinkedBlockingQueue<ApplicationEvent> eventQueue;
    
    public ReceiveMailTask(final LocalConnection localConnection, LinkedBlockingQueue<ApplicationEvent> eventQueue)
    {
        this.localConnection = localConnection;
        this.eventQueue = eventQueue;
        xstream = new XStream();
        eventFactory = new EventFactory();
    }

    @Override
    public void run()
    {
        try
        {
            final Message[] unreadMessages = localConnection.getUnreadMessages();
            final List<Message> messagesToDelete = new ArrayList<Message>();
            for (final Message message : unreadMessages)
            {
                final String xml = message.getContent().toString();
                try
                {
                    final AbstractOperation operation = deserializeOperation(xml); 
                    ApplicationEvent event = eventFactory.getEvent(operation);
                    eventQueue.put(event);
                }
                catch (final StreamException e)
                {
                    messagesToDelete.add(message);
                }
            }
            deleteMessages(messagesToDelete);
        }
        catch(final MessagingException e)
        {
            e.printStackTrace();
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        
    }

    private AbstractOperation deserializeOperation(final String xml)
    {
        return (AbstractOperation)xstream.fromXML(xml);
    }

    private void deleteMessages(final List<Message> messagesToDelete) throws MessagingException
    {
        localConnection.deleteMessages( messagesToDelete.toArray(new Message[messagesToDelete.size()]));
    }
};