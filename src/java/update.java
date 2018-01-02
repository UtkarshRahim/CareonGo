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
@WebServlet(urlPatterns = {"/update"})
public class update extends HttpServlet {

   
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            
        response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
             String sel = request.getParameter("medicines");
                String val = request.getParameter("unit") ;
               Integer u= Integer.valueOf(val);
               String ins=null;
            try { 
            Class.forName("com.mysql.jdbc.Driver").newInstance();
                   
                  Connection conn =
                        DriverManager.getConnection("jdbc:mysql://localhost:3307/opm","root","123456");
            int a=0;
        
            String site = "display";
           Statement stat1 = conn.createStatement();
           PreparedStatement stat2=null;
           String sql="Update stock set Amount = Amount + "+val+" where name = \""+sel+"\"";
          
            
            stat1.executeUpdate(sql);
           
               conn.close();
                response.sendRedirect(site);
        }
              catch (Exception e) {
                    e.printStackTrace();
                    }
     }
}
    