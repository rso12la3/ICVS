package pl.edu.pw.rso2012.a1.dvcs.controller.handler.application;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class ErrorHandler extends ApplicationHandler 
{
	public ErrorHandler(final Controller controller) 
	{
		super(controller);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
		controller.endApplication();
	}
	
}
