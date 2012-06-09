package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

public class EditEvent extends ApplicationEvent {
	
	private final String email, password;
	
	public EditEvent(final String email, final String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
}
