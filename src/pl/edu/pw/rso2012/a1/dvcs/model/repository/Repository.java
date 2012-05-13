/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.repository;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Grzegorz Sancewicz
 * @email g.sancewicz@stud.elka.pw.edu.pl
 * 
 */
public class Repository
{
    private String name;
    private String absolutePath;
    private final List<Commit> commits;
    public Repository(final String name, final String absolutePath)
    {
        this.name = name;
        this.absolutePath = absolutePath;
        this.commits = new LinkedList<Commit>();
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(final String name)
    {
        this.name = name;
    }
    /**
     * @return the absolutePath
     */
    public String getAbsolutePath()
    {
        return absolutePath;
    }
    /**
     * @param absolutePath the absolutePath to set
     */
    public void setAbsolutePath(final String absolutePath)
    {
        this.absolutePath = absolutePath;
    }

    /**
     * @return the commits
     */
    public List<Commit> getCommits()
    {
        return commits;
    }
}
