package pl.edu.pw.rso2012.a1.dvcs.controller.exception;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

/**
 * @author Grzegorz Sancewicz
 * @email g.sancewicz@stud.elka.pw.edu.pl
 * 
 */
public class NoHandlerException extends Exception
{
    private static final long serialVersionUID = 1L;

    public NoHandlerException(final ApplicationEvent event)
    {
        super("Zdarzenie " + event.getClass().getSimpleName() + " nie posiada zarejestrowanego handlera.");
    }
}
