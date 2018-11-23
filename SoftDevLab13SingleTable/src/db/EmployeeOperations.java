/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author x00157506
 */
public class EmployeeOperations {
    private Connection conn;
    private PreparedStatement pstmt;
    
    public void openDB() {
        try {
            OracleDataSource ods = new OracleDataSource();

	    // Tallaght
             ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
             ods.setUser("x00157506");
             ods.setPassword("db16Apr93");
            
            // Home Oracle XE
//            ods.setURL("jdbc:oracle:thin:HR/pmagee@localhost:1521:XE");
//            ods.setUser("hr");
//            ods.setPassword("passhr");

            conn = ods.getConnection();
            System.out.println("connected.");
        } catch (Exception e) {
            System.out.print("Unable to load driver " + e);
            System.exit(1);
        }
    }
    
    public void dropEmployeeSequence() {
        String s1 = "drop sequence eid_seq";
        System.out.println("Checking for existing sequences.");

        try {
            pstmt = conn.prepareStatement(s1);
            // Drop the Contacts table.
            pstmt.execute();
            System.out.println("Sequence dropped.");
        } catch (SQLException ex) {
            // No need to report an error.
            // The sequence simply did not exist.
        }
    }
    
    public void createEmployeeSequence(){
        try{         
            String createseq = "create sequence eid_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq);
            pstmt.executeUpdate();
            System.out.println("Sequence Created");
        } catch (SQLException e) {
            System.out.println("ERROR in CreateEmployeeSequence() method: " + e.getMessage());
        }
    }
    
    public void dropEmployeeTable() {
        String s1 = "DROP TABLE Employee13EX1";
        System.out.println("Checking for existing tables.");

        try {
            pstmt = conn.prepareStatement(s1);
            // Drop the Contacts table.
            pstmt.execute();
            System.out.println("Employee13EX1 table dropped.");
        } catch (SQLException ex) {
            // No need to report an error.
            // The table simply did not exist.
        }
    }
    
    public void createEmployeeTable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE Employee13EX1 (id NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "type VARCHAR2(5),"
                    + "name VARCHAR2(40),"
                    + "startDate DATE,"
                    + "salary DECIMAL(8,2),"
                    + "rate DECIMAL(5,2)," 
                    + "hours DECIMAL(5,2))";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "Employee table" + ex.getMessage());
        }
        
    }
    
    public void fillTables(){
        Statement stmt;
        try{
            // Insert data into table
            stmt = conn.createStatement();

            
            String sql1 = "INSERT INTO Employee13EX1 VALUES "
                    + "(eid_seq.nextVal,'FT','Peter',TO_DATE('2015/01/23','yyyy/mm/dd'),"
                    + "50000, NULL,NULL)";
            stmt.executeUpdate(sql1);
            String sql2 = "INSERT INTO Employee13EX1 VALUES"
                    + " (eid_seq.nextVal,'PT','John',TO_DATE('2015/02/13','yyyy/mm/dd'),"
                    + "NULL, 45,70)";
            stmt.executeUpdate(sql2);
            String sql3 = "INSERT INTO Employee13EX1 VALUES "
                    + "(eid_seq.nextVal,'FT','Mary',TO_DATE('2014/03/16','yyyy/mm/dd'),"
                    + "40000,NULL,NULL)";
            stmt.executeUpdate(sql3);

            String sql4 = "INSERT INTO Employee13EX1 VALUES "
                    + "(eid_seq.nextVal,'PT','Pat',TO_DATE('2014/04/29','yyyy/mm/dd'),"
                    + "NULL,30.5,40)";
            stmt.executeUpdate(sql4);
            String sql5 = "INSERT INTO Employee13EX1 VALUES "
                    + "(eid_seq.nextVal,'FT','Karen',TO_DATE('2014/03/16','yyyy/mm/dd'),"
                    + "65000,NULL,NULL)";
            stmt.executeUpdate(sql5);
            
            System.out.println("Employee table populated");
        } catch (SQLException ex) {
            System.out.println("SQL Exception inserting values into "
                    + "Employee table" + ex.getMessage());
        }
    }
    
    public void closeDB() {
        try {
            pstmt.close();
            conn.close();
            System.out.print("Connection closed");
        } catch (SQLException e) {
            System.out.print("Could not close connection ");
        }
    }


}
