package ex1;

import java.util.List;
import javax.persistence.*;

public class RetrieveAlbumNamedQueryParameter {
    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("InClassExJPAPU");
        EntityManager em = emf.createEntityManager();

        Query query
                = em.createNamedQuery("Album.findByPriceArtist", Album.class).setParameter("artist", "Keane").setParameter("price", 7.0);

        List<Album> results = query.getResultList();
        for (Album a : results) {
            System.out.println(a.toString());
        }
        em.close();
        emf.close();
    }
}
