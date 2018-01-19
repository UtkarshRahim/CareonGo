
import java.JDBCSingleton;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;  

/**
 *
 * @author wms
 */
@WebServlet(urlPatterns = {"/NewServlet2"})
public class NewServlet2 extends HttpServlet {

    
    
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  		JDBCSingleton jdbc = JDBCSingleton.getInstance();

           PrintWriter out = response.getWriter();
           String add=null;
           String n=null;
       
         HttpSession session = request.getSession();
String a = (String) session.getAttribute("a1");
int cid = (Integer) session.getAttribute("cid");
         
          out.println("<html>\n" +
"	<head>\n" +
"		<title>Careongo Pharmacy </title>\n" +
"\n" +
"		<link href='http://fonts.googleapis.com/css?family=Graduate' rel='stylesheet' type='text/css'>\n" +
"		<![if !IE]>\n" +
"		<link href=\"style.css\" rel='stylesheet' type='text/css'>\n" +
"		<![endif]>\n" +
"	</head>\n" );
           try {
            
                 ResultSet rs,rs1,rs2;
                 String nm = null;
                 int i=0;
                 
              String t = null;
              Date date = new Date();
              Calendar calendar = Calendar.getInstance();

             ResultSet rs3 = jdbc.result("Select PRODUCT_ID,QUANTITY from ORDER_DET where ORDER_ID = \""+a+"\"");
           
              while(rs3.next()){
           int pid=rs3.getInt("PRODUCT_ID") ;
           int quantity = rs3.getInt("QUANTITY");
           String sql5=null;
           ResultSet rs4 = jdbc.result("Select BATCH_NUMBER,QUANTITY from inventory where PRODUCT_ID = \""+pid+"\"");
           while(rs4.next())
           {
               String b = rs4.getString("BATCH_NUMBER");
               int supply=rs4.getInt("QUANTITY");
               
               if (supply<=quantity)
               {
                    quantity=quantity-supply;
                 supply=0;
                 sql5= "Update inventory set QUANTITY = 0 where BATCH_NUMBER = \""+b+"\"";
                PreparedStatement ps2= jdbc.prepare(sql5);
                ps2.executeUpdate();
                String sql7= "Update order_det set Batch.No = \""+b+"\" where PRODUCT_ID = (Select PRODUCT_ID from inventory where BATCH_NUMBER = \""+b+"\" AND QUANTITY =0)";
                PreparedStatement ps5= jdbc.prepare(sql7);
                ps5.executeUpdate();
               }
               
               else {
               if(supply>quantity)
               {
                   int rem= supply-quantity;
               sql5 = "Update inventory set QUANTITY = '"+rem+"' where BATCH_NUMBER = \""+b+"\"";
                PreparedStatement ps2= jdbc.prepare(sql5);
                ps2.executeUpdate();
                String sql8="Update order_det set Batch_No = \""+b+"\" where (PRODUCT_ID = \""+pid+"\" AND QUANTITY = \""+quantity+"\") AND ORDER_ID=\""+a+"\"";
                PreparedStatement ps4= jdbc.prepare(sql8);
                ps4.executeUpdate();
                break;
               }
               }
               
               
               }
                   
            }
           
             String sql6 = "Update orders set STATUS = \"Ordered\" where ORDER_ID = \""+a+"\"";
                PreparedStatement ps3= jdbc.prepare(sql6);
                ps3.executeUpdate();
           
                
         String sq = "Select CUSTOMER_NAME,ADDRESS from customers where CUSTOMER_ID= \""+cid+"\"";
         ResultSet r = jdbc.result(sq);
         while(r.next())
         {
         add= r.getString("ADDRESS");
         n=r.getString("CUSTOMER_NAME");
         }
           
           
           
           jdbc.connect().close();
           }
            catch(ClassNotFoundException | SQLException e){
  out.println("SQLException caught: " + e.getMessage());
  }  
           
           session.setAttribute("cid",cid);
     
out.println("<body><center><h1>Thank you "+n+"</h1><br> "
        + "<h3>Your order will be delivered to "+add
        + "</h3></center>"
        );      
           out.println(" <br><br>\n" +
" <form action = \"custcat\" > <input type=\"submit\" value=\"Go back\">\n" +
"</form>    \n" +" <form action = \"fpage.html\"> <input type=\"submit\" value=\"Logout\">\n" +
"</form>    \n"+
"</body>\n" +
"</html>");

           
           
           
    }
}