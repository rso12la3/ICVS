package pl.edu.pw.rso2012.a1.dvcs.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.AddEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.CloneEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.CommitEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.CreateEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.DeleteEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.PullEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.PushEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.repository.Repository;
import pl.edu.pw.rso2012.a1.dvcs.utils.Log;
import pl.edu.pw.rso2012.a1.dvcs.view.menu.MenuBarComp;
import pl.edu.pw.rso2012.a1.dvcs.view.menu.MenuBarListener;
import pl.edu.pw.rso2012.a1.dvcs.view.menu.MenuBarListenerAdapter;
import pl.edu.pw.rso2012.a1.dvcs.view.utils.WindowUtils;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class View extends JFrame {
	
	private static final long serialVersionUID = 256169712369949332L;
	private static final String TAG = View.class.getSimpleName();
	//
	Controller mController;
	//
	MenuBarComp mMenuBar;
	
	public View() {
		super(Constants.APP_NAME);
		
		WindowUtils.setNativeLookAndFeel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(Constants.WINDOW_MIN_WIDTH,
				Constants.WINDOWS_MIN_HEIGHT));
		
		WindowUtils.setWindowSizeAndLocation(this, Toolkit.getDefaultToolkit()
				.getScreenSize(), Constants.WINDOW_TO_SCREEN_SIZE);
		
		mMenuBar = new MenuBarComp();
		mMenuBar.setListener(mMenuBarListener);
		mMenuBar.setEnabled(false);
		setJMenuBar(mMenuBar);
		
		pack();
		setVisible(true);
	}
	
	public void setController(Controller controller) {
		if (controller == null) throw new NullPointerException();
		
		mController = controller;
	}
	
	public void showSelectRepositoryView(List<Repository> repositories) {
		Log.o(TAG, Log.getCurrentMethodName());
		
		mMenuBar.setEnabled(true);
	}
	
	public void showRepositoryFolderView(Repository repository) {
		Log.o(TAG, Log.getCurrentMethodName());
		
		mMenuBar.setEnabled(true);
	}
	
	/*************** MENU LISTENER ***************/
	
	private MenuBarListener mMenuBarListener = new MenuBarListenerAdapter() {
		
		private final String TAG = MenuBarListener.class.getSimpleName();
		
		@Override
		public void onCreateRepositoryClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			CreateRepositoryPane repositoryPane = new CreateRepositoryPane();
			int ret = repositoryPane.showDialog(View.this, "Create");
			switch (ret) {
			case CreateRepositoryPane.APPROVE_OPTION:
				ApplicationEvent event = new CreateEvent(
						repositoryPane.getName(),
						repositoryPane.getServerUrl(),
						repositoryPane.getUsername(),
						repositoryPane.getPassword(),
						repositoryPane.getDirectory());
				mController.onEvent(event);
				break;
			}
		}
		
		@Override
		public void onCloneRepositoryClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			CreateRepositoryPane repositoryPane = new CreateRepositoryPane();
			int ret = repositoryPane.showDialog(View.this, "Clone");
			switch (ret) {
			case CreateRepositoryPane.APPROVE_OPTION:
				ApplicationEvent event = new CloneEvent(
						repositoryPane.getName(),
						repositoryPane.getServerUrl(),
						repositoryPane.getUsername(),
						repositoryPane.getPassword(),
						repositoryPane.getDirectory());
				mController.onEvent(event);
				break;
			}
		}
		
		@Override
		public void onCloseRepositoryClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
		}
		
		@Override
		public void onExitClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			dispose();
			System.exit(0);
		}
		
		@Override
		public void onPullClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			ApplicationEvent event = new PullEvent();
			mController.onEvent(event);
		}
		
		@Override
		public void onPushClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			ApplicationEvent event = new PushEvent();
			mController.onEvent(event);
		}
		
		@Override
		public void onCommitClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			ApplicationEvent event = new CommitEvent();
			mController.onEvent(event);
		}
		
		@Override
		public void onAddClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			ApplicationEvent event = new AddEvent();
			mController.onEvent(event);
		}
		
		@Override
		public void onDeleteClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			ApplicationEvent event = new DeleteEvent();
			mController.onEvent(event);
		}
		
	};
}
