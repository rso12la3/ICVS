package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation;

import java.util.List;

import javax.mail.MessagingException;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.UpdateCompleteEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.UpdateEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.Commit;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class UpdateHandler extends ApplicationHandler {
	public UpdateHandler(final Controller controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
		
		try 
		{
			String version = ((UpdateEvent)event).getVersion();
			
			List<Commit> commitList = controller.getModel().getMailbox().getCommitsBefore(version);
			
			
			controller.getModel().getRepository().update(commitList);
			
		
		
			controller.getEventQueue().put(new UpdateCompleteEvent());
		}
		catch (ClassCastException e)
		{
			
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (MessagingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			//TODO rzucic blad do gui
		}
		
		throw new HandlerNotImplementedException(this);
	}
	
}
