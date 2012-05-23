package pl.edu.pw.rso2012.a1.dvcs.controller;

import java.util.concurrent.LinkedBlockingDeque;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.NoHandlerException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.mapper.HandlerMap;
import pl.edu.pw.rso2012.a1.dvcs.model.Model;
import pl.edu.pw.rso2012.a1.dvcs.view.View;

public class Controller {
	
	private static final String TAG = Controller.class.getSimpleName();
	
	private final Model model;
	private final View view;
	private final LinkedBlockingDeque<ApplicationEvent> eventQueue;
	private boolean applicationEnd = false;
	
	private final HandlerMap handlerMap;
	
	public Controller() {
		handlerMap = new HandlerMap(this);
		eventQueue = new LinkedBlockingDeque<ApplicationEvent>();
		model = new Model(this);
		view = new View(this);
	}
	
	public void run() {
		model.start();
		
		while (!isApplicationEnd()) {
			try {
				final ApplicationEvent event = eventQueue.take();
				final ApplicationHandler applicationHandler = handlerMap.get(event);
				applicationHandler.handle(event);
			}
			catch (final InterruptedException e) {
				e.printStackTrace();
				endApplication();
			}
			catch (final NoHandlerException e) {
				e.printStackTrace();
			}
			catch (final HandlerNotImplementedException e) {
				e.printStackTrace();
			}
			
		}
		view.dispose();
		
		model.stop();
	}
	
	public View getView() {
		return view;
	}
	
	public Model getModel() {
		return model;
	}
	
	private boolean isApplicationEnd() {
		return applicationEnd;
	}
	
	public void endApplication() {
		this.applicationEnd = true;
	}
	
	public LinkedBlockingDeque<ApplicationEvent> getEventQueue() {
		return eventQueue;
	}

    public void onEvent(ApplicationEvent event) {
        eventQueue.offer(event);
    }
    public void onImportantEvent(ApplicationEvent event) {
        eventQueue.offerLast(event);
    }
}
