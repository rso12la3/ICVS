package pl.edu.pw.rso2012.a1.dvcs.model;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.Mailbox;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.Configuration;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.RepositoryConfiguration;
import pl.edu.pw.rso2012.a1.dvcs.model.repository.Repository;
import pl.edu.pw.rso2012.a1.dvcs.model.workingcopy.WorkingCopy;

/**
 * @author Grzegorz Sancewicz
 * 
 */
public class Model
{
    private final Repository repository;
    private final Mailbox mailbox;
    private final LinkedBlockingDeque<ApplicationEvent> eventQueue;
    private final RepositoryConfiguration repositoryConfiguration;
    
    public Model(final LinkedBlockingDeque<ApplicationEvent> eventQueue)
    {
        this.mailbox = new Mailbox(eventQueue);
        this.eventQueue = eventQueue;
        this.repositoryConfiguration = Configuration.getInstance().getRepositoryConfiguration();
        this.repository = new Repository(repositoryConfiguration.getRepositoryAbsolutePath());

    }

    public void start()
    {
        mailbox.startThreads();
    }
    
    public void stop()
    {
        mailbox.stopThreads();
    }

    public Mailbox getMailbox()
    {
        return mailbox;
    }

    public Repository getRepository()
    {
        return repository;
    }


}
