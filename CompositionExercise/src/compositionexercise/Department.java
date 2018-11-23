/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compositionexercise;

/**
 *
 * @author x00157506
 */
public class Department {

    private int departmentID;
    private String departmentName;

    public Department(int depID, String depName) {
        this.departmentID = depID;
        this.departmentName = depName;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentID(int depID) {
        this.departmentID = depID;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
