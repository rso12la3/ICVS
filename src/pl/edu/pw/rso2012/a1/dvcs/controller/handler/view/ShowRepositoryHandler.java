/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.handler.view;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowRepositoryEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;

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
		
		ShowRepositoryEvent repositoryEvent = (ShowRepositoryEvent) event;
		
		controller.getView().showRepositoryFolderView(repositoryEvent.getRootDirectory(), repositoryEvent.getVersionedFilePaths());
	}
	
}
