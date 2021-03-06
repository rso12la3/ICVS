package pl.edu.pw.rso2012.a1.dvcs.view.tree;

import java.io.File;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
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
	
	private static final String TAG = FolderTreeUtils.class.getSimpleName();
	
	private FolderTreeUtils() {}
	
	/**
	 * Returned length depends on the last character of the string.
	 * @param rootDirectory
	 * @return
	 */
	public static int getProperRootPathLenght(File rootDirectory){
		String rootPath = rootDirectory.getAbsolutePath();
		return rootPath.endsWith(File.separator) ? rootPath.length() : rootPath.length() + 1;
	}
	
	public static TreeNode createTree(File rootDirectory, Set<String> versionedFilePaths) {
		return addNode(null, rootDirectory, versionedFilePaths, getProperRootPathLenght(rootDirectory));
	}
	
	private static FileTreeNode addNode(FileTreeNode currentParent, File directory,
			Set<String> versionedFiles, int rootAbsolutePathLength) {
		
		FileTreeNode dirNode = new FileTreeNode(new FileInfo(directory, rootAbsolutePathLength));
		if (currentParent != null) currentParent.add(dirNode);
		
		String[] childrenArray = directory.list();
		List<String> childrenList = new ArrayList<String>(childrenArray.length);
		if (currentParent == null) {
			for (String child : childrenArray) {
				if(child.equals(FoldersTreeComp.METADATA_FILENAME) || child.equals(FoldersTreeComp.SNAPSHOT_DIR))
					continue;
				
				childrenList.add(child);
			}
		} else {
			for (String child : childrenArray) {
				childrenList.add(child);
			}
		}
		Collator collator = Collator.getInstance();
		collator.setStrength(Collator.PRIMARY);
		Collections.sort(childrenList, collator);
		
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		
		String path = directory.getPath();
		for (String node : childrenList) {
			String newPath = path + File.separator + node;
			
			File file = new File(newPath);
			if (file.isDirectory()) {
				addNode(dirNode, file, versionedFiles, rootAbsolutePathLength);
			} else {
				FileInfo fileInfo = new FileInfo(file, rootAbsolutePathLength);
				fileInfo.setVersioned(versionedFiles);
				fileInfoList.add(fileInfo);
				
				// Log.o(TAG, file.getAbsolutePath() + " " +
				// fileInfo.getFilePathFromRoot());
			}
		}
		
		for (FileInfo info : fileInfoList) {
			dirNode.add(new DefaultMutableTreeNode(info));
		}
		
		return dirNode;
	}
}
