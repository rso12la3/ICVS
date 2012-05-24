package pl.edu.pw.rso2012.a1.dvcs.controller.handler.operation;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.CreateEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.Configuration;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.RepositoryConfiguration;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class CreateHandler extends ApplicationHandler 
{
	public CreateHandler(final Controller controller) 
	{
		super(controller);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException 
	{
	    RepositoryConfiguration config = Configuration.getInstance().getRepositoryConfiguration();
	    CreateEvent createEvent = (CreateEvent) event;
	    config.setRepositoryAbsolutePath(createEvent.getBaseDirectory().getAbsolutePath());
	    config.setRepositoryAddress(createEvent.getEmail());
	    config.setRepositoryPass(createEvent.getPassword());
	    Configuration.getInstance().save();
	    
	    controller.getModel().onRepositoryConfigurationUpdated();
	}
	
}
