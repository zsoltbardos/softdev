/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softdevlab3;

/**
 *
 * @author x00157506
 */
public class Staff {
    private StaffMember[] staffMembers;

    public Staff() {
        this.staffMembers = new StaffMember[]{ new Executive("John", "1 This St", "0874323476", "R6574635", 2423.07), 
                                               new Employee("Paul", "2 That St", "0854345222", "P8884635", 1246.15),
                                               new Employee("Dave", "3 Other St", "0864343566", "S9876635", 1169.23),
                                               new Hourly("Joanne", "4 Long Ave.", "0836789676", "T0987635", 10.55),
                                               new Volunteer("Norma", "5 Short Ave.", "0864323456"),
                                               new Volunteer("Kate", "6 Pecks Lane", "0854676767"),
                                               new Comission("Molly", "7 Nowhere Rd.", "0864326789", "L6599635", 6.25, 0.2),
                                               new Comission("Joe", "8 My Place", "0864344545", "G8888635", 9.75, 0.15)
                                              };
    }
    
    public void processStaff() {
        for(int i=0;i<staffMembers.length;i++){
            if (staffMembers[i].name.equals("John")){
                Executive john = (Executive) staffMembers[i];
                john.awardBonus(500);
            } else if (staffMembers[i].name.equals("Joanne")){
                Hourly joanne = (Hourly) staffMembers[i];
                joanne.addHours(50);
            } else if (staffMembers[i].name.equals("Molly")){
                Comission molly = (Comission) staffMembers[i];
                molly.addHours(55);
                molly.addSales(400);
            } else if (staffMembers[i].name.equals("Joe")){
                Comission joe = (Comission) staffMembers[i];
                joe.addHours(45);
                joe.addSales(950);
            }
       }
    }
    
    public void payDay(){
        for(int i=0;i<staffMembers.length;i++){
            System.out.println(staffMembers[i].toString());
            staffMembers[i].pay();
        }
    }
    
    
}
