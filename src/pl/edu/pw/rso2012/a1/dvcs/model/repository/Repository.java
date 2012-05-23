/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.ChangeData;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.Commit;
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
	
	public CloneRequestOperation cloneRequest(final Controller controller)
	{
		return new CloneRequestOperation();
	}
	
	public CloneOperation clone(final Controller controller)
	{
		// bierze liste z ostatniego commita
		// diff przechowywany w mapie
		return null;
	}
	
	public PushResponseOperation push(final Controller controller)
	{
		
		return null;
	}
	
	public PullResponseOperation pull(final Controller controller)
	{
		return null;
	}
	
	public PushRequestOperation pushRequest(Set<String> fileList, final Controller controller)
	{
		return null;
	}
	
	public PullRequestOperation pullReqeust(Set<String> fileList, final Controller controller)
	{
		return null;
	}
	
	public void update(List<Commit> commitList)
	{
		List<ChangeData> changeList = prepareChangeList(commitList);
		
		workingCopy.recoverFiles(changeList);
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
    
    
    private List<ChangeData> prepareChangeList(final List<Commit> commitList)
    {
    	List<ChangeData> changeList = new ArrayList<ChangeData>();
    	if (commitList != null && !commitList.isEmpty())
    	{
	    	CommitOperation operation;
	    	Collections.sort(commitList);
	    	Map<String, ChangeData> map;
	    	Map<String, ChangeData> finalMap;
	    	ChangeData data;
	    	
	    	Commit lastCommit = commitList.get(commitList.size() - 1);
//	    	finalMap = lastCommit.getCommitOperation().getMap();
	    	Set<String> fileSet = finalMap.keySet();
	    	
	    	for (String filename : fileSet)
			{
//				finalMap.get(filename)
	    		
			}
	    	
	    	
	    	
	    	for (int i = 0; i < commitList.size(); ++i)
			{
				operation = commitList.get(i).getCommitOperation();
	//			map = operation.getMap();
				
				for (String filename : fileSet)
				{
					data = map.get(filename);
					if (data != null)
					{
						
					}
					else
					{
						
					}
				}
				
			}
    	}
    	else
    	{
    		//TODO zastanowic sie czy dla null nie powinno rzucac wyjatkiem
    		return changeList;
    	}
    }
    
   
    
}
