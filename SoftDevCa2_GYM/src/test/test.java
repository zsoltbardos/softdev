/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import db.MemberOperations;
import db.PersistenceOperations;
import java.util.Scanner;

/**
 *
 * @author x00157506
 */
public class test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        MemberOperations mo = new MemberOperations();
        mo.openDB();

        mo.dropClassSequence();
        mo.dropMemberSequence();
        mo.dropMembershipSequence();
        mo.dropTrainerSequence();

        mo.createClassSequence();
        mo.createMemberSequence();
        mo.createMembershipSequence();
        mo.createTrainerSequence();

        mo.dropClassTable();
        mo.dropMemberTable();
        mo.dropMembershipTable();
        mo.dropTrainerTable();

        mo.createTrainerTable();
        mo.createClassTable();
        mo.createMembershipTable();
        mo.createMemberTable();
        
        mo.fillMembershipTable();
        mo.fillMemberTable();
        mo.fillTrainerTable();
        mo.fillClassTable();




//        so.fillDepartmentTable();
//        so.fillAddressTable();
//        so.fillStudentTable();
//        so.fillGSTable();
//        so.fillNGTable();
//        so.fillModuleTable();
//        so.fillSTUDENTMODULETable();

        PersistenceOperations po = new PersistenceOperations();
    }
}
