package pl.edu.pw.rso2012.a1.dvcs.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.application.ExitEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.AddFilesEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.CommitFilesEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.CreateEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.DeleteFilesEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.UpdateEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.PullRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.PushRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.utils.Log;
import pl.edu.pw.rso2012.a1.dvcs.view.WaitbarDialog.WaitbarListener;
import pl.edu.pw.rso2012.a1.dvcs.view.menu.MenuBarComp;
import pl.edu.pw.rso2012.a1.dvcs.view.menu.MenuBarListener;
import pl.edu.pw.rso2012.a1.dvcs.view.menu.MenuBarListenerAdapter;
import pl.edu.pw.rso2012.a1.dvcs.view.tree.FoldersTreeComp;
import pl.edu.pw.rso2012.a1.dvcs.view.tree.FoldersTreeComp.FoldersTreeActionListener;
import pl.edu.pw.rso2012.a1.dvcs.view.utils.TextUtils;
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
	FoldersTreeComp mFoldersTree;
	WaitbarListener mWaitbarListener;
	
	public View(Controller controller) {
		super(Constants.APP_NAME);
		
		if (controller == null) throw new NullPointerException();
		mController = controller;
		
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
		mMenuBar.setEnabled(false);
		setJMenuBar(mMenuBar);
		
		mFoldersTree = new FoldersTreeComp();
		mFoldersTree.setListener(mTreeListener);
		getContentPane().add(mFoldersTree.getScrollPane(), BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
	
	public void showNoRepositoryView() {
		Log.o(TAG, Log.getCurrentMethodName());
		
		mMenuBar.setEnabledCreateRepository();
	}
	
	public void showRepositoryFolderView(File rootDirectory, List<String> versionedFilePaths) {
		Log.o(TAG, Log.getCurrentMethodName());
		
		mMenuBar.setEnabledRepositoryCreated();
		mFoldersTree.setFiles(rootDirectory, versionedFilePaths);
	}
	
	public void onUpdateComplete() {
		Log.o(TAG, Log.getCurrentMethodName());
		
		Runnable command = new Runnable() {
			@Override
			public void run() {
				if (mWaitbarListener != null) {
					mWaitbarListener.hide();
					mWaitbarListener = null;
				}
			}
		};
		
		SwingUtilities.invokeLater(command);
	}
	
	/*************** TREE LISTENER ***************/
	
	FoldersTreeComp.FoldersTreeActionListener mTreeListener = new FoldersTreeActionListener() {
		
		@Override
		public void onDelete(Set<String> files) {
			mController.onEvent(new DeleteFilesEvent(files));
		}
		
		@Override
		public void onCommit(Set<String> files) {
			mController.onEvent(new CommitFilesEvent(files));
		}
		
		@Override
		public void onAdd(Set<String> files) {
			mController.onEvent(new AddFilesEvent(files));
		}
	};
	
	/*************** MENU LISTENER ***************/
	
	private MenuBarListener mMenuBarListener = new MenuBarListenerAdapter() {
		
		private final String TAG = MenuBarListener.class.getSimpleName();
		
		@Override
		public void onCreateRepositoryClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			Runnable command = new Runnable() {
				@Override
				public void run() {
					CreateRepositoryPane repositoryPane = new CreateRepositoryPane();
					int ret = repositoryPane.showDialog(View.this);
					switch (ret) {
					case CreateRepositoryPane.APPROVE_OPTION:
						ApplicationEvent event = new CreateEvent(repositoryPane.getEmailAddress(),
								repositoryPane.getPassword(), repositoryPane.getBaseDirectory());
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
					String email = (String) JOptionPane.showInputDialog(View.this, "Type email address to clone:",
							"Clone", JOptionPane.PLAIN_MESSAGE, null, null, null);
					
					if (!TextUtils.isEmpty(email)) {
						ApplicationEvent event = new PullRequestEvent(email);
						mController.onEvent(event);
					}
				}
			};
			
			SwingUtilities.invokeLater(command);
		}
		
		@Override
		public void onPullClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			Runnable command = new Runnable() {
				@Override
				public void run() {
					String email = (String) JOptionPane.showInputDialog(View.this, "Type email address to pull from:",
							"Pull", JOptionPane.PLAIN_MESSAGE, null, null, null);
					
					if (!TextUtils.isEmpty(email)) {
						ApplicationEvent event = new PullRequestEvent(email);
						mController.onEvent(event);
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
					String email = (String) JOptionPane.showInputDialog(View.this, "Type email address to push to:",
							"Push", JOptionPane.PLAIN_MESSAGE, null, null, null);
					
					if (!TextUtils.isEmpty(email)) {
						ApplicationEvent event = new PushRequestEvent(email);
						mController.onEvent(event);
					}
				}
			};
			
			SwingUtilities.invokeLater(command);
		}
		
		@Override
		public void onUpdateClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			Runnable command = new Runnable() {
				@Override
				public void run() {
					String revision = (String) JOptionPane.showInputDialog(View.this, "Update to revision number:",
							"Update", JOptionPane.PLAIN_MESSAGE, null, null, null);
					
					if (!TextUtils.isEmpty(revision)) {
						ApplicationEvent event = new UpdateEvent(revision);
						mController.onEvent(event);
						
						mWaitbarListener = WaitbarDialog.showDialog(View.this, "Update",
								String.format("Updatint to revision: %s", revision));
						mWaitbarListener.show();
					}
				}
			};
			
			SwingUtilities.invokeLater(command);
		}
		
		@Override
		public void onExitClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			ApplicationEvent event = new ExitEvent();
			mController.onEvent(event);
		}
		
		@Override
		public void onCommitClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			ApplicationEvent event = new CommitFilesEvent(null);
			mController.onEvent(event);
		}
		
		@Override
		public void onAddClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			ApplicationEvent event = new AddFilesEvent(null);
			mController.onEvent(event);
		}
		
		@Override
		public void onDeleteClicked() {
			Log.o(TAG, Log.getCurrentMethodName());
			
			ApplicationEvent event = new DeleteFilesEvent(null);
			mController.onEvent(event);
		}
		
	};
	
	public void showMessageDialogInformation(final String message) {
		showMessageDialog(message, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showMessageDialogWarning(final String message) {
		showMessageDialog(message, "Warning", JOptionPane.WARNING_MESSAGE);
	}
	
	public void showMessageDialogError(final String message) {
		showMessageDialog(message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void showMessageDialog(final String message, final String title, final int messageType) {
		Runnable command = new Runnable() {
			@Override
			public void run() {
				JOptionPane.showMessageDialog(View.this, message, title, messageType);
			}
		};
		
		SwingUtilities.invokeLater(command);
	}
}
