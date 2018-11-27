package test;

import db.PersistenceOperations;
import db.StudentOperations;
import java.util.Calendar;
import java.util.Scanner;
import model.Address;
import model.Department;
import model.Module;
import model.Student;

public class TestStudentDB {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        StudentOperations so = new StudentOperations();
        so.openDB();

        so.dropDepartmentSequence();
        so.dropAddressSequence();
        so.dropModuleSequence();
        so.dropStudentSequence();

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

        so.fillDepartmentTable();
        so.fillAddressTable();
        so.fillStudentTable();
        so.fillGSTable();
        so.fillNGTable();
        so.fillModuleTable();
        so.fillSTUDENTMODULETable();

        PersistenceOperations po = new PersistenceOperations();

        while (true) {
            System.out.println("Please press 1 to add a new department");
            System.out.println("Please press 2 to add a new student");
            System.out.println("Please press 3 to add a new module");
            System.out.println("Please press 4 to enrol a student in a module");
            System.out.println("Please press 5 to unenrol a student from a module");

            System.out.println("Please press 6 to view all department details");
            System.out.println("Please press 7 to view all students");
            System.out.println("Please press 8 to view all grant students");
            System.out.println("Please press 9 to view all non grant students");

            System.out.println("Please press 10 to view all students in a department");
            System.out.println("Please press 11 to view all modules for a student");
            System.out.println("Please press 12 to view all students enrolled in a module");

            System.out.println("Please press 13 to delete a student ");
            System.out.println("Please press 14 to delete a department");
            System.out.println("Please press 15 to delete a module");

            System.out.println("Press 16 to quit");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("please enter the name of the depeartment "
                            + "you wish to add");
                    String name = in.nextLine();
                    po.addDep(name);
                    break;
                case 2:
                    System.out.println("please enter the name of student "
                            + "you wish to add");
                    name = in.nextLine();
                    System.out.println("Please enter the dob year of "
                            + "the employee");
                    int year = in.nextInt();
                    in.nextLine();
                    System.out.println("Please enter the dob month of "
                            + "the employee");
                    int month = in.nextInt();
                    in.nextLine();
                    System.out.println("Please enter the dob day of "
                            + "the employee");
                    int day = in.nextInt();
                    in.nextLine();
                    Calendar sdate = Calendar.getInstance();
                    sdate.set(year, month, day);
                    System.out.println("Please enter the address of the student");
                    String addr = in.nextLine();
                    System.out.println("Please enter the eir code of the student");
                    String eir = in.nextLine();
                    Address a = new Address(addr, eir);
                    System.out.println("Please enter the department ID where this student will be based");
                    int did = in.nextInt();
                    in.nextLine();
                    System.out.println("please enter the student type");
                    String type = in.nextLine();
                    if (type.equals("GS")) {
                        System.out.println("Pleaes enter the grant amount for this student");
                        double grant = in.nextDouble();
                        po.addGrantStudent(name, sdate, type, did, grant, a);
                    } else if (type.equals("NG")) {
                        System.out.println("Pleaes enter the fees due for this student");
                        double fees = in.nextDouble();
                        po.addNonGrantStudent(name, sdate, type, did, fees, a);
                    }
                    break;
                case 3:
                    System.out.println("please enter the name of the module you wish to add");
                    String module = in.nextLine();
                    System.out.println("Please enter the workload per week in hours");
                    int hrs = in.nextInt();
                    in.nextLine();
                    po.addModule(module, hrs);
                    break;
                case 4:
                    System.out.println("Please enter the student id");
                    int id = in.nextInt();
                    in.nextLine();
                    System.out.println("Please enter the module id");
                    int mid = in.nextInt();
                    in.nextLine();
                    po.enrolModule(id, mid);
                    break;
                case 5:
                    System.out.println("Please enter the student id");
                    id = in.nextInt();
                    in.nextLine();
                    System.out.println("Please enter the module id");
                    mid = in.nextInt();
                    in.nextLine();
                    po.unenrolModule(id, mid);
                    break;
                case 6:
                    po.viewDepartments();
                    break;
                case 7:
                    po.viewStudents();
                    break;
                case 8:
                    po.viewGStudents();
                    break;
                case 9:
                    po.viewNonGrantStudents();
                    break;
                case 10:
                    System.out.println("please enter the id of the department where "
                            + "you wish to view the students");
                    did = in.nextInt();
                    in.nextLine();
                    po.viewDepStudents(did);
                    break;
                case 11:
                    System.out.println("please enter the id of the student whose "
                            + "modules you wish to view");
                    id = in.nextInt();
                    in.nextLine();
                    po.viewStudentModules(id);
                    break;
                case 12:
                    System.out.println("please enter the id of the module to view the"
                            + " list of students enrolled");
                    mid = in.nextInt();
                    in.nextLine();
                    po.viewModuleStudents(mid);
                    break;
                case 13:
                    System.out.println("Please enter the id of the student you wish to delete");
                    id = in.nextInt();
                    in.nextLine();
                    Student s = po.findStudent(id);
                    if (s != null) {
                        po.deleteStudent(id);
                    }
                    break;
                case 14:
                    System.out.println("Please enter the id of the department you wish to delete");
                    did = in.nextInt();
                    in.nextLine();
                    Department d = po.findDepartment(did);
                    if (d != null) {
                        po.deleteDepartment(did);
                    }
                    break;
                case 15:
                    System.out.println("Please enter the id of the module you wish to delete");
                    mid = in.nextInt();
                    in.nextLine();
                    Module m = po.findModule(mid);
                    if (m != null) {
                        po.deleteModule(mid);
                    }
                    break;
                case 16:
                    po.close();
                    so.closeDB();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option entered");
                    break;
            }
        }
    }

}
