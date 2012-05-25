package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.response;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PushResponseOperation;

public class PushResponseEvent extends ApplicationEvent {
	private final PushResponseOperation operation;
	
	public PushResponseEvent(PushResponseOperation operation) {
		this.operation= operation;
	}

	public PushResponseOperation getOperation(){
		return operation;
	}

}
