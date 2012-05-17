package pl.edu.pw.rso2012.a1.dvcs.controller.event.view;

import java.util.List;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.file.File;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class ShowRepositoryEvent extends ApplicationEvent {
	
	private final List<File> mFiles;
	
	public ShowRepositoryEvent(final List<File> files) {
		super();
		this.mFiles = files;
	}
	
	public List<File> getFiles() {
		return mFiles;
	}
}
