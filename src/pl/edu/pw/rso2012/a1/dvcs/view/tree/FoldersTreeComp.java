package pl.edu.pw.rso2012.a1.dvcs.view.tree;

import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class FoldersTreeComp {
	JTree mTree;
	VersionedFileRenderer mRenderer;
	JScrollPane mScrollPane;
	
	public FoldersTreeComp() {
		mRenderer = new VersionedFileRenderer();
		
		mTree = createJTree(null);
		
		mScrollPane = new JScrollPane();
		mScrollPane.setViewportView(mTree);
	}
	
	public JScrollPane getScrollPane() {
		return mScrollPane;
	}
	
	public JTree getTree() {
		return mTree;
	}
	
	public JTree createJTree(TreeNode rootNode){
		JTree tree = new JTree(rootNode);
		tree.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		tree.setShowsRootHandles(true);
		tree.setCellRenderer(mRenderer);
		
		return tree;
	}
	
	public void setFiles(File rootDirectory, List<String> versionedFilePaths){
		TreeNode rootNode = FolderTreeUtils.createTree(rootDirectory, versionedFilePaths);
		mTree = createJTree(rootNode);
		mScrollPane.setViewportView(mTree);
	}
}
