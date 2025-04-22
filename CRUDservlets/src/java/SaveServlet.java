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
public class SaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
          
        String name=request.getParameter("name");  
        String password=request.getParameter("password");  
        String email=request.getParameter("email");  
        String country=request.getParameter("country");  
          
        Employee employee = new Employee();  
        employee.setName(name);  
        employee.setPassword(password);  
        employee.setEmail(email);  
        employee.setCountry(country);  
        
        int status=EmployeeDao.save(employee);  
        if(status>0){  
            out.print("<h3 style='color:blue;'>Record saved successfully!</h3>");              
        } else {  
            out.println("<h3 style='color:red;'>Sorry! unable to save record</h3>");  
        } 
        request.getRequestDispatcher("./add.html").include(request, response);
        out.close();
    }

}
