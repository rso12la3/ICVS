package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation.request;

import java.util.List;

import javax.mail.MessagingException;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.UpdateCompleteEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.UpdateEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.CloneMailRequestEvent;
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
public class CloneMailRequestHandler extends ApplicationHandler 
{
	public CloneMailRequestHandler(final Controller controller) 
	{
		super(controller);
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
		try 
		{
			CloneRequestOperation requestOperation =((CloneMailRequestEvent)event).getOperation();
			List<Commit> commitList = controller.getModel().getMailbox().getCommits();
			
			if (controller.getView().showBlockingQuestionDialog("Czy chcesz udostepnic repozytorium dla adresu: " + requestOperation.getEmail(), "Clone"))
			{
				CloneOperation operation = controller.getModel().getRepository().prepareClone(commitList);
			    String body = controller.getModel().getRepository().OperationToXML(operation);
			    MailMessage message = new MailMessage(requestOperation.getEmail(), "cloneResponse", body);
			    controller.getModel().getMailbox().putMessage(message);
			}
			
		}
		catch (ClassCastException e)
		{
			controller.onImportantEvent(new ShowErrorEvent("Wystąpił nieoczekiwany błąd, kod: 1"));
		}
		catch (MessagingException e)
		{
			controller.onImportantEvent(new ShowErrorEvent("Wystąpił nieoczekiwany błąd, kod: 2"));
		}
		catch (InterruptedException e)
		{
			controller.onImportantEvent(new ShowErrorEvent("Wystąpił nieoczekiwany błąd, kod: 3"));
		}
	}
	
}
