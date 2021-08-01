import java.sql.Connection;
import java.util.Properties;
import java.util.concurrent.Callable;
import javax.sql.DataSource;
import java.unit.concurrent.TimeUnit; 
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcManager {
  private Cache<DataSourcekey, DataSource> hikariDsCache;
  private final Properties hikariDsConfig;

  public JdbcManager(DataSourceConfigurator dsc, CacheConfigurator cc) {
    hikariDsCache = CacheBuilder.newBuilder()
    .maximumSize(cc.getMaximumSize())
                                     .expireAfterWrite(cc.getExpireAfterWrite(),
TimeUnit.valueOf(cc.getTimeUnit()))
                                     .build();
    hikariDsConfig = dsc.getConfiguration();
  }
  public Connection getConnection(String clientID, String dbHostUrl, 
String userName,String password) throws RuntimeException {
        DataSourceKey dataSourceKey = new DataSourceKey(clientId, dbHostUrl,
  userName, password);
        try {
           DataSource dataSouce = 
  hikariDsCache.get(dataSourceKey,
               createDataSource(dataSourceKey, hikariDsConfig));
           return dataSource.getConnection();
        } catch (Exception e) { 
          throw new RuntimeException(e.getMessage());
        }
  }
  Callable <DataSource> createDataSource(DataSourceKey dataSourceKey,
                                         Properties config) {
        return new Callable<DataSource>() {
          @Override
          public DataSource call() throws Exception {
              String url = dataSourceKey.getConnProps().getProperty("jdbc-url");
     String user = dataSourceKey.getConnProps().getProperty("user");
     String pass = dataSourceKey.getConnProps().getProperty("password"); 
              HikariConfig config = new HikariConfig(config);
              config.setPoolName(dataSourceKey.getKey());
              config.setJdbcUrl(url);
              config.setUsername(user); 
              config.setPassword(pass);  
              DataSource dataSource = new HikariDataSource(config);
              return  dataSource;
          }
        };
  }
}
