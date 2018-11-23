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
public class University {
   private String universityName;
   private Department[] departmentList;
   
   public University(String universityName, int[] departmentIDs, String [] departmentNames){
       this.universityName = universityName;
       departmentList = new Department[departmentIDs.length];
       
       for(int i=0;i<departmentList.length;i++){
           departmentList[i] = new Department(departmentIDs[i], departmentNames [i]);
       }
   }

    public String getUniversityName() {
        return universityName;
    }

    public Department[] getDepartmentList() {
        return departmentList;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public void setDepartmentList(Department[] departmentList) {
        this.departmentList = departmentList;
    }
    
    public void showList() {
        for (int i=0; i<departmentList.length; i++){
            System.out.println(departmentList[i].getDepartmentID() + " " + departmentList[i].getDepartmentName());
        }
    }
    
    public void changeDepartmentName(String oldName, String newName){
        for(int i=0;i<departmentList.length;i++){
            if(departmentList[i].getDepartmentName().equals(oldName)){
                departmentList[i].setDepartmentName(newName);
            }
        }
    }
   
   
}
