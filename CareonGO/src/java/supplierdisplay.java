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
@WebServlet(urlPatterns = {"/supplierdisplay"})
public class supplierdisplay extends HttpServlet {
 protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
      out.println("<html>\n" +
"	<head>\n" +
"		<title>Careongo Pharmacy </title>\n" +
"\n" +
"		<link href='http://fonts.googleapis.com/css?family=Graduate' rel='stylesheet' type='text/css'>\n" +
"		<![if !IE]>\n" +
"		<link href=\"style.css\" rel='stylesheet' type='text/css'>\n" +
"		<![endif]>\n" +
"	</head>\n" +
"<body> <form method = \"get\" action =\"retail.html\">"+
"<table border=\"1\">\n" +
"<caption>Medicines</caption>\n" +
"<tr>\n" +
"  <th>SID\n" +
"  <th>SName\n" +
"  <th>SEmail\n" +
"  <th>Sphone\n" + "<th>SAddress \n"+
"</tr>");
              
          try {
            /* TODO output your page here. You may use following sample code. */
             Class.forName("com.mysql.jdbc.Driver");
                 ResultSet rs;
              
                  Connection conn =
            DriverManager.getConnection("jdbc:mysql://localhost:3307/pro","root","123456");
                     Statement stmt=conn.createStatement();
                       rs = stmt.executeQuery("select * from suppliers group by SUPPLIER_ID asc" );
          while(rs.next())
            {
                 int id = rs.getInt("SUPPLIER_ID");
            	 String sn = rs.getString("SUPPLIER_NAME");
                  String se = rs.getString("EMAIL_ADDRESS");
                   String sp = rs.getString("PHONE_NUMBER");
                    String sad = rs.getString("ADDRESS");
            out.println("<tr><td>" + id + "</td><td>" + sn + "</td><td>" + se +"</td><td>" + sp+"</td><td>" + sad+ "</td></tr>"); 
            }
             
          
          conn.close();
        
        
        
        }
       
        
        catch(Exception e){
  out.println("SQLException caught: " + e.getMessage());
  }
          out.println("<input type=\"submit\" value=\"GO BACK\">\n" +
"    </form></form>   </body>\n" +
"</html>");

    }
}
