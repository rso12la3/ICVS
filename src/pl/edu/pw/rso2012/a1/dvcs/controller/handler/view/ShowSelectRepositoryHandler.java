/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.handler.view;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowSelectRepositoryEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class ShowSelectRepositoryHandler extends ApplicationHandler {
	
	public ShowSelectRepositoryHandler(final Controller controller) {
		super(controller);
	}
	
	@Override
	public void handle(final ApplicationEvent event)
			throws HandlerNotImplementedException {
		
		ShowSelectRepositoryEvent repositoryEvent = (ShowSelectRepositoryEvent) event;
		
		controller.getView().showSelectRepositoryView(
				repositoryEvent.getRepositoriesNames());
	}
	
}
