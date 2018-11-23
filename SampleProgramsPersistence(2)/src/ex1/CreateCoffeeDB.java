package ex1;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * This program creates the CoffeeDB database.
 */
public class CreateCoffeeDB {

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
//            ods.setURL("jdbc:oracle:thin:hr/hr@localhost:1521/XE");
//            ods.setUser("hr");
//            ods.setPassword("passhr");

            conn = ods.getConnection();
            System.out.println("connected.");
        } catch (SQLException e) {
            System.out.print("Unable to load driver " + e);
            System.exit(1);
        }
    }

    /**
     * The dropTables method drops the table if it already exists.
     */
    public void dropTables() {
        System.out.println("Checking for existing tables.");

        try {
            // Get a Statement object.
            stmt = conn.createStatement();

            try {
                // Drop the Coffee table.
                stmt.execute("DROP TABLE Coffee");
                System.out.println("Coffee table dropped.");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    /**
     * The buildCoffeeTable method creates the Coffee table and adds some rows to it.
     */
    public void buildCoffeeTable() {
        try {
            // Get a Statement object.
            stmt = conn.createStatement();

            // Create the table.
            stmt.executeUpdate("CREATE TABLE Coffee "
                    + "(Description VARCHAR(25),ProdNum VARCHAR(10) NOT NULL PRIMARY KEY, Price DECIMAL(5,2))");

            // Insert row #1.
            stmt.execute("INSERT INTO Coffee VALUES ('Bolivian Dark','14-001',8.95 )");

            // Insert row #2.
            stmt.execute("INSERT INTO Coffee VALUES ('Bolivian Medium','14-002',8.95 )");

            // Insert row #3.
            stmt.execute("INSERT INTO Coffee VALUES ('Brazilian Dark','15-001',7.95 )");

            // Insert row #4.
            stmt.execute("INSERT INTO Coffee VALUES ('Brazilian Medium','15-002',7.95 )");

            // Insert row #5.
            stmt.execute("INSERT INTO Coffee VALUES ('Brazilian Decaf','15-003',8.55 )");

            System.out.println("Coffee table created.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void closeDB() {
        try {
            stmt.close();
            conn.close();
            System.out.print("Connection closed");
        } catch (SQLException e) {
            System.out.print("Could not close connection ");
        }
    }
    
    public static void main(String[] args) {
		CreateCoffeeDB cdb = new CreateCoffeeDB();
		cdb.openDB();
		cdb.dropTables();
		cdb.buildCoffeeTable();
		cdb.closeDB();
	}

}
