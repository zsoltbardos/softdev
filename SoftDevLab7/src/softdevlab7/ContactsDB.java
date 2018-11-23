/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softdevlab7;

import java.sql.*;
import java.util.Scanner;


import oracle.jdbc.pool.OracleDataSource;


/**
 *
 * @author x00157506
 */
public class ContactsDB {
    
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
            String sql1 = "DROP TABLE Contacts1";
            // Get a Statement object.
            pstmt = conn.prepareStatement(sql1);
            pstmt.executeUpdate();
            System.out.println("Contacts table dropped");
            
        } catch (SQLException ex) {
            System.out.println("ERROR in drop: " + ex.getMessage());
        }
    }
    
    
    public void buildContactsTable(){
        try {
            String sql1 = "CREATE TABLE Contacts1"
                    + "(id NUMBER PRIMARY KEY NOT NULL,"
                    + "name VARCHAR(40),"
                    + "address VARCHAR(255), "
                    + "phone VARCHAR(255), "
                    + "email VARCHAR(255)"
                    + ")";
            pstmt = conn.prepareStatement(sql1);
            pstmt.executeUpdate();

            String sql2 = "INSERT INTO Contacts1 VALUES(?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql2);
            
            pstmt.setInt(1, 1);
            pstmt.setString(2, "Peter");
            pstmt.setString(3,"23 Lime Lane");
            pstmt.setString(4,"018976543");
            pstmt.setString(5,"p.cassidy@b.com");
            pstmt.executeUpdate();
            
            pstmt.setInt(1, 2);
            pstmt.setString(2, "Donal");
            pstmt.setString(3,"2 Shelbourne rd");
            pstmt.setString(4,"012446578");
            pstmt.setString(5,"d.oreilly@b.com");
            pstmt.executeUpdate();
            
            pstmt.setInt(1, 3);
            pstmt.setString(2, "Mary");
            pstmt.setString(3,"4 Richmond rd");
            pstmt.setString(4,"019887654");
            pstmt.setString(5,"m.lawlor@b.com");
            pstmt.executeUpdate();
            
            pstmt.setInt(1, 4);
            pstmt.setString(2, "Glen");
            pstmt.setString(3,"4 Richmond Lane");
            pstmt.setString(4,"011223876");
            pstmt.setString(5,"g.whelan@b.com");
            pstmt.executeUpdate();
  
            System.out.println("Contacts table created.");
            
        } catch (SQLException ex) {
            System.out.println("ERROR in build: " + ex.getMessage());     
        }
        
    }
    
    public void showDB() {
        try {
            String sql1 = "SELECT * FROM CONTACTS1";
            pstmt = conn.prepareStatement(sql1);
            
            rset = pstmt.executeQuery(sql1);
            
            while(rset.next()){
                System.out.println(rset.getInt(1) + " " +
                        rset.getString(2) + " " +
                        rset.getString(3) + " " +
                        rset.getString(4) + " " +
                        rset.getString(5)); 
            }
        } catch (SQLException e) {
            System.out.println("Error in showDB method" + e.getMessage());
        }
    }
    
    public void queryDB() {
        try {
            String sql1 = "SELECT COUNT(*) FROM CONTACTS1";
            pstmt = conn.prepareStatement(sql1);
            
            rset = pstmt.executeQuery(sql1);
            
            while(rset.next()){
                System.out.println("================Query 1=======================");
                System.out.print("The number of contacts in the database is: ");
                System.out.println(rset.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Error in query 1" + e.getMessage());
        }
        
        try {
            String sql1 = "SELECT phone FROM CONTACTS1 WHERE name = 'Peter'";
            pstmt = conn.prepareStatement(sql1);            
            rset = pstmt.executeQuery(sql1);
            
            while(rset.next()){
                System.out.println("================Query 2=======================");
                System.out.println("The phone number for Peter is: ");
                System.out.println(rset.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error in query 2" + e.getMessage());
        }
        
        try {
            String sql1 = "SELECT * FROM CONTACTS1 ORDER BY name";
            pstmt = conn.prepareStatement(sql1);            
            rset = pstmt.executeQuery(sql1);
            
            System.out.println("================Query 3=======================");
            while(rset.next()){
                System.out.println(rset.getInt(1) + " " +
                        rset.getString(2) + " " +
                        rset.getString(3) + " " +
                        rset.getString(4) + " " +
                        rset.getString(5)); 
            }
        } catch (SQLException e) {
            System.out.println("Error in showDB method" + e.getMessage());
        }
        
    }
    
    public void insert() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("New Contact");
            
            System.out.println("Enter ID:");
            int IDNumber = Integer.parseInt(scan.nextLine());
            
            System.out.println("Enter name:");
            String name = scan.nextLine();
            
            System.out.println("Enter address:");
            String address = scan.nextLine();

            System.out.println("Enter phone number:");
            String phone = scan.nextLine();
            
            System.out.println("Enter email:");
            String email = scan.nextLine();
            
            
            String sql1 = "INSERT INTO Contacts1 VALUES(?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql1);    
            
            pstmt.setInt(1, IDNumber);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            pstmt.setString(4, phone);
            pstmt.setString(5, email);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error in showDB method" + e.getMessage());
        }
    }
}
