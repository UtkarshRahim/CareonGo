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
"  <th>EXP\n" + 
"</tr>" );
 
 
  try {
             Class.forName("com.mysql.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/pro","root","123456");
             // Here dsnname- mydsn,user id- system(for oracle 10g),password is pintu.
             Statement stmt = con.createStatement();
             Statement stmt1 = con.createStatement();
             Statement stmt2= con.createStatement();
             ResultSet rs = stmt.executeQuery("Select PRODUCT_NAME from products");
          
                 while (rs.next()) {

                 String nm = rs.getString("PRODUCT_NAME");
                 
                 int s = 0; String a =null;
                 ResultSet rs1= stmt1.executeQuery("Select sum(QUANTITY) from inventory where PRODUCT_ID = (SELECT PRODUCT_ID from products where PRODUCT_NAME =\""+nm+"\")");
                if(rs1.next()){s=rs1.getInt("sum(Quantity)");}
                ResultSet rs2 = stmt2.executeQuery("Select EXP_DATE from inventory where PRODUCT_ID = (SELECT PRODUCT_ID from products where PRODUCT_NAME =\""+nm+"\")");
                if(rs2.next()){a=rs2.getString("EXP_DATE");}
              
             out.println("</td><td>" + nm + "</td><td>" + s +"</td><td>" + a+ "</td></tr>"); 
             }
      
             con.close();
            }
             catch(Exception e){
  out.println("SQLException caught: " + e.getMessage());
  }
         

out.println("</table><br><input type=\"submit\" value=\"GO BACK\">\n" +
"    </form></form>   </body>\n" +
"</html>");

        }
}