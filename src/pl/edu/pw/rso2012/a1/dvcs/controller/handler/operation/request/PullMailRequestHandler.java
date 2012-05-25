package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.request;

import javax.mail.MessagingException;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.PullMailRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowErrorEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.MailMessage;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PullOperation;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PullRequestOperation;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PullResponseOperation;

/**
 * 
 * @author Oskar Leszczynski
 * 
 */
public class PullMailRequestHandler extends ApplicationHandler 
{
	public PullMailRequestHandler(final Controller controller) 
	{
		super(controller);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
		try 
		{
			PullRequestOperation requestOperation =((PullMailRequestEvent)event).getOperation();
			

			PullOperation operation = controller.getModel().getRepository().preparePull(); 
		    String body = controller.getModel().getRepository().OperationToXML(operation);
		    MailMessage message = new MailMessage(requestOperation.getEmail(), "pullResponse", body);
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
