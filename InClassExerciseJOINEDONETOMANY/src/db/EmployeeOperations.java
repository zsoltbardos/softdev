/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

public class EmployeeOperations {

    private Connection conn;
    private PreparedStatement pstmt;

    // This method opens a connection to the Oracle database
    public Connection openDB() {
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
        } catch (SQLException e) {
            System.out.print("Unable to load driver " + e.getMessage());
        }
        return conn;
    }

    public void dropEmployeeSequence() {
        try {
            String s2 = "drop sequence eid_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();
            System.out.println("Employee Sequence dropped");
        } catch (SQLException ex) {

        }
    }

    public void createEmployeeSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence eid_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("Employee Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with Employee Sequence " + ex.getMessage());
        }

    }

    public void dropDepartmentSequence() {
        try {
            String s2 = "drop sequence did_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();
            System.out.println("Department Sequence dropped");
        } catch (SQLException ex) {

        }
    }

    public void createDepartmentSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence did_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("Employee Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with department Sequence " + ex.getMessage());
        }

    }

    public void dropEmployeeTable() {
        System.out.println("Checking for existence of Employee table");
        try {
            String s1 = "DROP TABLE EmployeeJ";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("Employee table dropped");
        } catch (SQLException ex) {

        }
    }

    public void dropFTTable() {
        System.out.println("Checking for existence of FULLTIMEEMPLOYEE  table");
        try {
            String s1 = "DROP TABLE FULLTIMEEMPLOYEE ";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("FULLTIMEEMPLOYEE  table dropped");
        } catch (SQLException ex) {

        }
    }

    public void dropPTTable() {
        System.out.println("Checking for existence of PARTTIMEEMPLOYEE  table");
        try {
            String s1 = "DROP TABLE PARTTIMEEMPLOYEE ";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("PARTTIMEEMPLOYEE  table dropped");
        } catch (SQLException ex) {

        }
    }

    public void dropDEPTable() {
        System.out.println("Checking for existence of DEPARTMENT  table");
        try {
            String s1 = "DROP TABLE DEPARTMENT ";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("DEPARTMENT  table dropped");
        } catch (SQLException ex) {

        }
    }

    public void createEmployeetable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE EmployeeJ (empid NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "type VARCHAR2(5),"
                    + "name VARCHAR2(40),"
                    + "startDate DATE,"
                    + "depid INTEGER,"
                    + "FOREIGN KEY (depid) REFERENCES DEPARTMENT (did))";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "Employee table" + ex.getMessage());
        }
    }

    public void createFTtable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE FULLTIMEEMPLOYEE (empid NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "salary decimal(10,2),"
                    + "FOREIGN KEY (EMPID) REFERENCES EMPLOYEEJ (EMPID))";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "FULLTIMEEMPLOYEE table" + ex.getMessage());
        }
    }

    public void createPTtable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE PARTTIMEEMPLOYEE   (empid NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "rate decimal(10,2),"
                    + "hours decimal(10,2),"
                    + "FOREIGN KEY (EMPID) REFERENCES EMPLOYEEJ (EMPID))";

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "PARTTIMEEMPLOYEE table" + ex.getMessage());
        }
    }

    public void createDEPtable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE DEPARTMENT (did NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "depname VARCHAR2(30))";

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "DEPARTMENT table" + ex.getMessage());
        }
    }
    

    public void fillTables(){
        Statement stmt;
        try{
            // Insert data into table
            stmt = conn.createStatement();
            String sqld1 = "INSERT INTO DEPARTMENT VALUES (did_seq.nextVal,'IT')";
            stmt.executeUpdate(sqld1);
            
            String sqlemp1 = "INSERT INTO EmployeeJ VALUES (eid_seq.nextVal,'FT','Peter',TO_DATE('2015/01/23','yyyy/mm/dd'),did_seq.currVal)";
            stmt.executeUpdate(sqlemp1);
   
            String sqlft1 = "INSERT INTO FULLTIMEEMPLOYEE VALUES (eid_seq.currVal,35000)";
            stmt.executeUpdate(sqlft1);

            String sqlemp2 = "INSERT INTO EmployeeJ VALUES (eid_seq.nextVal,'FT','John',TO_DATE('2015/02/13','yyyy/mm/dd'),did_seq.currVal)";
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlemp2);
   
            String sqlft2 = "INSERT INTO FULLTIMEEMPLOYEE VALUES (eid_seq.currVal,60000)";
            stmt.executeUpdate(sqlft2);

            String sqlemp3 = "INSERT INTO EmployeeJ VALUES (eid_seq.nextVal,'FT','Mary',TO_DATE('2014/03/16','yyyy/mm/dd'),did_seq.currVal)";
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlemp3);
   
            String sqlft3 = "INSERT INTO FULLTIMEEMPLOYEE VALUES (eid_seq.currVal,67000)";
            stmt.executeUpdate(sqlft3);


            String sqld2 = "INSERT INTO DEPARTMENT VALUES (did_seq.nextVal,'HR')";
            stmt.executeUpdate(sqld2);
            
            String sqlemp4 = "INSERT INTO EmployeeJ VALUES (eid_seq.nextVal,'PT','Pat',TO_DATE('2014/04/29','yyyy/mm/dd'),did_seq.currVal)";
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlemp4);
   
            String sqlpt1 = "INSERT INTO PARTTIMEEMPLOYEE VALUES (eid_seq.currVal,34,45)";
            stmt.executeUpdate(sqlpt1); 
            
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
            System.out.println("Connection closed");
        } catch (SQLException ex) {
            System.out.println("Could not close connection " + ex.getMessage());
        }
    }
}
