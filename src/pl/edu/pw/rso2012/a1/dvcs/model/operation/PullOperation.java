package pl.edu.pw.rso2012.a1.dvcs.model.operation;

import java.util.HashMap;
import java.util.Map;

import pl.edu.pw.rso2012.a1.dvcs.model.newdata.NewData;


public class PullOperation extends AbstractOperation{
	private final Map<String, NewData> data;
	private final boolean reject;
	private final String email;
	
	public PullOperation(Map<String, NewData> data, final String email){
		super();
		this.data= data;
		this.reject = false;
		this.email = email;
	}
	
	public PullOperation(Map<String, NewData> data, final String email ,final boolean reject){
		super();
		this.data= data;
		this.reject = reject;
		this.email = email;
	}
	
	public Map<String, NewData> getData(){
		return data;
	}

	public boolean isReject()
	{
		return reject;
	}

	public String getEmail()
	{
		return email;
	}
	
	
	
	
	
	
}
