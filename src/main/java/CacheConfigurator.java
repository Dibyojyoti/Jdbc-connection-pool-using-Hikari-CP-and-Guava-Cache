public class CacheConfigurator {

    private String cacheMaxSize = System.getProperty(“cacheMaxSize”, “true”);
    private String cacheExpTime = System.getProperty(“cacheExpTime”, “300000”);
    private String cacheExpTimeUnit = System
.getProperty(“cacheExpTimeUnit”, “HOURS”);
 
    private int maximumSize;
    private long expireAfterWrite;

    public CacheConfigurator() {
      maximumSize = Integer.parseInt(cacheMaxSize);
      expireAfterWrite= Long.parseLong(cacheExpTime);
    }  
    public int getMaximumSize() {return maximumSize;}
    public long getExpireAfterWrite() {return expireAfterWrite;}
    public String getTimeUnit() {return cacheExpTimeUnit ;}
}
