package pl.edu.pw.rso2012.a1.dvcs.model.operation;

/**
 * @author Oskar Leszczynski
 * 
 */

public class PullResponseOperation extends AbstractOperation {
	private final String result;

	public PullResponseOperation(String result) {
		super();
		this.result = result;
	}

	public String getResult() {
		return result;
	}
}
