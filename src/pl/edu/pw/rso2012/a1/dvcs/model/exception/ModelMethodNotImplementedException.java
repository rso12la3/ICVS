package pl.edu.pw.rso2012.a1.dvcs.model.exception;


/**
 * @author Grzegorz Sancewicz
 * 
 */
public class ModelMethodNotImplementedException extends Exception
{
    private static final long serialVersionUID = 1L;

    public ModelMethodNotImplementedException()
    {
        super("Metoda nie zosta�a zaimplementowana.");
    }
}
