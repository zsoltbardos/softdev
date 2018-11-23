/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex1;

import java.util.List;
import javax.persistence.*;

public class RetrieveContactNamedQuery {
    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory
        ("SoftDevLab12PU");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Contact> query
                = em.createNamedQuery("Contact.findAll", Contact.class);

        List<Contact> results = query.getResultList();
        for (Contact c : results) {
            System.out.println(c.toString());
        }
        em.close();
        emf.close();
    }    
}
