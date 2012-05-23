package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation;

import java.util.ArrayList;
import java.util.Set;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.thoughtworks.xstream.XStream;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.CommitFilesEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowErrorEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.MailMessage;
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
			filesToCommit = new ArrayList<String>(ev.getFilesToCommit());
			CommitOperation resutl= controller.getModel().getRepository().commit(filesToCommit);
			
			XStream xStream= new XStream();
			String content= xStream.toXML(resutl);
			MailMessage message= new MailMessage();
			message.setBody(content);
			message.setSubject("commit");		//NJ: jaki powinien byc temat wiadomosci??
			
			
			try {
				controller.getModel().getMailbox().putMessage(message);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    else{
	    	try {
				controller.getEventQueue().put(new ShowErrorEvent("casting to CommitFilesEvent failed!"));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	}
	
}
