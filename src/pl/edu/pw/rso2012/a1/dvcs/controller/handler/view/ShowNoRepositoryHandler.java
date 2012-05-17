/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.handler.view;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class ShowNoRepositoryHandler extends ApplicationHandler {
	
	public ShowNoRepositoryHandler(final Controller controller) {
		super(controller);
	}
	
	@Override
	public void handle(final ApplicationEvent event)
			throws HandlerNotImplementedException {
		
		controller.getView().showNoRepositoryView();
	}
	
}
