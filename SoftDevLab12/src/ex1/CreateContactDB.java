package ex1;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

public class CreateContactDB {

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
    public void dropSequence() {
        String s1 = "drop sequence c_seq";
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
        String s1 = "DROP TABLE Contact";
        System.out.println("Checking for existing tables.");

        try {
            pstmt = conn.prepareStatement(s1);
            // Drop the Contacts table.
            pstmt.execute();
            System.out.println("Contact table dropped.");
        } catch (SQLException ex) {
            // No need to report an error.
            // The table simply did not exist.
        }
    }
    
    public void CreateContactSequence(){
        try{         
            String createseq = "create sequence c_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq);
            pstmt.executeUpdate();
            System.out.println("Sequence Created");
        } catch (SQLException e) {
            System.out.println("ERROR in CreateContactSequence() method: " + e.getMessage());
        }
    }
    
     public void CreateContactTable() {
        try {

            // Create a Table
            String create = "CREATE TABLE Contact" +
                     "(id NUMBER PRIMARY KEY, name VARCHAR2(40), address VARCHAR2(30),pnumber VARCHAR2(20), email VARCHAR2(30))";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate();
            System.out.println("Table Created");
           
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
        CreateContactDB co = new CreateContactDB();
        co.openDB();
        co.dropSequence();
        co.dropTable();
        co.CreateContactTable();
        co.CreateContactSequence();
    }
}
