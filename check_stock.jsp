<html>
<head>
<title>Stock check</title>
<link href='tab.css' rel='stylesheet' type='text/css'>
</head>

<body background="bg.jpg">
<table border="1">
<caption>Medicines</caption>
<tr>
  <th>S.No
  <th>Name
  <th>Price
  <th>Amount in stock
</tr>
<%@ page import ="java.sql.*" %>
<%
     try {
                Class.forName("com.mysql.jdbc.Driver");
                    } catch (Exception e) {
                    e.printStackTrace();
                    }

                String r = null;
                Connection conn =
                     DriverManager.getConnection("jdbc:mysql://localhost:3307/opm","root","123456");
                Statement stat = conn.createStatement();
               
              ResultSet rs = stat.executeQuery("Select * from stock") ;
 
              while (rs.next()) {
                  %>
           <tr>
               <td style="font-weight: bold;"><%= rs.getInt("S.No") %></td>
           <td style="font-weight: bold;"><%= rs.getString("Name") %></td>
           <td style="font-weight: bold;"><%= rs.getFloat("Price/tablet") %></td>
           <td style="font-weight: bold;"><%= rs.getInt("Amount") %></td>
            </tr>
        <% }
    rs.close();
    conn.close();
    %>
</table>   
</body>
</html>
                   