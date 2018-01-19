import java.JDBCSingleton;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wms
 */
@WebServlet(urlPatterns = {"/tracking"})

public class tracking extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		JDBCSingleton jdbc = JDBCSingleton.getInstance();

         PrintWriter out = response.getWriter() ;

            String cid = request.getParameter("oid");
            out.println("<html>\n" +
"	<head> 	<title>Careongo Pharmacy </title>\n" +
"		<link href='http://fonts.googleapis.com/css?family=Graduate' rel='stylesheet' type='text/css'>\n" +
"		<link href=\"style.css\" rel='stylesheet' type='text/css'>\n" +

"	</head>\n" +
"<body><form action=\"alert\""
             + "<br>" );
             out.println("<br><br><table border=\"1\">"  +
"<caption>Medicines</caption>"  +
"<tr>"  +
" <th>NAME"  +
                     " <th>TIME"+
                     " <th>ADDRESS"+
                     " <th>STATUS"+
"</tr>");  

              try {
            /* TODO output your page here. You may use following sample code. */
          
              String t = null;
               
                 
  String time=null,add=null,status=null;
                
  ResultSet rs0 = jdbc.result("SELECT PRODUCT_ID from order_det where ORDER_ID =\""+cid+"\"");
  while(rs0.next())
  {
      
      int pid = rs0.getInt("PRODUCT_ID");
      
              ResultSet rs = jdbc.result("Select PRODUCT_NAME from products where PRODUCT_ID=\""+pid+"\"");
              while(rs.next())
              {
                String pname = rs.getString("PRODUCT_NAME")  ;
                ResultSet rs1 = jdbc.result("Select TIME from orders where ORDER_ID =\""+cid+"\"");
                if(rs1.next()){ time = String.valueOf(rs1.getTimestamp("TIME"));}
                ResultSet rs2 = jdbc.result("Select ADDRESS from customers where CUSTOMER_ID = (Select CUSTOMER_ID from orders where ORDER_ID  =\""+cid+"\")");
                if(rs2.next()){ add = rs2.getString("ADDRESS");}
                ResultSet rs3 = jdbc.result("Select STATUS from orders where ORDER_ID =\""+cid+"\"");
                if(rs3.next()){ status = rs3.getString("STATUS");}
                
                           
                
                
                
                
                
                
                
                
                
 out.println("<tr><td>" + pname + "</td><td>" + time + "</td><td>" + add +"</td><td>" + status+"</td><td> </td></tr>"); 
              }
              
              
  }jdbc.connect().close();
              }
                catch(ClassNotFoundException | SQLException e){
  out.println("SQLException caught: " + e.getMessage());
  }
             
             
       out.println("<br><br><a href='index.html' align =\"right\">GO BACK </a> </body>\n" +
"</html>");      
             
             
             
  }
}