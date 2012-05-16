/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation;

import java.io.File;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

public class CreateEvent  extends ApplicationEvent
{
    private final String email, password;
    private final File baseDirectory;
    
    public CreateEvent(final String email, final String password, final File baseDirectory) {
        super();
        this.email = email;
        this.password = password;
        this.baseDirectory = baseDirectory;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public File getBaseDirectory() {
        return baseDirectory;
    }
    
	
}
