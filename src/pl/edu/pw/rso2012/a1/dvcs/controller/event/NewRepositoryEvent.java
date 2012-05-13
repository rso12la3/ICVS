package pl.edu.pw.rso2012.a1.dvcs.controller.event;

import java.io.File;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class NewRepositoryEvent extends ApplicationEvent {
	
	private final String repositoryName, serverUrl, username, password;
	private final File localDirectory;
	
	public NewRepositoryEvent(String repositoryName, String serverUrl,
			String username, String password, File localDirectory) {
		super();
		this.repositoryName = repositoryName;
		this.serverUrl = serverUrl;
		this.username = username;
		this.password = password;
		this.localDirectory = localDirectory;
	}

	public String getRepositoryName() {
		return repositoryName;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public File getLocalDirectory() {
		return localDirectory;
	}
	
	
}
