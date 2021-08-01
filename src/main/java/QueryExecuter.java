public class QueryExecuter {

   private  final JdbcManager  jdbcManager;
   private static final DataSourceConfigurator dsc = 
new DataSourceConfigurator();
   private static final CacheConfigurator cc = new CacheConfigurator();
   public  QueryExecuter() { 
    this.jdbcManager =  new JdbcManager(dcs, cc) ; 
   }
 
   public Employees getResult(String clientId, String dbHostUrl, String userName, 
String password) {
     String SQL_QUERY = "select * from emp";
     List<Employee> employees = null; 
     try(Connection jdbcConn = 
jdbcManager.getConnection(clientId, dbHostUrl, userName, password);
       PreparedStatement pst = jdbcConn.prepareStatement(SQL_QUERY);
       ResultSet rs = pst.executeQuery();) {
            employees = new ArrayList<>();
            Employee employee;
            while (rs.next()) {
                employee = new Employee();
                employee.setEmpNo(rs.getInt("empno"));
                employee.setEname(rs.getString("ename"));
                employee.setJob(rs.getString("job"));
                employee.setSal(rs.getInt("sal"));
                employee.setDeptno(rs.getInt("deptno"));
                employees.add(employee);
            }
       }
     return employees;
}
