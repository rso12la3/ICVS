/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.snapshot;

import java.io.File;
import java.util.List;

import pl.edu.pw.rso2012.a1.dvcs.model.filesystem.FileSystem;
import difflib.*;

/**
 * @author
 *
 */
public class SnapShot extends FileSystem{

	/**
	 * 
	 */
	
	public SnapShot(final String address, final String root) {
		super(address,root);

		File f = new File(root);
		
		if(!f.exists())
			f.mkdir();
			
	}

	
	public Patch getDiff (final List<String> commited, final List<String> working) {
        
        return DiffUtils.diff(commited, working);
	}
	
	public List<String> applyPatch (final List<String> ls, final Patch patch) throws PatchFailedException {
		
		return (List<String>) patch.applyTo(ls);
	}
}
