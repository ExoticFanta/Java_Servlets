import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author aggelos
 */
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String sid=request.getParameter("id");  
        int id=Integer.parseInt(sid);  
        EmployeeDao.delete(id);  
        response.sendRedirect("./ViewServlet");  
    }

}
