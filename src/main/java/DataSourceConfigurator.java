import java.unit.Properties;

public class DataSourceConfigurator {

    Properties static final properties = new  Properties();
 
    private String autoCommit = System.getProperty(“autoCommit”, “true”);
    private String connTimeout = System.getProperty(“connTimeout”, “300000”);
    private String idleTimeout = System.getProperty(“idleTimeout”, “600000”);
    private String maxLifetime = System.getProperty(“maxLifetime”, “1800000”);
    private String minimumIdle = System.getProperty(“minimumIdle”, “2”);
    private static String maxPoolSize = System.getProperty(“maxPoolSize”, “12”);

    public DataSourceConfigurator() {
       properties.setProperty("autoCommit", autoCommit);
       properties.setProperty("connectionTimeout", connTimeout);
       properties.setProperty("idleTimeout", idleTimeout); 
       properties.setProperty("maxLifetime", maxLifetime); 
       properties.setProperty("minimumIdle", minimumIdle); 
       properties.setProperty("maximumPoolSize", maxPoolSize);
    } 
 
    public Properties getConfiguration() {
       return properties;   
    }
}
