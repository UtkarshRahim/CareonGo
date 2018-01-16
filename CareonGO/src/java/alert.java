/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 *
 * @author wms
 */
@WebServlet(urlPatterns = {"/alert"})
public class alert extends HttpServlet {

   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
     int id = 0;
        
       out.println("<html>\n" +
"	<head>\n" +
"		<title>Careongo Pharmacy </title>\n" +
"\n" +
"		<link href='http://fonts.googleapis.com/css?family=Graduate' rel='stylesheet' type='text/css'>\n" +
"		<![if !IE]>\n" +
"		<link href=\"style.css\" rel='stylesheet' type='text/css'>\n" +
"		<![endif]>\n" +
"	</head>\n" +
"<body><form method = \"get\" action =\"processal\">"
             + "<br>"
             
     );  
     
   try {
            
             Class.forName("com.mysql.jdbc.Driver");
    Connection conn =
            DriverManager.getConnection("jdbc:mysql://localhost:3307/pro","root","123456");
                     Statement stmt=conn.createStatement();
  
                    ResultSet rs2 = stmt.executeQuery("Select ORDER_ID from orders where STATUS = \"Ordered\" ");   
                    if(rs2.next())
                    {
                 ResultSet rs1 = stmt.executeQuery("Select ORDER_ID from orders where STATUS = \"Ordered\" ");   
     out.println("<br><label style =\"font-size: 18px;\">Order ID</label>" +
"        <select class=\"form-control\" style=\"width:250px;\" name=\"id\" id=\"dropdown\" >\n\" +\n");
                    while (rs1.next()) {
                   id=rs1.getInt("ORDER_ID"); 
             out.println("<option value =\""+id+"\">Order id -" +id +"</option>"); 
                  }
                 request.setAttribute("orderid",id);
                       out.println("</select><br>"); 
                 out.println("<br><input type=\"submit\" value=\"Proceed to shipping\">\n" +
"</form>    ");
   
                    } 
                      else{
                      out.println("No new orders yet !!");
                      out.println("<span style=\"display:inline-block; width: 300px;\"></span><a href = 'retail.html'>GO BACK</a>");
                      }
//                      
   
   
   }                   
      catch(ClassNotFoundException | SQLException e){
  out.println("SQLException caught: " + e.getMessage());
  }
out.println("</body>\n" +
"</html>");

  
    }
}
