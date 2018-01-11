/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wms
 */
public class custcat extends HttpServlet {
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          
         PrintWriter out = response.getWriter();
              String dealer_name = request.getParameter("dealer_name");
             String d_address = request.getParameter("d_address");
                String d_phone = request.getParameter("d_phone");
                String d_email = request.getParameter("d_email");
        try {
            /* TODO output your page here. You may use following sample code. */
             Class.forName("com.mysql.jdbc.Driver");
                 ResultSet rs;
                 String nm = null;
                 int s=0;
              String t = null;
                 Connection conn =
            DriverManager.getConnection("jdbc:mysql://localhost:3307/pro","root","123456");
                     Statement stmt=conn.createStatement();
                     
                 out.println("<html>\n" +
"	<head>\n" +
"		<title>Careongo Pharmacy </title>\n" +
"\n" +
"		<link href='http://fonts.googleapis.com/css?family=Graduate' rel='stylesheet' type='text/css'>\n" +
"		<![if !IE]>\n" +
"		<link href=\"style.css\" rel='stylesheet' type='text/css'>\n" +
"		<![endif]>\n" +
"	</head>\n" +
"<body>");
                 out.println("<table border=\"1\">\n" +
"<caption>Medicines</caption>\n" +
"<tr>\n" +
"  <th>Product\n" +
"  <th>Price/quantity in Rs \n" +
"  <th>Available Quantity\n" +
"  <th>Type\n" +
"  <th>Needed Quantity\n" +
"</tr>");
                 
                 ResultSet rs1;
                 ResultSet rs2=null;
                 ResultSet rs3=null;
                 ResultSet rs4=null;
                      Statement stmt1 = conn.createStatement();
                         Statement stmt2 = conn.createStatement();
                            Statement stmt3 = conn.createStatement();
                            Statement stmt4 = conn.createStatement();
                            
              rs1 = stmt1.executeQuery("Select PRODUCT_NAME from products");
                    int i=1;
                 while (rs1.next()) {
                 String n = rs1.getString("PRODUCT_NAME");
              
                 
                 rs2=stmt2.executeQuery("Select distinct UNIT_SELL_PRICE from inventory where PRODUCT_ID = (Select PRODUCT_ID from products where PRODUCT_NAME =\""+n+"\")");     
                 if(rs2.next()){
                    nm = rs2.getString("UNIT_SELL_PRICE");}
              
                 
                 rs3 =stmt3.executeQuery("Select sum(QUANTITY) from inventory where PRODUCT_ID = (Select PRODUCT_ID from products where PRODUCT_NAME =\""+n+"\")");  
              if(rs3.next())
              {
              s = rs3.getInt("sum(QUANTITY)"); }
              
              rs4=stmt4.executeQuery("Select distinct TYPE from inventory where PRODUCT_ID = (Select PRODUCT_ID from products where PRODUCT_NAME =\""+n+"\")");
               if(rs4.next()){t= rs4.getString("TYPE");}
              
              out.println("<tr><td>" + n + "</td><td>" + nm + "</td><td>" + s +"</td><td>" + t+"</td><td> <input type=\"text\" name=\""+n+"\"></td></tr>"); 
             i++;

                 }
              rs1.close();
                 rs2.close();
                 rs3.close();                
         conn.close();     
        }
       
    catch(ClassNotFoundException | SQLException e){
  out.println("SQLException caught: " + e.getMessage());
  }  
       
         out.println("</table>   \n" +"<br><br><input type=\"submit\" value=\"Submit\">\n"+
"</body>\n" +
"</html>");
         
    }
}

   