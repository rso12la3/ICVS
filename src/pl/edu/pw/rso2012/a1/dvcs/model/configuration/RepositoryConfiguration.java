/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.configuration;

import pl.edu.pw.rso2012.a1.dvcs.view.utils.TextUtils;

/**
 * @author Grzegorz Sancewicz
 * 
 */
public class RepositoryConfiguration
{
    private String repositoryAddress;
    private String repositoryPass;
    private String repositoryAbsolutePath;

    public void setRepositoryAddress(String repositoryAddress)
    {
        this.repositoryAddress = repositoryAddress;
    }

    public String getRepositoryAddress()
    {
        return repositoryAddress;
    }

    public String getRepositoryPass()
    {
        return repositoryPass;
    }

    public void setRepositoryPass(String repositoryPass)
    {
        this.repositoryPass = repositoryPass;
    }

    public String getRepositoryAbsolutePath()
    {
        return repositoryAbsolutePath;
    }

    public void setRepositoryAbsolutePath(String repositoryAbsolutePath)
    {
        this.repositoryAbsolutePath = repositoryAbsolutePath;
    }
    
    public boolean isInitialized(){
    	return (!TextUtils.isEmpty(repositoryAddress) && !TextUtils.isEmpty(repositoryPass) && !TextUtils.isEmpty(repositoryAbsolutePath));
    }
}
