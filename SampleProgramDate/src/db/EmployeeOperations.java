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

     public void dropEmployeeSequence() {
        try {
            String s2 = "drop sequence eid_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();
            System.out.println("Employee Sequence dropped");
        } catch (SQLException ex) {
            
        }
    }

    public void createEmployeeSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence eid_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("Employee Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with Employee Sequence " + ex.getMessage());
        }

    }

    public void dropEmployeeTable() {
        System.out.println("Checking for existence of Employee table");
        try {
            String s1 = "DROP TABLE Employee";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("Employee table dropped");
        } catch (SQLException ex) {
            
        }

    }
    public void createEmployeetable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE Employee (id NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "name VARCHAR2(40),"
                    + "startDate DATE,"
                    + "dob DATE,"
                    + "salary DECIMAL(8,2))";
        

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            
             } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "Employee table" + ex.getMessage());
        }
    }

    public void fillEmployeeTable(){
        try{
            // Insert data into table
            String sql = "INSERT INTO Employee(id,name,startDate,dob,salary) "
                    + "values(eid_seq.nextVal,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1,"Peter");
            pstmt.setDate(2,Date.valueOf("2015-01-23"));
            pstmt.setDate(3,Date.valueOf("1970-01-08"));
            pstmt.setDouble(4, 46000);
            pstmt.executeUpdate();

            
            pstmt.setString(1,"John");
            pstmt.setDate(2,Date.valueOf("2015-02-13"));
            pstmt.setDate(3,Date.valueOf("1965-06-12"));
            pstmt.setDouble(4, 56000);
            pstmt.executeUpdate();
           
            pstmt.setString(1,"Mary");
            pstmt.setDate(2,Date.valueOf("2014-03-16"));
            pstmt.setDate(3,Date.valueOf("1968-10-23"));
            pstmt.setDouble(4, 66000);
            pstmt.executeUpdate();
            
            pstmt.setString(1,"Pat");
            pstmt.setDate(2,Date.valueOf("2000-04-29"));
            pstmt.setDate(3,Date.valueOf("1980-02-21"));
            pstmt.setDouble(4, 76000);
            pstmt.executeUpdate();
            
            pstmt.setString(1,"Karen");
            pstmt.setDate(2,Date.valueOf("2014-04-29"));
            pstmt.setDate(3,Date.valueOf("1960-05-06"));
            pstmt.setDouble(4, 86000);
            pstmt.executeUpdate();
         

            System.out.println("Employee table populated");
        } catch (SQLException ex) {
            System.out.println("SQL Exception inserting values into "
                    + "Employee table" + ex.getMessage());
        }
    }
    public ResultSet getEmployees() {
        String sqlStatement = "SELECT * FROM Employee order by id";
        try {
            pstmt = conn.prepareStatement(sqlStatement);
            rset = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR in getEmployees() method " + ex.getMessage());
        }
        return rset;
    }
    public ResultSet getMaxEmployee() {
        String sqlStatement = "SELECT * FROM Employee where id in "
                + "(select max (id) from Employee)";

        try {
            pstmt = conn.prepareStatement(sqlStatement);
            rset = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR in getMaxEmployee() method " + ex.getMessage());
        }
        return rset;
    }
     public void add(String n, Calendar sdate,Calendar dob, double sal) {
        try {
            // Create a string with an INSERT statement.
            String sqlStatement = "INSERT INTO Employee VALUES "
                    + "(eid_seq.nextVal,?,?,?,?)";
            pstmt = conn.prepareStatement(sqlStatement);
            
            pstmt.setString(1,n);
            pstmt.setDate(2,new java.sql.Date(sdate.getTimeInMillis()));
            pstmt.setDate(3,new java.sql.Date(dob.getTimeInMillis()));
            pstmt.setDouble(4, sal);
            pstmt.executeUpdate();
            

            // Display the results.
            System.out.println(" row added to the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR in add() method " + ex.getMessage());
        }
    }
    public void updateRecord(int id, double sal) {
        try {
            // Create a string with an UPDATE statement.
            String sqlStatement = "UPDATE Employee SET salary = ?"+
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
            String sqlStatement = "DELETE FROM Employee WHERE id = ?";

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


    
}
