import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*; 


/**
 *
 * @author wms
 */
@WebServlet(urlPatterns = {"/display"})
public class display extends HttpServlet {

        public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
         PrintWriter out = res.getWriter();
       out.println("<html>\n" +
"<head>\n" +
"<title>Stock check</title>\n" +
"<link href='tab.css' rel='stylesheet' type='text/css'>\n" +
"</head>\n" +
"\n" +
"<body background=\"bg.jpg\">\n" +
"<table border=\"1\">\n" +
"<caption>Medicines</caption>\n" +
"<tr>\n" +
"  <th>S.No\n" +
"  <th>Name\n" +
"  <th>Price\n" +
"  <th>Amount in stock\n" +
"</tr>");
        
         try {
             Class.forName("com.mysql.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/opm","root","123456");
             // Here dsnname- mydsn,user id- system(for oracle 10g),password is pintu.
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("Select * from stock");
          
                 while (rs.next()) {
                 int n = rs.getInt("S.No");
                 String nm = rs.getString("Name");
                 float s = rs.getFloat("Price/tablet"); 
                 int a=rs.getInt("Amount");
             out.println("<tr><td>" + n + "</td><td>" + nm + "</td><td>" + s +"</td><td>" + a+ "</td></tr>"); 
             }
             
             con.close();
            }
             catch (Exception e) {
             out.println("error");
         }
     out.println("</table>   \n" +
"</body>\n" +
"</html>");
     
        }
 }