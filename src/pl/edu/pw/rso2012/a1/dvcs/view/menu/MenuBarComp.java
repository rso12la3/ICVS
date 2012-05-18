package pl.edu.pw.rso2012.a1.dvcs.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import pl.edu.pw.rso2012.a1.dvcs.utils.Log;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class MenuBarComp extends JMenuBar {
	
	private static final long serialVersionUID = -1845447657509250494L;
	private static final String TAG = MenuBarComp.class.getSimpleName();
	
	protected static final String 
			ACTION_CREATE_REPOSITORY = "CREATEREPOSITORY",
			ACTION_CLONE_REPOSITORY = "CLONEREPOSITORY", 
			ACTION_EXIT = "EXIT",
			ACTION_UPDATE = "UPDATE",
			ACTION_PULL = "PULL", 
			ACTION_PUSH = "PUSH",
			ACTION_COMMIT = "COMMIT", 
			ACTION_ADD = "ADD",
			ACTION_DELETE = "DELETE";
	
	protected MenuBarListener mListener;
	protected JMenuItem mCreate, mClone, mCloseProject, mExit, mUpdate, mPull,
			mPush, mCommit, mAdd, mDelete;
	
	public MenuBarComp() {
		super();
		initialize();
	}
	
	protected void initialize() {
		Log.o(TAG, Log.getCurrentMethodName());
		
		JMenu repository = new JMenu("Repository");
		
		mCreate = new JMenuItem("Create repository");
		mCreate.setActionCommand(ACTION_CREATE_REPOSITORY);
		mCreate.addActionListener(mActionListener);
		repository.add(mCreate);
		
		mClone = new JMenuItem("Clone repository");
		mClone.setActionCommand(ACTION_CLONE_REPOSITORY);
		mClone.addActionListener(mActionListener);
		repository.add(mClone);
		
		repository.add(new JPopupMenu.Separator());
		
		mExit = new JMenuItem("Exit");
		mExit.setActionCommand(ACTION_EXIT);
		mExit.addActionListener(mActionListener);
		repository.add(mExit);
		
		this.add(repository);
		
		JMenu synchronization = new JMenu("Synchronization");
		
		mUpdate = new JMenuItem("Update");
		mUpdate.setActionCommand(ACTION_UPDATE);
		mUpdate.addActionListener(mActionListener);
		synchronization.add(mUpdate);
		
		mPull = new JMenuItem("Pull");
		mPull.setActionCommand(ACTION_PULL);
		mPull.addActionListener(mActionListener);
		synchronization.add(mPull);
		
		mPush = new JMenuItem("Push");
		mPush.setActionCommand(ACTION_PUSH);
		mPush.addActionListener(mActionListener);
		synchronization.add(mPush);
		
		synchronization.add(new JPopupMenu.Separator());
		
		mCommit = new JMenuItem("Commit");
		mCommit.setActionCommand(ACTION_COMMIT);
		mCommit.addActionListener(mActionListener);
		synchronization.add(mCommit);
		
		mAdd = new JMenuItem("Add");
		mAdd.setActionCommand(ACTION_ADD);
		mAdd.addActionListener(mActionListener);
		synchronization.add(mAdd);
		
		mDelete = new JMenuItem("Delete");
		mDelete.setActionCommand(ACTION_DELETE);
		mDelete.addActionListener(mActionListener);
		synchronization.add(mDelete);
		
		this.add(synchronization);
	}
	
	protected ActionListener mActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (mListener == null) return;
			
			switch (event.getActionCommand()) {
			case ACTION_CREATE_REPOSITORY:
				mListener.onCreateRepositoryClicked();
				break;
			case ACTION_CLONE_REPOSITORY:
				mListener.onCloneRepositoryClicked();
				break;
			case ACTION_EXIT:
				mListener.onExitClicked();
				break;
			case ACTION_UPDATE:
				mListener.onUpdateClicked();
				break;
			case ACTION_PULL:
				mListener.onPullClicked();
				break;
			case ACTION_PUSH:
				mListener.onPushClicked();
				break;
			case ACTION_COMMIT:
				mListener.onCommitClicked();
				break;
			case ACTION_ADD:
				mListener.onAddClicked();
				break;
			case ACTION_DELETE:
				mListener.onDeleteClicked();
				break;
			}
		}
	};
	
	public void setListener(MenuBarListener listener) {
		if (listener == null) throw new NullPointerException();
		
		mListener = listener;
	}
	
	public void setEnabledCreateRepository(){
		setEnabled(false);
		mCreate.setEnabled(true);
	}
	
	public void setEnabledRepositoryCreated(){
		setEnabled(true);
		mCreate.setEnabled(false);
	}
	
	public void setEnabled(boolean enabled) {
		mCreate.setEnabled(enabled);
		mClone.setEnabled(enabled);
		mExit.setEnabled(true); // allways enabled
		mUpdate.setEnabled(enabled);
		mPull.setEnabled(enabled);
		mPush.setEnabled(enabled);
		mCommit.setEnabled(enabled);
		mAdd.setEnabled(enabled);
		mDelete.setEnabled(enabled);
	}
	
}
