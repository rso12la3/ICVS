package pl.edu.pw.rso2012.a1.dvcs.controller.handler.view;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowQuestionEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class ShowQuestionHandler extends ApplicationHandler {
	
	public ShowQuestionHandler(final Controller controller) {
		super(controller);
	}
	
	@Override
	public void handle(final ApplicationEvent event) throws HandlerNotImplementedException {
		
		ShowQuestionEvent questionEvent = (ShowQuestionEvent) event;
		
		controller.getView().showQuestionDialog(questionEvent.getMessage(), questionEvent.getTitle(), questionEvent.getId(), questionEvent.isBlocking());
	}
	
}
