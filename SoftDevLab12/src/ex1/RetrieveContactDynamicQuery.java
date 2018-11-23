package ex1;

import java.util.List;
import javax.persistence.*;


public class RetrieveContactDynamicQuery {

    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("SoftDevLab12PU");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT c1 FROM Contact c1 ORDER BY c1.id",
                Contact.class);
        List<Contact> cList = query.getResultList();
        System.out.println("Contact List:");
        for (Contact c : cList) {
            System.out.println(c);
        }
        em.close();
        emf.close();
    }
}
