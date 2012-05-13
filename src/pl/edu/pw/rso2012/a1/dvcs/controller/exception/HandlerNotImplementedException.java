package pl.edu.pw.rso2012.a1.dvcs.controller.exception;

import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;

/**
 * @author Grzegorz Sancewicz
 * @email g.sancewicz@stud.elka.pw.edu.pl
 * 
 */
public class HandlerNotImplementedException extends Exception
{
    private static final long serialVersionUID = 1L;

    public HandlerNotImplementedException(final ApplicationHandler handler)
    {
        super("Handler " + handler.getClass().getSimpleName() + " nie zosta³ jeszcze zaimplementowany.");
    }
}
