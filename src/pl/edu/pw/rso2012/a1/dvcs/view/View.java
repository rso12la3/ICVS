package pl.edu.pw.rso2012.a1.dvcs.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.application.ExitEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.AddFilesEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.CommitFilesEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.CreateEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.DeleteFilesEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.CloneRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.PullRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.PushRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.file.File;
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				ApplicationEvent event = new ExitEvent();
				mController.onEvent(event);
			}
		});
		setMinimumSize(new Dimension(Constants.WINDOW_MIN_WIDTH, Constants.WINDOWS_MIN_HEIGHT));
		
		WindowUtils.setWindowSizeAndLocation(this, Toolkit.getDefaultToolkit().getScreenSize(),
				Constants.WINDOW_TO_SCREEN_SIZE);
		
		mMenuBar = new MenuBarComp();
		mMenuBar.setListener(mMenuBarListener);
		mMenuBar.setEnabled(true);
		setJMenuBar(mMenuBar);
		
		pack();
		setVisible(true);
	}
	
	public void setController(Controller controller) {
		if (controller == null) throw new NullPointerException();
		
		mController = controller;
	}
	
	public void showSelectRepositoryView(List<String> repositories) {
		Log.o(TAG, Log.getCurrentMethodName());
		
		mMenuBar.setEnabled(true);
	}
	
	public void showRepositoryFolderView(List<File> files, String repositoryName) {
		Log.o(TAG, Log.getCurrentMethodName());
		
		mMenuBar.setEnabled(true);
	}
	
	/*************** MENU LISTENER ***************/
	
	// TODO: refactor invoke later commands
	
	private MenuBarListener mMenuBarListener = new MenuBarListenerAdapter() {
		
		private final String TAG = MenuBarListener.class.getSimpleName();
		
		@Override
		public void onCreateRepositoryClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			Runnable command = new Runnable() {
				@Override
				public void run() {
					CreateRepositoryPane repositoryPane = new CreateRepositoryPane();
					int ret = repositoryPane.showDialog(View.this, "Create");
					switch (ret) {
					case CreateRepositoryPane.APPROVE_OPTION:
						ApplicationEvent event = new CreateEvent(repositoryPane.getUsername(),
								repositoryPane.getPassword(), repositoryPane.getDirectory());
						mController.onEvent(event);
						break;
					}
				}
			};
			
			SwingUtilities.invokeLater(command);
		}
		
		@Override
		public void onCloneRepositoryClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			Runnable command = new Runnable() {
				@Override
				public void run() {
					CreateRepositoryPane repositoryPane = new CreateRepositoryPane();
					int ret = repositoryPane.showDialog(View.this, "Clone");
					switch (ret) {
					case CreateRepositoryPane.APPROVE_OPTION:
						ApplicationEvent event = new CloneRequestEvent(repositoryPane.getUsername());
						mController.onEvent(event);
						break;
					}
				}
			};
			
			SwingUtilities.invokeLater(command);
		}
		
		@Override
		public void onCloseRepositoryClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
		}
		
		@Override
		public void onExitClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			ApplicationEvent event = new ExitEvent();
			mController.onEvent(event);
		}
		
		@Override
		public void onPullClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			Runnable command = new Runnable() {
				@Override
				public void run() {
					CreateRepositoryPane repositoryPane = new CreateRepositoryPane();
					int ret = repositoryPane.showDialog(View.this, "Clone");
					switch (ret) {
					case CreateRepositoryPane.APPROVE_OPTION:
						ApplicationEvent event = new PullRequestEvent(repositoryPane.getUsername());
						mController.onEvent(event);
						break;
					}
				}
			};
			
			SwingUtilities.invokeLater(command);
		}
		
		@Override
		public void onPushClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			Runnable command = new Runnable() {
				@Override
				public void run() {
					CreateRepositoryPane repositoryPane = new CreateRepositoryPane();
					int ret = repositoryPane.showDialog(View.this, "Clone");
					switch (ret) {
					case CreateRepositoryPane.APPROVE_OPTION:
						ApplicationEvent event = new PushRequestEvent(repositoryPane.getUsername());
						mController.onEvent(event);
						break;
					}
				}
			};
			
			SwingUtilities.invokeLater(command);
		}
		
		@Override
		public void onCommitClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			ApplicationEvent event = new CommitFilesEvent(new ArrayList<String>());
			mController.onEvent(event);
		}
		
		@Override
		public void onAddClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			ApplicationEvent event = new AddFilesEvent(new ArrayList<String>());
			mController.onEvent(event);
		}
		
		@Override
		public void onDeleteClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			ApplicationEvent event = new DeleteFilesEvent(new ArrayList<String>());
			mController.onEvent(event);
		}
		
	};
}
