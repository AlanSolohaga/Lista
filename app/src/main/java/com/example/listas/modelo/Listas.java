package com.example.listas.modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Listas implements Serializable {

    private ArrayList<Lista> listas;

    public Listas(ArrayList<Lista> listas) {
        this.listas = listas;
    }

    public Listas() {
    }

    public ArrayList<Lista> getListas() {
        return listas;
    }

    public void setListas(ArrayList<Lista> listas) {
        this.listas = listas;
    }
}
