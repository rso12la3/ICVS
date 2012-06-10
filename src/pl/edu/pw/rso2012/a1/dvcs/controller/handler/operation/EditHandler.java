package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.EditEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.Configuration;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.RepositoryConfiguration;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class EditHandler extends ApplicationHandler {
	public EditHandler(final Controller controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException {
		RepositoryConfiguration config = Configuration.getInstance().getRepositoryConfiguration();
		EditEvent editEvent = (EditEvent) event;
		config.setRepositoryAddress(editEvent.getEmail());
		config.setRepositoryPass(editEvent.getPassword());
		Configuration.getInstance().save();
	}
	
}
