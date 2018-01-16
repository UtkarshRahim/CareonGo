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
import java.util.Date;
import java.text.*;
import javax.servlet.annotation.WebServlet;



/**
 *
 * @author wms
 */
@WebServlet(urlPatterns = {"/stokinsert"})

public class stokinsert extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
        

              String p_name = request.getParameter("p_name");
              String type = request.getParameter("type");
             String batch = request.getParameter("batch");
                String mfd = request.getParameter("mfd");
                String exp = request.getParameter("exp");
                String quant= request.getParameter("quant");
                 String price_p = request.getParameter("price_p");
                String price_s= request.getParameter("price_s");
                 String dname = request.getParameter("dname");
                String company= request.getParameter("company");
           
                Float pricep = Float.valueOf(price_p);
                Float prices = Float.valueOf(price_s);
             // int d_id = Integer.valueOf(did);
                int d_id =0;
          
              try {
                   
            /* TODO output your page here. You may use following sample code. */
             Class.forName("com.mysql.jdbc.Driver");
                 ResultSet rs;
              String name = null;
                  Connection conn =
            DriverManager.getConnection("jdbc:mysql://localhost:3307/pro","root","123456");
                    out.println(dname);
                 Statement stmt2 = conn.createStatement();
                 String sq1="Select dealer_id from dealer where dealer_name=\""+dname+"\"";
                rs= stmt2.executeQuery(sq1);
                if(rs.next())
                {
                 d_id = rs.getInt("dealer_id");
                 out.println(d_id);
                }
                
            String sql = "insert into stock (p_name,type,batch,mfd,exp,quant,price_p,price_s,d_id,d_name,company) values (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
             pst.setString(1,p_name);
             pst.setString(2,type);
             pst.setString(3,batch);
             pst.setString(4,mfd);
             pst.setString(5,exp);
             pst.setString(6,quant);
             pst.setFloat(7,pricep);
             pst.setFloat(8,prices);
             pst.setInt(9,d_id);
             pst.setString(10,dname);
             pst.setString(11,company);
                         
             pst.executeUpdate();
        }
         
        catch(Exception e){
  out.println("SQLException caught: " + e.getMessage());
  }
              
                   
            
    }
}

