/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.communication;

import pl.edu.pw.rso2012.a1.dvcs.model.operation.CommitOperation;

/**
 * TODO: Zmienić pakiet
 * Napisac klasę :)
 * 
 */
public class Commit
{
    private final CommitOperation commitOperation;
    private final String revision;
    
    public Commit(CommitOperation commitOperation, String revision)
    {
        this.commitOperation = commitOperation;
        this.revision = revision;
    }

    public CommitOperation getCommitOperation()
    {
        return commitOperation;
    }

    public String getRevision()
    {
        return revision;
    }

}
