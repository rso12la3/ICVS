/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.communication;

import pl.edu.pw.rso2012.a1.dvcs.model.exception.ModelMethodNotImplementedException;

/**
 * @author Grzegorz Sancewicz
 * @email g.sancewicz@stud.elka.pw.edu.pl
 * 
 */
public class Mailbox
{
    private String username;
    private String password;
    private MessageQueue inputQueue;
    private MessageQueue outputQueue;
    
    public boolean checkSecurity() throws ModelMethodNotImplementedException
    {
        throw new ModelMethodNotImplementedException();
    }
    
}
