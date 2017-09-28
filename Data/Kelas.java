
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

public class Kelas {
    List<Metode> listmetode;
    String nama="";
    String modifier="";

    public Kelas(String nama, String modifier) {
        this.nama = nama;
        this.modifier = modifier;
    }

    public void insertMetode(Metode metode) {
        listmetode.add(metode);
    }

    public boolean isEqual(String nama) {
        return this.nama.matches(nama);
    }
    
  
    public Metode getMetode(String namametode) {
        return null;
    }
    
}
