package pl.edu.pw.rso2012.a1.dvcs.view.tree;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class FileTreeNode extends DefaultMutableTreeNode {
	private static final long serialVersionUID = -910105076487349755L;
	
	public FileTreeNode(FileInfo fileInfo) {
		super(fileInfo);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		FileTreeNode other = (FileTreeNode) obj;
		if (this.userObject == null) {
			if (other.userObject != null) return false;
		} else if (!this.userObject.equals(other.userObject)) return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.userObject == null) ? 0 : this.userObject.hashCode());
		return result;
	}
	
}
