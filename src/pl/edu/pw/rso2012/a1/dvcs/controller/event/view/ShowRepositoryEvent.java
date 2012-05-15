package pl.edu.pw.rso2012.a1.dvcs.controller.event.view;

import java.util.List;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.file.File;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class ShowRepositoryEvent extends ApplicationEvent {

    private final List<File> mFiles;
    private final String mRepositoryName;

    public ShowRepositoryEvent(final List<File> files, final String repositoryName) {
        super();
        this.mFiles = files;
        this.mRepositoryName = repositoryName;
    }

    public List<File> getFiles() {
        return mFiles;
    }

    public String getRepositoryName() {
        return mRepositoryName;
    }
}
