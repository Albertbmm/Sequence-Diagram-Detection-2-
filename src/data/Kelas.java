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

public class Kelas {
    List<Metode> listmetode = new ArrayList<Metode>();
    String nama="";
    String modifier="";
    String tipe ="";
    public Kelas() {
        
    }
    public Kelas(String nama, String modifier,String tipe) {
        this.nama = nama;
        this.modifier = modifier;
        this.tipe = tipe;
    }
   
    public void insertMetode(Metode metode) {
        
        listmetode.add(metode);
        
    }
   
     public void checkIsiMetode(){
        for(int x=0;x<listmetode.size();x++){
            System.out.println(listmetode.get(x).toString());
        }
        if(!listmetode.isEmpty()){
        System.out.println("ada isinya");    
        }
        
    }
    public boolean isEqual(String nama) {
        //mengecek metode ini sama atau tidak , dengan metode yang lain
        return this.nama.matches(nama);
    }
    
    public Metode getMetode(String namametode) {
        //untuk mengambil nama metode yang kita cari 
        return null;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(modifier).append(" ").append(tipe).append(" ").append(nama);
        return stringBuilder.toString();
    }
}
