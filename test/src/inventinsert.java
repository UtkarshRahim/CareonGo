
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;





/**
 *
 * @author wms
 */

@WebServlet(urlPatterns = {"/inventinsert"})
public class inventinsert extends HttpServlet {

   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  		JDBCSingleton jdbc = JDBCSingleton.getInstance();
         PrintWriter out = response.getWriter();
       
         
                String dname= null;
                String pname=null;
                 int d_id=0;
                 String prod_name = null;
                 out.println("<html>\n" +
                		 "	<head>\n" +
                		 "		<title>Careongo Pharmacy </title>\n" +
                		 "\n" +
                		 "		<link href='http://fonts.googleapis.com/css?family=Graduate' rel='stylesheet' type='text/css'>\n" +
                		 "		<![if !IE]>\n" +
                		 "		<link href=\"style.css\" rel='stylesheet' type='text/css'>\n" +
                		 "		<![endif]>\n" +
                		 "	</head>\n" +
                		 "<body>\n" +
                		 "        <form action= 'inventins' >\n" +
                		 "          <input type=\"radio\" name=\"p_id\" value=\"p_id1\" \n" +
                		 "onclick=\"document.getElementById('sel').removeAttribute('disabled')\">Product already exists?\n" +
                		 "        <select id=\"sel\" name=\"pname\" disabled>");  
              try {
                 
           
                String name1,name2 ;
                 
             ResultSet rs1 = jdbc.result("Select PRODUCT_NAME from products");
             
         
                 while (rs1.next()) {
                   name1=rs1.getString("PRODUCT_NAME"); 
             out.println("<option value =\""+name1+"\"> " + name1 +"</option>"); 
                  }
                 request.setAttribute("pname",pname);
                 
              
              out.println("<br><br><input type=\"radio\" name=\"p_id\" value=\"p_id\" \n" +
"onclick=\"document.getElementById('text').removeAttribute('disabled')\" \n" +
">You don't? Enter the product name\n" +
"        <input type=\"text\" id=\"text\" name=\"p_name\" \n" +
"               placeholder=\"Product Name\" required disabled>\n");
              out.println("<br><br><label style =\"font-size: 18px;\">Dealer</label>" +
"        <select class=\"form-control\" style=\"width:450px;\" name=\"dname\" id=\"dropdown\" >\n\" +\n" +
"          <option value =\"-1\">Dealer</option>");
     
                      
             ResultSet rs = jdbc.result("Select SUPPLIER_NAME from suppliers");
             
         
                 while (rs.next()) {
                   name2=rs.getString("SUPPLIER_NAME"); 
             out.println("<option value =\""+name2+"\"> " + name2 +"</option>"); 
                  }
                 request.setAttribute("dname",dname);
                 
              out.println("</select><br><br>Batch number<br>\n" +
"  <input type=\"text\" name=\"b_no\" required>\n" +
"  <br>\n" +
"  Mfg :<br>\n" +
"  <input type=\"date\" name=\"mfg\" required>\n" +
"  <br>\n" +
"  Exp :<br>\n" +
"  <input type=\"date\" name=\"exp\" required>\n" +
"  <br>\n" +
"  Quantity :<br>\n" +
"  <input type=\"number\" name=\"quantity\" required>\n" +
"  <br>\n" +
"  Sales price <br>\n" +
"  <input type=\"text\" name=\"sp\" required>\n" +
"  <br>\n"  +
"  Purchase prie<br>\n" +
"  <input type=\"text\" name=\"pp\" required>\n" + "Description <br>"+ 
"  <input type=\"text\" name=\"des\" required>\n" +
"  <br>\n"+
"  <br>");
               out.println("<label style =\"font-size: 18px;\">Type</label> required \n" +
"      <select class=\"form-control\" style=\"width:450px;\" name=\"type\" id=\"dropdown\" >\n" +
"            <option value =\"tablet\">Tablet</option>\n" +
"            <option value= \"capsule\">Capsule</option>\n" +
"            <option value =\"syrup\">Syrup</option>\n" +
"            <option value =\"cream\">Cream</option>\n" +
"            <option value =\"balm\">Balm</option>\n" +
"            <option value =\"powder\">Powder</option>\n" +
"      </select>");
              
              jdbc.connect().close();
              }
              
              
        catch(ClassNotFoundException | SQLException e){
  out.println("SQLException caught: " + e.getMessage());
  }
        out.println("<input type=\"submit\" value=\"Submit\">\n" +
"</form>    \n" +
"</body>\n" +
"</html>\n");
    }
}

