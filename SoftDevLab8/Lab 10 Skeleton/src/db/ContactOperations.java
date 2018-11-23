package db;

import java.sql.*;

import oracle.jdbc.pool.OracleDataSource;

public class ContactOperations {

    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;
    

    // This method opens a connection to the Oracle database
    public Connection openDB() {
        try {
            OracleDataSource ods = new OracleDataSource();

            // Tallaght
            // ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
            // ods.setUser("jdbcdemo");
            // ods.setPassword("jdbcdemo");
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
        return conn;
    }
    
    public void dropContactsTable() {       
        System.out.println("Checking for existence of Contacts table");
        try {
            String s1 = "DROP TABLE MyContacts";
            pstmt = conn.prepareStatement(s1);
            try {
                // Drop the Contacts table.
                pstmt.execute();
                System.out.println("Contacts table dropped.");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
            }
        } catch (SQLException ex) {
            System.out.println("Problem dropping the Contacts table");
        }

    }
    
    public void dropContactsSequence() {
        try {
            String s1 = "drop sequence cid_seq";
            pstmt = conn.prepareStatement(s1); 
             try {
                pstmt.execute();
                System.out.println("Contact Sequence dropped.");
            } catch (SQLException ex) {
                // No need to report an error.
                // The sequence does not exist.
            }
        } catch (SQLException ex) {
            System.out.println("Problem dropping the Contact sequence");
        }
    }

    public void createContactsSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence cid_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("Contact Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with Create Sequence " + ex.getMessage());
        }
            
    }

    public void CreateContactsTable() {
        try {

            // Create a Table
            String create = "CREATE TABLE MyContacts "
                    + "(id NUMBER PRIMARY KEY, name VARCHAR(40), address VARCHAR(30), pnumber VARCHAR(30), email VARCHAR(20))";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate();

	    // Insert data into table
            String insertString1 = "INSERT INTO MyContacts(id,name,address,pnumber,email) "
                    + "values(cid_seq.nextVal,?,?,?,?)";
            pstmt = conn.prepareStatement(insertString1);

            pstmt.setString(1, "Peter");
            pstmt.setString(2, "23 Lime Lane");
            pstmt.setString(3, "018776543");
            pstmt.setString(4, "p.cassidy@b.com");
            pstmt.execute();

            pstmt.setString(1, "Donal");
            pstmt.setString(2, "2 Shelbourne rd");
            pstmt.setString(3, "012445678");
            pstmt.setString(4, "d.oreilly@b.com");
            pstmt.execute();

            pstmt.setString(1, "Kevin");
            pstmt.setString(2, "4 Richmond rd");
            pstmt.setString(3, "018765456");
            pstmt.setString(4, "m.reilly@rich.com");
            pstmt.execute();

            pstmt.setString(1, "Glen");
            pstmt.setString(2, "35 Sommerville Lane");
            pstmt.setString(3, "017854563");
            pstmt.setString(4, "p.whelan@siske.com");
            pstmt.execute();

            System.out.println("Contacts table created");
        } catch (SQLException e) {
            System.out.print("SQL Exception creating and inserting values into Contacts " + e.getMessage());
            System.exit(1);
        }
    }

    public ResultSet getContacts() {
        String sqlStatement = "SELECT * FROM MyContacts order by id";
        try {
            pstmt = conn.prepareStatement(sqlStatement,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rset = pstmt.executeQuery();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return rset;
    }

    public void add(String n, String a, String pnum, String em) {
        try {
            // Create a string with an INSERT statement.
            String sqlStatement = "INSERT INTO MyContacts VALUES (cid_seq.nextVal,?,?,?,?)";
            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setString(1, n);
            pstmt.setString(2, a);
            pstmt.setString(3, pnum);
            pstmt.setString(4, em);
            pstmt.executeUpdate();

            // Display the results.
            System.out.println(" row added to the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    public void updateRecord(int id, String num) {
        try {
            // Create a string with an UPDATE statement.
            String sqlStatement = "UPDATE MyContacts SET pnumber = ?"+
                                  "WHERE id = ?" ;

            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setString(1, num);
            pstmt.setInt(2, id);        
            pstmt.executeUpdate();

            // Display the results.
            System.out.println(" row updated in the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    public void deleteRecord(int id) {
        try {
            // Create a string with an DELETE statement.
            String sqlStatement = "DELETE FROM MyContacts WHERE id = ?";

            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            // Display the results.
            System.out.println(" row deleted to the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
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
}
