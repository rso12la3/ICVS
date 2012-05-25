package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.request;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.PullRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowErrorEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.MailMessage;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PullRequestOperation;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class PullRequestHandler extends ApplicationHandler 
{
	public PullRequestHandler(final Controller controller) 
	{
		super(controller);
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
		try 
		{
			
			String email =((PullRequestEvent)event).getEmail();
		    PullRequestOperation operation = controller.getModel().getRepository().pullRequest();
		    
		    String body = controller.getModel().getRepository().OperationToXML(operation);
		    MailMessage message = new MailMessage(email, "pullRequest", body);
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
