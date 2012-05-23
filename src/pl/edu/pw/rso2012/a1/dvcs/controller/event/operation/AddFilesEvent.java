/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.event.operation;

import java.util.Set;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

public class AddFilesEvent extends ApplicationEvent
{
    private final Set<String> filesToAdd;

    /*
     * Event posiada w sobie dane potrzebne do jego obsłużenia
     * W tym przypadku jest to lista nowych plików (ścieżka+nazwa z rozszerzeniem)
     */
    public AddFilesEvent(final Set<String> filesToAdd)
    {
        this.filesToAdd = filesToAdd;
    }

    public Set<String> getFilesToAdd()
    {
        return filesToAdd;
    }

}
