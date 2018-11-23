package ex1;

import java.util.List;
import javax.persistence.*;

public class RetrieveCoffeesNamedQuery {

    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory
        ("Persistence_2_PU");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Coffee> query
                = em.createNamedQuery("Coffee.findAll", Coffee.class);

        List<Coffee> results = query.getResultList();
        for (Coffee c : results) {
            System.out.println(c.toString());
        }
        em.close();
        emf.close();
    }
}


