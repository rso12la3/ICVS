/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation;

import java.util.List;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

public class CommitFilesEvent extends ApplicationEvent
{
    private final List<String> filesToCommitList;

    public CommitFilesEvent(final List<String> filesToCommitList)
    {
        this.filesToCommitList = filesToCommitList;
    }

    public List<String> getFilesToCommitList()
    {
        return filesToCommitList;
    }

}
