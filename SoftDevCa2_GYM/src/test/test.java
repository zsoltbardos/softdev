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

        MemberOperations dbo = new MemberOperations();
        dbo.openDB();

        dbo.dropMemberSequence();
        dbo.dropClassSequence();
        dbo.dropTrainerSequence();
        dbo.dropMembershipSequence();

        so.createDepartmentSequence();
        so.createAddressSequence();
        so.createModuleSequence();
        so.createStudentSequence();

        so.dropAddressTable();
        so.dropStudentTable();
        so.dropDEPTable();
        so.dropGSTable();
        so.dropNGTable();
        so.dropModuleTable();
        so.dropSTUDENTMODULETable();

        so.createDEPtable();
        so.createAddresstable();
        so.createStudenttable();
        so.createGStable();
        so.createNGtable();
        so.createModuletable();
        so.createSTUDENTMODULEtable();
    }
}
