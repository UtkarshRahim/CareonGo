

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/custinsert"})
public class custinsert extends HttpServlet {

  
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  		JDBCSingleton jdbc = JDBCSingleton.getInstance();

         PrintWriter out = response.getWriter();
              String c_name = request.getParameter("c_name");
             String c_address = request.getParameter("c_address");
                String c_phone = request.getParameter("c_phone");
                String c_email = request.getParameter("c_email");
                String c_user = request.getParameter("c_user");
                String c_pass = request.getParameter("c_pass");
               int cid =0 ;
                
                
                try {
            /* TODO output your page here. You may use following sample code. */
            
              
               
                     
              ResultSet rs;
            String sql = "insert into customers (CUSTOMER_NAME,EMAIL_ADDRESS,PHONE_NUMBER,ADDRESS,Username,PASSWORD) values (?,?,?,?,?,?)";
            PreparedStatement pst = jdbc.prepare(sql);
             pst.setString(1,c_name);
              pst.setString(2,c_email);
              pst.setString(3,c_phone);
             pst.setString(4,c_address);
             pst.setString(5, c_user);
             pst.setString(6,c_pass);
             
          
             
              pst.executeUpdate();
              
            
               String sql1 = "Select CUSTOMER_ID from customers where Username = '"+c_user+"' AND PASSWORD = '"+c_pass+"'";
               rs = jdbc.result(sql1);
               if(rs.next())
               {
                cid=rs.getInt("CUSTOMER_ID");
               }
              
              HttpSession session=request.getSession();  
        session.setAttribute("cid",cid);  
               response.sendRedirect("custcat");
              
             
          jdbc.connect().close();
        
        
        
        }
       
        
        catch(Exception e){
  out.println("SQLException caught: " + e.getMessage());
  }
    }
}
    