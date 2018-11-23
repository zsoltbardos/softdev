/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
import java.util.Calendar;
import oracle.jdbc.pool.OracleDataSource;


public class EmployeeOperations {
    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;

    // This method opens a connection to the Oracle database
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
            System.out.print("Unable to load driver " + e.getMessage());
        }
        return conn;
    }

     public void dropEmployee1Sequence() {
        try {
            String s2 = "drop sequence eid_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();
            System.out.println("Employee1 Sequence dropped");
        } catch (SQLException ex) {
            
        }
    }

    public void createEmployee1Sequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence eid_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("Employee1 Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with Employee1 Sequence " + ex.getMessage());
        }

    }

    public void dropEmployee1Table() {
        System.out.println("Checking for existence of Employee1 table");
        try {
            String s1 = "DROP TABLE Employee1";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("Employee1 table dropped");
        } catch (SQLException ex) {
            System.out.println("ERROR in dropEmployee1Table() method: " + ex.getMessage());
        }

    }
    public void createEmployee1table() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE Employee1 (id NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "name VARCHAR2(40),"
                    + "startDate DATE,"
                    + "salary DECIMAL(8,2))";
        

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            
             } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "Employee1 table" + ex.getMessage());
        }
    }

    public void fillEmployee1Table(){
        try{
            // Insert data into table
            String sql = "INSERT INTO Employee1(id,name,startDate,salary) "
                    + "values(eid_seq.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1,"Peter");
            pstmt.setDate(2,Date.valueOf("2015-01-23"));
            pstmt.setDouble(3, 46000);
            pstmt.executeUpdate();

            
            pstmt.setString(1,"John");
            pstmt.setDate(2,Date.valueOf("2015-02-13"));
            pstmt.setDouble(3, 56000);
            pstmt.executeUpdate();
           
            pstmt.setString(1,"Mary");
            pstmt.setDate(2,Date.valueOf("2014-03-16"));
            pstmt.setDouble(3, 66000);
            pstmt.executeUpdate();
            
            pstmt.setString(1,"Pat");
            pstmt.setDate(2,Date.valueOf("2014-04-29"));
            pstmt.setDouble(3, 76000);
            pstmt.executeUpdate();
            
            pstmt.setString(1,"Karen");
            pstmt.setDate(2,Date.valueOf("2014-05-06"));
            pstmt.setDouble(3, 86000);
            pstmt.executeUpdate();
         

            System.out.println("Employee1 table populated");
        } catch (SQLException ex) {
            System.out.println("SQL Exception inserting values into "
                    + "Employee1 table" + ex.getMessage());
        }
    }
    public ResultSet getEmployees() {
        String sqlStatement = "SELECT * FROM Employee1 order by id";
        try {
            pstmt = conn.prepareStatement(sqlStatement);
            rset = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR in getEmployees() method " + ex.getMessage());
        }
        return rset;
    }
    public ResultSet getMaxEmployee() {
        String sqlStatement = "SELECT * FROM Employee1 where id in "
                + "(select max (id) from Employee1)";

        try {
            pstmt = conn.prepareStatement(sqlStatement);
            rset = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR in getMaxEmployee() method " + ex.getMessage());
        }
        return rset;
    }
     
    public void updateRecord(int id, double sal) {
        try {
            // Create a string with an UPDATE statement.
            String sqlStatement = "UPDATE Employee1 SET salary = ?"+
                                  "WHERE id = ?" ;

            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setDouble(1, sal);
            pstmt.setInt(2, id);        
            pstmt.executeUpdate();

            // Display the results.
            System.out.println(" row updated in the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR in updateRecord() method " + ex.getMessage());
        }
    }
     public void deleteRecord(int id) {
        try {
            // Create a string with an DELETE statement.
            String sqlStatement = "DELETE FROM Employee1 WHERE id = ?";

            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            // Display the results.
            System.out.println(" row deleted to the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR in deleteRecord() method " + ex.getMessage());
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
     
     public void add(String n, Calendar dob, double sal) {
         try {
             //Create a string with an INSERT statement.
             String sqlStatement = "INSERT INTO Employee1 VALUES (eid_seq.nextVal, ?, ?, ?)";
             pstmt = conn.prepareStatement(sqlStatement);
             
             pstmt.setString(1, n);
             pstmt.setDate(2, new java.sql.Date(dob.getTimeInMillis()));
             pstmt.setDouble(3, sal);
             pstmt.executeUpdate();
             
             //Display the results.
             System.out.println(" row added to the table");
         } catch (SQLException ex) {
             System.out.println("ERROR in add() method: " + ex.getMessage());
         }
     }


    
}
