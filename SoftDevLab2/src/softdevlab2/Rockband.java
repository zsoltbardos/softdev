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
public class Rockband {
    private String bandName;
    private String bandLabel;
    private Musician[] musicians;

    public Rockband(String bandName, String bandLabel, String[] musicianName, String[] instrumentName) {
        this.bandName = bandName;
        this.bandLabel = bandLabel;
        musicians = new Musician[musicianName.length];
        
        for(int i=0;i<musicians.length;i++){
            musicians[i] = new Musician(musicianName[i], instrumentName[i]);
       }
    }
    
    public boolean changeRecordLabel(String labelName){
        if (this.bandLabel.equals(labelName)){
            return false;
        } else {
            this.bandLabel = labelName;
            return true;
        }
    }

    @Override
    public String toString() {
        String myString;
        myString = "Name: " + bandName + "\nRecord company: " + bandLabel;
        for (int i=0;i<musicians.length;i++){
            myString += "\n" + musicians[i];
        }
        return myString;
    }
    
    
    
    
}
