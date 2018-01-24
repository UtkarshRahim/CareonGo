import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*; 

@WebServlet(urlPatterns = {"/dispsup"})
public class dispsup extends HttpServlet {

        public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
      		JDBCSingleton jdbc = JDBCSingleton.getInstance();

        	PrintWriter out = res.getWriter();
 out.println("<html>\n" +
"	<head>\n" +
"		<title>Careongo Pharmacy </title>\n" +
"\n" +
"		<link href='http://fonts.googleapis.com/css?family=Graduate' rel='stylesheet' type='text/css'>\n" +
"		<![if !IE]>\n" +
"		<link href=\"style.css\" rel='stylesheet' type='text/css'>\n" +
"		<![endif]>\n" +
"	</head>\n" +
"<body><form method = \"get\" action =\"retail.html\">"  + "<table border=\"1\">\n" +
"<caption>Medicines</caption>\n" +
"<tr>\n" +
"  <th>Name\n" +
"  <th>Quantity\n" +
"  <th>Selling Price\n" + 
"</tr>" );
 
 
  try {
             
           Date date = null;
             ResultSet rs = jdbc.result("Select PRODUCT_NAME from products");
          
                 while (rs.next()) {

                 String nm = rs.getString("PRODUCT_NAME");
                 
                 int s = 0; String a =null;
                 ResultSet r = jdbc.result("SELECT PRODUCT_ID from products where PRODUCT_NAME ='"+nm+"'");
                 while(r.next())
                 {  
                 int pd = r.getInt("PRODUCT_ID");
                 ResultSet rs1= jdbc.result("Select sum(QUANTITY) from inventory where PRODUCT_ID ="+pd+"");
               if(rs1.next()){s=rs1.getInt("sum(Quantity)");}
                ResultSet rs2 = jdbc.result("Select UNIT_SELL_PRICE from inventory where PRODUCT_ID ="+pd+"");
                if(rs2.next()){a=rs2.getString("UNIT_SELL_PRICE");
                
//               date = Date.valueOf(a);
//                out.println(date);
//                
                }
                 
             out.println("</td><td>" + nm + "</td><td>" + s +"</td><td>" + a+ "</td></tr>"); 
             }}
      
             jdbc.connect().close();
            }
             catch(Exception e){
  out.println("SQLException caught: " + e.getMessage());
  }
         

out.println("</table><br><input type=\"submit\" value=\"GO BACK\">\n" +
"    </form></form>   </body>\n" +
"</html>");

        }
}