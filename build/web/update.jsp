<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
             
        <%
            String sel = request.getParameter("medicines");
                String val = request.getParameter("unit") ;
               Integer u= Integer.valueOf(val);
               String ins=null;
            try { 
            Class.forName("com.mysql.jdbc.Driver").newInstance();
                    } catch (Exception e) {
                    e.printStackTrace();
                    }
                  Connection conn =
                        DriverManager.getConnection("jdbc:mysql://localhost:3307/opm","root","123456");
            int a=0;
        
            String site = "check_stock.jsp";
            ResultSet rs1,rs2;
           Statement stat1 = conn.createStatement();
           PreparedStatement stat2=null;
        //   rs1=stat1.executeQuery("Select Amount from stock where Name = '"+medicines+"'");
        //   while(rs1.next()){
         //      ins= rs1.getString("Amount");
        //       }
         //  a = Integer.valueOf(ins) ;
          String sql="Update stock set Amount = Amount + ? where name = ? ";
           stat2 = conn.prepareStatement(sql);
            stat2.setInt(1,u);
            stat2.setString(2,sel);
            stat2.executeUpdate();
           
               conn.close();
                response.sendRedirect(site);
        %>
    
    
    
    
    </body>
</html>
