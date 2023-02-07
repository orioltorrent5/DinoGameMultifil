package com.example.dinogamemultifil;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Observable;

public class Metorit extends Observable implements Runnable{

    private String nombre;
    private Posicio posicions = new Posicio(0, 0);
    private final ImageView imageView = new ImageView("https://i.imgur.com/EK2sGjw.png");

    public Metorit(String _nombre){
        this.nombre = _nombre;
    }

    public Posicio getPosicions() {
        return posicions;
    }

    public void setPosicions(Posicio posicions) {
        this.posicions = posicions;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void run(){
        int numAleatoriY;
        int posicionY = 0;
        int aleatoriX = generarAleatori(1, 399);

        try {
            posicions.setPosicioX(aleatoriX);

            boolean repetir = true;
            while (repetir){
                if (this.posicions.getPosicioY() <= 450){
                    numAleatoriY = generarAleatori(4, 8);
                    posicionY += numAleatoriY;

                    this.posicions.setPosicioY(posicionY);

                    setChanged();
                    notifyObservers(this.posicions);
                    clearChanged();
                    Thread.sleep(55);
                }else
                {
                    this.posicions.setPosicioY(0);
                    posicionY = 0;
                    setChanged();
                    notifyObservers(this.posicions);
                    clearChanged();
                }

            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    //Generació del moviment de forma aleatoria
    public static int generarAleatori(int minimo, int maximo){
        int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }

}
