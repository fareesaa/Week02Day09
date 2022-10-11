package Comp.Assignment.Assignment2.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Model {
    int id ;
    String nama ="";
    ArrayList<Double> nilai = new ArrayList<>();
    public Model(int id, String nama, ArrayList<Double> nilai){
        this.id = id;
        this.nama = nama;
        this.nilai = nilai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public ArrayList<Double> getNilai() {
        return nilai;
    }

    public void setNilai(ArrayList<Double> nilai) {
        this.nilai = nilai;
    }
}
