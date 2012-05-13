package pl.edu.pw.rso2012.a1.dvcs.controller;

import java.util.concurrent.LinkedBlockingQueue;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.NoHandlerException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.mapper.HandlerMap;
import pl.edu.pw.rso2012.a1.dvcs.controller.schedule.Schedule;
import pl.edu.pw.rso2012.a1.dvcs.model.Model;
import pl.edu.pw.rso2012.a1.dvcs.view.View;

/**
 * @author Grzegorz Sancewicz
 * @email g.sancewicz@stud.elka.pw.edu.pl
 * 
 */
public class Controller {
    
    private final Model model;
    private final View view;
    private final LinkedBlockingQueue<ApplicationEvent> eventQueue;
    private boolean applicationEnd;
 
    private final HandlerMap handlerMap;
    private final Schedule schedule;

    public Controller()
    {
        model = new Model();
        view = new View();
        view.setController(this);
        handlerMap = new HandlerMap(this);
        eventQueue = new LinkedBlockingQueue<ApplicationEvent>();
        schedule = new Schedule(this);
        applicationEnd = false;
    }

    public void run()
    {
        while(!isApplicationEnd())
        {
            try
            {
                final ApplicationEvent event = getEventQueue().take();
                final ApplicationHandler applicationHandler = handlerMap.get(event); 
                applicationHandler.handle(event);
            }
            catch(final InterruptedException e)
            {
                //nie uda�o si� pobra� eventu
                e.printStackTrace();
                endApplication();
            }
            catch(final NoHandlerException e)
            {
                //nie uda�o si� znalezc handlera
                e.printStackTrace();
            }
            catch(final HandlerNotImplementedException e)
            {
                //metoda handle znalezionego handlera nie zostala zaimplementowana
                e.printStackTrace();
            }
           
        }
        schedule.kill();
    }
    
    public View getView(){
    	return view;
    }

    /**
     * @return the model
     */
    public Model getModel()
    {
        return model;
    }

    /**
     * @return the applicationEnd
     */
    private boolean isApplicationEnd()
    {
        return applicationEnd;
    }   
    /**
     * Ko�czy dzia�anie aplikacji
     */
    public void endApplication()
    {
        this.applicationEnd = true;
    }

    /**
     * @return the eventQueue
     */
    public LinkedBlockingQueue<ApplicationEvent> getEventQueue()
    {
        return eventQueue;
    }
    
    public void onEvent(ApplicationEvent event){
    	eventQueue.offer(event);
    }

    
}
