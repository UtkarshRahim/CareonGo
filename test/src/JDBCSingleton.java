

import java.sql.*;
  
class JDBCSingleton {  
    
    private static JDBCSingleton instance;
    private JDBCSingleton()
    {
    
    //null;
    
    }
        
       public static JDBCSingleton getInstance()
       {
           if (instance==null)
           {
               instance=new JDBCSingleton();
               
           }
           return instance;
       
       }
    
    
         public Connection connect()throws ClassNotFoundException, SQLException  
          {  
                
               
      
        	 Class.forName("oracle.jdbc.driver.OracleDriver");  
        	   
        	
        	 Connection conn=DriverManager.getConnection(  
        	 "jdbc:oracle:thin:@localhost:1521:orcl","utkarsh","admin");  
             
              return conn;  
                
          }  
            
         public Statement state() throws ClassNotFoundException, SQLException 
         {
         
            Statement st = connect().createStatement() ;
             
         return st;
         }
       public ResultSet result(String sql) throws ClassNotFoundException, SQLException 
         {
         
            ResultSet rs = state().executeQuery(sql) ;
             
         return rs;
         }
       
       public PreparedStatement prepare(String sql1) throws ClassNotFoundException, SQLException 
       {
       
          PreparedStatement ps = connect().prepareStatement(sql1) ;
           
       return ps;
 }