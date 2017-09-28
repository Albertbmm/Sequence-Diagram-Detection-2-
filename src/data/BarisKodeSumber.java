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
public class BarisKodeSumber {
    private Kelas kelas;
    private Metode metode;
    String pemanggil;
    String dipanggil;
    //untuk membikin tipe data KodeSumber
    public BarisKodeSumber(String pemanggil,String dipanggil){
        this.pemanggil = pemanggil;
        this.dipanggil = dipanggil;
    }
    public void setKelas(Kelas kelas) {
        this.kelas = kelas;
    }
    
    public void setMetode(Metode metode) {
       this.metode = metode;   
    }
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(pemanggil).append(".").append(dipanggil);
        return stringBuilder.toString();
    }
}
