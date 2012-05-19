package pl.edu.pw.rso2012.a1.dvcs.view.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
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
	
	JTree mTree;
	VersionedFileRenderer mRenderer;
	JScrollPane mScrollPane;
	JPopupMenu mPopupMenu;
	
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
	
	public void setFiles(File rootDirectory, List<String> versionedFilePaths) {
		TreeNode rootNode = FolderTreeUtils.createTree(rootDirectory, versionedFilePaths);
		mTree = createJTree(rootNode);
		mScrollPane.setViewportView(mTree);
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
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Log.o(TAG, Log.getCurrentMethodName());
		
		mTree.getSelectionPaths();
		
		switch (event.getActionCommand()) {
		case COMMIT_COMMAND:
			break;
		case ADD_COMMAND:
			break;
		case DELETE_COMMAND:
			break;
		}
	}
}
