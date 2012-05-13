package pl.edu.pw.rso2012.a1.dvcs.model.exception;


/**
 * @author Grzegorz Sancewicz
 * @email g.sancewicz@stud.elka.pw.edu.pl
 * 
 */
public class ModelMethodNotImplementedException extends Exception
{
    private static final long serialVersionUID = 1L;

    public ModelMethodNotImplementedException()
    {
        super("Metoda nie zosta³a zaimplementowana.");
    }
}
