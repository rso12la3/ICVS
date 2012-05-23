/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.edu.pw.rso2012.a1.dvcs.model.changedata.ChangeData;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.Configuration;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.RepositoryConfiguration;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.*;
import pl.edu.pw.rso2012.a1.dvcs.model.workingcopy.WorkingCopy;


/**
 * @author Grzegorz Sancewicz & Oskar Leszczynski
 * 
 */
public class Repository
{
    private String absolutePath;
    private final WorkingCopy workingCopy;
    
    public Repository(final String absolutePath)
    {
        RepositoryConfiguration repositoryConfiguration = Configuration.getInstance().getRepositoryConfiguration();
        this.absolutePath = absolutePath;
        workingCopy = new WorkingCopy(repositoryConfiguration.getRepositoryAddress(), repositoryConfiguration.getRepositoryAbsolutePath());
    }

    public String getAbsolutePath()
    {
        return absolutePath;
    }

    public void setAbsolutePath(final String absolutePath)
    {
        this.absolutePath = absolutePath;
    }
    
    
    
	public CommitOperation commit(ArrayList<String> filesToCommit)
	{
		Map<String,ChangeData> diffResult= workingCopy.diffFiles(filesToCommit);
		CommitOperation operation= new CommitOperation(diffResult);
		
		return operation;
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
	
	public PushResponseOperation push()
	{
		
		return null;
	}
	
	public PullResponseOperation pull()
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
		workingCopy.addFiles(fileList);
	}
	
	public void delete(final ArrayList<String> fileList)
	{
		workingCopy.deleteFiles(fileList);
	}

    public WorkingCopy getWorkingCopy()
    {
        return workingCopy;
    }
    

}
