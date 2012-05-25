/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PullRequestOperation;

/**
 * 
 * @author Oskar Leszczynski
 * 
 */

public class PullMailRequestEvent extends ApplicationEvent {
	private final PullRequestOperation operation;

	public PullMailRequestEvent(final PullRequestOperation operation) {
		this.operation = operation;
	}

	public PullRequestOperation getOperation() {
		return this.operation;
	}

}
