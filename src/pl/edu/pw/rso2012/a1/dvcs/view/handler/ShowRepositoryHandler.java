/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.view.handler;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.view.event.ShowRepositoryEvent;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class ShowRepositoryHandler extends ApplicationHandler {
	
	public ShowRepositoryHandler(final Controller controller) {
		super(controller);
	}
	
	@Override
	public void handle(final ApplicationEvent event)
			throws HandlerNotImplementedException {
		
		controller.getView().showRepositoryFolderView(
				((ShowRepositoryEvent) event).getRepository());
	}
	
}
