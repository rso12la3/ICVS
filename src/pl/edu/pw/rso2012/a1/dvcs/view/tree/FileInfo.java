package pl.edu.pw.rso2012.a1.dvcs.view.tree;

import java.io.File;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class FileInfo {
	
	private final File file;
	private boolean isVersioned;
	
	FileInfo(File file) {
		if (file == null) throw new NullPointerException();
		
		this.file = file;
	}
	
	FileInfo(File file, boolean isVersioned) {
		if (file == null) throw new NullPointerException();
		
		this.file = file;
		this.isVersioned = isVersioned;
	}
	
	public File getFile() {
		return file;
	}
	
	public boolean isVersioned() {
		return isVersioned;
	}
	
	public void setVersioned(boolean isVersioned) {
		this.isVersioned = isVersioned;
	}
	
	@Override
	public String toString() {
		return file.getName();
	}
	
}
