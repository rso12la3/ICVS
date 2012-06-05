package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.request;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.PushMailRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowErrorEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.MailMessage;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PushOperation;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PushRequestOperation;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PushResponseOperation;

/**
 * 
 * @author Oskar Leszczynski
 * 
 */
public class PushMailRequestHandler extends ApplicationHandler 
{
	public PushMailRequestHandler(final Controller controller) 
	{
		super(controller);
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
		try 
		{
			PushRequestOperation requestOperation =((PushMailRequestEvent)event).getOperation();
			
			//preparePush i przepisanie danych do innej klasy dla zachowania konwensji operacji wystepujacej w clone, pull
			PushOperation operation = controller.getModel().getRepository().preparePush(requestOperation.getData());
			PushResponseOperation opResult= controller.getModel().getRepository().push(controller, operation);
			
		    String body = controller.getModel().getRepository().OperationToXML(opResult);
		    MailMessage message = new MailMessage(requestOperation.getEmailCallback(), "pushResponse", body);
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
