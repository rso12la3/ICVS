package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.AddFilesEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowErrorEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;

public class AddFilesHandler extends ApplicationHandler 
{
	private ArrayList<String> filesSet;
	
	public AddFilesHandler(final Controller controller) 
	{
		super(controller);		
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
		AddFilesEvent ev;
		
		//zrzutuj event sprawdzajac czy to ten wlasciwy dla handlera
		if (event instanceof AddFilesEvent) {
			ev = (AddFilesEvent) event;
			filesSet= new ArrayList<String>(ev.getFilesToAdd());
			controller.getModel().getRepository().add(filesSet);
		}
		else{
			//wyslij komunikat do guja o bledzie (poprzez contorler)
			try {
				controller.getEventQueue().put(new ShowErrorEvent("casting to AddFilesEvent failed!"));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
