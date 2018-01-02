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
@WebServlet(urlPatterns = {"/product"})
public class product extends HttpServlet {

        public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
         PrintWriter out = res.getWriter();
       out.println("<html lang=\"en\">\n" +
"\n" +
"  <head>\n" +
"\n" +
"    <meta charset=\"utf-8\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
"    <meta name=\"description\" content=\"\">\n" +
"    <meta name=\"author\" content=\"\">\n" +
"\n" +
"    <title>Business Casual - Start Bootstrap Theme</title>\n" +
"\n" +
"    <!-- Bootstrap core CSS -->\n" +
"    <link href=\"vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
"\n" +
"    <!-- Custom fonts for this template -->\n" +
"    <link href=\"https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i\" rel=\"stylesheet\">\n" +
"    <link href=\"https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i\" rel=\"stylesheet\">\n" +
"\n" +
"    <!-- Custom styles for this template -->\n" +
"    <link href=\"css/business-casual.min.css\" rel=\"stylesheet\">\n" +
"\n" +
"  </head>\n" +
"\n" +
"  <body>\n" +
"\n" +
"    <h1 class=\"site-heading text-center text-white d-none d-lg-block\">\n" +
"      <span class=\"site-heading-upper text-primary mb-3\">Update stock</span>\n" +
"      <span class=\"site-heading-lower\"></span>\n" +
"    </h1>\n" +
"\n" +
"    <!-- Navigation -->\n" +
"    <nav class=\"navbar navbar-expand-lg navbar-dark py-lg-4\" id=\"mainNav\">\n" +
"      <div class=\"container\">\n" +
"        <a class=\"navbar-brand text-uppercase text-expanded font-weight-bold d-lg-none\" href=\"#\"></a>\n" +
"        <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarResponsive\" aria-controls=\"navbarResponsive\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
"          <span class=\"navbar-toggler-icon\"></span>\n" +
"        </button>\n" +
"        <div class=\"collapse navbar-collapse\" id=\"navbarResponsive\">\n" +
"          <ul class=\"navbar-nav mx-auto\">\n" +
"            <li class=\"nav-item px-lg-4\">\n" +
"              <a class=\"nav-link text-uppercase text-expanded\" href=\"index1.html\">Home\n" +
"                <span class=\"sr-only\">(current)</span>\n" +
"              </a>\n" +
"            </li>\n" +
"                       <li class=\"nav-item active px-lg-4\">\n" +
"              <a class=\"nav-link text-uppercase text-expanded\" href=\"products.jsp\">Products</a>\n" +
"            </li>\n" +
"            <li class=\"nav-item px-lg-4\">\n" +
"              <a class=\"nav-link text-uppercase text-expanded\" href=\"store.html\">Store</a>\n" +
"            </li>\n" +
"          </ul>\n" +
"        </div>\n" +
"      </div>\n" +
"    </nav>\n" +
"\n" +
"  \n" +
"\n" +
"\n" +
"    <!-- Bootstrap core JavaScript -->\n" +
"    <script src=\"vendor/jquery/jquery.min.js\"></script>\n" +
"    <script src=\"vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>\n" +
" <br><br><br>\n" +
"    <center>\n" +
"        <form action=\"update\">\n" +
"        <label style =\"font-size: 18px;\">Medicine</label>\n" +
"        <select class=\"form-control\" style=\"width:450px;\" name=\"medicines\" id=\"dropdown\" >\n" +
"            <option value =\"-1\">Select Medicine</option>");
       String medicines=null;
        int unit=0;
         try {
             Class.forName("com.mysql.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/opm","root","123456");
             // Here dsnname- mydsn,user id- system(for oracle 10g),password is pintu.
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("Select Name from stock");
          
                 while (rs.next()) {
                  String name=rs.getString("Name"); 
             out.println("<option value =\""+name+"\"> " + name +"</option>"); 
             }
                 
                 out.println("/select>\n" +
"                    <br><br><br><br>\n" +
"                    <label style=\"font-size: 18px;\"> Amount</label> <br><br>\n" +
"                    <input type=\"number\" name=\"unit\" id=\"unit\" style=\"width:300px;\"><br><br>\n" +
"    <input type=\"submit\" value=\"Submit\" style=\"color: #000;border: 1px #ddd solid;\">\n" +
"        </form>\n" +
"    \n" +
"    </center>");
                 req.setAttribute("medicines",medicines);
                 req.setAttribute("unit",unit);
             
             con.close();
            }
             catch (Exception e) {
             out.println("error");
         }
     out.println("</body>\n" +
"\n" +
"</html>\n" +
"");
    
        }
 }