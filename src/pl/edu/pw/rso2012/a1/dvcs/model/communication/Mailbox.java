/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.communication;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.activity.ReceiveMailTask;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.activity.SendLocalMailRunnable;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.activity.SendRemoteMailRunnable;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.connection.LocalConnection;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.connection.RemoteConnection;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.Configuration;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.RepositoryConfiguration;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.CommitOperation;

import com.thoughtworks.xstream.XStream;


/**
 * @author Grzegorz Sancewicz
 * 
 */
public class Mailbox
{
    /**
     * TODO: 
     * * Obudować kolejki aby się serializowały co zmianę
     * * metoda dla Oskara
     * * 
     */
    private final RepositoryConfiguration repositoryConfiguration;
    private final PersistentBlockingQueue localSendQueue;
    private final PersistentBlockingQueue remoteSendQueue;
    private final LocalConnection localConnection;
    private final RemoteConnection remoteConnection;
    private final ScheduledExecutorService scheduledExecutor;
    private final Thread sendLocalMailThread;
    private final XStream xstream;
    private final Thread sendRemoteMailThread;
    private final SendLocalMailRunnable sendLocalMailRunnable;
    private final SendRemoteMailRunnable sendRemoteMailRunnable;
    private final ReceiveMailTask receiveMailTask;
    public Mailbox(final LinkedBlockingDeque<ApplicationEvent> eventQueue)
    {
        this.scheduledExecutor = Executors.newScheduledThreadPool(1);
        this.xstream = new XStream();
        this.repositoryConfiguration = Configuration.getInstance().getRepositoryConfiguration();

        localSendQueue  = new PersistentBlockingQueue("localSendQueue");
        remoteSendQueue = new PersistentBlockingQueue("remoteSendQueue");
        localConnection = new LocalConnection(repositoryConfiguration);
        remoteConnection = new RemoteConnection();
        
        sendLocalMailRunnable = new SendLocalMailRunnable(localSendQueue, localConnection);
        sendRemoteMailRunnable = new SendRemoteMailRunnable(remoteSendQueue, remoteConnection);
        sendLocalMailThread = new Thread(sendLocalMailRunnable);
        sendRemoteMailThread = new Thread(sendRemoteMailRunnable);
        receiveMailTask = new ReceiveMailTask(localConnection, eventQueue);
    }
    
    public void putMessage(final MailMessage message) throws InterruptedException
    {
        if (message.getSendTo().equals(repositoryConfiguration.getRepositoryAddress()))
        {
            localSendQueue.put(message);
        }
        else
        {
            remoteSendQueue.put(message);
        }
    }

    public List<Commit> getCommitsBefore(final String revision) throws MessagingException
    {
        Message[] commitMessages = localConnection.getCommitsUpToRevision(revision);
        return getCommitListFromMessages(commitMessages);
    }


    public List<Commit> getCommits() throws MessagingException
    {
        Message[] commitMessages = localConnection.getCommitMessages();
        return getCommitListFromMessages(commitMessages);
    }
    

    private List<Commit> getCommitListFromMessages(Message[] commitMessages)
            throws MessagingException
    {
        List<Commit> commitList = new LinkedList<Commit>();
        for (Message message : commitMessages)
        {
            Commit commit = getCommitFromMessage(message);
            commitList.add(commit);
        }
        return commitList;
    }
    
    private Commit getCommitFromMessage(Message message) throws MessagingException
    {
        CommitOperation commitOperation = getCommitOperationFromMessage(message);
        String version = message.getSubject().split(" ")[1];
        return new Commit(commitOperation, version);
    }

    private CommitOperation getCommitOperationFromMessage(Message message)
    {
        MimeMessage mimeMessage = (MimeMessage)message;
        try
        {
            String messageContent = mimeMessage.getContent().toString();
            CommitOperation result = (CommitOperation)xstream.fromXML(messageContent);
            return result;
        }
        catch(IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(MessagingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public void startThreads()
    {
        sendRemoteMailThread.start();
        sendLocalMailThread.start();
        scheduledExecutor.scheduleAtFixedRate(receiveMailTask,Constants.RECEIVE_DELAY, Constants.RECEIVE_DELAY, TimeUnit.SECONDS);
    }

    public void stopThreads()
    {
        sendLocalMailRunnable.stop();
        sendRemoteMailRunnable.stop();
        scheduledExecutor.shutdown();

        try
        {
            sendLocalMailThread.join();
            sendRemoteMailThread.join();
            scheduledExecutor.awaitTermination(Constants.SCHEDULED_TASK_WAIT_TIME, TimeUnit.SECONDS);
        }
        catch(final InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
}
