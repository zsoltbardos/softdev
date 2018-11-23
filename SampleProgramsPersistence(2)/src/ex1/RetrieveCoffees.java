package ex1;

import java.util.List;
import javax.persistence.*;

public class RetrieveCoffees {
    public static void main(String[] args) {

        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory
        ("SampleProgramsPersistence_2_PU");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT co FROM Coffee co",
                Coffee.class);
        List<Coffee> cList = query.getResultList();
        System.out.println("Coffee List:");
        for (Coffee c : cList) {
            System.out.println(c);
        }

        em.close();
        emf.close();
    }   
}
