package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.response;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.CloneOperation;

/**
 * 
 * @author Oskar Leszczynski
 * 
 */

public class CloneResponseEvent  extends ApplicationEvent
{
	private final CloneOperation operation;

	public CloneResponseEvent(CloneOperation operation)
	{
		this.operation = operation;
	}

	public CloneOperation getOperation()
	{
		return operation;
	}
	
	
}
