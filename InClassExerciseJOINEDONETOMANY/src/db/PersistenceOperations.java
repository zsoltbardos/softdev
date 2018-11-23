/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import model.Employee;
import model.FullTimeEmployee;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Department;
import model.PartTimeEmployee;


public class PersistenceOperations {

    EntityManagerFactory emf;
    EntityManager em;

    public PersistenceOperations() {
        emf = Persistence.createEntityManagerFactory("joinPU");
        em = emf.createEntityManager();
    }

    public Department findDepartment(int id) {
        Department d = em.find(Department.class, id);
        if (d == null) {
            System.out.println("Not found");
        }
        return d;
    }
    
    public void addDep(String name){
        em.getTransaction().begin();
        Department d = new Department();
        d.setDeptName(name);
        em.persist(d);
        em.getTransaction().commit();
    }
    
    public void addFTEmployee(String name, Calendar sDate, int dID, double salary){
        em.getTransaction().begin();
        FullTimeEmployee ft1 = new FullTimeEmployee(name, sDate, salary);
        Department d = em.find(Department.class, dID);
        if(d != null){
            d.addEmp(ft1);
        }
        
        em.getTransaction().commit();
        
    }
    
    public void addpTEmployee(String name, Calendar sDate, int dID, double rate, double hours){
        em.getTransaction().begin();
        PartTimeEmployee pt1 = new PartTimeEmployee(name, sDate, rate, hours);
        Department d = em.find(Department.class, dID);
        if(d != null){
            d.addEmp(pt1);
        }
        
        em.getTransaction().commit();
        
    }
    
    public void viewDepartments() {
        TypedQuery<Department> query = em.createQuery("SELECT d FROM Department d ORDER BY d.did", Department.class);
        List<Department> results = query.getResultList();
        for (Department d : results) {
            System.out.println(d);
        }
    }
    
    public void showAllEmployees(){
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e ORDER BY e.empid", Employee.class);
        List<Employee> results = query.getResultList();
        for (Employee e : results) {
            System.out.println(e);
        }
    }
    
    public void showAllPT(){
        TypedQuery<PartTimeEmployee> query = em.createQuery("SELECT pt FROM PartTimeEmployee pt ORDER BY pt.empid", PartTimeEmployee.class);
        List<PartTimeEmployee> results = query.getResultList();
        for (PartTimeEmployee pt : results) {
            System.out.println(pt);
        }
    }
    
    public void showAllFT(){
        TypedQuery<FullTimeEmployee> query = em.createQuery("SELECT ft FROM FullTimeEmployee ft ORDER BY ft.empid", FullTimeEmployee.class);
        List<FullTimeEmployee> results = query.getResultList();
        for (FullTimeEmployee ft : results) {
            System.out.println(ft);
        }
    }
    
    public void removeEmployee(int eid, int did){
        em.getTransaction().begin();
        
        Employee e = em.find(Employee.class, eid);
        Department d = em.find(Department.class, did);
        em.remove(e);
        d.getElist().remove(e);
        
        
        em.getTransaction().commit();
    }
    
    public void deleteDepartment(int did) {
        em.getTransaction().begin();
        Department d = em.find(Department.class, did);
        em.remove(d);
        em.getTransaction().commit();
    }





    public void close() {
        em.close();
        emf.close();
    }
}
