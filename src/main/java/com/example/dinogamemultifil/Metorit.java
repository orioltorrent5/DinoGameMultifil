package com.example.dinogamemultifil;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Observable;

public class Metorit extends Observable implements Runnable{

    private String nombre;
    private int posicionY = 0;

    public Metorit(String _nombre){
        this.nombre = _nombre;
    }


    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    //Run amb el moviment dels cavalls
    public void run(){
        int numAleatori;
        try {
            while (this.posicionY < 450){
                numAleatori = generarAleatori(7, 10);
                this.posicionY += numAleatori;
                this.setChanged();
                this.notifyObservers(this.posicionY);
                this.clearChanged();
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Generació del moviment de forma aleatoria
    public static int generarAleatori(int minimo, int maximo){
        int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }
}
