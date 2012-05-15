/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

public class CloneRequestEvent  extends ApplicationEvent{
	
    private final String email;
    
	public CloneRequestEvent(final String email)
    {
        this.email = email;
    }
	
    public String getEmail()
    {
        return email;
    }
	
}
