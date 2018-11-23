
package example1;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Database {
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
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    public void queryDB()
    {
        try {
            stmt = conn.createStatement();
            rset = stmt.executeQuery("select BANNER from SYS.V_$VERSION");
            while (rset.next()) {
                System.out.println(rset.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
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
}


