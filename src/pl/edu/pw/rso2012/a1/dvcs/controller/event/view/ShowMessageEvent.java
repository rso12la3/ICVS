package pl.edu.pw.rso2012.a1.dvcs.controller.event.view;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public abstract class ShowMessageEvent extends ApplicationEvent {
	
	private final String message;

    public ShowMessageEvent(final String message) {
        super();
        this.message = message;
    }

	public String getMessage() {
		return message;
	}
}
