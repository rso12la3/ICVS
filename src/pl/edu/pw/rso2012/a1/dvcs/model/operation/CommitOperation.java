package pl.edu.pw.rso2012.a1.dvcs.model.operation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Oskar Leszczynski
 * 
 */

public class CommitOperation extends AbstractOperation
{
	private HashMap<String, ChangeData> commitMap;

	
	public CommitOperation(HashMap<String, ChangeData> commitMap) {
		super();
		this.commitMap = commitMap;
	}

	public HashMap<String, ChangeData> getCommitMap() {
		return commitMap;
	}

	public void setCommitMap(HashMap<String, ChangeData> commitMap) {
		this.commitMap = commitMap;
	}
	
	
//	diffMap kluczem jest nazwa pliku, ca�o�� zawiera struktur� z nazwa(?), zegarem, diffem,
	//sprawdzamy czy mapa zawiera dany plik
	//jesli nie to olewamy
	// jesli tak to wykonujemy odpowiednie operacje
	
	
}
