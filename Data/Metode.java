
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Metode {
    List<BarisKodeSumber> bariskode;

    
    public Metode() {
        
    }
    
    public void insertBarisKode(BarisKodeSumber bks) {
        bariskode.add(bks);
    }
    
    public BarisKodeSumber getFirst() {
       return null; 
    }
    
    public BarisKodeSumber getNext() {
        return null;
    }
    
    
}
