/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.response;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

public class CloneResponseEvent  extends ApplicationEvent{
	
    private final String email;
    
	public CloneResponseEvent(final String email)
    {
        this.email = email;
    }
	
    public String getEmail()
    {
        return email;
    }
	
}
