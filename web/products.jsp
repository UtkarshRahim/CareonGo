<!DOCTYPE html>
    <%@ page import ="java.sql.*" %>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Business Casual - Start Bootstrap Theme</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/business-casual.min.css" rel="stylesheet">

  </head>

  <body>

    <h1 class="site-heading text-center text-white d-none d-lg-block">
      <span class="site-heading-upper text-primary mb-3">Update stock</span>
      <span class="site-heading-lower"></span>
    </h1>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark py-lg-4" id="mainNav">
      <div class="container">
        <a class="navbar-brand text-uppercase text-expanded font-weight-bold d-lg-none" href="#"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav mx-auto">
            <li class="nav-item px-lg-4">
              <a class="nav-link text-uppercase text-expanded" href="index1.html">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
                       <li class="nav-item active px-lg-4">
              <a class="nav-link text-uppercase text-expanded" href="products.jsp">Products</a>
            </li>
            <li class="nav-item px-lg-4">
              <a class="nav-link text-uppercase text-expanded" href="store.html">Store</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

  


    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
 <br><br><br>
    <center>
        <form action="update.jsp">
        <label style ="font-size: 18px;">Medicine</label>
        <select class="form-control" style="width:450px;" name="medicines" id="dropdown" >
            <option value ="-1">Select Medicine</option>
            
            <%  
                 try {        
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                    } catch (Exception e) {
                    e.printStackTrace();
                    }
                      Connection conn =
                        DriverManager.getConnection("jdbc:mysql://localhost:3307/opm","root","123456");
                    
                String medicines=null;
                int unit=0;
                Statement stat = conn.createStatement();
                  ResultSet rs = stat.executeQuery("Select Name from stock") ;
                 while (rs.next())
               
                 {  String name=rs.getString("Name");    
                %>
                         <option value ="<%= name %>"><%=name %></option>
                         
                    <% } %>
                  
             
                        
        </select>
                    <br><br>     
                    <label style="font-size: 18px;"> Amount</label> <br>
                  <input type="number" name="unit" id="unit" style="width:300px;"><br>
    <input type="submit" value="Submit">
        </form>
    
    </center>
    <%  request.setAttribute("medicines", medicines);
               request.setAttribute("unit",unit);
      rs.close();
      conn.close();
      onClick.confirm("Are you sure?");
    %> 
  </body>

</html>
