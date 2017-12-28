
<%@page import="java.awt.*"%>
<%@ page import ="java.sql.*" %>
<%
                 
                String rl = "admin";
                String site = "http://localhost:8080/admin/index1.html";
               String u= request.getParameter("user_name");
               String p= request.getParameter("pass_word");
                
           /*    try {
                Class.forName("com.mysql.jdbc.Driver");
                    } catch (Exception e) {
                    e.printStackTrace();
                    }

                String r = null;
                Connection conn =
                     DriverManager.getConnection("jdbc:mysql://localhost:3307/opm","root", "123456");
                Statement stat = conn.createStatement();
               
              //  ResultSet rs = stat.executeQuery("Select username,password from users;");
 
              //  while (rs.next()) {
              //      r=rs.getString("Role");
                   
            //    }
              */  if((u.equals("admin") && p.equals("root")))
                {
                    session.setAttribute("username",u);
               response.sendRedirect(site);
                }
                else 
                    out.println("Invalid password <a href='index.html'>try again</a>");
               
                
               
                
            %>
