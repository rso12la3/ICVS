package pl.edu.pw.rso2012.a1.dvcs.controller;

import java.util.concurrent.LinkedBlockingQueue;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowNoRepositoryEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowRepositoryEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.NoHandlerException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.mapper.HandlerMap;
import pl.edu.pw.rso2012.a1.dvcs.model.Model;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.Configuration;
import pl.edu.pw.rso2012.a1.dvcs.view.View;

public class Controller {
    
	private static final String TAG = Controller.class.getSimpleName();
	
	private static final boolean MODEL_ACTIVE = false; // TODO: temporarly for view debugging
	
    private final Model model;
    private final View view;
    private final LinkedBlockingQueue<ApplicationEvent> eventQueue;
    private boolean applicationEnd = false;
 
    private final HandlerMap handlerMap;

    public Controller()
    {
        handlerMap = new HandlerMap(this);
        eventQueue = new LinkedBlockingQueue<ApplicationEvent>();
        model = new Model(eventQueue);
        view = new View(this);
        Configuration.getInstance();
        
        // FIXME: event should be sent based on configuration - this is just for testing
        boolean IS_REPOSITORY_CREATED = true;
       
        if(IS_REPOSITORY_CREATED){
        	onEvent(new ShowRepositoryEvent(null));
        }else{
        	onEvent(new ShowNoRepositoryEvent());
        }
    }

    public void run()
    {
    	if(MODEL_ACTIVE) model.start();
    	
        while(!isApplicationEnd())
        {
            try
            {
                final ApplicationEvent event = eventQueue.take();
                final ApplicationHandler applicationHandler = handlerMap.get(event); 
                applicationHandler.handle(event);
            }
            catch(final InterruptedException e)
            {
                e.printStackTrace();
                endApplication();
            }
            catch(final NoHandlerException e)
            {
                e.printStackTrace();
            }
            catch(final HandlerNotImplementedException e)
            {
                e.printStackTrace();
            }
           
        }
        view.dispose();
        
        if(MODEL_ACTIVE) model.stop();
    }
    
    public View getView()
    {
    	return view;
    }

    public Model getModel()
    {
        return model;
    }
    
    private boolean isApplicationEnd()
    {
        return applicationEnd;
    }   
    
    public void endApplication()
    {
        this.applicationEnd = true;
    }

    public LinkedBlockingQueue<ApplicationEvent> getEventQueue()
    {
        return eventQueue;
    }
    
    public void onEvent(ApplicationEvent event)
    {
    	eventQueue.offer(event);
    }
}
