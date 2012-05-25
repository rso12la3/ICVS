package pl.edu.pw.rso2012.a1.dvcs.model.operation;

import java.util.Map;

import pl.edu.pw.rso2012.a1.dvcs.model.newdata.NewData;


public class PullOperation extends AbstractOperation{
	private final Map<String, NewData> data;
	
	public PullOperation(Map<String, NewData> data){
		super();
		this.data= data;
	}
	
	public Map<String, NewData> getData(){
		return data;
	}
}
