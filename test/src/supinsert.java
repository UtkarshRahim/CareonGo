
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author wms
 */
@WebServlet(urlPatterns={"/supinsert"})
public class supinsert extends HttpServlet {

  
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  		JDBCSingleton jdbc = JDBCSingleton.getInstance();
         PrintWriter out = response.getWriter();
              String dealer_name = request.getParameter("dealer_name");
             String d_address = request.getParameter("d_address");
                String d_phone = request.getParameter("d_phone");
                String d_email = request.getParameter("d_email");
        try {
            
                
              
                  
              
            String sql = "insert into suppliers (SUPPLIER_NAME,EMAIL_ADDRESS,PHONE_NUMBER,ADDRESS) values (?,?,?,?)";
            PreparedStatement pst =jdbc.prepare(sql);
             pst.setString(1,dealer_name);
              pst.setString(2,d_email);
              pst.setString(3,d_phone);
             pst.setString(4,d_address);
             
            
             
              pst.executeUpdate();
              
               out.println("<html>\n" +
"<head>\n" +
"<title>Dealer Insert</title>\n" +
"<link href='tab.css' rel='stylesheet' type='text/css'>\n" +
"</head>\n" +
"\n" +
"<body background=\"bg.jpg\">\n" +
"<table border=\"1\">\n" +
"<caption>Medicines</caption>\n" +
"<tr>\n" +
"  <th>SID\n" +
"  <th>SName\n" +
"  <th>SEmail\n" +
"  <th>Sphone\n" + "<th>SAddress \n"+
"</tr>");
              
               response.sendRedirect("retail.html");
          
               	jdbc.connect().close();
        
        
        
        }
       
        
        catch(Exception e){
  out.println("SQLException caught: " + e.getMessage());
  }
    }
}
    