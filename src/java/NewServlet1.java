/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;  
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 *
 * @author wms
 */
@WebServlet(urlPatterns = {"/NewServlet1"})
public class NewServlet1 extends HttpServlet {

  
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           PrintWriter out = response.getWriter();
           String[] vals = request.getParameterValues("array[]");
           int size=vals.length; 
            float sum=0;
           
     String a=null;
           out.println("<html>\n" +
"	<head>\n" +
"		<title>Careongo Pharmacy </title>\n" +
"\n" +
"		<link href='http://fonts.googleapis.com/css?family=Graduate' rel='stylesheet' type='text/css'>\n" +
"		<![if !IE]>\n" +
"		<link href=\"style.css\" rel='stylesheet' type='text/css'>\n" +
"		<![endif]>\n" +
"	</head>\n" +
"<body>\n" +
"<form action=\"NewServlet2\">\n" +
"  SUM : <br>");
           try {
            /* TODO output your page here. You may use following sample code. */
             Class.forName("com.mysql.jdbc.Driver");
                 ResultSet rs,rs1,rs2;
                 String nm = null;
      
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
                 Connection conn =
            DriverManager.getConnection("jdbc:mysql://localhost:3307/pro","root","123456");
                     Statement stmt=conn.createStatement();
                     Statement stmt2= conn.createStatement();
             
                  String p = null;
                  
                     float p1 = 0;
                     float p2=0;
                    
                while(i<size)
                {
                    
                    if("" != vals[i])
                   {
                       p2=Float.valueOf(vals[i]);
                rs = stmt.executeQuery("Select PRODUCT_NAME from products where PRODUCT_ID = \""+(i+1)+"\"");
                if(rs.next()){nm=rs.getString("PRODUCT_NAME");
                   }
                 
                rs1=stmt.executeQuery("Select distinct UNIT_SELL_PRICE from inventory where PRODUCT_ID =\""+(i+1)+"\"");
                if(rs1.next())
                {p= rs1.getString("UNIT_SELL_PRICE");
                p1= Float.valueOf(p);
                }
                   
                  
                float price = p1*p2;
              
                sum=sum+price;
                   }
              i+=1;
                
                }

           
           

             String sql = "insert into ORDERS (PRICE,TIME) values (?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
             pst.setFloat(1,sum);
             pst.setTimestamp(2, timestamp);
             
             pst.executeUpdate();
             
             
             String s1= String.valueOf(strDate1);
            
             rs2=stmt.executeQuery("Select ORDER_ID from ORDERS where TIME= \""+s1+"\"" );
             if(rs2.next()){a=rs2.getString("ORDER_ID");}
              i=0;
             
              while(i<size)
                {
                    
                    if("" != vals[i])
                   {
             
             String sql1= "Insert into ORDER_DET(ORDER_ID,PRODUCT_ID,QUANTITY) values (?,?,?)" ;
             PreparedStatement ps1 = conn.prepareStatement(sql1);
             ps1.setString(1,a);
             ps1.setInt(2,(i+1));
             ps1.setString(3,vals[i]);
             ps1.executeUpdate();
                   }
                  i+=1;
                }
              
 
             
             
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

 HttpSession session = request.getSession();
 session.setAttribute("a1",a);

     }
}
