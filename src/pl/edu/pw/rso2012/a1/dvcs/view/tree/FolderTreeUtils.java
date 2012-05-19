package pl.edu.pw.rso2012.a1.dvcs.view.tree;

import java.io.File;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class FolderTreeUtils {
	
	private FolderTreeUtils() {}
	
	public static TreeNode createTree(File rootDirectory, List<String> versionedFilePaths) {
		Set<String> versionedFiles = new HashSet<String>(versionedFilePaths);
		return addNode(null, rootDirectory, versionedFiles);
	}
	
	private static DefaultMutableTreeNode addNode(DefaultMutableTreeNode currentParent, File directory,
			Set<String> versionedFiles) {
		
		boolean isRoot = currentParent == null;
		DefaultMutableTreeNode dirNode = new DefaultMutableTreeNode(new FileInfo(directory));
		if (!isRoot) currentParent.add(dirNode);
		
		String[] childrenArray = directory.list();
		List<String> childrenList = new ArrayList<String>(childrenArray.length);
		for (String child : childrenArray) {
			childrenList.add(child);
		}
		Collator collator = Collator.getInstance();
		collator.setStrength(Collator.PRIMARY);
		Collections.sort(childrenList, collator);
		
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		
		String path = directory.getPath();
		for (String node : childrenList) {
			String newPath = isRoot ? node : path + File.separator + node;
			
			File file = new File(newPath);
			if (file.isDirectory()) {
				addNode(dirNode, file, versionedFiles);
			} else {
				fileInfoList.add(new FileInfo(file, versionedFiles.contains(file.getPath())));
			}
		}
		
		for (FileInfo info : fileInfoList) {
			dirNode.add(new DefaultMutableTreeNode(info));
		}
		
		return dirNode;
	}
}
