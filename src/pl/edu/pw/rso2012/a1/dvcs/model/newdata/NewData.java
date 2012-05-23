/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.newdata;



import java.util.Map;

import pl.edu.pw.rso2012.a1.dvcs.model.file.clock.LogicalClock;


/**
 * @author
 *
 */
public class NewData {
	private String filename;				 
	private String filecontent;			
	private LogicalClock lclock;
	
	/**
	 * @return the filename
	 */
	
	public NewData (final String filename){
		this.filename=filename;
		this.filecontent="";
		this.lclock = new LogicalClock();
	}
	
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(final String filename) {
		this.filename = filename;
	}
	/**
	 * @return the filecontent
	 */
	public String getFileContent() {
		return filecontent;
	}
	/**
	 * @param filecontent the filecontent to set
	 */
	public void setFileContent(final String filecontent) {
		this.filecontent = filecontent;
	}
	/**
	 * @return the lclock
	 */
	public Map<String,Integer> getLclock() {
		return lclock.getVersionVector();
	}

}
