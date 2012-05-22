package pl.edu.pw.rso2012.a1.dvcs.controller.event.view;

import java.io.File;
import java.util.Set;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class ShowRepositoryEvent extends ApplicationEvent {
	
	private final Set<String> versionedFilePaths;
	private final File rootDirectory;
	
	public ShowRepositoryEvent(final File rootDirectory, final Set<String> versionedFilePaths) {
		super();
		this.rootDirectory = rootDirectory;
		this.versionedFilePaths = versionedFilePaths;
	}

	public Set<String> getVersionedFilePaths() {
		return versionedFilePaths;
	}

	public File getRootDirectory() {
		return rootDirectory;
	}
	
}
