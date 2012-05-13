/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.handler;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;

/**
 * @author Grzegorz Sancewicz
 * @email g.sancewicz@stud.elka.pw.edu.pl
 * 
 */
public class AddHandler extends ApplicationHandler
{

    /**
     * @param controller
     */
    public AddHandler(final Controller controller)
    {
        super(controller);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler#handle(pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent)
     */
    @Override
    public void handle(final ApplicationEvent event) throws HandlerNotImplementedException
    {
        // TODO Auto-generated method stub
        throw new HandlerNotImplementedException(this);
    }

}
