package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.request;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class PushRequestHandler extends ApplicationHandler 
{
	public PushRequestHandler(final Controller controller) 
	{
		super(controller);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
	    throw new HandlerNotImplementedException(this);
	}
	
}
