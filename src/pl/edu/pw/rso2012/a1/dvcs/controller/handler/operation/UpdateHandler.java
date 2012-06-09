package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation;

import java.util.List;

import javax.mail.MessagingException;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.RefreshEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.UpdateCompleteEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.UpdateEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowErrorEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.Commit;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.exception.NoSuchCommitException;

public class UpdateHandler extends ApplicationHandler {
	public UpdateHandler(final Controller controller) {
		super(controller);
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
		try 
		{
			String version = ((UpdateEvent)event).getVersion();
			
			if (version == null || version.length() == 0)
				version = controller.getModel().getMailbox().getRevision();
			
			List<Commit> commitList = controller.getModel().getMailbox().getCommitsBefore(version);
			controller.getModel().getRepository().update(commitList);
			controller.onImportantEvent(new UpdateCompleteEvent());
			
			controller.onEvent(new RefreshEvent());
			
		}
		catch (ClassCastException e)
		{
			controller.onImportantEvent(new UpdateCompleteEvent());
			controller.onImportantEvent(new ShowErrorEvent("Wystąpił nieoczekiwany błąd, kod: 1"));
		}
		catch (MessagingException e)
		{
			controller.onImportantEvent(new UpdateCompleteEvent());
			controller.onImportantEvent(new ShowErrorEvent("Wystąpił nieoczekiwany błąd, kod: 2"));
		}
		catch (NoSuchCommitException e)
		{
			controller.onImportantEvent(new UpdateCompleteEvent());
			controller.onImportantEvent(new ShowErrorEvent("Nie ma takiej rewizji"));
		
		}
	}
	
}
