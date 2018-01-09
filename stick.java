/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
@WebServlet(urlPatterns = {"/stick"})
public class stick extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
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
"<form method = \"post\" action=\"stokinsert\">\n" +
"  Name:<br>\n" +
"  <input type=\"text\" name=\"p_name\" required >\n" +
"  <br>\n" +
" Batch no.<br>\n" +
"  <input type=\"text\" name=\"batch\" required >\n" +
"  <br>\n" +
"  MFG:<br>\n" +
"  <input type=\"date\" name=\"mfd\" required >\n" +
"  <br>\n" +
"  EXP:<br>\n" +
"  <input type=\"date\" name=\"exp\" required >\n" +
"  <br><br>\n" +
"   Quantity :<br>\n" +
"  <input type=\"number\" name=\"quant\" required >\n" +
"  <br>\n" +
"   Purchase price:<br>\n" +
"  <input type=\"text\" name=\"price_p\" required >\n" +
"  <br>\n" +
"   Selling price:<br>\n" +
"  <input type=\"text\" name=\"price_s\" required>\n" +
"  <br> \n" +
"   Company:<br>\n" +
"  <input type=\"text\" name=\"company\" required>\n" +
"  <br> <br>\n");
         out.println("<label style =\"font-size: 18px;\">Type</label> required \n" +
"      <select class=\"form-control\" style=\"width:450px;\" name=\"type\" id=\"dropdown\" >\n" +
"            <option value =\"tablet\">Tablet</option>\n" +
"            <option value= \"capsule\">Capsule</option>\n" +
"            <option value =\"syrup\">Syrup</option>\n" +
"            <option value =\"cream\">Cream</option>\n" +
"            <option value =\"balm\">Balm</option>\n" +
"            <option value =\"powder\">Powder</option>\n" +
"      </select>");
         
                out.println("<br><br><label style =\"font-size: 18px;\">Dealer</label>" +
"        <select class=\"form-control\" style=\"width:450px;\" name=\"dname\" id=\"dropdown\" >\n\" +\n" +
"          <option value =\"-1\">Dealer</option>");
                String dname= null;
                 int d_id=0;
             
              try {
                   
            /* TODO output your page here. You may use following sample code. */
             Class.forName("com.mysql.jdbc.Driver");
                 ResultSet rs;
              String name ;
                  Connection conn =
            DriverManager.getConnection("jdbc:mysql://localhost:3307/prototype","root","123456");
//                     Statement stmt=conn.createStatement();
                     
                   
                      Statement stmt1 = conn.createStatement();
             ResultSet rs1 = stmt1.executeQuery("Select SUPPLIER_NAME from suppliers");
             
         
                 while (rs1.next()) {
                   name=rs1.getString("SUPPLIER_NAME"); 
             out.println("<option value =\""+name+"\"> " + name +"</option>"); 
                  }
                 request.setAttribute("dname",dname);
                 
                out.println(dname);
              conn.close();
              }
          
        catch(Exception e){
  out.println("SQLException caught: " + e.getMessage());
  }
     out.println(" <input type=\"submit\" value=\"Submit\">\n" +
"</form>     \n" +
"</body>\n" +
"</html>\n" +
"");       
    }
}

