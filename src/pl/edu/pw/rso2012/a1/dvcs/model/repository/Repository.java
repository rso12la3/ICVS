/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.repository;

import java.util.ArrayList;

import pl.edu.pw.rso2012.a1.dvcs.model.operation.*;


/**
 * @author Grzegorz Sancewicz & Oskar Leszczynski
 * 
 */
public class Repository
{
    private String absolutePath;
//	private Snapshot snapshot;

    
    public Repository(final String absolutePath)
    {
        this.absolutePath = absolutePath;
    }

    public String getAbsolutePath()
    {
        return absolutePath;
    }

    public void setAbsolutePath(final String absolutePath)
    {
        this.absolutePath = absolutePath;
    }
    
    
    
	public CommitOperation commit(ArrayList<String> fileList)
	{
		
		return null;
	}
	
	public CloneRequestOperation cloneRequest()
	{
		return new CloneRequestOperation();
	}
	
	public CloneOperation clone()
	{
		// bierze liste z ostatniego commita
		// diff przechowywany w mapie
		return null;
	}
	
	public PushOperation push()
	{
		
		return null;
	}
	
	public PullOperation pull()
	{
		return null;
	}
	
	public PushRequestOperation pushRequest(ArrayList<String> fileList)
	{
		return null;
	}
	
	public PullRequestOperation pullReqeust(ArrayList<String> fileList)
	{
		return null;
	}
	
	public void update(String version)
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
