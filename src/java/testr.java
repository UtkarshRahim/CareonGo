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

/**
 *
 * @author wms
 */
public class testr extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String medicines=null;
        int unit=0;
       try {        
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                    } catch (Exception e) {
                    e.printStackTrace();
                    }
                      Connection conn =
                        DriverManager.getConnection("jdbc:mysql://localhost:3307/opm","root","123456");
                    
               
                Statement stat = conn.createStatement();
                  ResultSet rs = stat.executeQuery("Select Name from stock") ;
                 while (rs.next())
               
                 {  String name=rs.getString("Name");    
                }
                
      rs.close();
      conn.close();
    }
}
