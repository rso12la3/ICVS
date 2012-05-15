/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.file;

import pl.edu.pw.rso2012.a1.dvcs.model.file.clock.LogicalClock;

/**
 * @author Grzegorz Sancewicz
 * 
 */
public class File
{
    private final String name;
    private Long size;
    private final LogicalClock clock;
    
    public File(final String name)
    {
        final java.io.File file = new java.io.File(name);
        this.name = name;
        this.size = file.length();
        this.clock = new LogicalClock();
    }

    /**
     * @return the size
     */
    public Long getSize()
    {
        return size;
    }
    
    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return the clock
     */
    public LogicalClock getClock()
    {
        return clock;
    }
    
    
    
}
