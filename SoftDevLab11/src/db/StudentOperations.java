/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
import java.util.Calendar;
import oracle.jdbc.pool.OracleDataSource;


public class StudentOperations {
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

     public void dropStudentSequence() {
        try {
            String s2 = "drop sequence sid_seq";
            pstmt = conn.prepareStatement(s2);
            pstmt.executeUpdate();
            System.out.println("Student Sequence dropped");
        } catch (SQLException ex) {
            
        }
    }

    public void createStudentSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence sid_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("Student Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with Student Sequence " + ex.getMessage());
        }

    }

    public void dropEmployeeTable() {
        System.out.println("Checking for existence of Student table");
        try {
            String s1 = "DROP TABLE Student";
            pstmt = conn.prepareStatement(s1);
            pstmt.executeUpdate();
            System.out.println("Student table dropped");
        } catch (SQLException ex) {
            
        }

    }
    public void createStudenttable() {
        // Create a Table           
        try {
            String sql = "CREATE TABLE Student (id NUMBER PRIMARY KEY "
                    + "NOT NULL,"
                    + "name VARCHAR2(40),"
                    + "dob DATE,"
                    + "regDate DATE)";
        

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            
             } catch (SQLException ex) {
            System.out.println("SQL Exception creating "
                    + "Student table" + ex.getMessage());
        }
    }

    public void fillStudentTable(){
        try{
            // Insert data into table
            String sql = "INSERT INTO Student(id,name,dob,regDate) "
                    + "values(sid_seq.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1,"Mary Brady");
            pstmt.setDate(2,Date.valueOf("1999-01-23"));
            pstmt.setDate(3, Date.valueOf("2018-09-13"));
            pstmt.executeUpdate();

            
            pstmt.setString(1,"Kelly Dolan");
            pstmt.setDate(2,Date.valueOf("1997-02-23"));
            pstmt.setDate(3,Date.valueOf("2018-09-13"));
            pstmt.executeUpdate();
           
            pstmt.setString(1,"James Wright");
            pstmt.setDate(2,Date.valueOf("1990-03-04"));
            pstmt.setDate(3,Date.valueOf("2018-09-25"));
            pstmt.executeUpdate();
            
            pstmt.setString(1,"Paris Hilton");
            pstmt.setDate(2,Date.valueOf("1996-02-19"));
            pstmt.setDate(3,Date.valueOf("2018-09-28"));
            pstmt.executeUpdate();
            
            pstmt.setString(1,"Karen White");
            pstmt.setDate(2,Date.valueOf("1980-08-16"));
            pstmt.setDate(3,Date.valueOf("2018-09-25"));
            pstmt.executeUpdate();
         

            System.out.println("Student table populated");
        } catch (SQLException ex) {
            System.out.println("SQL Exception inserting values into "
                    + "Student table" + ex.getMessage());
        }
    }
    public ResultSet getStudents() {
        String sqlStatement = "SELECT * FROM Student order by id";
        try {
            pstmt = conn.prepareStatement(sqlStatement);
            rset = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR in getStudents() method " + ex.getMessage());
        }
        return rset;
    }
    public ResultSet getMaxStudent() {
        String sqlStatement = "SELECT * FROM Student where id in "
                + "(select max (id) from Student)";

        try {
            pstmt = conn.prepareStatement(sqlStatement);
            rset = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR in getMaxStudent() method " + ex.getMessage());
        }
        return rset;
    }
    

     public void deleteRecord(int id) {
        try {
            // Create a string with an DELETE statement.
            String sqlStatement = "DELETE FROM Student WHERE id = ?";

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
    
     public void add(String name, Calendar birthDate, Calendar regDate) {
        try {
            // Create a string with an INSERT statement.
            String sqlStatement = "INSERT INTO Student VALUES "
                    + "(eid_seq.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(sqlStatement);
            
            pstmt.setString(1,name);
            pstmt.setDate(2,new java.sql.Date(birthDate.getTimeInMillis()));
            pstmt.setDate(3,new java.sql.Date(regDate.getTimeInMillis()));
            pstmt.executeUpdate();
            

            // Display the results.
            System.out.println(" row added to the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR in add() method " + ex.getMessage());
        }
    }    
}
