/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation;

import java.util.Set;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

public class CommitFilesEvent extends ApplicationEvent
{
    private final Set<String> filesToCommit;

    public CommitFilesEvent(final Set<String> filesToCommit)
    {
        this.filesToCommit = filesToCommit;
    }

    public Set<String> getFilesToCommit()
    {
        return filesToCommit;
    }

}
