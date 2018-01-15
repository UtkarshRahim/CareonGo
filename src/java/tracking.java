import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wms
 */
@WebServlet(urlPatterns = {"/tracking"})

public class tracking extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter out = response.getWriter() ;

            String cid = request.getParameter("cid");
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
" <th>NAME"  +
"<th>Amounting"  +
                     " <th>TIME"+
                     " <th>PRODUCTS"+
                     " <th>ADDRESS"+
"</tr>");  

              try {
            /* TODO output your page here. You may use following sample code. */
             Class.forName("com.mysql.jdbc.Driver");
                 
              String t = null;
                 Connection conn =
            DriverManager.getConnection("jdbc:mysql://localhost:3307/pro","root","123456");
                     Statement stmt=conn.createStatement();
              }
                catch(ClassNotFoundException | SQLException e){
  out.println("SQLException caught: " + e.getMessage());
  }
             
             
             
             
             
             
  }
}