package ex1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author x00157506
 */

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;

public class StudentDB {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rset;
    
    public void openDB() {
        try {
            // Load the Oracle JDBC driver
            OracleDataSource ods = new OracleDataSource();

            ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");               
            ods.setUser("x00157506");
            ods.setPassword("db16Apr93");
           // ods.setURL("jdbc:oracle:thin:hr/hr@localhost:1521/XE");
            //ods.setUser("hr");
            //ods.setPassword("passhr");

            conn = ods.getConnection();
            System.out.println("connected.");
        } catch (SQLException e) {
            System.out.print("Unable to load driver " + e);
            System.exit(1);
        }
    }
    
    public void closeDB() {
        try {
            pstmt.close();
            rset.close();
            conn.close();
            System.out.print("Connection closed");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    public void dropTables() {
        System.out.println("Checking for existing tables.");
        
        try {
            String sql1 = "DROP TABLE Student";
            // Get a Statement object.
            pstmt = conn.prepareStatement(sql1);
            pstmt.executeUpdate();
            System.out.println("Student table dropped");
            
        } catch (SQLException ex) {
            System.out.println("ERROR in drop: " + ex.getMessage());
        }
    }
    
    public void buildStudentTable(){
        try {
            String sql1 = "CREATE TABLE STUDENT"
                    + "(id NUMBER PRIMARY KEY NOT NULL,"
                    + "name VARCHAR(40), age NUMBER)";
            pstmt = conn.prepareStatement(sql1);
            pstmt.executeUpdate();

            String sql2 = "INSERT INTO STUDENT VALUES(?, ?, ?)";
            pstmt = conn.prepareStatement(sql2);
            
            pstmt.setInt(1, 1);
            pstmt.setString(2, "John");
            pstmt.setInt(3, 23);
            pstmt.executeUpdate();
            
            pstmt.setInt(1, 2);
            pstmt.setString(2, "Paul");
            pstmt.setInt(3, 18);
            pstmt.executeUpdate();
            
            pstmt.setInt(1, 3);
            pstmt.setString(2, "Mary");
            pstmt.setInt(3, 19);
            pstmt.executeUpdate();
            
            pstmt.setInt(1, 4);
            pstmt.setString(2, "Ken");
            pstmt.setInt(3, 43);
            pstmt.executeUpdate();
  
            
        } catch (SQLException ex) {
            System.out.println("ERROR in build: " + ex.getMessage());     
        }
        
    }
}
