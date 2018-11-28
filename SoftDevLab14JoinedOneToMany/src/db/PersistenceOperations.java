/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.Calendar;
import java.util.List;
import javax.persistence.*;
import model.Contact;
import model.Employee;
import model.FullTimeEmployee;
import model.PartTimeEmployee;

/**
 *
 * @author x00157506
 */
public class PersistenceOperations {
    EntityManagerFactory emf;
    EntityManager em;

    public PersistenceOperations() {
        emf = Persistence.createEntityManagerFactory("SoftDevLab14JoinedOneToManyPU");
        em = emf.createEntityManager();
    }
    
    public void showAllEmployees(){
        em.getTransaction().begin();
        
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e order by e.empid", Employee.class);
        List<Employee> results = query.getResultList();
        
        for (Employee e : results) {
            System.out.println(e);
            
        }
        em.getTransaction().commit();
    }
    
    public void showContacts(int id) {
        em.getTransaction().begin();
        Employee e = em.find(Employee.class, id);
        if(e==null){
            System.out.println("Employee does not exist");
        }else{
        System.out.println(e.toString_contacts());
        }
        em.getTransaction().commit();

    }
    
    
    
    public void showAllPT() {
        em.getTransaction().begin();
        TypedQuery<PartTimeEmployee> query = em.createQuery("SELECT pt FROM PartTimeEmployee pt "
                + "ORDER BY pt.empid", PartTimeEmployee.class);
        List<PartTimeEmployee> results = query.getResultList();
        for (PartTimeEmployee pt : results) {
            System.out.println(pt);
        }
        em.getTransaction().commit();
    }
    
    public void showAllFT() {
        em.getTransaction().begin();
        TypedQuery<FullTimeEmployee> query = em.createQuery("SELECT ft FROM FullTimeEmployee ft "
                + "ORDER BY ft.empid", FullTimeEmployee.class);
        List<FullTimeEmployee> results = query.getResultList();
        for (FullTimeEmployee ft : results) {
            System.out.println(ft);
        }
        em.getTransaction().commit();
    }
    
    public Employee findEmployee(int empid) {
        Employee d = em.find(Employee.class, empid);
        if (d == null) {
            System.out.println("Not found");
        }
        return d;
    }
    
    public void deleteEmployee(int eid) {
        Employee e = em.find(Employee.class, eid);
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
        System.out.println("Employee Deleted");
    }
    
    public void addFTEmployee(String name, Calendar sdate, double salary) {
        em.getTransaction().begin();
        FullTimeEmployee ft1 = new FullTimeEmployee(name, sdate, salary);
        em.persist(ft1);
        em.getTransaction().commit();
    }
    
    public void addpTEmployee(String name, Calendar sdate, double rate, double hrs) {
        em.getTransaction().begin();
        PartTimeEmployee pt1 = new PartTimeEmployee(name, sdate, rate, hrs);
        em.persist(pt1);
        em.getTransaction().commit();
    }
    
    public void addContact(Employee e, Contact c) {
        em.getTransaction().begin();
        e.addContact(c);
        em.getTransaction().commit();
    }
    
    public void deleteContact(int id, int eid){
        Contact c = em.find(Contact.class, id);
        Employee e = em.find(Employee.class, eid);
        em.getTransaction().begin();
        em.remove(c);
        e.getClist().remove(c);
        em.getTransaction().commit();
    }
    
    public void close(){
        em.close();
        emf.close();
    }
    
    
}
