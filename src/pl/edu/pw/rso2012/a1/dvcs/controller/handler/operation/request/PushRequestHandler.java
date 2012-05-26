package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.request;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.PushRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.response.PushResponseEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowErrorEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.MailMessage;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PushRequestOperation;

public class PushRequestHandler extends ApplicationHandler 
{
	public PushRequestHandler(final Controller controller) 
	{
		super(controller);
	}
	
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
		try 
		{
			String email =((PushRequestEvent)event).getEmail();
		    PushRequestOperation operation = controller.getModel().getRepository().pushRequest();
		    
		    String body = controller.getModel().getRepository().OperationToXML(operation);
		    MailMessage message = new MailMessage(email, "pushRequest", body);
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
