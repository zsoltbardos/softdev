/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author furry
 */
public class SCOperations {

    private Connection conn;
    private PreparedStatement pstmt;

    // This method opens a connection to the Oracle database
    public Connection openDB() {
        try {
            OracleDataSource ods = new OracleDataSource();

            // Tallaght
//             ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
//             ods.setUser("pmagee");
//             ods.setPassword("tupulo");
            // Home Oracle XE
            ods.setURL("jdbc:oracle:thin:HR/pmagee@localhost:1521:XE");
            ods.setUser("hr");
            ods.setPassword("passhr");

            conn = ods.getConnection();
            System.out.println("connected.");
        } catch (SQLException e) {
            System.out.print("Unable to load driver " + e.getMessage());
        }
        return conn;
    }

    public void dropStaffSequence() {
        try {
            String s2 = "drop sequence sid_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();
            System.out.println("STAFF Sequence dropped");
        } catch (SQLException ex) {

        }
    }

    public void createStaffSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence sid_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("STAFF Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with STAFF Sequence " + ex.getMessage());
        }

    }

    public void dropStaffTable() {
        System.out.println("Checking for existence of STAFF table");
        try {
            String s1 = "DROP TABLE STAFF";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("STAFF table dropped");
        } catch (SQLException ex) {

        }
    }

    public void createStafftable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE STAFF (sid NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "sname VARCHAR2(40))";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "STAFF table" + ex.getMessage());
        }
    }

    public void dropContactSequence() {
        try {
            String s2 = "drop sequence cid_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();
            System.out.println("CONTACT Sequence dropped");
        } catch (SQLException ex) {

        }
    }

    public void createContactSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence cid_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("CONTACT Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with CONTACT Sequence " + ex.getMessage());
        }

    }

    public void dropContactTable() {
        System.out.println("Checking for existence of CONTACT table");
        try {
            String s1 = "DROP TABLE CONTACT";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("CONTACT table dropped");
        } catch (SQLException ex) {

        }
    }

    public void createContactstable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE CONTACT (cid NUMBER PRIMARY KEY NOT NULL, "
                    + "CNAME VARCHAR2(25),"
                    + "ADDRESS VARCHAR2(25),"
                    + "PHONE VARCHAR2(15),"
                    + "EMAIL VARCHAR2(25),"
                    + "STAFF_ID INTEGER,"
                    + "FOREIGN KEY (STAFF_ID) REFERENCES STAFF(SID))";

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "CONTACT table" + ex.getMessage());
        }
    }

}
