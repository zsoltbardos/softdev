/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Member;
import model.Trainer;
import model.Class;
import model.Membership;

/**
 *
 * @author x00157506
 */
public class PersistenceOperations {
    EntityManagerFactory emf;
    EntityManager em;

    public PersistenceOperations() {
        emf = Persistence.createEntityManagerFactory("SoftDevCa2_GYMPU");
        em = emf.createEntityManager();
    }

    public void addMember(String firstName, String lastName, String gender, String phone, String email, String address, Calendar dob, String medical_condition) {
        em.getTransaction().begin();
        Member m = new Member(firstName, lastName, gender, phone, email, address, dob, medical_condition);
        em.persist(m);
        em.getTransaction().commit();
    }
    
    public void addTrainer(String firstName, String lastName, String phone, String email) {
        Trainer t = new Trainer(firstName, lastName, phone, email);
        em.persist(t);
        em.getTransaction().commit();
    }
    
    public void addClass(String name, String day, String time) {
        Class c = new Class(name, day, time);
        em.persist(c);
        em.getTransaction().commit();
    }

    public void enrolClass(int mem_id, int class_id) {
        em.getTransaction().begin();
        Member mem = em.find(Member.class, mem_id);
        Class c = em.find(Class.class, class_id);
        c.addMember(mem);
        em.getTransaction().commit();
    }
    
    public void unenrolClass(int mem_id, int class_id) {
        em.getTransaction().begin();
        Member mem = em.find(Member.class, mem_id);
        Class c = em.find(Class.class, class_id);
        c.removeMember(mem);
        em.getTransaction().commit();
    }


    public void viewMembers() {
        em.getTransaction().begin();

        TypedQuery<Member> query
                = em.createQuery("SELECT m FROM Member m order by m.mem_id",
                        Member.class);
        List<Member> results = query.getResultList();

        for (Member m : results) {
            System.out.println(m);
        }
        em.getTransaction().commit();

    }
    public void viewClasses() {
        em.getTransaction().begin();

        TypedQuery<Class> query
                = em.createQuery("SELECT c FROM Class c order by c.class_id",
                        Class.class);
        List<Class> results = query.getResultList();

        for (Class c : results) {
            System.out.println(c);
        }
        em.getTransaction().commit();

    }
    public void viewTrainers() {
        em.getTransaction().begin();

        TypedQuery<Trainer> query
                = em.createQuery("SELECT t FROM Trainer t order by t.trainer_id",
                        Trainer.class);
        List<Trainer> results = query.getResultList();

        for (Trainer t : results) {
            System.out.println(t);
        }
        em.getTransaction().commit();

    }
    public void viewMemberships() {
        em.getTransaction().begin();

        TypedQuery<Membership> query
                = em.createQuery("SELECT m FROM Membership m order by m.mship_id",
                        Membership.class);
        List<Membership> results = query.getResultList();

        for (Membership m : results) {
            System.out.println(m);
        }
        em.getTransaction().commit();

    }

    public void viewClassMember(int class_id){
        em.getTransaction().begin();
        Class c = em.find(Class.class, class_id);
        if(c==null){
            System.out.println("Class does not exist");
        }else{
        System.out.println(c);
        }
        em.getTransaction().commit();

    }

    public void viewTrainerClass(int trainer_id){
        em.getTransaction().begin();
        Trainer t = em.find(Trainer.class, trainer_id);
        if(t==null){
            System.out.println("Trainer does not exist");
        }else{
        t.printClasses();
        }
        em.getTransaction().commit();
    }

    public void viewMemberMembership(int mem_id){
         em.getTransaction().begin();
        Member m = em.find(Member.class, mem_id);
        if(m==null){
            System.out.println("Member does not exist");
        }else{
        m.printMembership();
        }
        em.getTransaction().commit();
    }

    public Member findMember(int id) {
        Member m = em.find(Member.class, id);
        if (m == null) {
            System.out.println("Not found");
        }
        return m;
    }
    
    public void deleteMember(int mem_id) {
        Member m = em.find(Member.class, mem_id);
        em.getTransaction().begin();
        em.remove(m);
        em.getTransaction().commit();
    }

    public Class findClass(int id) {
        Class c = em.find(Class.class, id);
        if (c == null) {
            System.out.println("Not found");
        }
        return c;
    }

    public void deleteClass(int class_id) {
        Class c = em.find(Class.class, class_id);
        em.getTransaction().begin();
        em.remove(c);
        c.remove();
        em.getTransaction().commit();
    }
    
    public Trainer findTrainer(int id) {
        Trainer t = em.find(Trainer.class, id);
        if (t == null) {
            System.out.println("Not found");
        }
        return t;
    }
    
    public void deleteTrainer(int trainer_id) {
        Trainer t = em.find(Trainer.class, trainer_id);
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
    }

    public void close() {
        em.close();
        emf.close();
    }


}
