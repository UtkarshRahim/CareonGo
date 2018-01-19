
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wms
 */
@WebServlet(urlPatterns = {"/retlogin"})
public class retlogin extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
                String site = "retailogin.html";
                
               String u= request.getParameter("user_name");
               String p= request.getParameter("pass_word");
         
                if((u.equals("admin") && p.equals("admin")))
                {
                   response.sendRedirect("retail.html") ;
               
                }
                else 
                    response.sendRedirect(site);
               
    }

}
