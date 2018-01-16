

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/login1"})
public class login1 extends HttpServlet {

      protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter out = response.getWriter() ;
         int cid=0;
         HttpSession session=request.getSession();  
         String user= request.getParameter("uname");
         String pass = request.getParameter("pass");
         String cname = null;
       out.println("<html>\n" +
"    <head>\n " +
"        <title>Customer info</title>\n" +
"    <link href='http://fonts.googleapis.com/css?family=Graduate' rel='stylesheet' type='text/css'>    \n" +
"    <link href=\"style1.css\" rel='stylesheet' type='text/css'>\n" +
"    </head>\n" +
"    <style>\n" +
"     body  {\n" +
"    background-image: url(\"bg.jpg\");\n" +
"    background-color: #cccccc;\n" +
"    \n" +
"     background-repeat: no-repeat;\n" +
"}</style>\n" +
"    <body><center>");
         
         try {
            /* TODO output your page here. You may use following sample code. */
             Class.forName("com.mysql.jdbc.Driver");
                 
              String t = null;
              
                 Connection conn =
            DriverManager.getConnection("jdbc:mysql://localhost:3307/pro","root","123456");
                     Statement stmt=conn.createStatement();
                     Statement stmt1=conn.createStatement();
                     ResultSet rs = stmt.executeQuery("Select CUSTOMER_ID,CUSTOMER_NAME from customers where Username = \""+user+"\" AND PASSWORD = \""+pass+"\""); 
                     while(rs.next())
                     { cid = rs.getInt("CUSTOMER_ID");
                        cname= rs.getString("CUSTOMER_NAME");
                     }
                     session.setAttribute("cid",cid);
                    out.println("<h1> Welcome "+cname + " </h1>\n" +
"    </center>\n" +
"        <br>\n" +
"        \n" + "<font size =\"16 \">"+
"        <p f>What would you like to do ?</p>\n" + "</font>"+
"      <font size =\" 20 \">  <a href =\"custcat\">Order Products</a><br>\n" +
"        <a href =\"afterlogin\"\">Track order</a></font>");   
                       
                       
                       
                       
                       

       
              
             
          conn.close();
                     
                     
                     
                     
         }
      catch(ClassNotFoundException | SQLException e){
  out.println("SQLException caught: " + e.getMessage());
  }
      }
}