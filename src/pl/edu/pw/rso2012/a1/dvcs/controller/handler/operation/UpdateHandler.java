package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class UpdateHandler extends ApplicationHandler {
	public UpdateHandler(final Controller controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException {
		// FIXME: actual work needs to be performed here
		try { Thread.sleep(2000); }catch (InterruptedException e) {}
		
		controller.getView().onUpdateComplete();
		
		// FIXME: this part is left here just in case we forget about it
		throw new HandlerNotImplementedException(this);
	}
	
}
