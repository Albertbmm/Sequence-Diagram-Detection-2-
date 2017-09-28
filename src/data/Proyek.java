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
public class Proyek {
    List<Kelas> listkelas = new ArrayList<Kelas>();
    
    
    public Proyek() {
        listkelas = new ArrayList();
    }
    
    public void addKelas(Kelas kelas) {
        listkelas.add(kelas);
    }
    public void checkNamaClass(){
       for(int x=0;x<listkelas.size();x++){
           System.out.println(listkelas.get(x).toString());
       }    
    }
    
    
}
