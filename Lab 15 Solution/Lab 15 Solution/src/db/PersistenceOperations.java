/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.Calendar;
import java.util.List;
import javax.persistence.*;
import model.Address;
import model.Department;
import model.GrantStudent;
import model.Module;
import model.NonGrantStudent;
import model.Student;

public class PersistenceOperations {

    EntityManagerFactory emf;
    EntityManager em;

    public PersistenceOperations() {
        emf = Persistence.createEntityManagerFactory("Lab_15_SolutionPU");
        em = emf.createEntityManager();
    }

    public void addDep(String name) {
        em.getTransaction().begin();
        Department d = new Department();
        d.setDepName(name);
        em.persist(d);
        em.getTransaction().commit();
    }

    public void addGrantStudent(String name, Calendar dob, String course,
            int did, double grantAmt, Address a) {
        em.getTransaction().begin();
        GrantStudent gs1 = new GrantStudent(name, dob, course,grantAmt);
        Department d = em.find(Department.class, did);
        if (d != null) {
            d.addStudent(gs1);
        }
        gs1.setAddress(a);
        em.persist(gs1);
        em.getTransaction().commit();
    }

    public void addNonGrantStudent(String name, Calendar dob, String course,
            int did, double feesDue, Address a) {
        em.getTransaction().begin();
        NonGrantStudent ngs1 = new NonGrantStudent(name, dob, course, feesDue);
        Department d = em.find(Department.class, did);
        if (d != null) {
            d.addStudent(ngs1);
        }
        ngs1.setAddress(a);
        em.persist(ngs1);
        em.getTransaction().commit();
    }
    public void addModule(String name, int hrs) {
        em.getTransaction().begin();
        Module m = new Module(name, hrs);
        em.persist(m);
        em.getTransaction().commit();
    }

    public void enrolModule(int sid, int mid) {
        em.getTransaction().begin();
        Student s = em.find(Student.class, sid);
        Module m = em.find(Module.class, mid);
        m.addStudent(s);
        em.getTransaction().commit();
    }

    public void unenrolModule(int sid, int mid) {
        em.getTransaction().begin();
        Student s = em.find(Student.class, sid);
        Module m = em.find(Module.class, mid);
        m.removeStudent(s);
        em.getTransaction().commit();
    }

    public void viewDepartments() {
        em.getTransaction().begin();

        TypedQuery<Department> query
                = em.createQuery("SELECT d FROM Department d order by d.did",
                        Department.class);
        List<Department> results = query.getResultList();

        for (Department d : results) {
            System.out.println(d);
        }
        em.getTransaction().commit();

    }
    public void viewStudents() {
        em.getTransaction().begin();

        TypedQuery<Student> query
                = em.createQuery("SELECT s FROM Student s order by s.sid",
                        Student.class);
        List<Student> results = query.getResultList();

        for (Student s : results) {
            System.out.println(s);
        }
        em.getTransaction().commit();

    }
    public void viewGStudents() {
        em.getTransaction().begin();

        TypedQuery<GrantStudent> query
                = em.createQuery("SELECT gs FROM GrantStudent gs order by gs.sid",
                        GrantStudent.class);
        List<GrantStudent> results = query.getResultList();

        for (GrantStudent gs : results) {
            System.out.println(gs);
        }
        em.getTransaction().commit();

    }
    public void viewNonGrantStudents() {
        em.getTransaction().begin();

        TypedQuery<NonGrantStudent> query
                = em.createQuery("SELECT ngs FROM NonGrantStudent ngs order by ngs.sid",
                        NonGrantStudent.class);
        List<NonGrantStudent> results = query.getResultList();

        for (NonGrantStudent ngs : results) {
            System.out.println(ngs);
        }
        em.getTransaction().commit();

    }

    public void viewDepStudents(int did){
        em.getTransaction().begin();
        Department d = em.find(Department.class, did);
        if(d==null){
            System.out.println("Department does not exist");
        }else{
        System.out.println(d);
        }
        em.getTransaction().commit();

    }

    public void viewStudentModules(int id){
        em.getTransaction().begin();
        Student s = em.find(Student.class, id);
        if(s==null){
            System.out.println("Student does not exist");
        }else{
        s.printModules();
        }
        em.getTransaction().commit();
    }

    public void viewModuleStudents(int mid){
         em.getTransaction().begin();
        Module m = em.find(Module.class, mid);
        if(m==null){
            System.out.println("Module does not exist");
        }else{
        m.printStudents();
        }
        em.getTransaction().commit();
    }

    public Student findStudent(int id) {
        Student s = em.find(Student.class, id);
        if (s == null) {
            System.out.println("Not found");
        }
        return s;
    }
    
    public void deleteStudent(int sid) {
        Student s = em.find(Student.class, sid);
        em.getTransaction().begin();
        em.remove(s);
        em.getTransaction().commit();
    }

    public Department findDepartment(int id) {
        Department d = em.find(Department.class, id);
        if (d == null) {
            System.out.println("Not found");
        }
        return d;
    }

    public void deleteDepartment(int eid) {
        Department d = em.find(Department.class, eid);
        em.getTransaction().begin();
        em.remove(d);
        em.getTransaction().commit();
    }
    
    public Module findModule(int id) {
        Module d = em.find(Module.class, id);
        if (d == null) {
            System.out.println("Not found");
        }
        return d;
    }
    
    public void deleteModule(int mid) {
        Module m = em.find(Module.class, mid);
        em.getTransaction().begin();
        em.remove(m);
        m.remove();
        em.getTransaction().commit();
    }

    public void close() {
        em.close();
        emf.close();
    }


}


