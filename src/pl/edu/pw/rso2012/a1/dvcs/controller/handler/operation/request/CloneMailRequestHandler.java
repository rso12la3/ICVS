package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.request;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;

/**
 * 
 * @author Oskar Leszczynski
 * 
 */
public class CloneMailRequestHandler extends ApplicationHandler 
{
	public CloneMailRequestHandler(final Controller controller) 
	{
		super(controller);
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
	    throw new HandlerNotImplementedException(this);
	    
	    
	}
	
}
