package pl.edu.pw.rso2012.a1.dvcs.view.event;

import java.util.List;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class ShowSelectRepositoryEvent extends ApplicationEvent {
	private final List<String> mRepositories;

	public ShowSelectRepositoryEvent(List<String> repository) {
		mRepositories = repository;
	}

	public List<String> getRepositoriesNames() {
		return mRepositories;
	}
}
