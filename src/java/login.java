/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wms
 */
@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {

    

  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter out = response.getWriter() ;
         int cid=0;
          out.println("<html>\n" +
"	<head>\n" +
"		<title>Careongo Pharmacy </title>\n" +
"\n" +
"		<link href='http://fonts.googleapis.com/css?family=Graduate' rel='stylesheet' type='text/css'>\n" +
"		<![if !IE]>\n" +
"		<link href=\"style.css\" rel='stylesheet' type='text/css'>\n" +
"		<![endif]>\n" +
"	</head>\n" +
"<body><form method = \"get\" action = \"tracking\">"
             + "<br>"
             
     );  
         String user= request.getParameter("u_name");
         String pass = request.getParameter("password");
         
          try {
            /* TODO output your page here. You may use following sample code. */
             Class.forName("com.mysql.jdbc.Driver");
                 
              String t = null;
                 Connection conn =
            DriverManager.getConnection("jdbc:mysql://localhost:3307/pro","root","123456");
                     Statement stmt=conn.createStatement();
                     Statement stmt1=conn.createStatement();
                     ResultSet rs = stmt.executeQuery("Select CUSTOMER_ID from login where Username = \""+user+"\" AND Password = \""+pass+"\""); 
                     if(rs.next())
                     {int id = rs.getInt("CUSTOMER_ID");
                     
                     ResultSet rs1 = stmt1.executeQuery("Select ORDER_ID from orders where CUSTOMER_ID = \""+id+"\" ");   
     out.println("<br><label style =\"font-size: 18px;\">Select your Order </label>" +
"        <select class=\"form-control\" style=\"width:250px;\" name=\"cid\" id=\"dropdown\" >\n\" +\n");
                    while (rs1.next()) {
                   id=rs1.getInt("ORDER_ID"); 
             out.println("<option value =\""+id+"\">Order id -" +id +"</option>"); 
                     }
                out.println("<br><br>\n" +
"  <input type=\"submit\" value=\"Submit\">\n" +
"</form>  ");    
                    
      }
                     else{out.println("NO RECORD!!!");}
                     request.setAttribute("cid",cid);
          }
          
           catch(ClassNotFoundException | SQLException e){
  out.println("SQLException caught: " + e.getMessage());
  }
          out.println("</body>\n" +
"</html>");
    }
   
}
