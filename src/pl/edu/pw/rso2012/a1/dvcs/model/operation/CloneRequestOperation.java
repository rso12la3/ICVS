package pl.edu.pw.rso2012.a1.dvcs.model.operation;

/**
 * @author Oskar Leszczynski
 * 
 */

public class CloneRequestOperation extends AbstractOperation
{
	private final String email;
	
	

	public CloneRequestOperation(String email)
	{
		super();
		this.email = email;
	}



	public String getEmail()
	{
		return email;
	}
	
	

}
