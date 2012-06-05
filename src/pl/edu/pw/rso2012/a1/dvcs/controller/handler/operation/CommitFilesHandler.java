package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation;

import java.util.ArrayList;
import java.util.Set;

import javax.mail.MessagingException;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.thoughtworks.xstream.XStream;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.CommitFilesEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.RefreshEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowErrorEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.MailMessage;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.Configuration;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.CommitOperation;

/**
 * 
 * @author Oskar Leszczynski
 * 
 */
public class CommitFilesHandler extends ApplicationHandler 
{
	private ArrayList<String> filesToCommit;
	
	public CommitFilesHandler(final Controller controller) 
	{
		super(controller);
		
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
	    if (event instanceof CommitFilesEvent) {
			CommitFilesEvent ev = (CommitFilesEvent) event;
			CommitOperation result= controller.getModel().getRepository().commit(ev.getFilesToCommit());
			
			if (result == null)
				return;
			String content= controller.getModel().getRepository().OperationToXML(result);
			MailMessage message= new MailMessage();
			message.setBody(content);
			String currentRevision = null;
            try
            {
                currentRevision = controller.getModel().getMailbox().getRevision();
            }
            catch(MessagingException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
			String newRevision = null;
			if (currentRevision == null)
			{
			    newRevision = "1";
			}
			else
			{
			    newRevision = new Integer(new Integer(currentRevision) + 1).toString();
			}
			message.setSubject("commit " + newRevision);	
			message.setSendTo(Configuration.getInstance().getRepositoryConfiguration().getRepositoryAddress());
			
			controller.onEvent(new RefreshEvent());
			
			try {
				controller.getModel().getMailbox().putMessage(message);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    else{
	    		controller.onImportantEvent(new ShowErrorEvent("casting to CommitFilesEvent failed!"));
	    }
	    
	}
	
}
