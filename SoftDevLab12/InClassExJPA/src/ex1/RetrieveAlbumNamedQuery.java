/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex1;

import java.util.List;
import javax.persistence.*;

public class RetrieveAlbumNamedQuery {
    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory
        ("InClassExJPAPU");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Album> query
                = em.createNamedQuery("Album.findAll", Album.class);

        List<Album> results = query.getResultList();
        for (Album c : results) {
            System.out.println(c.toString());
        }
        em.close();
        emf.close();
    }    
}
