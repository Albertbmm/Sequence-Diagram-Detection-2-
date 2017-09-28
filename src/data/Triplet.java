package data;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Triplet {
    private String subyek;
    private String predikat;
    private String obyek;
    
    public Triplet(String subyek,String predikat,String obyek){
        this.subyek = subyek;
        this.predikat = predikat;
        this.obyek = obyek;
        //ini yang di cek di kelas , cara mengecek nya mengikuti urutan sequence 
        //inputan berupa list of triplet, dan berupa source code. nanti tinggal di samakan 
        //disimpan saja modifier dan tipe return
    }
     @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(subyek).append(" ").append(obyek).append(" ").append(predikat);
        return stringBuilder.toString();
    }
}
