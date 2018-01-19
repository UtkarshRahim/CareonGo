

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;


@WebServlet(urlPatterns = {"/afterlogin"})
public class afterlogin extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	  		JDBCSingleton jdbc = JDBCSingleton.getInstance();
           PrintWriter out = response.getWriter();  
           String pname=null;
           HttpSession session = request.getSession();
            int cid = (Integer) session.getAttribute("cid");
            String name =null; 
            int oid = 0;
            
            out.println("<html>\n" +
"    <head> <style>\n" +
"     body  {\n" +
"    background-image: url(\"bg.jpg\");\n" +
"    background-color: #cccccc;\n" +
"    \n" +
"     background-repeat: no-repeat;\n" +
"}</style>\n" +
"        <title>Customer info</title>\n" +
"    <link href='http://fonts.googleapis.com/css?family=Graduate' rel='stylesheet' type='text/css'>    \n" +
"    <link href=\"style.css\" rel='stylesheet' type='text/css'>\n" +
"    </head><body ><form action = \"tracking\"");
             out.println("<br><br><label style =\"font-size: 18px;\">ORDER ID</label>" +
"        <select class=\"form-control\" style=\"width:450px;\" name=\"oid\" id=\"dropdown\" >\n\" +\n");
            try {
            /* TODO output your page here. You may use following sample code. */
            
                     
//                     Statement stmt1=conn.createStatement();
//                     Statement stmt2=conn.createStatement();
                    // session.setAttribute("cid",cid);
                     
                     ResultSet rs= jdbc.result("Select ORDER_ID from orders where CUSTOMER_ID=\""+cid+"\"");
                     while(rs.next())
                     {
                         
                      oid = rs.getInt("ORDER_ID");
                    
                     out.println("<option value =\""+oid+"\">Order Number - " + oid +"</option>"); 
                     
                     
                     }
            
                   jdbc.connect().close(); 
                     
                }
                     
                     
                     
                     
                     
                     
                     
                     
          
                
                  




                               catch(ClassNotFoundException | SQLException e){
  out.println("SQLException caught: " + e.getMessage());
  }  
             out.println("</select> <input type=\"submit\" value=\"Check\">\n" +
"    </form>\n" +

"    </body>\n" +
"</html>" );
  }


}
