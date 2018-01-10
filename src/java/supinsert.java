/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author wms
 */
@WebServlet(urlPatterns={"/supinsert"})
public class supinsert extends HttpServlet {

  
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
              String dealer_name = request.getParameter("dealer_name");
             String d_address = request.getParameter("d_address");
                String d_phone = request.getParameter("d_phone");
                String d_email = request.getParameter("d_email");
        try {
            /* TODO output your page here. You may use following sample code. */
             Class.forName("com.mysql.jdbc.Driver");
                 ResultSet rs;
              
                  Connection conn =
            DriverManager.getConnection("jdbc:mysql://localhost:3307/pro","root","123456");
                     Statement stmt=conn.createStatement();
              
            String sql = "insert into dealer (dealer_name,d_address,d_phone,d_email) values (?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
             pst.setString(1,dealer_name);
             pst.setString(2,d_address);
             pst.setString(3,d_phone);
             pst.setString(4,d_email);
             
              pst.executeUpdate();
              
               out.println("<html>\n" +
"<head>\n" +
"<title>Stock check</title>\n" +
"<link href='tab.css' rel='stylesheet' type='text/css'>\n" +
"</head>\n" +
"\n" +
"<body background=\"bg.jpg\">\n" +
"<table border=\"1\">\n" +
"<caption>Medicines</caption>\n" +
"<tr>\n" +
"  <th>SID\n" +
"  <th>SName\n" +
"  <th>Saddress\n" +
"  <th>Sphone\n" + "<th>Semail \n"+
"</tr>");
              
                rs = stmt.executeQuery("select * from dealer group by dealer_id asc" );
          while(rs.next())
            {
                 int id = rs.getInt("dealer_id");
            	 String sn = rs.getString("dealer_name");
                  String sad = rs.getString("d_address");
                   String sp = rs.getString("d_phone");
                    String se = rs.getString("d_email");
            out.println("<tr><td>" + id + "</td><td>" + sn + "</td><td>" + sad +"</td><td>" + sp+"</td><td>" + se+ "</td></tr>"); 
            }
             
          
          conn.close();
        
        
        
        }
       
        
        catch(Exception e){
  out.println("SQLException caught: " + e.getMessage());
  }
    }
}
    