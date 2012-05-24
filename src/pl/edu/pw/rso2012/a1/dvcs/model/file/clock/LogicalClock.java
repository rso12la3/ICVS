/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.file.clock;

import java.util.HashMap;
import java.util.Map;

import pl.edu.pw.rso2012.a1.dvcs.model.exception.ModelMethodNotImplementedException;

/**
 * @author Grzegorz Sancewicz
 * 
 */
public class LogicalClock {
	public enum CompareResult {
		EQUAL, LESS, GREATER, UNKNOWN
	}

	private final Map<String, Integer> versionVector;

	public LogicalClock() {
		versionVector = new HashMap<String, Integer>();
	}

	public Integer getVersion(final String email) {
		return versionVector.get(email);
	}

	public void setVersion(final String email, final Integer versionNumber) {
		versionVector.put(email, versionNumber);
	}

	public Map<String, Integer> getVersionVector() {
		return versionVector;
	}

	public void increment(final String email) {
		int i = versionVector.get(email);
		i++;
		versionVector.put(email, i);
	}

	/*
	 * Zegary rowne gdy wszystkie elementy sa rowne
	 * A < B gdy wszystkie elementy z A < B
	 * A > B gdy wszystkie elementy z A > B
	 */
	public CompareResult compare(final LogicalClock clock) {
		boolean isGreater = true;
		boolean isLess = true;
		boolean isEqual = true;
		Map<String, Integer> vectorToCompare = clock.getVersionVector();

		if (vectorToCompare.size() != versionVector.size())
			return CompareResult.UNKNOWN;

		//można by to sprytniej zrobić...
		for (String key : versionVector.keySet()) {
			if (versionVector.get(key) > vectorToCompare.get(key)){
				isGreater = false;
				isEqual= false;
			}
			else if (versionVector.get(key) < vectorToCompare.get(key)){
				isLess = false;
				isEqual= false;
			}
				
		}

		if (isGreater && !isLess & !isEqual)
			return CompareResult.GREATER;
		else if (!isGreater && isLess & !isEqual)
			return CompareResult.LESS;
		else
			return CompareResult.EQUAL;
	}
}
