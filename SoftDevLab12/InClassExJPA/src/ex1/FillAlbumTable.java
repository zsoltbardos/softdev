/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex1;

import javax.persistence.*;

public class FillAlbumTable {
    public static void main(String[] args) {
        EntityManagerFactory emf
        = Persistence.createEntityManagerFactory
        ("InClassExJPAPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        Album a1 = new Album();
        a1.setTitle("Strangeland");
        a1.setArtist("Keane");
        a1.setPrice(9.99);     
        em.persist(a1);      
        
        Album a2 = new Album();
        a2.setTitle("Hopes & Fears");
        a2.setArtist("Keane");
        a2.setPrice(8.99);     
        em.persist(a2);  
        
        Album a3 = new Album();
        a3.setTitle("Parachutes");
        a3.setArtist("ColdPlay");
        a3.setPrice(10.99);     
        em.persist(a3);
        
        em.getTransaction().commit();
        
        em.close();
        emf.close();      
    }   
}
