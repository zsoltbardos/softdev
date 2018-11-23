package ex1;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

public class CreateAlbumDB {

    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;

    // This method opens a connection to the Oracle database
    public void openDB() {
        try {
            OracleDataSource ods = new OracleDataSource();

	    // Tallaght
            // ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
            // ods.setUser("");
            // ods.setPassword("");
            
            // Home Oracle XE
            ods.setURL("jdbc:oracle:thin:HR/pmagee@localhost:1521:XE");
            ods.setUser("hr");
            ods.setPassword("passhr");

            conn = ods.getConnection();
            System.out.println("connected.");
        } catch (Exception e) {
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
            
            String createseq = "create sequence a_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq);
            pstmt.executeUpdate();
            System.out.println("Sequence Created");
           
        }
        catch (SQLException e) {
            System.out.print("SQL Exception here" + e);
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
            System.out.print("Could not close connection ");
        }
    }

    public static void main(String[] args) {
        CreateAlbumDB ao = new CreateAlbumDB();
        ao.openDB();
        ao.dropSequence();
        ao.dropTable();
        ao.CreateAlbumTable();
    }
}
