package ex1;

import java.util.List;
import javax.persistence.*;

public class RetrieveContactNamedQueryParameter {
    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("SoftDevLab12PU");
        EntityManager em = emf.createEntityManager();

        Query query
                = em.createNamedQuery("Contact.findByID", Contact.class).setParameter("id", 3);

        List<Contact> results = query.getResultList();
        for (Contact c : results) {
            System.out.println(c.toString());
        }
        em.close();
        emf.close();
    }
}
