package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.response;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.response.PullResponseEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowErrorEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PullOperation;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PullResponseOperation;

/**
 * 
 * @author Oskar Leszczynski
 * 
 */
public class PullResponseHandler extends ApplicationHandler 
{
	public PullResponseHandler(final Controller controller) 
	{
		super(controller);
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
		try 
		{
			PullOperation operation =((PullResponseEvent)event).getOperation();
			PullResponseOperation opResult= controller.getModel().getRepository().pull(controller, operation);
			//za pomoca opResult mozna poinformowac guja o wyniku pull requesta...
		}
		catch (ClassCastException e)
		{
			controller.onImportantEvent(new ShowErrorEvent("Wystąpił nieoczekiwany błąd, kod: 1"));
		}
	}
	
}
