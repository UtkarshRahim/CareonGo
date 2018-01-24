
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;  



@WebServlet(urlPatterns = {"/NewServlet1"})
public class NewServlet1 extends HttpServlet {

  
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   		JDBCSingleton jdbc = JDBCSingleton.getInstance();

           PrintWriter out = response.getWriter();
           HttpSession session = request.getSession();
            int cid = (Integer) session.getAttribute("cid");
           String[] vals = request.getParameterValues("array[]");
           int size=vals.length; 
            float sum=0;
         
         


           
           out.println("<html>\n" +
"	<head>\n" +
"		<title>Careongo Pharmacy </title>\n" +
"\n" +
"		<link href='http://fonts.googleapis.com/css?family=Graduate' rel='stylesheet' type='text/css'>\n" +
"		<![if !IE]>\n" +
"		<link href=\"style.css\" rel='stylesheet' type='text/css'>\n" +
"		<![endif]>\n" +
"	</head>\n" +
"<body><h1>Welcome "+"</h1>"  +
"<form method = \"get\" action=\"NewServlet2\">\n" +
"  SUM : <br>");
           

          
        	
      
           try {
        	   ResultSet rs,rs1,rs2;
               String nm = null;
               	int a=0;
             int i=0;
            String t = null;
            Date date = new Date();
            
            Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.SECOND, 1);
    Date strDate = calendar.getTime();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    String strDate1= formatter.format(strDate);  

              Timestamp timestamp = new Timestamp(new Date().getTime());
          
            
                  String p = null;
                  
                     float p1 = 0;
                     float p2=0;
                    
                while(i<size)
                {
                    
                    if("" != vals[i])
                   {
                       p2=Float.valueOf(vals[i]);
                rs = jdbc.result("Select PRODUCT_NAME from products where PRODUCT_ID = '"+(i+1)+"'");
                if(rs.next()){nm=rs.getString("PRODUCT_NAME");
                   }
                 
                rs1=jdbc.result("Select distinct UNIT_SELL_PRICE from inventory where PRODUCT_ID ='"+(i+1)+"'");
                if(rs1.next())
                {p= rs1.getString("UNIT_SELL_PRICE");
                p1= Float.valueOf(p);
                }
                   
                  
                float price = p1*p2;
              
                sum=sum+price;
                   }
              i+=1;
                
                }
                String id = timestamp+" Placed"+cid ;
                
         out.println(id);
          String sql = "insert into ORDERS (PRICE,TIME,STATUS,CUSTOMER_ID,COLUMN_ID) values (?,?,?,?,?)";
         PreparedStatement pst = jdbc.prepare(sql);
          pst.setFloat(1,sum);
          pst.setTimestamp(2, timestamp);
          pst.setString(3,"PLACED");
          pst.setInt(4,cid);
          pst.setString(5, id);
          pst.executeUpdate();
          
          out.print(vals);
          
       
          
          out.println(strDate1);
         
          rs2=jdbc.result("Select ORDER_ID from ORDERS where COLUMN_ID= '"+id+"'" );
          if(rs2.next()){a=rs2.getInt("ORDER_ID");}
           i=0;
   out.println(a) ;      
           while(i<size)
             {
                 
                 if("" != vals[i])
                {
          
          String sql1= "Insert into ORDER_DET(ORDER_ID,PRODUCT_ID,QUANTITY) values (?,?,?)" ;
          PreparedStatement ps1 = jdbc.prepare(sql1);
          ps1.setInt(1,a);
          ps1.setInt(2,(i+2));
          ps1.setString(3,vals[i]);
          ps1.executeUpdate();
                }
               i+=1;
             }
           
           session.setAttribute("a1",a);
           session.setAttribute("cid",cid);
          
             
           }
           catch(ClassNotFoundException | SQLException e){
  out.println("SQLException caught: " + e.getMessage());
  }
           
           out.println(sum);
           out.println(" <br><br>\n" +
          		  "  <input type=\"submit\" value=\"PAY\">\n" +
          		  "</form>    \n" +
          		  "</body>\n" +
          		  "</html>");  
           
   
        }
}
