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
                    + "mem_medical_condition VARCHAR2(100),"
                    + "mship_id INTEGER,"
                    + "FOREIGN KEY (mship_id) REFERENCES MEMBERSHIP (mship_id))";


            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("TABLE Member created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "Member table" + ex.getMessage());
        }
    }
    
    // Address Table
    public void dropMembershipTable() {
        System.out.println("Checking for existence of MEMBERSHIP table");
        try {
            String s1 = "DROP TABLE MEMBERSHIP CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("MEMBERSHIP table dropped");
        } catch (SQLException ex) {

        }
    }

    public void createMembershipTable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE MEMBERSHIP (MSHIP_ID NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "mship_type VARCHAR2(90),"
                    + "mship_price FLOAT,"
                    + "mship_duration INTERVAL DAY [3] TO SECOND)";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("TABLE MEMBERSHIP created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "MEMBERSHIP table" + ex.getMessage());
        }
    }

    // Student Table
    public void dropClassTable() {
        System.out.println("Checking for existence of Student table");
        try {
            String s1 = "DROP TABLE CLASS CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("Class table dropped");
        } catch (SQLException ex) {

        }
    }

    public void createClassTable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE CLASS (class_id NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "class_name VARCHAR2(50),"
                    + "class_day VARCHAR2(40),"
                    + "class_time VARCHAR2(50))";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("TABLE CLASS created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "CLASS table" + ex.getMessage());
        }
    }
    //Drop Grant Student table

    public void dropTrainerTable() {
        System.out.println("Checking for existence of TRAINER  table");
        try {
            String s1 = "DROP TABLE TRAINER CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("TRAINER  table dropped");
        } catch (SQLException ex) {

        }
    }

    public void createTrainerTable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE TRAINER (trainer_id NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "trainer_first_name VARCHAR2(255),"
                    + "trainer_last_name VARCHAR2(255),"
                    + "trainer_phone VARCHAR2(255),"
                    + "trainer_email VARCHAR2(255))";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("TABLE TRAINER created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "TRAINER table" + ex.getMessage());
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
