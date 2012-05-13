package pl.edu.pw.rso2012.a1.dvcs.controller.handler;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class ExitHandler extends ApplicationHandler {
	public ExitHandler(final Controller controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException {
		controller.endApplication();
	}
	
}
