/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import model.Contact;
import model.Staff;

public class PersistenceOperations {

    EntityManagerFactory emf;
    EntityManager em;

    public PersistenceOperations() {
        emf = Persistence.createEntityManagerFactory("SampleProgramCompositionPU");
        em = emf.createEntityManager();
    }

    public void showContacts(int id) {
        em.getTransaction().begin();
        Staff s = em.find(Staff.class, id);
        if(s==null){
            System.out.println("Staff member does not exist");
        }else{
        System.out.println(s);
        }
        em.getTransaction().commit();

    }

    public Staff findStaff(int id) {
        Staff s = em.find(Staff.class, id);
        if (s == null) {
            System.out.println("Not found");
        }
        return s;
    }

    public void addStaff(String name) {
        em.getTransaction().begin();

        Staff s = new Staff();
        s.setName(name);
        em.persist(s);

        em.getTransaction().commit();
    }

    public void addContact(Staff s, Contact c) {
        em.getTransaction().begin();
        s.addContact(c);
        em.getTransaction().commit();
    }
    
    public void deleteContact(int id, int sid){
        Contact c = em.find(Contact.class, id);
        Staff s = em.find(Staff.class, sid);
        em.getTransaction().begin();
        em.remove(c);
        s.getClist().remove(c);
        em.getTransaction().commit();
    }
    
    public void deleteStaff(int sid) {
        Staff s = em.find(Staff.class, sid);
        em.getTransaction().begin();
        em.remove(s);
        em.getTransaction().commit();
    }
    public void close() {
        em.close();
        emf.close();
    }
}
