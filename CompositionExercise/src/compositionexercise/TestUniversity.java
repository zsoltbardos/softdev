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
public class TestUniversity {
    
    public static void main(String[] args) {
        // TODO code application logic here
        int departmentIDs[] = {1,2,3,4};
        String departmentNames[] = {"Computing", "Business", "Languages", "Engineering"};
        
        University university1 = new University("DCU", departmentIDs, departmentNames);
        university1.showList();
        university1.changeDepartmentName("Business", "Business & Marketing");
        university1.showList();
    }
    
}
