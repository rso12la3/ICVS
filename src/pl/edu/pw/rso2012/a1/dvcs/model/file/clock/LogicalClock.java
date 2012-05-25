/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.file.clock;

import java.util.HashMap;
import java.util.Map;

import pl.edu.pw.rso2012.a1.dvcs.model.exception.ModelMethodNotImplementedException;

/**
 * @author Grzegorz Sancewicz && Oskar Leszczynski(compare, merge, createMissingValue)
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
	 * A < B gdy choc jeden element A jest mniejszy od B a reszta jest rowna
	 * A < B gdy choc jeden element A jest wiekszy od B a reszta jest rowna
	 * wynik GREATER mowi, ze obcy zegar jest wiekszy w stosunku do naszego
	 */
	public CompareResult compare(final LogicalClock clock)
	{
		boolean isGreater = true;
		boolean isLess = true;
		
		Integer compareValue;
		Map<String, Integer> vectorToCompare = clock.getVersionVector();

		this.createMissingValue(clock);
		

		for (String key : versionVector.keySet())
		{
			compareValue = vectorToCompare.get(key);
			if (compareValue == null)
			{
				isGreater = false;
			}
			else
			{
				if (versionVector.get(key) > compareValue)
				{
					isGreater = false;
				}
				else if (versionVector.get(key) < vectorToCompare.get(key))
				{
					isLess = false;
				}
			}
		}
		
		if (isGreater && isLess)
			return CompareResult.EQUAL;
		else if (isGreater && !isLess)
			return CompareResult.GREATER;
		else if (!isGreater && isLess)
			return CompareResult.LESS;
		else
			return CompareResult.UNKNOWN;
	}
	
	//normalizacja zegara
	//tworzy brakujace pola
	//jesli w naszym zegarze nie ma danego pola to jest dodawane
	//z wartoscia 0
	public void createMissingValue(LogicalClock otherClock)
	{
		Map<String, Integer> otherVector = otherClock.getVersionVector();
		Integer value;
		
		for (String key : otherVector.keySet())
		{
			value = versionVector.get(key);
			if (value == null)
			{
				value = new Integer(0);
				versionVector.put(key, value);
			}
		}
	}
	
	//scala dwa zegary po scaleniu plikow
	//wykonywac po kazdym merge na pliku
	//nie wymaga znormalizowanych zegarow
	public void merge(LogicalClock otherClock)
	{
		Map<String, Integer> otherVector = otherClock.getVersionVector();
		Integer value, otherValue;
		
		for (String key : otherVector.keySet())
		{
			otherValue = otherVector.get(key);
			value = versionVector.get(key);
			if (value == null || otherValue > value)
			{
				versionVector.put(key, new Integer(otherValue));
			}
		}
	}
}
