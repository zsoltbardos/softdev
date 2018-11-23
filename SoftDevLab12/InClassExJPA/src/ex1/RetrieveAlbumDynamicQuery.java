package ex1;

import java.util.List;
import javax.persistence.*;


public class RetrieveAlbumDynamicQuery {

    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("InClassExJPAPU");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT al FROM Album al ORDER BY al.id",
                Album.class);
        List<Album> aList = query.getResultList();
        System.out.println("Album List:");
        for (Album a : aList) {
            System.out.println(a);
        }
        em.close();
        emf.close();
    }
}
