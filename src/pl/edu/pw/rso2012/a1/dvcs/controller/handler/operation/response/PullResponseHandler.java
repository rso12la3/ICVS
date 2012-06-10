package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.response;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.RefreshEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.response.PullResponseEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowErrorEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowInformationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PullOperation;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PullResponseOperation;
import pl.edu.pw.rso2012.a1.dvcs.utils.Log;

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
			Log.o("PullResponseHandler start");
			PullOperation operation =((PullResponseEvent)event).getOperation();
			if (operation.isReject())
			{
				controller.onImportantEvent(new ShowInformationEvent(String.format("Adres: %s odrzucil prosbe o udostepnienie pulla", operation.getEmail())));
				return;
			}	
			PullResponseOperation opResult= controller.getModel().getRepository().pull(controller, operation);
			controller.onEvent(new RefreshEvent());
			//za pomoca opResult mozna poinformowac guja o wyniku pull requesta...
			controller.onImportantEvent(new ShowInformationEvent(String.format("Wykonano operacje pull dla adresu: %s \n wynik operacji: %s", operation.getEmail(), opResult.getResult())));
			Log.o("PullResponseHandler stop");
		}
		catch (ClassCastException e)
		{
			controller.onImportantEvent(new ShowErrorEvent("Wystąpił nieoczekiwany błąd, kod: 1"));
		}
	}
	
}
