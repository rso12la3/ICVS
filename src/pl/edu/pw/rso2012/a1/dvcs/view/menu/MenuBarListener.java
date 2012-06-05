package pl.edu.pw.rso2012.a1.dvcs.view.menu;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public interface MenuBarListener {
	void onCreateRepositoryClicked();
	
	void onCloneRepositoryClicked();
	
	void onExitClicked();
	
	void onUpdateClicked();
	
	void onUpdateToHeadClicked();
	
	void onPullClicked();
	
	void onPushClicked();
	
	void onCommitClicked();
	
	void onAddClicked();
	
	void onDeleteClicked();
	
	void onRefreshClicked();
}
