package pl.edu.pw.rso2012.a1.dvcs.controller.event.view;

import java.io.File;
import java.util.List;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class ShowRepositoryEvent extends ApplicationEvent {
	
	private final List<String> versionedFilePaths;
	private final File rootDirectory;
	
	public ShowRepositoryEvent(final File rootDirectory, final List<String> versionedFilePaths) {
		super();
		this.rootDirectory = rootDirectory;
		this.versionedFilePaths = versionedFilePaths;
	}

	public List<String> getVersionedFilePaths() {
		return versionedFilePaths;
	}

	public File getRootDirectory() {
		return rootDirectory;
	}
	
}
