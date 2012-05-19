/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation;

import java.util.Set;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

public class AddFilesEvent extends ApplicationEvent
{
    private final Set<String> filesToAdd;

    public AddFilesEvent(final Set<String> filesToAdd)
    {
        this.filesToAdd = filesToAdd;
    }

    public Set<String> getFilesToAdd()
    {
        return filesToAdd;
    }

}
