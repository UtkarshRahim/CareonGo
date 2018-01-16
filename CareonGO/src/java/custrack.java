/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
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
@WebServlet(urlPatterns = {"/custrack"})

public class custrack extends HttpServlet {

    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
PrintWriter out = response.getWriter();
HttpSession session=request.getSession();  
int cid = (Integer)session.getAttribute("cid");
String orderid= request.getParameter("id");
int oid= 0;

out.println("<html>\n" +
"	<head>\n" +
"		<title>Careongo Pharmacy </title>\n" +
"		<link href='http://fonts.googleapis.com/css?family=Graduate' rel='stylesheet' type='text/css'>\n" +
"		<![if !IE]>\n" +
"		<link href=\"style.css\" rel='stylesheet' type='text/css'>\n" +
"		<![endif]>\n" +
"	</head>\n" +
"<body><form action=\"alert\""
             + "<br>" );
             out.println("<table border=\"1\">"  +
"<caption>Medicines</caption>"  +
"<tr>"  +
" <th>OrderID"  +
"<th>Amounting"  +
                     " <th>TIME"+
"</tr>");  
   try {
            /* TODO output your page here. You may use following sample code. */
             Class.forName("com.mysql.jdbc.Driver");
    Connection conn =
            DriverManager.getConnection("jdbc:mysql://localhost:3307/pro","root","123456");
     Statement stmt=conn.createStatement();
                 ResultSet rs1 = stmt.executeQuery("Select STATUS from orders where CUSTOMER_ID = \""+cid+"\"");  
                 
                 while(rs1.next())
                 {
                      oid= rs1.getInt("ORDER_ID");
                     float price=rs1.getFloat("PRICE");
                     String time = String.valueOf(rs1.getTimestamp("TIME"));
                     
             out.println("<tr><td>" + oid + "</td><td>" + price + "</td><td>" + time +"</td></tr>"); 
                 }
                 
                  out.println("</table>   \n" +"<br><br><input type=\"submit\" value=\"SHIP AND UPDATE\">\n"+
"</form></body>\n" +
"</html>");
         
              String sql5= "Update ORDERS set STATUS = 'SHIPPED' where ORDER_ID = \""+oid+"\"";
                PreparedStatement ps2= conn.prepareStatement(sql5);
                ps2.executeUpdate();
         
   
    }
    catch(ClassNotFoundException | SQLException e){
  out.println("SQLException caught: " + e.getMessage());
  }

    }
}
