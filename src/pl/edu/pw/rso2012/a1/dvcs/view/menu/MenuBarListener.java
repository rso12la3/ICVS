package pl.edu.pw.rso2012.a1.dvcs.view.menu;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public interface MenuBarListener {
	void onCreateRepositoryClicked();
	
	void onCloneRepositoryClicked();
	
	void onCloseRepositoryClicked();
	
	void onExitClicked();
	
	void onPullClicked();
	
	void onPushClicked();
	
	void onCommitClicked();
	
	void onAddClicked();
	
	void onDeleteClicked();
}
