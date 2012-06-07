package pl.edu.pw.rso2012.a1.dvcs.view.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import pl.edu.pw.rso2012.a1.dvcs.utils.Log;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class FoldersTreeComp implements ActionListener {
	
	private static final String TAG = FoldersTreeComp.class.getSimpleName();
	
	public static final String SNAPSHOT_DIR = ".snapshot", METADATA_FILENAME = ".metadata.xml";
	
	JTree mTree;
	VersionedFileRenderer mRenderer;
	JScrollPane mScrollPane;
	JPopupMenu mPopupMenu;
	FoldersTreeActionListener mListener;
	
	public FoldersTreeComp() {
		mRenderer = new VersionedFileRenderer();
		mPopupMenu = createPopupMenu();
		
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
	
	public JTree createJTree(TreeNode rootNode) {
		JTree tree = new JTree(rootNode);
		tree.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		tree.setShowsRootHandles(true);
		tree.setCellRenderer(mRenderer);
		tree.addMouseListener(mMouseListener);
		
		return tree;
	}
	
	public void getExpadnedPaths(JTree tree, TreePath parent, List<TreePath> list) {
		if (!tree.isVisible(parent)) {
			return;
		}
		list.add(parent);
		
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				getExpadnedPaths(tree, path, list);
			}
		}
	}
	
	public void setFiles(File rootDirectory, Set<String> versionedFilePaths) {
		DefaultTreeModel model = (DefaultTreeModel) mTree.getModel();
		
		List<TreePath> paths = null;
		Object root = model.getRoot();
		if (root != null) {
			paths = new ArrayList<TreePath>();
			getExpadnedPaths(mTree, new TreePath(root), paths);
		}
		
		TreeNode newRootNode = FolderTreeUtils.createTree(rootDirectory, versionedFilePaths);
		model.setRoot(newRootNode);
		model.reload();
		
		if (paths != null) for (TreePath path : paths)
			mTree.expandPath(path);
	}
	
	private static final String COMMIT_COMMAND = "COMMIT", ADD_COMMAND = "ADD", DELETE_COMMAND = "DELETE";
	
	private JPopupMenu createPopupMenu() {
		JPopupMenu menu = new JPopupMenu();
		
		JMenuItem item = new JMenuItem("Commit");
		item.addActionListener(this);
		item.setActionCommand(COMMIT_COMMAND);
		menu.add(item);
		
		item = new JMenuItem("Add");
		item.addActionListener(this);
		item.setActionCommand(ADD_COMMAND);
		menu.add(item);
		
		item = new JMenuItem("Delete");
		item.addActionListener(this);
		item.setActionCommand(DELETE_COMMAND);
		menu.add(item);
		
		menu.setOpaque(true);
		menu.setLightWeightPopupEnabled(true);
		
		return menu;
	}
	
	MouseListener mMouseListener = new MouseAdapter() {
		
		@Override
		public void mouseReleased(MouseEvent event) {
			TreePath path = mTree.getPathForLocation(event.getX(), event.getY());
			if (path == null) {
				mTree.clearSelection();
			} else if (!mTree.isPathSelected(path) && event.isPopupTrigger()) {
				mTree.setSelectionPath(path);
			}
			
			if (path != null && event.isPopupTrigger()) {
				mPopupMenu.show((JComponent) event.getSource(), event.getX(), event.getY());
			}
		}
	};
	
	public Set<String> getAllFilesInTree() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) mTree.getModel().getRoot();
		Set<String> fileSet = new HashSet<String>();
		addChildrenFilesToSet(node, fileSet);
		
		Log.o(TAG, Log.getCurrentMethodName() + " " + fileSet.size() + " " + fileSet.toString());
		
		return fileSet;
	}
	
	private void addChildrenFilesToSet(DefaultMutableTreeNode node, Set<String> files) {
		FileInfo info = (FileInfo) node.getUserObject();
		File file = info.getFile();
		if (file.isDirectory() && !node.isLeaf()) {
			for (int i = 0, childCount = node.getChildCount(); i < childCount; i++) {
				DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
				addChildrenFilesToSet(child, files);
			}
		} else if (!file.isDirectory()) {
			files.add(info.getFilePathFromRoot());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Log.o(TAG, Log.getCurrentMethodName());
		
		if (mListener == null) return;
		
		TreePath[] paths = mTree.getSelectionPaths();
		Set<String> fileSet = new HashSet<String>();
		for (TreePath path : paths) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
			addChildrenFilesToSet(node, fileSet);
		}
		
		Log.o(TAG, event.getActionCommand() + " " + fileSet.toString());
		
		switch (event.getActionCommand()) {
		case COMMIT_COMMAND:
			mListener.onCommit(fileSet);
			break;
		case ADD_COMMAND:
			mListener.onAdd(fileSet);
			break;
		case DELETE_COMMAND:
			mListener.onDelete(fileSet);
			break;
		}
	}
	
	public void setListener(FoldersTreeActionListener listener) {
		mListener = listener;
	}
	
	public interface FoldersTreeActionListener {
		
		public void onCommit(Set<String> files);
		
		public void onAdd(Set<String> files);
		
		public void onDelete(Set<String> files);
	}
}
