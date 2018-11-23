package db;

import java.sql.*;

import oracle.jdbc.pool.OracleDataSource;

public class ProductOperations {

    private PreparedStatement pstmt;
    private ResultSet rset;
    private Connection conn;

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
            System.out.print("Unable to load driver " + e);
            System.exit(1);
        }
        return conn;
    }

    public void dropTables() {
        System.out.println("Checking for existing tables.");

        try {
            // Dropping a sequence
            String dropseq = "drop sequence capid_seq";
            pstmt = conn.prepareStatement(dropseq);
            pstmt.executeUpdate();
            System.out.println("Product Sequence dropped.");
        } catch (SQLException ex) {
            // No need to report an error.
            // The table simply did not exist.
        }
        try {
            // Drop the products table.
            String droptab = "drop TABLE caproducts";
            pstmt = conn.prepareStatement(droptab);
            pstmt.executeUpdate();
            System.out.println("Products table dropped.");
        } catch (SQLException ex) {
            // No need to report an error.
            // The table simply did not exist.
        }
    }

    public void createTables() {
        try {
            // Create Products Table
            String created = "CREATE TABLE caproducts( "
                    + "pid NUMBER PRIMARY KEY," + "name VARCHAR(40)," + "brand VARCHAR(40)," + "price DECIMAL(9,2)," + "qty NUMBER," + "category VARCHAR(40),"
                    + "branch VARCHAR(40), releaseDate DATE)";
            pstmt = conn.prepareStatement(created);
            pstmt.executeUpdate();

            // Create Products sequence
            String createseq2 = "create sequence capid_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq2);
            pstmt.executeUpdate();

            // Insert records into the Products table
            String insertString2 = "INSERT INTO caproducts(pid,name,brand,price,qty,category,branch,releaseDate) "
                    + "values(capid_seq.nextVal,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(insertString2);

            pstmt.setString(1, "HP Stream 11 inch");
            pstmt.setString(2, "HP");
            pstmt.setDouble(3, 250.00);
            pstmt.setInt(4, 10);
            pstmt.setString(5, "Laptops");
            pstmt.setString(6, "Blanchardstown");
            pstmt.setDate(7,Date.valueOf("2018-01-23"));
            pstmt.execute();

            pstmt.setString(1, "Acer Aspire 11.6 inch");
            pstmt.setString(2, "ACER");
            pstmt.setDouble(3, 340.00);
            pstmt.setInt(4, 10);
            pstmt.setString(5, "Laptops");
            pstmt.setString(6, "Blanchardstown");
            pstmt.setDate(7,Date.valueOf("2018-02-13"));
            pstmt.execute();

            pstmt.setString(1, "Lenovo G50 15.6 inch");
            pstmt.setString(2, "LENOVO");
            pstmt.setDouble(3, 450.00);
            pstmt.setInt(4, 10);
            pstmt.setString(5, "Laptops");
            pstmt.setString(6, "Blanchardstown");
            pstmt.setDate(7,Date.valueOf("2018-04-29"));
            pstmt.execute();

            pstmt.setString(1, "HP Stream 11 inch");
            pstmt.setString(2, "HP");
            pstmt.setDouble(3, 250.00);
            pstmt.setInt(4, 10);
            pstmt.setString(5, "Laptops");
            pstmt.setString(6, "Liffey Valley");
            pstmt.setDate(7,Date.valueOf("2018-05-06"));
            pstmt.execute();

            pstmt.setString(1, "Acer Aspire 11.6 inch");
            pstmt.setString(2, "ACER");
            pstmt.setDouble(3, 350.00);
            pstmt.setInt(4, 15);
            pstmt.setString(5, "Laptops");
            pstmt.setString(6, "Liffey Valley");
            pstmt.execute();

            pstmt.setString(1, "Lenovo G50 15.6 inch");
            pstmt.setString(2, "LENOVO");
            pstmt.setDouble(3, 450.00);
            pstmt.setInt(4, 1);
            pstmt.setString(5, "Laptops");
            pstmt.setString(6, "Liffey Valley");
            pstmt.setDate(7,Date.valueOf("2017-05-06"));
            pstmt.execute();

            pstmt.setString(1, "Lenovo H30 Desktop PC");
            pstmt.setString(2, "LENOVO");
            pstmt.setDouble(3, 250.00);
            pstmt.setInt(4, 15);
            pstmt.setString(5, "Desktops");
            pstmt.setString(6, "Liffey Valley");
            pstmt.setDate(7,Date.valueOf("2017-11-06"));
            pstmt.execute();

            pstmt.setString(1, "ACER Aspire XC-703");
            pstmt.setString(2, "ACER");
            pstmt.setDouble(3, 550.00);
            pstmt.setInt(4, 15);
            pstmt.setString(5, "Desktops");
            pstmt.setString(6, "Blanchardstown");
            pstmt.setDate(7,Date.valueOf("2017-06-16"));
            pstmt.execute();

            pstmt.setString(1, "HP Pavillion 23 inch");
            pstmt.setString(2, "HP");
            pstmt.setDouble(3, 900.00);
            pstmt.setInt(4, 1);
            pstmt.setString(5, "Desktops");
            pstmt.setString(6, "Dundalk");
            pstmt.setDate(7,Date.valueOf("2016-04-03"));
            pstmt.execute();

            pstmt.setString(1, "HP Stream ");
            pstmt.setString(2, "HP");
            pstmt.setDouble(3, 150.00);
            pstmt.setInt(4, 64);
            pstmt.setString(5, "Tablets");
            pstmt.setString(6, "Dundalk");
            pstmt.setDate(7,Date.valueOf("2015-01-25"));
            pstmt.execute();

            pstmt.setString(1, "Acer Memo Pad");
            pstmt.setString(2, "ACER");
            pstmt.setDouble(3, 100.00);
            pstmt.setInt(4, 35);
            pstmt.setString(5, "Tablets");
            pstmt.setString(6, "Blanchardstown");
            pstmt.setDate(7,Date.valueOf("2015-01-25"));
            pstmt.execute();

        } catch (SQLException e) {
            System.out.print("SQL Exception in createTables() method " + e);
            System.exit(1);
        }
    }



    public ResultSet getProducts() {
        try {
            String queryString = "select * from caproducts order by pid";
            pstmt = conn.prepareStatement(queryString);
            rset = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("SQL Exception in getProducts() method");
        }

        return rset;
    }


    
    public void deleteDBProduct(int pid) {
        try {
            String cmd = "SELECT pid,name,brand,price,qty,category,branch FROM caproducts  WHERE pid = " + pid;
            pstmt = conn.prepareStatement(cmd, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);            
            rset = pstmt.executeQuery();
            if (rset.next()) // try to go to row
            {
                System.out.println("Deleting..");
                rset.deleteRow();                 	    
                System.out.println("Row deleted");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in deleteProduct() method");
        }
    }
    public void closeDB() {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (rset != null) {
                rset.close();
            }
            if (conn != null) {
                conn.close();
                System.out.print("Connection closed");
            }

        } catch (SQLException e) {
            System.out.print("Could not close connection ");
        }
    }
    
    public void updateRecord(int id, double price) {
        try {
            String sqlStatement = "UPDATE caproducts SET price = ?"+
                                  "WHERE pid = ?" ;

            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setDouble(1, price);
            pstmt.setInt(2, id);        
            pstmt.executeUpdate();

            System.out.println(" row updated in the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR in updateRecord() method: " + ex.getMessage());
        }
    }
    
    public void updateRecord(String brand, double discountedPrice) {
        try {
            String sqlStatement = "UPDATE caproducts SET price = ?"+
                                  "WHERE brand = ?" ;

            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setDouble(1, discountedPrice);
            pstmt.setString(2, brand);        
            pstmt.executeUpdate();

            System.out.println(" row updated in the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR in updateRecord() method: " + ex.getMessage());
        }
    }
}
