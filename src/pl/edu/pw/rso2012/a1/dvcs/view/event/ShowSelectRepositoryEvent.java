package pl.edu.pw.rso2012.a1.dvcs.view.event;

import java.util.List;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.repository.Repository;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class ShowSelectRepositoryEvent extends ApplicationEvent {
	private final List<Repository> mRepositories;

	public ShowSelectRepositoryEvent(List<Repository> repository) {
		mRepositories = repository;
	}

	public List<Repository> getRepositories() {
		return mRepositories;
	}
}
