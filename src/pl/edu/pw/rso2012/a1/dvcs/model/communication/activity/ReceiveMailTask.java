package pl.edu.pw.rso2012.a1.dvcs.model.communication.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingDeque;

import javax.mail.MessagingException;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.EventFactory;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.MailMessage;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.connection.LocalConnection;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.AbstractOperation;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;

public class ReceiveMailTask extends TimerTask
{
    private final LocalConnection localConnection;
    private final XStream xstream;
    private final EventFactory eventFactory;
    private final LinkedBlockingDeque<ApplicationEvent> eventQueue;
    
    public ReceiveMailTask(final LocalConnection localConnection, final LinkedBlockingDeque<ApplicationEvent> eventQueue)
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
            final MailMessage[] unreadMessages = localConnection.getUnreadMessages();
            final List<String> messagesToDelete = new ArrayList<String>();
            for (final MailMessage message : unreadMessages)
            {
                final String xml = message.getBody();
                try
                {
                    final AbstractOperation operation = deserializeOperation(xml); 
                    final ApplicationEvent event = eventFactory.getEvent(operation);
                    eventQueue.put(event);
                }
                catch (final StreamException e)
                {
                    messagesToDelete.add(message.getSubject());
                }
            }
            deleteMessages(messagesToDelete);
        }
        catch(final MessagingException e)
        {
            e.printStackTrace();
        }
        catch(final InterruptedException e)
        {
            e.printStackTrace();
        }
        
    }

    private AbstractOperation deserializeOperation(final String xml)
    {
        return (AbstractOperation)xstream.fromXML(xml);
    }

    private void deleteMessages(final List<String> messagesToDelete) throws MessagingException
    {
        localConnection.deleteMessages( messagesToDelete);
    }
};