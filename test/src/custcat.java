
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/custcat"})

public class custcat extends HttpServlet {
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  		JDBCSingleton jdbc = JDBCSingleton.getInstance();

         PrintWriter out = response.getWriter();
           HttpSession session=request.getSession();  
         int cid = (Integer) session.getAttribute("cid");
         
        try {
            /* TODO output your page here. You may use following sample code. */
           
                 String nm = null;
                 int s=0;
              String t = null;
              String des=null;
                
                 
                     
                     
                 out.println("<html>\n" +
"	<head>\n" +
"		<title>Careongo Pharmacy </title>\n" +
"\n" +
"		<link href='http://fonts.googleapis.com/css?family=Graduate' rel='stylesheet' type='text/css'>\n" +
"		<![if !IE]>\n" +
"		<link href=\"style.css\" rel='stylesheet' type='text/css'>\n" +
"		<![endif]>\n" +
"	</head>\n" +
"<body><h1>Welcome "+cid+"</h1>" + " <form method = \"get\" action= 'NewServlet1' >");
                 out.println("<table border=\"1\">\n" +
"<caption>Medicines</caption>\n" +
"<tr>\n" +
"  <th>Product\n" +
"  <th>Price/quantity in Rs \n" +
"  <th>Available Quantity\n" +
"  <th>Type\n" +
           "  <th>Description\n" +              
"  <th>Needed Quantity\n" +
"</tr>");
                 
                                 
                 
                 ResultSet rs=null;
                 ResultSet rs2=null;
                 ResultSet rs3=null;
                 ResultSet rs4=null;
                 ResultSet rs5=null;
                 ResultSet rs11 = null;    
                            
                            
              rs = jdbc.result("Select PRODUCT_NAME from products ORDER BY PRODUCT_ID ASC");
                    int i=1;
                 while (rs.next()) {
                 String n = rs.getString("PRODUCT_NAME");
                 
                 rs11=jdbc.result("Select PRODUCT_ID from products where PRODUCT_NAME ='"+n+"'");
                 if(rs11.next())
                 {
              int pd = rs11.getInt("PRODUCT_ID");
                 
                 rs2=jdbc.result("Select UNIT_SELL_PRICE from inventory where PRODUCT_ID = "+pd+"");     
                 if(rs2.next()){
                    nm = rs2.getString("UNIT_SELL_PRICE");}

                rs3 =jdbc.result("Select SUM(QUANTITY) as \"SUM\" from inventory where PRODUCT_ID = "+pd+"");     
              if(rs3.next())
              {
             s = rs3.getInt("SUM"); }
              
             rs4=jdbc.result("Select TYPE from inventory where PRODUCT_ID ="+pd+"");     
               if(rs4.next()){t= rs4.getString("TYPE");}
               
               rs5 = jdbc.result("Select Description from products where PRODUCT_NAME ='"+n+"' ");
               if(rs5.next())
               {des = rs5.getString("Description");}
                 }
              out.println("<tr><td>" + n + "</td><td>" + nm + "</td><td>" + s +"</td><td>" + t+"</td><td>" +des+"</td><td> <input type=\"number\" name=\"array[]\" min =\"1\" max = \""+s+"\"></td></tr>"); 
                 
              i++;

                 }
                  out.println("</table>   \n" +"<br><br><input type=\"submit\" value=\"Submit\">\n"+
"</form></body>\n" +
"</html>");    
                 session.setAttribute("cid",cid);
            
                
      jdbc.connect().close();     
        }
       
    catch(ClassNotFoundException | SQLException e){
  out.println("SQLException caught: " + e.getMessage());
  }  
       
     
         
    }
}

   