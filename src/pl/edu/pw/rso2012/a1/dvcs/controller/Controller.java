package pl.edu.pw.rso2012.a1.dvcs.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowNoRepositoryEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.view.ShowRepositoryEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.HandlerNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.controller.exception.NoHandlerException;
import pl.edu.pw.rso2012.a1.dvcs.controller.handler.ApplicationHandler;
import pl.edu.pw.rso2012.a1.dvcs.controller.mapper.HandlerMap;
import pl.edu.pw.rso2012.a1.dvcs.model.Model;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.Configuration;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.RepositoryConfiguration;
import pl.edu.pw.rso2012.a1.dvcs.view.View;

public class Controller {
	
	private static final String TAG = Controller.class.getSimpleName();
	
	// FIXME: temporary for view debugging
	private static final boolean MODEL_ACTIVE = false;
	
	private final Model model;
	private final View view;
	private final LinkedBlockingDeque<ApplicationEvent> eventQueue;
	private boolean applicationEnd = false;
	
	private final HandlerMap handlerMap;
	
	public Controller() {
		handlerMap = new HandlerMap(this);
		eventQueue = new LinkedBlockingDeque<ApplicationEvent>();
		model = new Model(eventQueue);
		view = new View(this);
		
		Configuration configuration = Configuration.getInstance();
		RepositoryConfiguration repoConfig = configuration.getRepositoryConfiguration();
		String rootDirAbsolutePath = repoConfig.getRepositoryAbsolutePath();
		File rootDir = null;
		if(rootDirAbsolutePath != null)
			rootDir = new File(rootDirAbsolutePath);
		if(rootDir != null){
			onEvent(new ShowRepositoryEvent(rootDir, null));
		}else{
			onEvent(new ShowNoRepositoryEvent());
		}
		
//		// FIXME: event should be sent based on configuration - this is just for
//		// testing
//		boolean test_IsRepositoryCreated = true;
//		//java.io.File test_RootDir = new java.io.File(".");
//		java.io.File test_RootDir = new java.io.File("C:\\android-sdk\\tools");
//		List<String> test_Versioned = new ArrayList<String>();
//		test_Versioned.add("Config.xml");
//		test_Versioned.add("src\\pl\\edu\\pw\\rso2012\\a1\\dvcs\\Dvcs.java");
//		test_Versioned.add("android.bat");
//		test_Versioned.add("adb_has_moved.txt");
//		
//		
//		if (test_IsRepositoryCreated) {
//			onEvent(new ShowRepositoryEvent(test_RootDir, test_Versioned));
//		} else {
//			onEvent(new ShowNoRepositoryEvent());
//		}
	}
	
	public void run() {
		if (MODEL_ACTIVE) model.start();
		
		while (!isApplicationEnd()) {
			try {
				final ApplicationEvent event = eventQueue.take();
				final ApplicationHandler applicationHandler = handlerMap.get(event);
				applicationHandler.handle(event);
			}
			catch (final InterruptedException e) {
				e.printStackTrace();
				endApplication();
			}
			catch (final NoHandlerException e) {
				e.printStackTrace();
			}
			catch (final HandlerNotImplementedException e) {
				e.printStackTrace();
			}
			
		}
		view.dispose();
		
		if (MODEL_ACTIVE) model.stop();
	}
	
	public View getView() {
		return view;
	}
	
	public Model getModel() {
		return model;
	}
	
	private boolean isApplicationEnd() {
		return applicationEnd;
	}
	
	public void endApplication() {
		this.applicationEnd = true;
	}
	
	public LinkedBlockingDeque<ApplicationEvent> getEventQueue() {
		return eventQueue;
	}
	
	public void onEvent(ApplicationEvent event) {
		eventQueue.offer(event);
	}
}
