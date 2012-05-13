/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.file.diff;

import pl.edu.pw.rso2012.a1.dvcs.model.exception.ConflictsResolveErrorException;
import pl.edu.pw.rso2012.a1.dvcs.model.exception.FileMergeErrorException;
import pl.edu.pw.rso2012.a1.dvcs.model.exception.ModelMethodNotImplementedException;
import pl.edu.pw.rso2012.a1.dvcs.model.file.File;

/**
 * @author Grzegorz Sancewicz
 * @email g.sancewicz@stud.elka.pw.edu.pl
 * 
 */
public class FileComparator
{
    public static boolean compareFiles(final File sourceFile, final File destinationFile) 
        throws ModelMethodNotImplementedException
    {
        throw new ModelMethodNotImplementedException();
    }
    public static boolean mergeFiles(final File sourceFile, final File destinationFile) 
        throws ModelMethodNotImplementedException, FileMergeErrorException
    {
        throw new ModelMethodNotImplementedException();
    }
    public static boolean resolveConflicts(final File sourceFile, final File destinationFile) 
        throws ModelMethodNotImplementedException, ConflictsResolveErrorException
    {
        throw new ModelMethodNotImplementedException();
    }
}
