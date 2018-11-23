/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex1;

import java.util.List;
import javax.persistence.*;

public class NamedQueryParameter {
    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("Persistence_2_PU");
        EntityManager em = emf.createEntityManager();

        Query query
                = em.createNamedQuery("Coffee.findByPrice", Coffee.class).setParameter("price", 8.95);

        List<Coffee> results = query.getResultList();
        for (Coffee c : results) {
            System.out.println(c.toString());
        }
        em.close();
        emf.close();
    }
}
