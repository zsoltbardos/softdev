/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softdevlab6;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.*; 
import java.util.*; 
/**
 *
 * @author x00157506
 */
public class ContactsDB {
    
    private Connection conn;
    private Statement stmt;
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
    
    public void buildContactsTable(){
        try {
            String sql1 = "CREATE TABLE CONTACTS1("
                    + "id NUMBER PRIMARY KEY NOT NULL, "
                    + "name VARCHAR(40), " 
                    + "address VARCHAR(255), "
                    + "phone VARCHAR(255), "
                    + "email VARCHAR(255)"
                    + ")";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
            
            stmt.executeUpdate("INSERT INTO CONTACTS1 VALUES(1, 'Peter', '23 Lime Lane', '018976543', 'p.cassidy@b.com')");
            
            stmt.executeUpdate("INSERT INTO CONTACTS1 VALUES(2, 'Donal', '2 Shelbourne rd', '012446578', 'd.oreilly@b.com')");
            
            stmt.executeUpdate("INSERT INTO CONTACTS1 VALUES(3, 'Mary', '4 Richmond rd', '019887654', 'm.lawlor@b.com')");
            
            stmt.executeUpdate("INSERT INTO CONTACTS1 VALUES(4, 'Glen', '4 Richmond Lane', '011223876', 'g.whelan@b.com')");
            
            System.out.println("Contacts table created.");

        } catch (SQLException ex) {
            System.out.println("ERROR in build: " + ex.getMessage());     
        }
        
    }
    
    public void dropTables() {
        System.out.println("Checking for existing tables.");

        try {
            // Get a Statement object.
            stmt = conn.createStatement();

            try {
                // Drop the Coffee table.
                stmt.execute("DROP TABLE CONTACTS1");
                System.out.println("Contacts table dropped.");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
            }
        } catch (SQLException ex) {
            System.out.println("ERROR in drop: " + ex.getMessage());
        }
    }
    
    public void showDB() {
        try {
            String sql1 = "SELECT * FROM CONTACTS1";
            stmt = conn.createStatement();
            
            rset = stmt.executeQuery(sql1);
            
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
            stmt = conn.createStatement();
            
            rset = stmt.executeQuery(sql1);
            
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
            stmt = conn.createStatement();
            
            rset = stmt.executeQuery(sql1);
            
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
            stmt = conn.createStatement();
            
            rset = stmt.executeQuery(sql1);
            
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
    
    public void closeDB() {
        try {
            stmt.close();
            rset.close();
            conn.close();
            System.out.print("Connection closed");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
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
            
            stmt = conn.createStatement();
            //stmt.executeUpdate("INSERT INTO CONTACTS VALUES(" + Integer.parseInt(newContact.get(0)) + "," + "'" + newContact.get(1) + "'" + "," + '23 Lime Lane', '018976543', 'p.cassidy@b.com')");
            stmt.executeUpdate(String.format("INSERT INTO CONTACTS1 VALUES(%d, '%s', '%s', '%s', '%s')", IDNumber, name, address, phone, email));
        } catch (SQLException e) {
            System.out.println("Error in insert method" + e.getMessage());
        }
    }
    
}
