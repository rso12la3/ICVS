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
public class LogicalClock 
{
    public enum CompareResult
    {
        EQUAL,
        LESS,
        GREATER,
        UNKNOWN
    }
    private final Map<String, Integer> versionVector;
    
    public LogicalClock()
    {
        versionVector = new HashMap<String, Integer>();
    }

    public Integer getVersion(final String email)
    {
        return versionVector.get(email);
    }
    
    public void setVersion(final String email, final Integer versionNumber)
    {
        versionVector.put(email, versionNumber);
    }
    
    public CompareResult compare(final LogicalClock clock) throws ModelMethodNotImplementedException
    {
        throw new ModelMethodNotImplementedException();
    }
}
