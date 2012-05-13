/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.event;

import java.io.File;

/**
 * @author Grzegorz Sancewicz
 * @email g.sancewicz@stud.elka.pw.edu.pl
 * 
 */
public class CreateEvent extends NewRepositoryEvent {
	
	public CreateEvent(String repositoryName, String serverUrl,
			String username, String password, File localDirectory) {
		super(repositoryName, serverUrl, username, password, localDirectory);
	}
	
}
