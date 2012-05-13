package pl.edu.pw.rso2012.a1.dvcs.view;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class FilePickingHelper {
	private static final FileFilter DEFAULT_FILE_FILTER = new FolderFileFilter();
	
	private FilePickingHelper() {}
	
	public static File openFolderChooser(Component owner) {
		JFileChooser fileChooser = new JFileChooser(new File("."));
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setFileFilter(DEFAULT_FILE_FILTER);
		
		switch (fileChooser.showOpenDialog(owner)) {
		case JFileChooser.APPROVE_OPTION:
			return fileChooser.getSelectedFile();
		default:
			return null;
		}
	}
	
	private static class FolderFileFilter extends FileFilter {
		
		@Override
		public boolean accept(File f) {
			return f != null && (f.isDirectory());
		}
		
		@Override
		public String getDescription() {
			return null;
		}
	}
}
