/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.changedata;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import difflib.Patch;

/**
 * @author
 *
 */
public class ChangeData {

		private String filename;				 
		private List<Patch> difflist;			
		private Map<String,Integer> lclock;
		
		public ChangeData (final String filename){
			this.filename=filename;
			this.difflist=new LinkedList<Patch>();
			this.lclock = new HashMap<String,Integer>();
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
			return lclock;
		}

		/**
		 * @param lclock the lclock to set
		 */
		public void setLclock(Map<String, Integer> lclock) {
			this.lclock = lclock;
		}

		
}
