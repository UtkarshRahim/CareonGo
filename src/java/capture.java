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
import javax.servlet.annotation.WebServlet;
//import java.sql.ResultSet;
//import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.DriverManager;
//import java.sql.Statement;
import java.sql.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat; 
/**
 *
 * @author wms
 */
@WebServlet(urlPatterns = {"/capture "})

public class capture extends HttpServlet {
 

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           PrintWriter out = response.getWriter();
           String[] vals = request.getParameterValues("array[]");
           int size=vals.length; 
           
          
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
//calendar.add(Calendar.SECOND, 1);
Date strDate = calendar.getTime();
               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    String strDate1= formatter.format(strDate);  
   
              Timestamp timestamp = new Timestamp(new Date().getTime());
                 Connection conn =
            DriverManager.getConnection("jdbc:mysql://localhost:3307/pro","root","123456");
                     Statement stmt=conn.createStatement();
                     Statement stmt2= conn.createStatement();
             
                  String p = null;
                  String a=null;
                     float p1 = 0;
                     float p2=0;
                     float sum=0;
                while(i<size)
                {
                    
                    if("" != vals[i])
                   {
                       p2=Float.valueOf(vals[i]);
                rs = stmt.executeQuery("Select PRODUCT_NAME from products where PRODUCT_ID = \""+(i+1)+"\"");
                if(rs.next()){nm=rs.getString("PRODUCT_NAME");
                    out.println("\n"+nm);}
                 
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
           out.println(sum);
           
           

             String sql = "insert into ORDERS (PRICE,TIME) values (?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
             pst.setFloat(1,sum);
             pst.setTimestamp(2, timestamp);
             
             pst.executeUpdate();
             
             
             String s1= String.valueOf(strDate1);
             out.println(strDate1);
             rs2=stmt.executeQuery("Select ORDER_ID from ORDERS where TIME= \""+s1+"\"" );
             if(rs2.next()){a=rs2.getString("ORDER_ID");}
              i=0;
              out.println(a);
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
              
              ResultSet rs3 = stmt.executeQuery("Select PRODUCT_ID,QUANTITY from ORDER_DET where ORDER_ID = \""+a+"\"");
           
              while(rs3.next()){
           int pid=rs3.getInt("PRODUCT_ID") ;
           int quantity = rs3.getInt("QUANTITY");
           String sql5=null;
           ResultSet rs4 = stmt2.executeQuery("Select BATCH_NUMBER,QUANTITY from inventory where PRODUCT_ID = \""+pid+"\"");
           while(rs4.next())
           {
               String b = rs4.getString("BATCH_NUMBER");
               int supply=rs4.getInt("QUANTITY");
               
               if (supply<=quantity)
               {
                    quantity=quantity-supply;
                 supply=0;
                 sql5= "Update inventory set QUANTITY = 0 where BATCH_NUMBER = \""+b+"\"";
               
               }
               
               else {
               if(supply>quantity)
               {
                   int rem= supply-quantity;
               sql5 = "Update inventory set QUANTITY = '"+rem+"' where BATCH_NUMBER = \""+b+"\"";
               
               }
               }
               
                PreparedStatement ps2= conn.prepareStatement(sql5);
                ps2.executeUpdate();
               
               }
                   
            }
           
           
           
           
           }
            catch(ClassNotFoundException | SQLException e){
  out.println("SQLException caught: " + e.getMessage());
  }  

   
}
}
