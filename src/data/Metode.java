package data;

import java.util.ArrayList;
import java.util.List;
import data.BarisKodeSumber;

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
    //baris code isinya method call
    List<BarisKodeSumber> bariskode = new ArrayList<BarisKodeSumber>();
    String namaMetode = "";
    String modifierMetode = "";
    String tipeReturnMetode = "";
    //mengisi methode dengan nama methode , modifier , dan dll pindahkan semua dari yang arraylist ke sini
    
    public Metode(String namaMetode,String modifierMetode,String tipeReturnMetode) {
        this.namaMetode = namaMetode;
        this.modifierMetode = modifierMetode;
        this.tipeReturnMetode = tipeReturnMetode;
    }
    
    public void insertBarisKode(BarisKodeSumber bks) {
        bariskode.add(bks);
    }
    public void checkBarisKode(){
        for(int x=0;x<bariskode.size();x++){
            System.out.println(bariskode.get(x).toString());
        }
    }
    public BarisKodeSumber getFirst() {
       //metode baris perintah , untuk memanggil kode sumber
       //metode bisa disearch, bisa di search by keyword
       //di search dulu , baru melakukan getFirst getNext
       //mennunjukkan pointer sementara posisi baris perintah , untuk pemanggilan kode sumber
       BarisKodeSumber barisData = null;
       barisData = bariskode.get(0);
       return barisData;
    }
    
    public BarisKodeSumber getNext() {
        //untuk mengecek metode selanjutnya.
        //refrensi
        //https://stackoverflow.com/questions/16244205/using-next-to-call-next-iteration-of-arraylist
        int barisSize = bariskode.size();
        return null;
    }
    
    public boolean isExist(String bariskode) {
        //untuk mengecek apakah bariskode yang dimasukkan ada atau tidak
        if(!bariskode.isEmpty()){
            //do something
            return true;
        }else{
            return false;
        }   
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(tipeReturnMetode).append(" ").append(modifierMetode).append(" ").append(namaMetode);
        return stringBuilder.toString();
    }
}
