/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;

/**
 * @author Grzegorz Sancewicz
 * 
 */
public final class Configuration {
    
    private final static String CONFIG_FILE = "Config.xml";
    private final static XStream xstream = new XStream();
    private static volatile Configuration instance = null;
    /** Pola przechowujace konfiguracje */
    //Repozytorium
    final private RepositoryConfiguration repositoryConfiguration;
 
    public static Configuration getInstance() {
        if (instance == null) {
            synchronized (Configuration.class) {
                if (instance == null) {
                    prepareInstance();
                }
            }
        }
        return instance;
    }

    private static void prepareInstance()
    {
        FileInputStream fis;
        try
        {
            fis = new FileInputStream(CONFIG_FILE);
            prepareXStreamAliases();
            instance = (Configuration)xstream.fromXML(fis);
        }
        catch(FileNotFoundException e)
        {
            instance = new Configuration();
            instance.save();
        }
    }

    private static void prepareXStreamAliases()
    {
        xstream.alias("Configuration", Configuration.class);
        xstream.alias("RepositoryConfiguration", RepositoryConfiguration.class);
    }
    
    public void save()
    {
        FileOutputStream fs;
        try
        {
            fs = new FileOutputStream(CONFIG_FILE);
            xstream.toXML(this, fs);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
 
    private Configuration() {
        repositoryConfiguration = new RepositoryConfiguration();
    }

    public RepositoryConfiguration getRepositoryConfiguration()
    {
        return repositoryConfiguration;
    }

	public boolean isInitialized() {
		return repositoryConfiguration.isInitialized();
	}
 
}