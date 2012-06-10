package pl.edu.pw.rso2012.a1.dvcs.controller.mapper;

import java.util.HashMap;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.application.ErrorEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.application.ExitEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.AddFilesEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.CommitFilesEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.CreateEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.DeleteFilesEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.EditEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.RefreshEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.UpdateCompleteEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.UpdateEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.CloneMailRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.CloneRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.PullMailRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.PullRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.PushMailRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.PushRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.response.CloneResponseEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.response.PullResponseEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.response.PushResponseEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowErrorEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowInformationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowNoRepositoryEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowQuestionEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowRepositoryEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowWarningEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.NoHandlerException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.application.ErrorHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.application.ExitHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.AddFilesHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.CommitFilesHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.CreateHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.DeleteFilesHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.EditHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.RefreshHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.UpdateCompleteHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.UpdateHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.request.CloneMailRequestHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.request.CloneRequestHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.request.PullMailRequestHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.request.PullRequestHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.request.PushMailRequestHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.request.PushRequestHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.response.CloneResponseHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.response.PullResponseHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.response.PushResponseHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.view.ShowErrorHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.view.ShowInformationHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.view.ShowNoRepositoryHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.view.ShowQuestionHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.view.ShowRepositoryHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.view.ShowWarningHandler;

/**
 * Mapa odwzorowująca zdarzenie ({@link ApplicationEvent}) na odpowiedni handler ({@link ApplicationHandler})
 * 
 * @author Grzegorz Sancewicz
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
        // Operacje
        eventHandlerMap.put(AddFilesEvent.class,  new AddFilesHandler(controller));
        eventHandlerMap.put(CommitFilesEvent.class, new CommitFilesHandler(controller));
        eventHandlerMap.put(CreateEvent.class,  new CreateHandler(controller));
        eventHandlerMap.put(EditEvent.class,  new EditHandler(controller));
        eventHandlerMap.put(DeleteFilesEvent.class,  new DeleteFilesHandler(controller));
        eventHandlerMap.put(UpdateCompleteEvent.class,  new UpdateCompleteHandler(controller));
        eventHandlerMap.put(UpdateEvent.class,  new UpdateHandler(controller));
        
        // Operacje - request
        eventHandlerMap.put(CloneRequestEvent.class,  new CloneRequestHandler(controller));
        eventHandlerMap.put(PushRequestEvent.class,  new PushRequestHandler(controller));
        eventHandlerMap.put(PullRequestEvent.class,  new PullRequestHandler(controller));
        eventHandlerMap.put(PullMailRequestEvent.class,  new PullMailRequestHandler(controller));
        eventHandlerMap.put(PushMailRequestEvent.class,  new PushMailRequestHandler(controller));
        eventHandlerMap.put(CloneMailRequestEvent.class,  new CloneMailRequestHandler(controller));
        
        // Operacje - response
        eventHandlerMap.put(PushResponseEvent.class,  new PushResponseHandler(controller));
        eventHandlerMap.put(PullResponseEvent.class,  new PullResponseHandler(controller));
        eventHandlerMap.put(CloneResponseEvent.class,  new CloneResponseHandler(controller));
        
        
        // Widok
        eventHandlerMap.put(ShowRepositoryEvent.class, new ShowRepositoryHandler(controller));
        eventHandlerMap.put(ShowNoRepositoryEvent.class, new ShowNoRepositoryHandler(controller));
        eventHandlerMap.put(ShowErrorEvent.class, new ShowErrorHandler(controller));
        eventHandlerMap.put(ShowWarningEvent.class, new ShowWarningHandler(controller));
        eventHandlerMap.put(ShowInformationEvent.class, new ShowInformationHandler(controller));
        eventHandlerMap.put(ShowQuestionEvent.class, new ShowQuestionHandler(controller));
        eventHandlerMap.put(RefreshEvent.class, new RefreshHandler(controller));
        
        // Główne
        eventHandlerMap.put(ExitEvent.class, new ExitHandler(controller));
        eventHandlerMap.put(ErrorEvent.class, new ErrorHandler(controller));
        
    }

    public ApplicationHandler get(final ApplicationEvent event) throws NoHandlerException
    {
        final ApplicationHandler handler = eventHandlerMap.get(event.getClass());
        if(handler==null)
        {
            throw new NoHandlerException(event);
        }
        return handler;
    }

}
