/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.filesystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * @author
 *
 */
public abstract class FileSystem {
	
	/*
	 * Fields
	 */
	private java.io.File root;
	private String address;
	private Map<String,Map<String,Integer>> filelist;
	
	/*
	 * Constructor
	 */
	
	public FileSystem(final String address, final String root)
    {
		this.address = address;
        this.root = new java.io.File(root);
        this.filelist = new HashMap<String,Map<String,Integer>>();
    }
	
	/*
	 * Method for setting root directory
	 */
	
	public void setRoot (final String root){
		this.root = new java.io.File(root);
	}
	
	/*
	 * Method for reading root directory
	 */
	
	public String getRoot(){
		return this.root.getAbsolutePath();
	}
	
	/*
	 * Method for setting email address
	 */
	
	public void setAddress (final String address){
		this.address = address;
	}
	
	/*
	 * Method for reading address
	 */
	
	public String getAddress(){
		return this.address;
	}
	
	/*
	 * Method for reading filelist map
	 */
	
	public Map<String,Map<String,Integer>> getFilelist(){
		return this.filelist;
	}
	
	/*
	 * Method for setting filelist map
	 */
	
	public void setFilelist(final Map<String,Map<String,Integer>> filelist){
		this.filelist = filelist;
	}
	
	/*
	 * Method for reading from file
	 */
	
	public List<String> readFile(final String pathname) {
        List<String> lines = new LinkedList<String>();
        String line;
        try {
                BufferedReader in = new BufferedReader(new FileReader(pathname));
                while ((line = in.readLine()) != null) 
                        lines.add(line);
        } catch (IOException e) {
                e.printStackTrace();
        }
        return lines;
	}
	
	/*
	 * Method for writing to file
	 */
	
	public void writeFile(final String pathname, final List<String> content) {
		BufferedWriter out = null;
			try {
				out = new BufferedWriter(new FileWriter(pathname));
			
			for (String str: content){
            out.write(str);
            out.newLine();
			}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(out != null) {
                try {
					out.flush();
                out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
    }
	
	/*
	 * Method for filtering dir printing
	 */

	FilenameFilter filter = new FilenameFilter() {
		@Override
		public boolean accept(java.io.File dir, String name) {
			return !name.startsWith(".");
		}
	};
	
	/*
	 * Method for printing directory content with relative paths
	 */
	
	public List<String> dirRecursiveRel(final String pathname) {
	    
  		List<String> result = new LinkedList<String>();
	    java.io.File[] filesAndDirs = new java.io.File(pathname).listFiles(this.filter);

	    for(java.io.File file : Arrays.asList(filesAndDirs)) {
	      if(file.isFile()){
	    	result.add(file.getName());
	      }
	      
	      if (file.isDirectory() ) {
	       
	        List<String> nextResult = dirRecursiveRel(file.getAbsolutePath());
	        for(String str : nextResult)
	        	result.add(file.getName() + java.io.File.separatorChar + str);
	      }
	    }
	    return result;
	  } 
	
	/**
	 * 
	 * @param rootDir folder w ktorym znajduje sie repo
	 * @return set ze sciezkami wzgledem roota do plikow wersjonowanych
	 */
	public Set<String> getVersionedFiles(File rootDir){
		if(rootDir == null)
			throw new NullPointerException();
		
		return Collections.emptySet();
	}
	
	/*
	 *	Method for listing files(root relative paths), tags versioned files    
	 */
	
	public Map<String,Boolean> dirIsVersioned(final String pathname) {
	    Map<String,Boolean> map = new HashMap<String,Boolean>();
  		List<String> result = new LinkedList<String>();
	    java.io.File[] filesAndDirs = new java.io.File(pathname).listFiles(this.filter);

	    for(java.io.File file : Arrays.asList(filesAndDirs)) {
	      if(file.isFile()){
	    	result.add(file.getName());
	      }
	      
	      if (file.isDirectory() ) {
	       
	        List<String> nextResult = dirRecursiveRel(file.getAbsolutePath());
	        for(String str : nextResult)
	        	result.add(file.getName() + java.io.File.separatorChar + str);
	      }
	    }
	    
	    for(String str : result){
	    	if(this.getFileNames().contains(str))
	    		map.put(str,true);
	    	else
	    		map.put(str, false);
	    }
	    return map;
	    
	  }  	
	

	
	public void addFile (final String file){
		
		if(!this.filelist.containsKey(file))
			this.filelist.put(file, new HashMap<String,Integer>());
			this.filelist.get(file).put(this.address, 0);
	}
	
	/*
	 * Method for adding files to include in versioning system
	 */
	
	public void addFiles (final Set<String> files){
		
		for (String str : files)
			this.addFile(str);
	}
	
	public void deleteFile (final String file){
		
		if(this.filelist.containsKey(file))
			this.filelist.remove(file);
	}
	
	/*
	 * Method for deleting files from versioning system
	 */
	
	public void deleteFiles (final Set<String> files){
		
		for (String str : files)
			this.addFile(str);
	}
	
	public List<String> getFileNames(){
		List<String> filenames = new LinkedList<String>();

			filenames.addAll(this.filelist.keySet());	
		
		return filenames;
	}
	
	public void clearFilelist(){
		this.filelist.clear();
	}
	
	public void pDeleteFile(final String pathname){
		File f = new File(pathname);
		if (f.exists())
			f.delete();
	}
	
	public void pRecursiveDelete(final String pathname) {    
	    java.io.File[] filesAndDirs = new java.io.File(pathname).listFiles(this.filter);

	    for(java.io.File file : Arrays.asList(filesAndDirs)) { 
	      if (file.isDirectory() )  
	        pRecursiveDelete(file.getAbsolutePath());

	      file.delete();
	    }

	  }  
	
	public void pCreateFile(final String pathname){
		File f = new File(pathname);
		try {
			f.getParentFile().mkdirs();
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void pCopyFile(final String srcpath, final String destpath){
		List<String> content = this.readFile(srcpath);
		this.writeFile(destpath, content);	
	}
	
	
}