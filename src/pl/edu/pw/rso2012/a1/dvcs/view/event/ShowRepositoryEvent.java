package pl.edu.pw.rso2012.a1.dvcs.view.event;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.repository.Repository;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class ShowRepositoryEvent extends ApplicationEvent {
	private final Repository mRepository;

	public ShowRepositoryEvent(Repository repository) {
		mRepository = repository;
	}

	public Repository getRepository() {
		return mRepository;
	}
}
