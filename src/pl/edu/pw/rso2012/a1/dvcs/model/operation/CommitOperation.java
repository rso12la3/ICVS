package pl.edu.pw.rso2012.a1.dvcs.model.operation;

import java.util.Map;

import pl.edu.pw.rso2012.a1.dvcs.model.operation.ChangeData;

/*
 * Opakowuje informacje niezbedne do wykonania commita
 * W tym przypadku jest to to, co zwróci Workingcopy.diffFiles()
 */
public class CommitOperation extends AbstractOperation
{
	private Map<String,ChangeData> filesDiffs;
	
	public CommitOperation(Map<String,ChangeData> filesDiffs){
		this.filesDiffs= filesDiffs;
		
	}
	
	public Map<String,ChangeData> getFilesDiffs(){
		return this.filesDiffs;
	}

}
