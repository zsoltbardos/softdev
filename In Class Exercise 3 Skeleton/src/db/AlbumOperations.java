package db;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pmage_000
 */
public class AlbumOperations {
    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;

    // This method opens a connection to the Oracle database
    public void openDB() {
        try {
            OracleDataSource ods = new OracleDataSource();

	    // Tallaght
             ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
             ods.setUser("x00157506");
             ods.setPassword("db16Apr93");
            
            // Home Oracle XE
            //ods.setURL("jdbc:oracle:thin:HR/pmagee@localhost:1521:XE");
           // ods.setUser("hr");
            //ods.setPassword("passhr");

            conn = ods.getConnection();
            System.out.println("connected.");
        } catch (SQLException e) {
            System.out.print("Unable to load driver " + e);
            System.exit(1);
        }
    }
    public void dropSequence() {
        String s1 = "drop sequence a_seq";
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
    public void dropTable() {
        String s1 = "DROP TABLE Album";
        System.out.println("Checking for existing tables.");

        try {
            pstmt = conn.prepareStatement(s1);
            // Drop the Contacts table.
            pstmt.execute();
            System.out.println("Album table dropped.");
        } catch (SQLException ex) {
            // No need to report an error.
            // The table simply did not exist.
        }
    }
     public void CreateAlbumTable() {
        try {

            // Create a Table
            String create = "CREATE TABLE Album" +
                     "(id NUMBER PRIMARY KEY, title VARCHAR2(40), artist VARCHAR2(30),price DECIMAL(5,2))";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate();
            System.out.println("Table Created");
            
            // Creating a sequence
            String createseq = "create sequence a_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq);
            pstmt.executeUpdate();
            System.out.println("Sequence Created");
            
            
            // Insert data into table
            String insertString1 = "INSERT INTO Album(id,title,artist,price) "
                    + "values(a_seq.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(insertString1);
            pstmt.setString(1, "Strangeland");
            pstmt.setString(2, "Keane");
            pstmt.setDouble(3, 9.99);
            pstmt.executeUpdate();
            
            pstmt.setString(1, "Hopes and Fears");
            pstmt.setString(2, "Keane");
            pstmt.setDouble(3, 8.99);
            pstmt.executeUpdate();
            
            pstmt.setString(1, "Parachutes");
            pstmt.setString(2, "ColdPlay");
            pstmt.setDouble(3, 10.99);
            pstmt.executeUpdate();
            
            pstmt.setString(1, "Pure Heroine");
            pstmt.setString(2, "Lorde");
            pstmt.setDouble(3, 8.99);
            pstmt.executeUpdate();
            
            pstmt.setString(1, "Second Coming");
            pstmt.setString(2, "Stone Roses");
            pstmt.setDouble(3, 7.99);
            pstmt.executeUpdate();
            
            pstmt.setString(1, "Golden Greats");
            pstmt.setString(2, "Ian Brown");
            pstmt.setDouble(3, 5.99);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.print("SQL Exception in CreateAlbumTable() method" + e);
            System.exit(1);
        }
     }
     // Returns a scrollable resultset
     public ResultSet getAlbums() {
        String sqlStatement = "SELECT * FROM Album order by id";
        try {
            pstmt = conn.prepareStatement(sqlStatement);
            rset = pstmt.executeQuery();
          
        } catch (SQLException ex) {
            System.out.println("ERROR in getAlbums() method " + ex);
        }
        return rset;
    }
     
     public void queryDB() {
        String sqlStatement = "SELECT * FROM Album";
        try {
            // Send the statement to the DBMS.
            pstmt = conn.prepareStatement(sqlStatement);
            rset = pstmt.executeQuery();

	    // Display the contents of the result set.
            // The result set will have three columns.
            while (rset.next()) {
                System.out.printf("%2d %-20s%-20s%5.2f\n",
                        rset.getInt(1),
                        rset.getString(2), rset.getString(3),rset.getDouble(4));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR in queryDB() method " + ex);
        }
    }

      public void updateRecord(int prodID, double price) {
        try {
            // Create a string with an UPDATE statement.
            String sqlStatement = "UPDATE Album SET Price = ?"+
                                  "WHERE id = ?" ;

            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setDouble(1, price);
            pstmt.setInt(2, prodID);
            pstmt.executeUpdate();

            // Display the results.
            System.out.println(" row updated in the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR in updateRecord() method " + ex);
        }
    }
       public void deleteRecord(int prodID) {
        try {
            // Create a string with an DELETE statement.
            String sqlStatement = "DELETE FROM album WHERE id = ?";

            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setInt(1, prodID);
            pstmt.executeUpdate();

            // Display the results.
            System.out.println(" row deleted from the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR in deleteRecord() method  " + ex.getMessage());
        }
    }
       
       public ResultSet getMaxAlbum(){
           String sql1 = "SELECT * FROM ALBUM WHERE id IN (SELECT MAX(id) from ALBUM" ;
           
           try {
               pstmt = conn.prepareStatement(sql1);
               rset = pstmt.executeQuery();
           } catch (SQLException e) {
               System.out.println("ERROR in getMaxAlbum() method: " + e.getMessage());
           }
            return rset;
       }
       
       public void add(String t, String a, double p) {
        try {
            // Create a string with an INSERT statement.
            String sqlStatement = "INSERT INTO album VALUES (a_seq.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setString(1, t);
            pstmt.setString(2, a);
            pstmt.setDouble(3, p);
            pstmt.executeUpdate();

            // Display the results.
            System.out.println("Row added to the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR in add() method " + ex.getMessage());
        }
    }
    public void closeDB() {
        try {
            pstmt.close();
            rset.close();
            conn.close();
            System.out.print("Connection closed");
        } catch (SQLException e) {
            System.out.println("ERROR in closeDB() method: " + e.getMessage());
        }
    }
    
}
