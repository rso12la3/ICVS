package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation;

import java.util.List;
import java.util.Set;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class AddFilesHandler extends ApplicationHandler 
{
	private Set<String> filesSet;
	
	public AddFilesHandler(final Controller controller, Set<String> filesSet) 
	{
		super(controller);
		this.filesSet = filesSet;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
	    throw new HandlerNotImplementedException(this);
	}
	
}
