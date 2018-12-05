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

    public void addMember(String firstName, String lastName, String gender, String phone, String email, String address, Calendar dob, String medical_condition, int mshipID) {
        em.getTransaction().begin();
        Member m = new Member(firstName, lastName, gender, phone, email, address, dob, medical_condition);
        Membership mship = em.find(Membership.class, mshipID);
        if (mship != null) {
            mship.addMember(m);
        }
        em.persist(m);
        em.getTransaction().commit();
    }
    
    public void addTrainer(String firstName, String lastName, String phone, String email) {
        em.getTransaction().begin();
        Trainer t = new Trainer(firstName, lastName, phone, email);
        em.persist(t);
        em.getTransaction().commit();
    }
    
    public void addMembership(String type, double price, String duration) {
        em.getTransaction().begin();
        Membership mship = new Membership(type, price, duration);
        em.persist(mship);
        em.getTransaction().commit();
    }
    
    public void addClass(String name, String day, String time, int trainerID) {      
        em.getTransaction().begin();
        Class c = new Class(name, day, time);
        Trainer trainer = em.find(Trainer.class, trainerID);
        if (trainer != null){
            trainer.addClass(c);
        }
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
    
    public List getClasses(){
        em.getTransaction().begin();

        TypedQuery<Class> query
                = em.createQuery("SELECT c FROM Class c order by c.class_id",
                        Class.class);
        List<Class> results = query.getResultList();

        
        em.getTransaction().commit();
        
        return results;
        
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

    public void viewClassMembers(int class_id){
        em.getTransaction().begin();
        Class c = em.find(Class.class, class_id);
        if(c==null){
            System.out.println("Class does not exist");
        }else
        {
            System.out.println("Members enrolled in " + c.getClass_name() + ":");
            System.out.println(c.getMemberList());
        }
        em.getTransaction().commit();

    }

    public void viewTrainerClasses(int trainer_id){
        em.getTransaction().begin();
        Trainer t = em.find(Trainer.class, trainer_id);
        if(t==null){
            System.out.println("Trainer does not exist");
        }else{
        t.printClasses();
        }
        em.getTransaction().commit();
    }

    public void viewMemberClasses(int mem_id){
         em.getTransaction().begin();
        Member m = em.find(Member.class, mem_id);
        if(m==null){
            System.out.println("Member does not exist");
        }else{
        m.printClasses();
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
    
    public Membership findMembership(int id) {
        Membership m = em.find(Membership.class, id);
        if (m == null) {
            System.out.println("Not found");
        }
        return m;
    }
    
        
    public void deleteMembership(int mshipID) {
        Membership mship = em.find(Membership.class, mshipID);
        em.getTransaction().begin();
        em.remove(mship);
        em.getTransaction().commit();
    }
    
//    public void whichClassWhichDay(String day){
//        for (Object c : getClasses()) {
//           if (day.toLowerCase().equals(c.getClass_name().toLowerCase())){
//               System.out.println(class_name);
//           }
//        }
//    }
    
    public void whichClassWhichDay(String day){
        List<Class> classList = getClasses();
        for (int i=0; i < classList.size(); i++) {
            if (day.toLowerCase().equals(classList.get(i).getClass_day().toLowerCase())) {
                System.out.println(" - " + classList.get(i).getClass_name());
            }
        }
    }
    


    public void close() {
        em.close();
        emf.close();
    }


}
