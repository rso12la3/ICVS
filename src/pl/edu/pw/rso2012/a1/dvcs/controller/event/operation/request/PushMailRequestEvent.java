/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PushRequestOperation;

/**
 * 
 * @author Oskar Leszczynski
 * 
 */

public class PushMailRequestEvent extends ApplicationEvent
{
    private final PushRequestOperation operation;
    
    public PushMailRequestEvent(final PushRequestOperation operation)
    {
        this.operation= operation;
    }

    public PushRequestOperation getOperation()
    {
        return this.operation;
    }
}
