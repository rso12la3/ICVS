package pl.edu.pw.rso2012.a1.dvcs.controller.event.view;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class ShowQuestionEvent extends ApplicationEvent {
	
	private final boolean isBlocking;
	private final int id;
	private final String title, message;
	
	public ShowQuestionEvent(String title, String message, int id, boolean isBlocking) {
		super();
		this.isBlocking = isBlocking;
		this.id = id;
		this.title = title;
		this.message = message;
	}

	public boolean isBlocking() {
		return isBlocking;
	}


	public String getTitle() {
		return title;
	}


	public String getMessage() {
		return message;
	}

	public int getId() {
		return id;
	}
}
