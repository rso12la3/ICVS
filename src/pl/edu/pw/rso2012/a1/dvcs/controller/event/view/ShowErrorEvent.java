package pl.edu.pw.rso2012.a1.dvcs.controller.event.view;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class ShowErrorEvent extends ApplicationEvent {
	
	private final String errorMessage;

    public ShowErrorEvent(final String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

	public String getErrorMessage() {
		return errorMessage;
	}
}
