/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation;

import java.util.List;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

public class DeleteFilesEvent extends ApplicationEvent
{
    private final List<String> filesToDeleteList;

    public DeleteFilesEvent(final List<String> filesToDeleteList)
    {
        this.filesToDeleteList = filesToDeleteList;
    }

    public List<String> getFilesToDeleteList()
    {
        return filesToDeleteList;
    }

}
