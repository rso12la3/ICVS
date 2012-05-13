/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.handler;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;

/**'
 * Klasa obslugujaca zdarzenie
 * 
 * @author Grzegorz Sancewicz
 * @email g.sancewicz@stud.elka.pw.edu.pl
 * 
 */
public abstract class ApplicationHandler
{
    
    protected final Controller controller;

    public ApplicationHandler(final Controller controller)
    {
        this.controller = controller;
    }

    abstract public void handle(ApplicationEvent event) throws HandlerNotImplementedException;
}
