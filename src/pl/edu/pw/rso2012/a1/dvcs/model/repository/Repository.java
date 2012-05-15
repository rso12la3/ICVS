/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.repository;


/**
 * @author Grzegorz Sancewicz
 * 
 */
public class Repository
{
    private String absolutePath;
    
    public Repository(final String absolutePath)
    {
        this.absolutePath = absolutePath;
    }

    public String getAbsolutePath()
    {
        return absolutePath;
    }

    public void setAbsolutePath(final String absolutePath)
    {
        this.absolutePath = absolutePath;
    }

}
