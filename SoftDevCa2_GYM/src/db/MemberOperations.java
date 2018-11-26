/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author x00157506
 */

public class MemberOperations {

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

    //sequences
    public void dropMemberSequence() {
        try {
            String s2 = "drop sequence memid_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();
            System.out.println("Member Sequence dropped");
        } catch (SQLException ex) {

        }
    }

    public void createMemberSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence memid_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("Member Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with Member Sequence " + ex.getMessage());
        }

    }

    public void dropMembershipSequence() {
        try {
            String s2 = "drop sequence mshipid_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();
            System.out.println("Membership Sequence dropped");
        } catch (SQLException ex) {

        }
    }

    public void createMembershipeSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence mshipid_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("Membership Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with Membership Sequence " + ex.getMessage());
        }

    }

    public void dropClassSequence() {
        try {
            String s2 = "drop sequence classid_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();
            System.out.println("Class Sequence dropped");
        } catch (SQLException ex) {

        }
    }

    public void createClassSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence classid_seq increment "
                    + "by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("Class Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with Class Sequence "
                    + ex.getMessage());
        }
    }

    public void dropTrainerSequence() {
        try {
            String s2 = "drop sequence trainerid_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();
            System.out.println("Trainer Sequence dropped");
        } catch (SQLException ex) {

        }
    }

    public void createTrainerSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence trainerid_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("Trainer Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with Trainer Sequence " + ex.getMessage());
        }

    }

    //Department Table
    public void dropMemberTable() {
        System.out.println("Checking for existence of Member table");
        try {
            String s1 = "DROP TABLE MEMBER CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("Member table dropped");
        } catch (SQLException ex) {

        }
    }

    public void createMemberTable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE MEMBER (mem_id NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "mem_first_name VARCHAR2(50),"
                    + "mem_last_name VARCHAR2(50),"
                    + "mem_gender VARCHAR2(50),"
                    + "mem_phone VARCHAR2(50),"
                    + "mem_email VARCHAR2(50),"
                    + "mem_address VARCHAR2(50),"
                    + "mem_dob DATE,"
                    + "mem_medical_condition VARCHAR2(100))";


            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("TABLE Member created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "Member table" + ex.getMessage());
        }
    }
    
    // Address Table
    public void dropAddressTable() {
        System.out.println("Checking for existence of ADDRESS table");
        try {
            String s1 = "DROP TABLE ADDRESS CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("ADDRESS table dropped");
        } catch (SQLException ex) {

        }
    }

    public void createAddresstable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE ADDRESS (ADDID NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "ADDNAME VARCHAR2(90),"
                    + "EIRCODE VARCHAR2(40))";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("TABLE ADDRESS created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "ADDRESS table" + ex.getMessage());
        }
    }

    // Student Table
    public void dropStudentTable() {
        System.out.println("Checking for existence of Student table");
        try {
            String s1 = "DROP TABLE Student CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("Student table dropped");
        } catch (SQLException ex) {

        }
    }

    public void createStudenttable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE Student (sid NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "TYPE VARCHAR2(5),"
                    + "name VARCHAR2(40),"
                    + "dob DATE,"
                    + "course VARCHAR2(40),"
                    + "depid INTEGER,"
                    + "addressID INTEGER,"
                    + "FOREIGN KEY (depid) REFERENCES DEPARTMENT (did),"
                    + "FOREIGN KEY (addressID) REFERENCES ADDRESS (addid))";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("TABLE Student created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "Student table" + ex.getMessage());
        }
    }
    //Drop Grant Student table

    public void dropGSTable() {
        System.out.println("Checking for existence of GRANTSTUDENT  table");
        try {
            String s1 = "DROP TABLE GRANTSTUDENT CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("GRANTSTUDENT  table dropped");
        } catch (SQLException ex) {

        }
    }

    // Grant Student
    public void createGStable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE GRANTSTUDENT (sid NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "grantAmount decimal(10,2),"
                    + "FOREIGN KEY (SID) REFERENCES STUDENT (SID))";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("TABLE GRANTSTUDENT created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "GRANTSTUDENT table" + ex.getMessage());
        }
    }

    //Drop Non Grant Student table
    public void dropNGTable() {
        System.out.println("Checking for existence of NONGRANTSTUDENT  table");
        try {
            String s1 = "DROP TABLE NONGRANTSTUDENT CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("NONGRANT  table dropped");
        } catch (SQLException ex) {

        }
    }

    // Non Grant Student
    public void createNGtable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE NONGRANTSTUDENT (sid NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "feesDue decimal(10,2),"
                    + "FOREIGN KEY (SID) REFERENCES STUDENT (SID))";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("TABLE NONGRANTSTUDENT created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "NONGRANTSTUDENT table" + ex.getMessage());
        }
    }
        //Drop Module table
    public void dropModuleTable() {
        System.out.println("Checking for existence of MODULE15  table");
        try {
            String s1 = "DROP TABLE MODULE15 CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("MODULE15  table dropped");
        } catch (SQLException ex) {

        }
    }

    // Create Module Table
        public void createModuletable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE MODULE15 (mid NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "name VARCHAR2(40),"
                    + "hours decimal(10,2))";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("TABLE MODULE15 created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "MODULE15 table" + ex.getMessage());
        }
    }
    // Drop STUDENTMODULE Table
    public void dropSTUDENTMODULETable() {
        System.out.println("Checking for existence of STUDENTMODULE  table");
        try {
            String s1 = "DROP TABLE STUDENTMODULE CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("STUDENTMODULE table dropped");
        } catch (SQLException ex) {

        }
    }

    // Create STUDENTMODULE Table
    public void createSTUDENTMODULEtable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE STUDENTMODULE (sid NUMBER, "
                    + "mid NUMBER,"
                    + "PRIMARY KEY (SID, mID),"
                    + "FOREIGN KEY (SID) REFERENCES STUDENT (SID) ,"
                    + "FOREIGN KEY (MID) REFERENCES MODULE15 (MID))";

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("TABLE STUDENTMODULE created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "STUDENTMODULE table" + ex.getMessage());
        }
    }
    
    // Fill Department table
    public void fillDepartmentTable(){
         try {
            String sql = "INSERT INTO DEPARTMENT VALUES(did_seq.nextVal,?)";
            pstmt = conn.prepareStatement(sql);
                       
            pstmt.setString(1, "Computing");
            pstmt.executeUpdate();            
            
            pstmt.setString(1, "Business");
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL Exception filling "
                    + "DEPARTMENT table" + ex.getMessage());
        }
    }
    
    // Fill Address Table
    public void fillAddressTable() {
        try {
            String sql = "INSERT INTO ADDRESS VALUES(adid_seq.nextVal,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, "2 Grafton St");
            pstmt.setString(2, "D02 Y527");
            pstmt.executeUpdate();

            pstmt.setString(1, "4 Henry St");
            pstmt.setString(2, "D01 VE04");
            pstmt.executeUpdate();

            pstmt.setString(1, "3 Moore St");
            pstmt.setString(2, "D01 W5C0");
            pstmt.executeUpdate();

            pstmt.setString(1, "107 Dorset St");
            pstmt.setString(2, "D01 W9P4");
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Exception filling "
                    + "STAFF table" + ex.getMessage());
        }
    }

    
    // Fill Student table
    public void fillStudentTable(){
         try {
            String sql = "INSERT INTO STUDENT VALUES(sid_seq.nextVal,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
                       
            pstmt.setString(1,"GS");
            pstmt.setString(2,"Peter");
            pstmt.setDate(3,Date.valueOf("1996-01-23"));
            pstmt.setString(4,"HC Information Technology Management");
            pstmt.setInt(5,1);
            pstmt.setInt(6,1);
            pstmt.executeUpdate();

            pstmt.setString(1,"NG");
            pstmt.setString(2,"James");
            pstmt.setDate(3,Date.valueOf("1991-03-24"));
            pstmt.setString(4,"BSc Computer Security");
            pstmt.setInt(5,1);
            pstmt.setInt(6,2);
            pstmt.executeUpdate();
 
            pstmt.setString(1,"NG");
            pstmt.setString(2,"Caroline");
            pstmt.setDate(3,Date.valueOf("1975-05-13"));
            pstmt.setString(4,"HC Business Management");
            pstmt.setInt(5,2);
            pstmt.setInt(6,3);
            pstmt.executeUpdate();
            
            pstmt.setString(1,"GS");
            pstmt.setString(2,"Geoffrey");
            pstmt.setDate(3,Date.valueOf("1985-02-23"));
            pstmt.setString(4,"BBs Accounting");
            pstmt.setInt(5,2);
            pstmt.setInt(6,4);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Exception filling "
                    + "STUDENT table" + ex.getMessage());
        }
    }
    
        // Fill GrantStudent table
    public void fillGSTable(){
         try {
            String sql = "INSERT INTO GRANTSTUDENT VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
                       
            pstmt.setInt(1,1);
            pstmt.setDouble(2,6500);
            pstmt.executeUpdate();

            pstmt.setInt(1,4);
            pstmt.setDouble(2,3500);
            pstmt.executeUpdate();
         } catch (SQLException ex) {
            System.out.println("SQL Exception filling "
                    + "GRANTSTUDENT table" + ex.getMessage());
        }
    }

    // Fill Non Grant Student table
    public void fillNGTable(){
         try {
            String sql = "INSERT INTO NONGRANTSTUDENT VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
                       
            pstmt.setInt(1,2);
            pstmt.setDouble(2,3000);
            pstmt.executeUpdate();

            pstmt.setInt(1,3);
            pstmt.setDouble(2,2000);
            pstmt.executeUpdate();
            
         } catch (SQLException ex) {
            System.out.println("SQL Exception filling "
                    + "NONGRANTSTUDENT table" + ex.getMessage());
        }
    }
     
        // Fill Module Table
    public void fillModuleTable() {
        try {
            String sql = "INSERT INTO MODULE15 VALUES(mid_seq.nextVal,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,"SDEV3");
            pstmt.setInt(2, 7);
            pstmt.executeUpdate();

            pstmt.setString(1,"DATABASES");
            pstmt.setInt(2,5);
            pstmt.executeUpdate();

            pstmt.setString(1,"Law");
            pstmt.setInt(2, 3);
            pstmt.executeUpdate();

            pstmt.setString(1,"Maths");
            pstmt.setInt(2,2);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Exception filling "
                    + "MODULE15 table" + ex.getMessage());
        }
    }
    
    //Fill STUDENTMODULE table
         public void fillSTUDENTMODULETable(){
         try {
            String sql = "INSERT INTO STUDENTMODULE VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
                       
            pstmt.setInt(1,1);
            pstmt.setInt(2,1);
            pstmt.executeUpdate();
            
            pstmt.setInt(1,1);
            pstmt.setInt(2,2);
            pstmt.executeUpdate();
            

            pstmt.setInt(1,2);
            pstmt.setInt(2,1);
            pstmt.executeUpdate();
            
            pstmt.setInt(1,2);
            pstmt.setInt(2,2);
            pstmt.executeUpdate();
            
            pstmt.setInt(1,3);
            pstmt.setInt(2,3);
            pstmt.executeUpdate();
            
            pstmt.setInt(1,3);
            pstmt.setInt(2,4);

            pstmt.setInt(1,4);
            pstmt.setInt(2,3);
            pstmt.executeUpdate();
            
            pstmt.setInt(1,4);
            pstmt.setInt(2,4);
            pstmt.executeUpdate();

         } catch (SQLException ex) {
            System.out.println("SQL Exception filling "
                    + "STUDENTMODULE table" + ex.getMessage());
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
