package pl.edu.pw.rso2012.a1.dvcs.controller.mapper;

import java.util.HashMap;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.AddEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.CheckPullRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.CheckPullResponseEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.CloneEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.CommitEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.CreateEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.DeleteEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.MergeEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.PullEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.PushEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.NoHandlerException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.AddHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.CheckPullRequestHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.CheckPullResponseHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.CloneHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.CommitHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.CreateHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.DeleteHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.MergeHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.PullHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.PushHandler;

/**
 * Mapa odwzorowuj¹ca zdarzenie ({@link ApplicationEvent}) na odpowiedni handler ({@link ApplicationHandler})
 * 
 * @author Grzegorz Sancewicz
 * @email g.sancewicz@stud.elka.pw.edu.pl
 * 
 */
public class HandlerMap
{

    private final Controller controller;
    private final HashMap<Class<? extends ApplicationEvent>, ApplicationHandler> eventHandlerMap;

    public HandlerMap(final Controller controller)
    {
        this.controller = controller;
        this.eventHandlerMap = new HashMap<Class<? extends ApplicationEvent>, ApplicationHandler>();
        prepareHandlers();
    }
    
    private void prepareHandlers()
    {
        // Podstawowe operacje repozytorium
        eventHandlerMap.put(AddEvent.class,     new AddHandler(controller));
        eventHandlerMap.put(CloneEvent.class,   new CloneHandler(controller));
        eventHandlerMap.put(CommitEvent.class,  new CommitHandler(controller));
        eventHandlerMap.put(CreateEvent.class,  new CreateHandler(controller));
        eventHandlerMap.put(DeleteEvent.class,  new DeleteHandler(controller));
        eventHandlerMap.put(MergeEvent.class,   new MergeHandler(controller));
        eventHandlerMap.put(PullEvent.class,    new PullHandler(controller));
        eventHandlerMap.put(PushEvent.class,    new PushHandler(controller));
        
        // Operacje cykliczne
        eventHandlerMap.put(CheckPullRequestEvent.class,    new CheckPullRequestHandler(controller));
        eventHandlerMap.put(CheckPullResponseEvent.class, new CheckPullResponseHandler(controller));
    }

    public ApplicationHandler get(final ApplicationEvent event) throws NoHandlerException
    {
        final ApplicationHandler handler = eventHandlerMap.get(event);
        if(handler==null)
        {
            throw new NoHandlerException(event);
        }
        return handler;
    }

}
