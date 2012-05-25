package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.response;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PullOperation;

public class PullResponseEvent extends ApplicationEvent{
private final PullOperation operation;
	
	public PullResponseEvent(PullOperation operation) {
		this.operation= operation;
	}

	public PullOperation getOperation(){
		return operation;
	}
}
