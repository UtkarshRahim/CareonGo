


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/processal"})
public class processal extends HttpServlet {

    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  		JDBCSingleton jdbc = JDBCSingleton.getInstance();

PrintWriter out = response.getWriter();

String orderid= request.getParameter("id");
int oid= 0;

out.println("<html>\n" +
"	<head>\n" +
"		<title>Careongo Pharmacy </title>\n" +
"		<link href='http://fonts.googleapis.com/css?family=Graduate' rel='stylesheet' type='text/css'>\n" +
"		<![if !IE]>\n" +
"		<link href=\"style.css\" rel='stylesheet' type='text/css'>\n" +
"		<![endif]>\n" +
"	</head>\n" +
"<body><form action=\"alert\""
             + "<br>" );
             out.println("<table border=\"1\">"  +
"<caption>Medicines</caption>"  +
"<tr>"  +
" <th>OrderID"  +
"<th>Amounting"  +
                     " <th>TIME"+"<th>Customer"+"<th>Address"+
"</tr>");  
   try {
           
                 ResultSet rs1 = jdbc.result("Select ORDER_ID,PRICE,TIME,CUSTOMER_ID from orders where ORDER_ID = '"+orderid+"'");  
                 
                 while(rs1.next())
                 {
                     String cname = null;
                     String add = null;
                     oid= rs1.getInt("ORDER_ID");
                     float price=rs1.getFloat("PRICE");
                     String time = String.valueOf(rs1.getTimestamp("TIME"));
                     int cid = rs1.getInt("CUSTOMER_ID");
                     
                     String sq = "Select CUSTOMER_NAME,ADDRESS from customers where CUSTOMER_ID = '"+cid+"'";
                     ResultSet r = jdbc.result(sq);
                     while(r.next())
                     {
                      cname = r.getString("CUSTOMER_NAME");
                      add= r.getString("ADDRESS");
                     }
             out.println("<tr><td>" + oid + "</td><td>" + price + "</td><td>" + time +"</td><td>" + cname+"</td><td>" + add+"</td></tr>"); 
                 }
                 
                  out.println("</table>   \n" +"<br><br><input type=\"submit\" value=\"SHIP AND UPDATE\">\n"+
"</form></body>\n" +
"</html>");
         
              String sql5= "Update ORDERS set STATUS = 'SHIPPED' where ORDER_ID = '"+oid+"'";
                PreparedStatement ps2= jdbc.prepare(sql5);
                ps2.executeUpdate();
         
   
    }
    catch(ClassNotFoundException | SQLException e){
  out.println("SQLException caught: " + e.getMessage());
  }

    }
}
