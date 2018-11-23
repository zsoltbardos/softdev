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
//             ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
//             ods.setUser("");
//             ods.setPassword("");
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

    public void dropContactsTable() {
        System.out.println("Checking for existence of Contacts table");
        try {
            String s1 = "DROP TABLE Contacts";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Problem dropping the Contacts table "
                    +ex.getMessage());
        }

    }

    public void dropAddressBookOwnersTable() {
        System.out.println("Checking for existence of AddressBookOwners table.");
        try {
            String s1 = "DROP TABLE AddressBookOwners";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("AddressBookOwners table dropped.");
        } catch (SQLException ex) {
            System.out.println("Problem dropping the AddressBook table "
                    +ex.getMessage());
        }
    }

    public void dropContactsAddressBookTable() {
        System.out.println("Checking for existence of ContactsAddressBook table.");
        try {
            String s1 = "DROP TABLE ContactsAddressBook";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("ContactsAddressBook table dropped.");
        } catch (SQLException ex) {
            System.out.println("Problem dropping the ContactsAddressBook table "
                    +ex.getMessage());
        }
    }

    public void dropAddressBookOwnerSequence() {
        try {
            String s2 = "drop sequence addressownerid_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();
            System.out.println("AddressBookOwner Sequence dropped");

        } catch (SQLException ex) {
            System.out.println("Problem dropping the AddressBook sequence "
                    +ex.getMessage());
        }
    }

    public void dropContactSequence() {
        try {
            String s2 = "drop sequence cid_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();
            System.out.println("Contact Sequence dropped");
        } catch (SQLException ex) {
            System.out.println("Problem dropping the Contact sequence "
                    +ex.getMessage());
        }
    }

    public void createContactSequence() {
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

    public void createAddressBookOwnersSequence() {
        // Creating a sequence    
        try {
            String createseq2 = "create sequence addressownerid_seq increment "
                    + "by 1 start with 1";
            pstmt = conn.prepareStatement(createseq2);
            pstmt.executeUpdate();
            System.out.println("AddressBookOwner Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with Create Sequence " + ex.getMessage());
        }
    }

    public void CreateContactsTable() {
        try {

            // Create a Table
            String create = "CREATE TABLE Contacts "
                    + "(id NUMBER PRIMARY KEY, cname VARCHAR(40), address VARCHAR(30),"
                    + "pnumber VARCHAR(30), email VARCHAR(20))";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate();

            // Insert data into table
            String insertString1 = "INSERT INTO Contacts(id,cname,address,pnumber,email)"
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
            System.out.print("SQL Exception creating and inserting values into "
                    + "Contacts " + e.getMessage());
            System.exit(1);
        }
    }

    public void createAddressBookOwnerstable() {
        // Create a Table           
        try {
            String create = "CREATE TABLE AddressBookOwners "
                    + "(id NUMBER PRIMARY KEY NOT NULL, name VARCHAR(40))";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate();

            // Insert data into table
            String insertString1 = "INSERT INTO AddressBookOwners(id,name) "
                    + "values(addressownerid_seq.nextVal,?)";
            pstmt = conn.prepareStatement(insertString1);

            pstmt.setString(1, "Mary");
            pstmt.executeUpdate();

            pstmt.setString(1, "Jane");
            pstmt.executeUpdate();

            pstmt.setString(1, "Paula");
            pstmt.executeUpdate();

            System.out.println("AddressBookOwners table created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating and inserting values into "
                    + "AddressBookOwners" + ex.getMessage());
        }
    }

    public void createContactsAddressBook() {
        // Create a Table           
        try {

            String create = "CREATE TABLE ContactsAddressBook "
                    + "(abid NUMBER, cid NUMBER, PRIMARY KEY (abid, cid), "
                    + "FOREIGN KEY (abid) REFERENCES AddressBookOwners (id),"
                    + "FOREIGN KEY (cid) REFERENCES Contacts (id) ON DELETE CASCADE )";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate();

            // Insert data into table
            String insertString1 = "INSERT INTO ContactsAddressBook(abid,cid) "
                    + "values(?,?)";
            pstmt = conn.prepareStatement(insertString1);

            pstmt.setInt(1, 1);
            pstmt.setInt(2, 1);
            pstmt.execute();
            pstmt.setInt(1, 1);
            pstmt.setInt(2, 2);
            pstmt.execute();

            pstmt.setInt(1, 2);
            pstmt.setInt(2, 1);
            pstmt.execute();
            pstmt.setInt(1, 2);
            pstmt.setInt(2, 3);
            pstmt.execute();

            pstmt.setInt(1, 3);
            pstmt.setInt(2, 1);
            pstmt.execute();

            System.out.println("ContactsAddressBook table created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating and inserting values "
                    + "into ContactsAddressBook" + ex.getMessage());
        }
    }

    public ResultSet getOwners() {
        String sqlStatement = "SELECT * FROM AddressBookOwners order by id";
        try {
            pstmt = conn.prepareStatement(sqlStatement);
            rset = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Problem in method getOwners(): " + ex.getMessage());
        }
        return rset;
    }

    public int getOwnerID(String owner) {
        int num = 0;
        try {
            String sqlStatement1 = "SELECT ID FROM ADDRESSBOOKOWNERS WHERE NAME = ?";
            pstmt = conn.prepareStatement(sqlStatement1);
            pstmt.setString(1, owner);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                num = rset.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Problem in method getOwnerID() " + ex.getMessage());
        }
        return num;
    }

    public ResultSet getContacts(String owner) {
        String sql1 = "SELECT co.id,co.cname,co.address, co.pnumber, co.email "
                + "FROM CONTACTS co, CONTACTSADDRESSBOOK cb, ADDRESSBOOKOWNERS o "
                + "WHERE o.id = cb.ABID AND cb.CID = co.ID AND o.name = ?";

        try {
            //pstmt = conn.prepareStatement(sql1);
            pstmt = conn.prepareStatement(sql1, 
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            pstmt.setString(1, owner);
            rset = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Problem in method getContacts() " + ex.getMessage());
        }
        return rset;
    }

    public void add(String n, String a, String pnum, String em) {
        try {
            // Create a string with an INSERT statement.
            String sqlStatement = "INSERT INTO Contacts VALUES "
                    + "(cid_seq.nextVal,?,?,?,?)";
            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setString(1, n);
            pstmt.setString(2, a);
            pstmt.setString(3, pnum);
            pstmt.setString(4, em);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Problem in method add(): " + ex);
        }
    }

    public int getContactID() {
        int cid = 0;
        String sqlStatement = "SELECT max(id) FROM Contacts";
        try {
            pstmt = conn.prepareStatement(sqlStatement);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                cid = rset.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Problem in method getContactID() " 
                    + ex.getMessage());
        }
        return cid;
    }

    public void addToLinkTable(String owner) {
        int id = getOwnerID(owner);
        int cid = getContactID();
        try {
            // Create a string with an INSERT statement.
            String sqlStatement = "INSERT INTO ContactsAddressBook VALUES (?,?)";
            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setInt(1, id);
            pstmt.setInt(2, cid);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Problem in method addToLinkTable()  " 
                    + ex.getMessage());
        }
    }

    public void updateRecord(int id, String num) {
        try {
            // Create a string with an UPDATE statement.
            String sqlStatement = "UPDATE Contacts SET pnumber = ?"
                    + "WHERE id = ?";

            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setString(1, num);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

            // Display the results.
            System.out.println(" row updated in the table.");
        } catch (SQLException ex) {
            System.out.println("Problem in method updateRecord(): " 
                    + ex.getMessage());
        }
    }

    public void deleteRecord(int id) {
        try {
            // Create a string with an DELETE statement.
            String sqlStatement = "DELETE FROM Contacts WHERE id = ?";

            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            // Display the results.
            System.out.println(" row deleted from the table.");
        } catch (SQLException ex) {
            System.out.println("Problem in method deleteRecord(): " 
                    + ex.getMessage());
        }
    }

    public void closeDB() {
        try {
            pstmt.close();
            rset.close();
            conn.close();
            System.out.println("Connection closed");
        } catch (SQLException ex) {
            System.out.println("Could not close connection "+ ex.getMessage());
        }
    }
}
