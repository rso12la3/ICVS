/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.communication;

import java.util.LinkedList;
import java.util.Queue;

import pl.edu.pw.rso2012.a1.dvcs.model.exception.InvalidMessageException;
import pl.edu.pw.rso2012.a1.dvcs.model.exception.ModelMethodNotImplementedException;

/**
 * @author Grzegorz Sancewicz
 * @email g.sancewicz@stud.elka.pw.edu.pl
 * 
 */
public class MessageQueue
{
    public enum QueueType 
    {
        /**
         * FIXME: Czy chodzi o to czy odbiorcza, czy nadawcza ?
         */
        IMAP,
        SMTP
    } 
    private final QueueType queueType;
    private final String name;
    private final Queue<Message> messageQueue;
    
    public MessageQueue(final QueueType queueType, final String name)
    {
        this.queueType = queueType;
        this.name = name;
        this.messageQueue = new LinkedList<Message>();
    }
    
    public void addMessage(final Message message) throws InvalidMessageException, ModelMethodNotImplementedException
    {
        if (!message.isValid())
        {
            throw new InvalidMessageException();
        }
        messageQueue.add(message);
    }
    
    public void removeMessage() throws ModelMethodNotImplementedException
    {
        throw new ModelMethodNotImplementedException();
    }
    
    public void retrieveMessage() throws ModelMethodNotImplementedException
    {
        throw new ModelMethodNotImplementedException();
    }
    
    /**
     * @return the queueType
     */
    public QueueType getQueueType()
    {
        return queueType;
    }
   
    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }
  
}
