package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.response;

import java.util.List;

import javax.mail.MessagingException;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.CloneMailRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.response.CloneResponseEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowErrorEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.Commit;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.MailMessage;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.CloneOperation;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.CloneRequestOperation;

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
			CloneOperation operation =((CloneResponseEvent)event).getOperation();
			controller.getModel().getRepository().clone(controller, operation);
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
