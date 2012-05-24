package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.request;

import javax.mail.MessagingException;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.CloneMailRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.CloneRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowErrorEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.MailMessage;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.CloneRequestOperation;

/**
 * 
 * @author Oskar Leszczynski
 * 
 */
public class CloneRequestHandler extends ApplicationHandler 
{
	public CloneRequestHandler(final Controller controller) 
	{
		super(controller);
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
		try 
		{
			String email =((CloneRequestEvent)event).getEmail();
		    CloneRequestOperation operation = controller.getModel().getRepository().cloneRequest();
		    String body = controller.getModel().getRepository().OperationToXML(operation);
		    MailMessage message = new MailMessage(email, "cloneRequest", body);
		    controller.getModel().getMailbox().putMessage(message);
		}
		catch (ClassCastException e)
		{
			controller.onImportantEvent(new ShowErrorEvent("Wystąpił nieoczekiwany błąd, kod: 1"));
		}
		catch (InterruptedException e)
		{
			controller.onImportantEvent(new ShowErrorEvent("Wystąpił nieoczekiwany błąd, kod: 3"));
		}
		
	}
	
}
