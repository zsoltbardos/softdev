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
    
    public void dropContactSequence() {
        try {
            String s2 = "drop sequence cid_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();
            System.out.println("Contact Sequence dropped");
        } catch (SQLException ex) {

        }
    }

    public void createContactSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence cid_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("Contact Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with contact Sequence " + ex.getMessage());
        }

    }
    
    public void dropEmployeeTable() {
        String s1 = "DROP TABLE EMPLOYEE14";
        System.out.println("Checking for existing tables.");

        try {
            pstmt = conn.prepareStatement(s1);
            // Drop the Contacts table.
            pstmt.execute();
            System.out.println("EMPLOYEE14 table dropped.");
        } catch (SQLException ex) {
            System.out.println("ERROR in dropemployee(): " + ex.getMessage());
        }
    }
    
    public void createEmployeeTable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE EMPLOYEE14 (empid NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "type VARCHAR2(5),"
                    + "name VARCHAR2(40),"
                    + "startDate DATE)";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            
            System.out.println("Employee table created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "Employee table" + ex.getMessage());
        }
    }
    
    public void createContactTable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE Contact (cid NUMBER PRIMARY KEY NOT NULL, "
                    + "cname VARCHAR2(40),"
                    + "address VARCHAR2(50),"
                    + "phone VARCHAR2(50),"
                    + "email VARCHAR2(50),"
                    + "eid INTEGER,"
                    + "FOREIGN KEY (eid) REFERENCES EMPLOYEE14 (empid))";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            
            System.out.println("Contact table created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "Contact table" + ex.getMessage());
        }
    }
       
    public void dropContactTable() {
        String s1 = "DROP TABLE Contact";
        System.out.println("Checking for existing tables.");

        try {
            pstmt = conn.prepareStatement(s1);
            // Drop the Contacts table.
            pstmt.execute();
            System.out.println("Contact table dropped.");
        } catch (SQLException ex) {
            System.out.println("ERROR in dropContactTable(): " + ex.getMessage());
        }
    }


    public void fillTables(){
        Statement stmt;
        try{
            // Insert data into table
            stmt = conn.createStatement();
            
            String sqlemp1 = "INSERT INTO EMPLOYEE14 VALUES "
                    + "(eid_seq.nextVal,'FT','Peter',TO_DATE('2015/01/23','yyyy/mm/dd'))";
            stmt.executeUpdate(sqlemp1);
   
            String sqlft1 = "INSERT INTO FULLTIMEEMPLOYEE VALUES (eid_seq.currVal,50000)";
            stmt.executeUpdate(sqlft1);            

            String sqlemp2 = "INSERT INTO EMPLOYEE14 VALUES "
                    + "(eid_seq.nextVal,'PT','John',TO_DATE('2015/02/13','yyyy/mm/dd'))";
            stmt.executeUpdate(sqlemp2);
   
            String sqlft2 = "INSERT INTO PARTTIMEEMPLOYEE VALUES (eid_seq.currVal,45,70)";
            stmt.executeUpdate(sqlft2);

            String sqlemp3 = "INSERT INTO EMPLOYEE14 VALUES "
                    + "(eid_seq.nextVal,'FT','Mary',TO_DATE('2014/03/16','yyyy/mm/dd'))";
            stmt.executeUpdate(sqlemp3);
   
            String sqlft3 = "INSERT INTO FULLTIMEEMPLOYEE VALUES (eid_seq.currVal,40000)";
            stmt.executeUpdate(sqlft3);
            
            String sqlemp4 = "INSERT INTO EMPLOYEE14 VALUES "
                                        + "(eid_seq.nextVal,'PT','Pat',TO_DATE('2014/04/29','yyyy/mm/dd'))";
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlemp4);
   
            String sqlpt1 = "INSERT INTO PARTTIMEEMPLOYEE VALUES (eid_seq.currVal,30.5,40)";
            stmt.executeUpdate(sqlpt1); 

            String sqlemp5 = "INSERT INTO EMPLOYEE14 VALUES "
                    + "(eid_seq.nextVal,'FT','Karen',TO_DATE('2014/04/29','yyyy/mm/dd'))";
            stmt.executeUpdate(sqlemp5);
   
            String sqlft4 = "INSERT INTO FULLTIMEEMPLOYEE VALUES (eid_seq.currVal,65000)";
            stmt.executeUpdate(sqlft4);
            
            System.out.println("Employee table populated");
        } catch (SQLException ex) {
            System.out.println("SQL Exception inserting values into "
                    + "Employee table: " + ex.getMessage());
        }
    }
    
    public void fillContactTable(){
        Statement stmt;
        try{
            // Insert data into table
            stmt = conn.createStatement();
            
            String sqlContact1 = "INSERT INTO Contact VALUES "
                    + "(1, 'Mary', '2 Grafton St', '0876545678', 'mary@grafton.com', 1)";
            stmt.executeUpdate(sqlContact1);
            
            String sqlContact2 = "INSERT INTO Contact VALUES "
                    + "(2, 'Ella', '2 Jones St', '0865432345', 'ella@jones.com', 2)";
            stmt.executeUpdate(sqlContact2);
            
            String sqlContact3 = "INSERT INTO Contact VALUES "
                    + "(3, 'Seamus', '12 Henry St', '0879876543', 'seamus@henry.com', 3)";
            stmt.executeUpdate(sqlContact3);
            
            System.out.println("Contact table populated");
        } catch (SQLException ex) {
            System.out.println("SQL Exception inserting values into "
                    + "Contact table: " + ex.getMessage());
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
    
    public void dropFTTable() {
        String s1 = "DROP TABLE FullTimeEmployee";
        System.out.println("Checking for existing tables.");

        try {
            pstmt = conn.prepareStatement(s1);
            pstmt.execute();
            System.out.println("FullTimeEmployee table dropped.");
        } catch (SQLException ex) {
            // No need to report an error.
            // The table simply did not exist.
        }
    }
    
    public void dropPTTable() {
        String s1 = "DROP TABLE PartTimeEmployee";
        System.out.println("Checking for existing tables.");

        try {
            pstmt = conn.prepareStatement(s1);
            pstmt.execute();
            System.out.println("PartTimeEmployee table dropped.");
        } catch (SQLException ex) {
            // No need to report an error.
            // The table simply did not exist.
        }
    }
    
    public void createFTTable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE FULLTIMEEMPLOYEE (empid NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "salary decimal(10,2),"
                    + "FOREIGN KEY (EMPID) REFERENCES EMPLOYEE14 (EMPID))";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            
            System.out.println("FTT table created");

        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "FULLTIMEEMPLOYEE table" + ex.getMessage());
        }
    }
    
    public void createPTTable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE PARTTIMEEMPLOYEE   (empid NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "rate decimal(10,2),"
                    + "hours decimal(10,2),"
                    + "FOREIGN KEY (EMPID) REFERENCES EMPLOYEE14 (EMPID))";

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            
            System.out.println("PTT table created");

        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "PARTTIMEEMPLOYEE table" + ex.getMessage());
        }
    }




}
