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
@WebServlet(urlPatterns = {"/custcat"})

public class custcat extends HttpServlet {
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          
         PrintWriter out = response.getWriter();
           HttpSession session=request.getSession();  
         int cid = (Integer) session.getAttribute("cid");
         
        try {
            /* TODO output your page here. You may use following sample code. */
             Class.forName("com.mysql.jdbc.Driver");
                 ResultSet rs;
                 String nm = null;
                 int s=0;
              String t = null;
              String des=null;
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
"<body><h1>Welcome "+cid+"</h1>" + " <form method = \"post\" action= 'NewServlet1' >");
                 out.println("<table border=\"1\">\n" +
"<caption>Medicines</caption>\n" +
"<tr>\n" +
"  <th>Product\n" +
"  <th>Price/quantity in Rs \n" +
"  <th>Available Quantity\n" +
"  <th>Type\n" +
           "  <th>Description\n" +              
"  <th>Needed Quantity\n" +
"</tr>");
                 
                                 
                 
                 ResultSet rs1;
                 ResultSet rs2=null;
                 ResultSet rs3=null;
                 ResultSet rs4=null;
                 ResultSet rs5=null;
                      Statement stmt1 = conn.createStatement();
                         Statement stmt2 = conn.createStatement();
                            Statement stmt3 = conn.createStatement();
                            Statement stmt4 = conn.createStatement();
                            Statement stmt5 = conn.createStatement();
                            
                            
              rs1 = stmt1.executeQuery("Select PRODUCT_NAME from products");
                    int i=1;
                 while (rs1.next()) {
                 String n = rs1.getString("PRODUCT_NAME");
              
                 
                 rs2=stmt2.executeQuery("Select distinct UNIT_SELL_PRICE,sum(QUANTITY),TYPE from inventory where PRODUCT_ID = (Select PRODUCT_ID from products where PRODUCT_NAME =\""+n+"\")");     
                 if(rs2.next()){
                    nm = rs2.getString("UNIT_SELL_PRICE");
                s = rs2.getInt("sum(QUANTITY)");
                 t= rs2.getString("TYPE");}
//                 rs3 =stmt3.executeQuery("Select sum(QUANTITY) from inventory where PRODUCT_ID = (Select PRODUCT_ID from products where PRODUCT_NAME =\""+n+"\")");  
//              if(rs3.next())
//              {
//              s = rs3.getInt("sum(QUANTITY)"); }
//              
//              rs4=stmt4.executeQuery("Select distinct TYPE from inventory where PRODUCT_ID = (Select PRODUCT_ID from products where PRODUCT_NAME =\""+n+"\")");
//               if(rs4.next()){t= rs4.getString("TYPE");}
               
               rs5 = stmt5.executeQuery("Select description from products where PRODUCT_NAME =\""+n+"\"");
               if(rs5.next())
               {des = rs5.getString("DESCRIPTION");}
              
              out.println("<tr><td>" + n + "</td><td>" + nm + "</td><td>" + s +"</td><td>" + t+"</td><td>" +des+"</td><td> <input type=\"number\" name=\"array[]\" min =\"1\" max = \""+s+"\"></td></tr>"); 
           
              i++;

                 }
                  out.println("</table>   \n" +"<br><br><input type=\"submit\" value=\"Submit\">\n"+
"</form></body>\n" +
"</html>");    
                 session.setAttribute("cid",cid);
              rs1.close();
                 rs2.close();
                 rs3.close(); 
                 rs5.close();
                
         conn.close();     
        }
       
    catch(ClassNotFoundException | SQLException e){
  out.println("SQLException caught: " + e.getMessage());
  }  
       
     
         
    }
}

   