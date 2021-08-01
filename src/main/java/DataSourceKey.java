import java.unit.Properties;

public class DataSourceKey {
   private String clientID;
   private String dbHostUrl;
   private String userName;
   private String password;
   private Properties connProperties;  

   public DataSourceKey(String clientID,String dbHostUrl, String userName,
  String password) {
      this.clientID =  clientID;
      this.dbHostUrl = dbHostUrl;
      this.userName = userName;
      this.password = password;
      this.connProperties = generateConnProperties(dbHostUrl,userName,password);
   }
   //add getters
 
   public String getKey() { 
return clientID + "_" +  dbHostUrl + "_" +   userName;
   }
 
   private Properties generateConnProperties(String dbHostUrl, String userName, 
  String password ) {
      connectionProperties = new Properties();
      connectionProperties.setProperty("jdbc-url", dbHostUrl);
      connectionProperties.setProperty("user", userName ); 
      connectionProperties.setProperty("password", password );  
      return connectionProperties;
   }

 @Override
   public boolean equals(Object obj) {
       if(obj == null || !(obj instanceof DatasourceKey)) { return false; }
       if(obj == this) {return true};

       DataSourceKey foreignObject = (DataSourceKey) obj;
       if(foreignObject.getClientID().equals(this.clientID) &&
          foreignObject.getDbHostUrl().equals(this.dbHostUrl) &&
          foreignObject.getUserName().equals(this.userName) &&
          foreignObject.getPassword().equals(this.password) { 
            return true;
       } else {
            return false;
       } 
  }

  @Override 
  public int hashCode() {
       String hashString = this.clientID + this.dbHostUrl + 
this.userName +  this.password;
       return  hashString.hashCode();
  } 
}
