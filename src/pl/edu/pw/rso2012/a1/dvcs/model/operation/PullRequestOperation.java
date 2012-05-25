package pl.edu.pw.rso2012.a1.dvcs.model.operation;

public class PullRequestOperation extends AbstractOperation {
	private final String emailCallback;

	public PullRequestOperation(final String email) {
		this.emailCallback = email;
	}

	public String getEmail() {
		return emailCallback;
	}
}
