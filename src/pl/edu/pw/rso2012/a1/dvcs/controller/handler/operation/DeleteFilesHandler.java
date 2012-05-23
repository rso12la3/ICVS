package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation;

import java.util.ArrayList;
import java.util.Set;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.DeleteFilesEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowErrorEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class DeleteFilesHandler extends ApplicationHandler 
{
	private ArrayList<String> filesSet;
	
	public DeleteFilesHandler(final Controller controller) 
	{
		super(controller);
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
	    if (event instanceof DeleteFilesEvent) {
			DeleteFilesEvent ev = (DeleteFilesEvent) event;
			filesSet= new ArrayList<String>(ev.getFilesToDeleteList());
			controller.getModel().getRepository().delete(filesSet);
		}
	    else{
	    	try {
				controller.getEventQueue().put(new ShowErrorEvent("casting to DeleteFilesEvent failed!"));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
}
