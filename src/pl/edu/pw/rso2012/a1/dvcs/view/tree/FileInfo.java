package pl.edu.pw.rso2012.a1.dvcs.view.tree;

import java.io.File;
import java.util.Set;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class FileInfo {
	
	private final File file;
	private boolean isVersioned;
	private final int rootPathLength;
	
	FileInfo(File file, int rootPathLength){
		if (file == null) throw new NullPointerException();
		
		this.file = file;
		this.rootPathLength = rootPathLength;
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
	
	public void setVersioned(Set<String> versionedFiles){
		this.isVersioned = versionedFiles.contains(getFilePathFromRoot());
	}
	
	public String getFilePathFromRoot(){
		return file.getAbsolutePath().substring(rootPathLength);
	}
	
	@Override
	public String toString() {
		return file.getName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		result = prime * result + rootPathLength;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		FileInfo other = (FileInfo) obj;
		if (file == null) {
			if (other.file != null) return false;
		} else if (!file.equals(other.file)) return false;
		if (rootPathLength != other.rootPathLength) return false;
		return true;
	}
	
	
}
