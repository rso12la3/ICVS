/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.changedata;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import pl.edu.pw.rso2012.a1.dvcs.model.file.clock.LogicalClock;

import difflib.Patch;

/**
 * @author
 *
 */
public class ChangeData {

		private String filename;				 
		private List<Patch> difflist;			
		private LogicalClock lclock;
		
		public ChangeData (final String filename){
			this.filename=filename;
			this.difflist=new LinkedList<Patch>();
			this.lclock = new LogicalClock();
		}
		
		
		/**
		 * @return the filename
		 */
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
		 * @return the difflist
		 */
		public List<Patch> getDifflist() {
			return difflist;
		}

		/**
		 * @param difflist the difflist to set
		 */
		public void setDifflist(List<Patch> difflist) {
			this.difflist = difflist;
		}

		/**
		 * @return the lclock
		 */
		public Map<String, Integer> getLclock() {
			return lclock.getVersionVector();
		}
		
		public void addDiffToList(Patch patch)
		{
			difflist.add(patch);
		}
		
		public void addDiffToList(List<Patch> patch)
		{
			difflist.addAll(patch);
		}
		
		public void clearDiffList()
		{
			difflist.clear();
		}
		
		public LogicalClock getVector()
		{
			return lclock;
		}
		
		public void setVector(LogicalClock vector)
		{
			this.lclock = vector;
		}
		
}
