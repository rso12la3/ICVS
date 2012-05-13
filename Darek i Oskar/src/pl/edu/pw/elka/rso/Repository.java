package pl.edu.pw.elka.rso;

import java.util.ArrayList;

public class Repository
{
	private Snapshot snapshot;
	
		
	public CommitOperation commit(ArrayList<String> fileList)
	{
		
	}
	
	public CloneRequestOperation cloneRequest()
	{
		return new CloneRequestOperation();
	}
	
	public CloneOperation clone()
	{
		// bierze liste z ostatniego commita
		
	}
	
	public PushOperation push()
	{
		
	}
	
	public PullOperation pull()
	{
		
	}
	
	public PushRequestOperation pushRequest(ArrayList<String> fileList)
	{
		
	}
	
	public PullRequestOperation pullReqeust(ArrayList<String> fileList)
	{
		
	}
	
	public void update(ArrayList<CommitOperation> commitOperationList)
	{
		
	}
	
	public UpdateRequestOperation updateRequest()
	{
		
	}
	
	public void add(final ArrayList<String> fileList)
	{
		//odwolanie do workingcopy
	}
	
	public void delete(final ArrayList<String> fileList)
	{
		
	}
	
}
