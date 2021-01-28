package com.example.listas.modelo;

import com.example.listas.modelo.Cosa;

import java.io.Serializable;
import java.util.ArrayList;

public class Lista implements Serializable {
    private String nombre;
    private ArrayList<Cosa> cosas;
    private float total;

    public Lista(String nombre, ArrayList<Cosa> cosas) {
        this.nombre = nombre;
        this.cosas = cosas;
    }

    public Lista() {
    }

    public float getTotal() {
        for(int i=0;i<cosas.size();i++){
            total +=cosas.get(i).getSubTotal();
        }
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Cosa> getCosas() {
        return cosas;
    }

    public void setCosas(ArrayList<Cosa> cosas) {
        this.cosas = cosas;
    }
}
