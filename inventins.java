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

@WebServlet(urlPatterns = {"/inventins "})
public class inventins extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
        
String prod_name=null;
int d_id=0;
int prid=0;

               String val = request.getParameter("p_id");
               String pname= request.getParameter("pname");
               String dname = request.getParameter("dname");
              String p_name=request.getParameter("p_name");
              String batch= request.getParameter("b_no");
               String type = request.getParameter("type");
              String mfg= request.getParameter("mfg");
              String exp= request.getParameter("exp");
              String quantity= request.getParameter("quantity");
              String sp= request.getParameter("sp");
              String pp= request.getParameter("pp");
              Float pricep = Float.valueOf(pp);
                Float prices = Float.valueOf(sp);
              
    
              
                 
            
              
              try {
                   
            /* TODO output your page here. You may use following sample code. */
             Class.forName("com.mysql.jdbc.Driver");
                 ResultSet rs,RS;
              String name = null;
                  Connection conn =
            DriverManager.getConnection("jdbc:mysql://localhost:3307/pro","root","123456");
                    out.println(dname);
                 Statement stmt2 = conn.createStatement();
                 String sq1="Select SUPPLIER_ID from suppliers where SUPPLIER_NAME=\""+dname+"\"";
                rs= stmt2.executeQuery(sq1);
                if(rs.next())
                {
                 d_id = rs.getInt("SUPPLIER_ID");
                 out.println(d_id);
                }
                 
                      if(val.equals("p_id"))
              {
                  prod_name= p_name;
             String sql3 = "Insert into products (PRODUCT_NAME) values (?)";
             PreparedStatement ps1 = conn.prepareStatement(sql3);
             ps1.setString(1,prod_name);}
                      else { if(val.equals("p_id1"))
              {
                 prod_name= pname ;
                
                      }
                      String sql2="Select PRODUCT_ID from products where PRODUCT_NAME =\""+prod_name+"\"";
                RS= stmt2.executeQuery(sql2);
                if(RS.next())
                {
                 prid = RS.getInt("PRODUCT_ID");
                 out.println(prid);
                }
              }
                
               
              
              
                   String sql = "insert into inventory (BATCH_NUMBER,SUPPLIER_ID,PRODUCT_ID,MFG_DATE,EXP_DATE,QUANTITY,UNIT_PURCHASE_PRICE,UNIT_SELL_PRICE,TYPE) values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);

             
             pst.setString(1,batch);
             pst.setInt(2,d_id);
             pst.setInt(3,prid);
             pst.setString(4,mfg);
             pst.setString(5,exp);
             pst.setString(6,quantity);
             pst.setFloat(7,pricep);
             pst.setFloat(8,prices);
             pst.setString(9,type);
   
                         
             pst.executeUpdate();
              
           
                     
            
             
              
              
              }
            catch(Exception e){
  out.println("SQLException caught: " + e.getMessage());
  }
              
                
   }
}


