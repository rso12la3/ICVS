/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

/**
 * 
 * @author Oskar Leszczynski
 * 
 */

public class PushMailRequestEvent extends ApplicationEvent
{
    private final String email;
    
    public PushMailRequestEvent(final String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
}
