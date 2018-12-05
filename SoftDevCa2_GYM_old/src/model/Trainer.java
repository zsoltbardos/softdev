/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


/**
 *
 * @author x00157506
 */

@Entity
@Table(name = "TRAINER")

@SequenceGenerator(name="trainerid_seq", initialValue=1, allocationSize=1)
@SuppressWarnings("SerializableClass")

public class Trainer {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="trainerid_seq")
    private int trainer_id;
    private String trainer_first_name;
    private String trainer_last_name;
    private String trainer_phone;
    private String trainer_email;
    
        @OneToMany(mappedBy = "trainer",cascade = CascadeType.ALL)
    private List<Class> classList = new ArrayList<>();

    public Trainer() {
    }
        
        

    public Trainer(String trainer_first_name, String trainer_last_name, String trainer_phone, String trainer_email) {
        this.trainer_first_name = trainer_first_name;
        this.trainer_last_name = trainer_last_name;
        this.trainer_phone = trainer_phone;
        this.trainer_email = trainer_email;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    public String getTrainer_first_name() {
        return trainer_first_name;
    }

    public void setTrainer_first_name(String trainer_first_name) {
        this.trainer_first_name = trainer_first_name;
    }

    public String getTrainer_last_name() {
        return trainer_last_name;
    }

    public void setTrainer_last_name(String trainer_last_name) {
        this.trainer_last_name = trainer_last_name;
    }

    public String getTrainer_phone() {
        return trainer_phone;
    }

    public void setTrainer_phone(String trainer_phone) {
        this.trainer_phone = trainer_phone;
    }

    public String getTrainer_email() {
        return trainer_email;
    }

    public void setTrainer_email(String trainer_email) {
        this.trainer_email = trainer_email;
    }
    
    
    public void addClass(Class c)
    {        
        classList.add(c);
        c.setTrainer(this);
    }
    
    public void printClasses(){
        System.out.println("Classes: ");
        for (int i = 0; i < classList.size(); i++) {
            System.out.println( classList.get(i));
        }

    }
    
}
