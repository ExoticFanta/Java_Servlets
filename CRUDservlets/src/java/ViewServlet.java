import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author aggelos
 */
public class ViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        out.println("<h1>Employees List</h1>");  
          
        List<Employee> list=EmployeeDao.getAllEmployees();  
          
        out.print("<table border='1' width='100%'");  
        out.print("<tr><th>Id</th><th>Name</th><th>Password</th><th>Email</th><th>Country</th><th>Edit</th><th>Delete</th></tr>");  
        
        for (Employee e : list) {
            out.print("<tr>"
                    + "<td>" + e.getId() + "</td>"
                    + "<td>" + e.getName() + "</td>"
                    + "<td>" + e.getPassword() + "</td>"
                    + "<td>" + e.getEmail() + "</td>"
                    + "<td>" + e.getCountry() + "</td>"
                    + "<td><a href='./EditServlet?id=" + e.getId() + "'>edit</a></td>"
                    + "<td><a href='./DeleteServlet?id=" + e.getId() + "'>delete</a></td>"
                    + "</tr>");
        }  
        out.print("</table>");  
        out.println("<p><a href='./add.html'>Add New Employee</a></p>");
        out.println("<p><a href='./index.html'>Home</a></p>");
          
        out.close();  
    }
    
}
