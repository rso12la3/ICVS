/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.repository;

import java.util.HashSet;
import java.util.Set;

import pl.edu.pw.rso2012.a1.dvcs.model.file.File;

/**
 * @author Grzegorz Sancewicz
 * @email g.sancewicz@stud.elka.pw.edu.pl
 * 
 */
public class Commit
{
    private final String author;
    private final String comment;
    private final Set<File> files;
    
    public Commit(final String author, final String comment)
    {
        this.author = author;
        this.comment = comment;
        files = new HashSet<File>();
    }

    /**
     * @return the author
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * @return the comment
     */
    public String getComment()
    {
        return comment;
    }

    /**
     * @return the files
     */
    public Set<File> getFiles()
    {
        return files;
    }
}
