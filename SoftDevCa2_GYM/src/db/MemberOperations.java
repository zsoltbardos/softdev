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

    public void createMembershipSequence() {
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
                    + "mem_medical_con VARCHAR2(100),"
                    + "mship_id NUMBER,"
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
    public void dropClassTable() {
        System.out.println("Checking for existence of Class table");
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
            String sql = "CREATE TABLE Class (class_id NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "class_name VARCHAR2(100),"
                    + "class_day VARCHAR2(100),"
                    + "class_time VARCHAR2(100),"
                    + "trainer_id NUMBER NOT NULL,"
                    + "FOREIGN KEY (trainer_id) REFERENCES TRAINER (trainer_id))";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("TABLE Class created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "Class table" + ex.getMessage());
        }
    }

    // Student Table
    public void dropMembershipTable() {
        System.out.println("Checking for existence of Membership table");
        try {
            String s1 = "DROP TABLE MEMBERSHIP CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("Membership table dropped");
        } catch (SQLException ex) {

        }
    }

    public void createMembershipTable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE MEMBERSHIP (mship_id NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "mship_type VARCHAR2(255),"
                    + "mship_price NUMBER,"
                    + "mship_duration NUMBER)";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("TABLE MEMBERSHIP created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "Membership table" + ex.getMessage());
        }
    }
    //Drop Grant Student table

    public void dropTrainerTable() {
        System.out.println("Checking for existence of Trainer  table");
        try {
            String s1 = "DROP TABLE TRAINER CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("Trainer  table dropped");
        } catch (SQLException ex) {

        }
    }

    // Grant Student
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
    
    public void dropMember_ClassTable() {
        System.out.println("Checking for existence of Member_Class  table");
        try {
            String s1 = "DROP TABLE MEMBER_CLASS CASCADE CONSTRAINTS";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("Member_Class  table dropped");
        } catch (SQLException ex) {

        }
    }
    
    public void createMember_ClassTable() {       
        try {
            String sql = "CREATE TABLE MEMBER_CLASS (mem_id NUMBER, "
                    + "class_id NUMBER,"
                    + "PRIMARY KEY (mem_id, class_id),"
                    + "FOREIGN KEY (mem_id) REFERENCES MEMBER (mem_id) ,"
                    + "FOREIGN KEY (class_id) REFERENCES CLASS (class_id))";

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("TABLE MEMBER_CLASS created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "MEMBER_CLASS table" + ex.getMessage());
        }
    }

   
    
    // Fill Department table
    public void fillMemberTable(){
         try {
            String sql = "INSERT INTO MEMBER VALUES(memid_seq.nextVal,?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
                       
            pstmt.setString(1, "Peter");
            pstmt.setString(2, "Pan");
            pstmt.setString(3, "Male");
            pstmt.setString(4, "123456789");
            pstmt.setString(5, "peter.pan@gmail.com");
            pstmt.setString(6, "Neverland");
            pstmt.setDate(7, Date.valueOf("1991-01-05"));
            pstmt.setString(8, "test");
            pstmt.setInt(9,1);
            pstmt.executeUpdate();            
            
            pstmt.setString(1, "Arnold");
            pstmt.setString(2, "Schwarzenegger");
            pstmt.setString(3, "Male");
            pstmt.setString(4, "123456789");
            pstmt.setString(5, "arnold.chwarzenegger@gmail.com");
            pstmt.setString(6, "California");
            pstmt.setDate(7, Date.valueOf("1991-01-05"));
            pstmt.setString(8, "test");
            pstmt.setInt(9,1);
            pstmt.executeUpdate();    
            

        } catch (SQLException ex) {
            System.out.println("SQL Exception filling "
                    + "Member table" + ex.getMessage());
        }
    }
    
    public void fillMembershipTable(){
         try {
            String sql = "INSERT INTO MEMBERSHIP VALUES(mshipid_seq.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(sql);
                       
            pstmt.setString(1, "Gym Only");
            pstmt.setDouble(2, 60.0);
            pstmt.setString(3, "30");          
            pstmt.executeUpdate();  
            
            pstmt.setString(1, "Gym Only");
            pstmt.setDouble(2, 90.0);
            pstmt.setString(3, "60");          
            pstmt.executeUpdate(); 
            
            pstmt.setString(1, "Gym Only");
            pstmt.setDouble(2, 200);
            pstmt.setString(3, "180");          
            pstmt.executeUpdate(); 
            
            pstmt.setString(1, "Gym Only");
            pstmt.setDouble(2, 350);
            pstmt.setString(3, "360");          
            pstmt.executeUpdate(); 
            
            pstmt.setString(1, "Gym and Pool");
            pstmt.setDouble(2, 80.0);
            pstmt.setString(3, "30");          
            pstmt.executeUpdate();  
            
            pstmt.setString(1, "Gym and Pool");
            pstmt.setDouble(2, 130.0);
            pstmt.setString(3, "60");          
            pstmt.executeUpdate(); 
            
            pstmt.setString(1, "Gym and Pool");
            pstmt.setDouble(2, 280);
            pstmt.setString(3, "180");          
            pstmt.executeUpdate(); 
            
            pstmt.setString(1, "Gym and Pool");
            pstmt.setDouble(2, 420.0);
            pstmt.setString(3, "360");          
            pstmt.executeUpdate(); 
            
             System.out.println("membership table filled");

        } catch (SQLException ex) {
            System.out.println("SQL Exception filling "
                    + "Membership table" + ex.getMessage());
        }
    }
    
    public void fillMember_ClassTable(){
         try {
            String sql = "INSERT INTO MEMBER_CLASS VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
                       
            pstmt.setInt(1, 1);
            pstmt.setInt(2, 1);        
            pstmt.executeUpdate();  
            
             System.out.println("member_class table filled");

        } catch (SQLException ex) {
            System.out.println("SQL Exception filling "
                    + "member_class table" + ex.getMessage());
        }
    }
    
    public void fillTrainerTable(){
         try {
            String sql = "INSERT INTO Trainer VALUES(trainerid_seq.nextVal,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
                       
            pstmt.setString(1, "Johnny");
            pstmt.setString(2, "Bravo");
            pstmt.setString(3, "johnny.bravo@gmail.com");
            pstmt.setString(4, "123456789");          
            pstmt.executeUpdate();  
            
             System.out.println("Trainer table filled");

        } catch (SQLException ex) {
            System.out.println("SQL Exception filling "
                    + "Trainer table" + ex.getMessage());
        }
    }
    
        public void fillClassTable(){
         try {
            String sql = "INSERT INTO Class VALUES(classid_seq.nextVal,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
                       
            pstmt.setString(1, "Boxing");
            pstmt.setString(2, "Monday");
            pstmt.setString(3, "15:00");
            pstmt.setInt (4, 1);
            pstmt.executeUpdate();  
            
             System.out.println("Class table filled");

        } catch (SQLException ex) {
            System.out.println("SQL Exception filling "
                    + "Class table" + ex.getMessage());
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
