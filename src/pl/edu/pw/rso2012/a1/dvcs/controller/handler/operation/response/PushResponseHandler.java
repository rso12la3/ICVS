package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.response;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.response.PushResponseEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowErrorEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowInformationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PushResponseOperation;

/**
 * 
 * @author Oskar Leszczynski
 * 
 */
public class PushResponseHandler extends ApplicationHandler 
{
	public PushResponseHandler(final Controller controller) 
	{
		super(controller);
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
		try 
		{
			PushResponseOperation opResponce= ((PushResponseEvent)event).getOperation();
			controller.getEventQueue().put(new ShowInformationEvent(opResponce.getResult()));		//powiadamiamy guja o wyniku wczesniejszego push requesta
		}
		catch (ClassCastException e)
		{
			controller.onImportantEvent(new ShowErrorEvent("Wystąpił nieoczekiwany błąd, kod: 1"));
		}
		catch (InterruptedException e)
		{
			controller.onImportantEvent(new ShowErrorEvent("Wykonywanie operacji clone spowodowalo nieoczekiwany blad, kod: 3"));
		}
	}
	
}
