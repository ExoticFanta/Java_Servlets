import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author aggelos
 */
public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        out.println("<h1>Update Employee</h1>");  
        String sid=request.getParameter("id");  
        int id=Integer.parseInt(sid);  
          
        Employee employee = EmployeeDao.getEmployeeById(id);
        
        out.print("<form action='./EditServlet' method='post'>");  
        out.print("<table>");  
        out.print("<tr><td></td><td><input type='hidden' name='id' value='" + employee.getId() + "'/></td></tr>");  
        out.print("<tr><td>Name:</td><td><input type='text' name='name' value='" + employee.getName() + "'/></td></tr>");  
        out.print("<tr><td>Password:</td><td><input type='password' name='password' value='" + employee.getPassword() + "'/></td></tr>");  
        out.print("<tr><td>Email:</td><td><input type='email' name='email' value='" + employee.getEmail() + "'/></td></tr>");  
        out.print("<tr><td>Country:</td><td>");  
        out.print("<select name='country' style='width:150px'>");  
        
        String selectedCountry = "";
        
        selectedCountry =(employee.getCountry().equals("Greece")) ? "selected" : "";
        out.print("<option " + selectedCountry + ">Greece</option>");
        selectedCountry =(employee.getCountry().equals("Germany")) ? "selected" : "";
        out.print("<option " + selectedCountry + ">Germany</option>");  
        selectedCountry =(employee.getCountry().equals("USA")) ? "selected" : "";
        out.print("<option " + selectedCountry + ">USA</option>");  
        selectedCountry =(employee.getCountry().equals("UK")) ? "selected" : "";
        out.print("<option " + selectedCountry + ">UK</option>");  
        selectedCountry =(employee.getCountry().equals("Other")) ? "selected" : "";
        out.print("<option " + selectedCountry + ">Other</option>");  
        
        out.print("</select>");  
        
        out.print("</td></tr>");  
        out.print("<tr><td colspan='2'><input type='submit' value='Save'/></td></tr>");  
        out.print("</table>");  
        out.print("</form>");  
          
        out.close();
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  

        Employee employee = new Employee();  
        employee.setId(Integer.parseInt(request.getParameter("id")));  
        employee.setName(request.getParameter("name"));  
        employee.setPassword(request.getParameter("password"));  
        employee.setEmail(request.getParameter("email"));  
        employee.setCountry(request.getParameter("country"));  
          
        int status=EmployeeDao.update(employee);  
        if (status > 0) {
            response.sendRedirect("./ViewServlet");
        } else {
            out.println("<h3 style='color:red;'>Sorry! unable to update record</h3>");
        }
          
        out.close();
    }

}
