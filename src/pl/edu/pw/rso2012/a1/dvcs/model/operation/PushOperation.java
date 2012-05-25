package pl.edu.pw.rso2012.a1.dvcs.model.operation;

import java.util.Map;

import pl.edu.pw.rso2012.a1.dvcs.model.newdata.NewData;

public class PushOperation extends AbstractOperation{
	private final Map<String, NewData>map;
	
	public PushOperation(Map<String, NewData> map){
		this.map= map;
	}
	
	public Map<String, NewData> getMap(){
		return this.map;
	}

}
