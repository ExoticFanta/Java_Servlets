import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aggelos
 *
 */
public class EmployeeDao {
    
    static final String MARIADB_DRIVER = "org.mariadb.jdbc.Driver";
    static final String MARIADB_SUB = "jdbc:mariadb:";
    static final String DB_SERVER = "//localhost:3306/";
    static final String DB_NAME = "netprog_db";
    static final String DB_USER = "netprog_user";
    static final String DB_PASS = "1234";
    
    static final String DB_URL = MARIADB_SUB + DB_SERVER + DB_NAME;

    
    public static Connection getConnection() {
        Connection conn = null;
        
        try {
            Class.forName(MARIADB_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException | SQLException ex) {      
            System.out.println(ex);
        }
        return conn;
    }
    
    public static int save(Employee employee) {
        int status = 0;
        String sql = "INSERT INTO employees(name, password, email, country) VALUES (?, ?, ?, ?)";
        Connection conn = EmployeeDao.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,employee.getName());  
            ps.setString(2,employee.getPassword());  
            ps.setString(3,employee.getEmail());  
            ps.setString(4,employee.getCountry());  
            status=ps.executeUpdate();                
            conn.close();
        } catch (SQLException ex) {  
            System.out.println(ex);
        }
        return status;
    }
    
    public static int update(Employee employee) {
        int status = 0;
        String sql = "UPDATE employees SET name=?, password=?, email=?, country=? WHERE id=?";
        Connection conn = EmployeeDao.getConnection();
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,employee.getName());  
            ps.setString(2,employee.getPassword());  
            ps.setString(3,employee.getEmail());  
            ps.setString(4,employee.getCountry()); 
            ps.setInt(5,employee.getId());
            status=ps.executeUpdate();                
            conn.close();
        } catch (SQLException ex) {  
            System.out.println(ex);
        }
        return status;
    }
    
    public static int delete(int id) {
        int status = 0;
        String sql = "DELETE FROM employees WHERE id=?";
        Connection conn = EmployeeDao.getConnection();
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            status=ps.executeUpdate();                
            conn.close();
        } catch (SQLException ex) { 
            System.out.println(ex);
        }
        return status;
    }
    
    public static Employee getEmployeeById(int id) {
        Employee employee = new Employee();
        String sql = "SELECT * FROM employees WHERE id=?";
        Connection conn = EmployeeDao.getConnection();  
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);  
            ResultSet rs = ps.executeQuery();  
            if(rs.next()){  
                employee.setId(rs.getInt(1));  
                employee.setName(rs.getString(2));  
                employee.setPassword(rs.getString(3));  
                employee.setEmail(rs.getString(4));  
                employee.setCountry(rs.getString(5));  
            }  
            conn.close();  
        } catch (SQLException ex) {  
            System.out.println(ex);
        }
        return employee;
    }
    
    public static List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        Connection conn = EmployeeDao.getConnection();  
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                Employee employee = new Employee();  
                employee.setId(rs.getInt(1));  
                employee.setName(rs.getString(2));  
                employee.setPassword(rs.getString(3));  
                employee.setEmail(rs.getString(4));  
                employee.setCountry(rs.getString(5));  
                list.add(employee);  
            }  
            conn.close(); 
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    
}
