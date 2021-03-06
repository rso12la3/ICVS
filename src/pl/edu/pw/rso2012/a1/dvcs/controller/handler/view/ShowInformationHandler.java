package pl.edu.pw.rso2012.a1.dvcs.controller.handler.view;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowInformationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class ShowInformationHandler extends ApplicationHandler {
	
	public ShowInformationHandler(final Controller controller) {
		super(controller);
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException {
		
		ShowInformationEvent informationEvent = (ShowInformationEvent) event;
		
		controller.getView().showMessageDialogInformation(informationEvent.getMessage());
	}
	
}
