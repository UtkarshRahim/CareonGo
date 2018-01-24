
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

//import java.util.Date;
//import java.text.*;
import javax.servlet.annotation.WebServlet;



/**
 *
 * @author wms
 */

@WebServlet(urlPatterns = {"/inventins"})
public class inventins extends HttpServlet {

   
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   		JDBCSingleton jdbc = JDBCSingleton.getInstance();

         PrintWriter out = response.getWriter();
        
String prod_name=null;
int d_id=0;
int prid=0;
SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");

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
			  String des = request.getParameter("des");
              Float pricep = Float.valueOf(pp);
                Float prices = Float.valueOf(sp);
              Integer q1 = Integer.valueOf(quantity);
    Date date1 = null, date2=null;
              
                 
            
              
              try {
            	  
           
            
                 ResultSet rs,RS;
             
                  
                
               
                 String sq1="Select SUPPLIER_ID from suppliers where SUPPLIER_NAME='"+dname+"'";
                rs= jdbc.result(sq1);
                if(rs.next())
                {
                 d_id = rs.getInt("SUPPLIER_ID");
                 
                }
                 
                      if(val.equals("p_id"))
              {
                  prod_name= p_name;
             String sql3 = "Insert into products (PRODUCT_NAME,Description) values (?,?)";
             PreparedStatement ps1 = jdbc.prepare(sql3);
             ps1.setString(1,prod_name);
			 ps1.setString(2,des);
              ps1.executeUpdate();}
                      else { if(val.equals("p_id1"))
              {
                 prod_name= pname ;
                
                      }}
                      String sql2="Select PRODUCT_ID from products where PRODUCT_NAME ='"+prod_name+"'";
                RS=jdbc.result(sql2);
                if(RS.next())
                {
                 prid = RS.getInt("PRODUCT_ID");
                
                }
              
                
               String sq = "select To_Date('"+mfg+"', 'YYYY-MM-DD') from dual";
               String sq10 = "select To_Date('"+exp+"', 'YYYY-MM-DD') from dual";
              ResultSet a = jdbc.result(sq);
              ResultSet b = jdbc.result(sq10);
              
               if(a.next()) {date1 = a.getDate(1);}
               if(b.next()) {date2 = b.getDate(1);}
               
                   String sql = "insert into inventory (BATCH_NUMBER,SUPPLIER_ID,PRODUCT_ID,MFG_DATE,EXP_DATE,QUANTITY,UNIT_PURCHASE_PRICE,UNIT_SELL_PRICE,TYPE) values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = jdbc.prepare(sql);

             
             pst.setString(1,batch);
             pst.setInt(2,d_id);
             pst.setInt(3,prid);
             pst.setDate(4,date1);
             pst.setDate(5,date2);
             pst.setString(6,quantity);
             pst.setFloat(7,pricep);
             pst.setFloat(8,prices);
             pst.setString(9,type);
   
                         
             pst.executeUpdate();
              
           
                     
            response.sendRedirect("retail.html");
             
              
              jdbc.connect().close();
              }
            catch(Exception e){
  out.println("SQLException caught: " + e.getMessage());
  }
              
                
   }
}


