/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softdevlab2;

/**
 *
 * @author X00157506
 */
public class Musician extends Person {
    private String instrumentName;

    public Musician(String personName, String instrumentName) {
        super(personName);
        this.instrumentName = instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public String getInstrumentName() {
        return instrumentName;
    }
    
    @Override
    public String toString() {
        return super.getPersonName() + " plays " + instrumentName;
    }
}
