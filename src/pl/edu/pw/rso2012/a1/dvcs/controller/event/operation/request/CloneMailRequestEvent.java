/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

/**
 * 
 * @author Oskar Leszczynski
 * 
 */

//cloneMailRequestEvent - gdy z maila przyszedł request z prosba o podanie
//danych do operacji clone;

//cloneRequestEvent - gdy gui poprosilo o wyslanie prosby o dane do clone
//pod podany adres

//cloneResponseEvent - gdy przyszedl mail z danymi do clone

public class CloneMailRequestEvent  extends ApplicationEvent{
	
    private final String email;
    
	public CloneMailRequestEvent(final String email)
    {
        this.email = email;
    }
	
    public String getEmail()
    {
        return email;
    }
	
}
