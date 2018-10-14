/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.util;


import com.milford.churchcms.dao.User;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.Optional;

public class DbUtil {
    static final String JDBC_DRIVER = "org.h2.Driver";   
    static final String DB_URL = "jdbc:h2:~/user";  
    static final String USER = "sa"; 
    static final String PASS = ""; 
      
    public static Optional<User> getUser(String userId){
  
      User user;
      Connection conn = null; 
      PreparedStatement stmt = null; 
      try { 
         // STEP 1: Register JDBC driver 
         Class.forName(JDBC_DRIVER); 
             
         //STEP 2: Open a connection 
         System.out.println("Connecting to database..."); 
         conn = DriverManager.getConnection(DB_URL,USER,PASS);  
         
         //STEP 3: Execute a query 
         System.out.println("Creating table in given database..."); 
         stmt = conn.prepareStatement("select * where userId=?"); 
         stmt.setString(1, userId);
         ResultSet rs = stmt.executeQuery();
         while (rs.next()) {
             user = new User(rs.getInt("ID"),rs.getString("USERID"),rs.getString("PASSWORD"),
                     rs.getString("FIRSTNAME"),rs.getString("LASTNAME"), null);
        }
          
         stmt.close(); 
         conn.close(); 
      } catch(SQLException se) { 
         //Handle errors for JDBC 
         se.printStackTrace(); 
      } catch(Exception e) { 
         //Handle errors for Class.forName 
         e.printStackTrace(); 
      } finally { 

         try{ 
            if(stmt!=null) stmt.close(); 
         } catch(SQLException se2) { 
         } try { 
            if(conn!=null) conn.close(); 
         } catch(SQLException se){ 
            se.printStackTrace(); 
         } 
      }
        return null;
    }
}
