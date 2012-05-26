package pl.edu.pw.rso2012.a1.dvcs.model;

import java.io.File;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowNoRepositoryEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowRepositoryEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.Mailbox;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.Configuration;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.RepositoryConfiguration;
import pl.edu.pw.rso2012.a1.dvcs.model.repository.Repository;
import pl.edu.pw.rso2012.a1.dvcs.utils.Log;
import pl.edu.pw.rso2012.a1.dvcs.view.utils.TextUtils;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class Model {
	private static final String TAG = Model.class.getSimpleName();
	
	private final Mailbox mailbox;
	private final Controller controller;
	//
	private Repository repository;
	//
	private volatile boolean canStart, initialized;
	
	public Model(final Controller controller) {
		this.mailbox = new Mailbox(controller.getEventQueue());
		this.controller = controller;
		
		initialize();
	}
	
	public void initialize() {
		if (isConfigurationInitialized()) {
			Log.o(TAG, Log.getCurrentMethodName());
			
			this.repository = new Repository();
			initialized = true;
			
			maybeStart();
			onRepositoryLoaded();
		}else{
			controller.onEvent(new ShowNoRepositoryEvent());
		}
	}
	
	public void onRepositoryConfigurationUpdated(){
		initialize();
	}
	
	public void refreshView(){
		onRepositoryLoaded();
	}
	
	private void onRepositoryLoaded(){
    	RepositoryConfiguration config = Configuration.getInstance().getRepositoryConfiguration();
    	if(!config.isInitialized()) return;
    	
    	String rootPath = config.getRepositoryAbsolutePath();
    	if(TextUtils.isEmpty(rootPath)) return;
    	
    	File rootDir = new File(rootPath);
    	if(rootDir == null || !rootDir.exists()) return;
    	
    	controller.onEvent(new ShowRepositoryEvent(rootDir, repository.getWorkingCopy().getVersionedFiles(rootDir)));
    }
	
	public boolean isConfigurationInitialized() {
		return Configuration.getInstance().isInitialized();
	}
	
	public void start() {
		Log.o(TAG, Log.getCurrentMethodName());
		
		canStart = true;
		maybeStart();
	}
	
	public void maybeStart() {
		if (initialized && canStart) {
			Log.o(TAG, Log.getCurrentMethodName());
			
			mailbox.startThreads();
		}
	}
	
	public void stop() {
		canStart = false;
		mailbox.stopThreads();
	}
	
	public Mailbox getMailbox() {
		return mailbox;
	}
	
	public Repository getRepository() {
		return repository;
	}
	
}
