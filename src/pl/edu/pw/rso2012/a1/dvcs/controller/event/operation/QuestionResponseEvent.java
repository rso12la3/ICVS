package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class QuestionResponseEvent extends ApplicationEvent {
	
	private final int id;
	private final boolean response;
	
	public QuestionResponseEvent(int id, boolean response) {
		super();
		this.id = id;
		this.response = response;
	}

	public int getId() {
		return id;
	}

	/**
	 * @return <code>true</code> for positive response, <code>false</code> otherwise
	 */
	public boolean getResponse() {
		return response;
	}
}
