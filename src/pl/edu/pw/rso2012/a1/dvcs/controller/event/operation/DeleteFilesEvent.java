/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation;

import java.util.Set;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

public class DeleteFilesEvent extends ApplicationEvent
{
    private final Set<String> filesToDelete;

    public DeleteFilesEvent(final Set<String> filesToDelete)
    {
        this.filesToDelete = filesToDelete;
    }

    public Set<String> getFilesToDeleteList()
    {
        return filesToDelete;
    }

}
