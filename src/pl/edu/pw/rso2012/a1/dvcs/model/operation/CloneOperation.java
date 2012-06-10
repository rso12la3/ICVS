package pl.edu.pw.rso2012.a1.dvcs.model.operation;

import java.util.List;

import pl.edu.pw.rso2012.a1.dvcs.model.communication.Commit;

/**
 * @author Oskar Leszczynski
 * 
 */

public class CloneOperation extends AbstractOperation
{
	private final List<Commit> commitList;
	private final String email;
	private final boolean reject;

	public CloneOperation(List<Commit> commitList, String email)
	{
		super();
		this.commitList = commitList;
		this.email = email;
		this.reject = false;
	}
	
	public CloneOperation(List<Commit> commitList, String email, final boolean reject)
	{
		super();
		this.commitList = commitList;
		this.email = email;
		this.reject = reject;
	}

	public List<Commit> getCommitList()
	{
		return commitList;
	}

	public String getEmail()
	{
		return email;
	}

	public boolean isReject()
	{
		return reject;
	}
	
	
	
}
