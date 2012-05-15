/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.event.application;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;


public class ErrorEvent extends ApplicationEvent
{
    private final String message;

    public ErrorEvent(final String message)
    {
        this.message = message;
        
    }

    public String getMessage()
    {
        return message;
    }
}
