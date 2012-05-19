package pl.edu.pw.rso2012.a1.dvcs.view.tree;

import java.awt.Component;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class VersionedFileRenderer extends DefaultTreeCellRenderer {
	
	private static final long serialVersionUID = -3632375295475932557L;
	private static final String IMAGE_DIR = "/pl/edu/pw/rso2012/a1/dvcs/view/tree/resources/";
	
	private ImageIcon versionedIcon, fileIcon;
	
	public VersionedFileRenderer() {
		super();
		URL versionedUrl = getClass().getResource(IMAGE_DIR + "document-new.png");
		URL fileUrl = getClass().getResource(IMAGE_DIR + "document.png");
		versionedIcon = new ImageIcon(versionedUrl);
		fileIcon = new ImageIcon(fileUrl);
	}
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		
		if (leaf && isVersioned(value)) {
			setIcon(versionedIcon);
		} else if (leaf) {
			setIcon(fileIcon);
		}
		
		return this;
	}
	
	private boolean isVersioned(Object value) {
		FileInfo fileInfo = (FileInfo) ((DefaultMutableTreeNode) value).getUserObject();
		return fileInfo.isVersioned();
	}
	
}
