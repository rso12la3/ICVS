/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

public class UpdateEvent extends ApplicationEvent
{
    private final String version;

    public UpdateEvent(final String version)
    {
        this.version = version;
    }

    public String getVersion()
    {
        return version;
    }


    
}
