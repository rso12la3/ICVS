package pl.edu.pw.rso2012.a1.dvcs.model.operation;

/**
 * @author Oskar Leszczynski
 * 
 */

public class PushResponseOperation extends AbstractOperation
{
	private final String result;
	
	public PushResponseOperation(String result){
		super();
		this.result= result;
	}
	
	public String getResult(){
		return result;
	}

}
