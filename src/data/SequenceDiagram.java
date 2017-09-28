package data;


import java.util.ArrayList;
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
public class SequenceDiagram {
    List<Triplet> tripletSequence=new ArrayList<Triplet>();
    
    public SequenceDiagram(){
        
    }
    public void insertTriplet(Triplet triplet){
        tripletSequence.add(triplet);
    }
    public void checkIsiTriplet(){
          for(int x=0;x<tripletSequence.size();x++){
           System.out.println(tripletSequence.get(x).toString());
       }    
    }
}
