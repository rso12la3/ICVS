package pl.edu.pw.rso2012.a1.dvcs.model.operation;

import java.util.Map;

import pl.edu.pw.rso2012.a1.dvcs.model.newdata.NewData;

/**
 * @author Oskar Leszczynski
 * 
 */

public class PushRequestOperation extends AbstractOperation {
	private final Map<String, NewData> data;
	private final String emailCallback;

	public PushRequestOperation(Map<String, NewData> data, String email) {
		this.data = data;
		this.emailCallback = email;
	}

	public Map<String, NewData> getData() {
		return data;
	}
	
	public String getEmailCallback(){
		return emailCallback;
	}

}
