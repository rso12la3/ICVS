package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.response;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.RefreshEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.response.CloneResponseEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowErrorEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowInformationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowMessageEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.CloneOperation;
import pl.edu.pw.rso2012.a1.dvcs.utils.Log;

/**
 * 
 * @author Oskar Leszczynski
 * 
 */
public class CloneResponseHandler extends ApplicationHandler 
{
	public CloneResponseHandler(final Controller controller) 
	{
		super(controller);
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
		try 
		{
			Log.o("CloneResponseHandler start");
			CloneOperation operation =((CloneResponseEvent)event).getOperation();
			if (operation.isReject())
			{
				
				controller.onImportantEvent(new ShowInformationEvent(String.format("Adres: %s odrzucil prosbe o udostepnienie repozytorium", operation.getEmail())));
				return;
			}
			controller.getModel().getRepository().clone(controller, operation);
			controller.onEvent(new RefreshEvent());
			controller.onImportantEvent(new ShowInformationEvent(String.format("Pobrano dane repozytorium spod adresu: %s ", operation.getEmail())));
			Log.o("CloneResponseHandler stop");
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
