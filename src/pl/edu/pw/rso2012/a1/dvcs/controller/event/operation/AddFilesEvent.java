/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation;

import java.util.List;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

public class AddFilesEvent extends ApplicationEvent
{
    private final List<String> filesToAddList;

    public AddFilesEvent(final List<String> filesToAddList)
    {
        this.filesToAddList = filesToAddList;
    }

    public List<String> getFilesToAddList()
    {
        return filesToAddList;
    }

}
