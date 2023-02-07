package com.example.dinogamemultifil;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.layout.Pane;

public class HelloApplication extends Application implements Observer {
    private double dinosaurX = 250;

    private final ImageView imageView1 = new ImageView();
    private final ImageView imageView2 = new ImageView();
    private final ImageView imageView3 = new ImageView();
    private final ImageView imageView4 = new ImageView();
    private final ImageView imageView5 = new ImageView();
    private final ImageView imageView6 = new ImageView();
    private Label scoreLabel;

    int puntucio = 0;
    boolean perdut = false;

    private Thread[] fils = new Thread[6];
    Image dinoImage = new Image("https://i.imgur.com/EK2sGjw.png");
    Image metoritImage = new Image("https://i.imgur.com/pppQ6wV.png");
    Alert alert = new Alert(Alert.AlertType.INFORMATION, "HAS PERDUT!!", ButtonType.OK);

    public static void main(String[] args) {
        launch(args);
    }

    Pane root = new Pane();

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Dino Game");
        ImageView dinosaur = new ImageView(dinoImage);

        imageView1.setImage(metoritImage);
        imageView2.setImage(metoritImage);
        imageView3.setImage(metoritImage);
        imageView4.setImage(metoritImage);
        imageView5.setImage(metoritImage);
        imageView6.setImage(metoritImage);

        imageView1.setX(0);
        imageView2.setX(150);
        imageView3.setX(300);
        imageView4.setX(400);
        imageView5.setX(350);
        imageView6.setX(200);

        scoreLabel = new Label("Puntuación: " + puntucio);
        Image[] images = new Image[4];

        images[0] = new Image("https://i.imgur.com/mFC3ZJv.png%22");
        images[1] = new Image("https://i.imgur.com/lZYplpE.png%22");
        images[2] = new Image("https://i.imgur.com/33gXh5p.png%22");
        images[3] = new Image("https://i.imgur.com/Fj93SGN.png%22");

        ImageView[] imageViews = new ImageView[4];
        for (int i = 0; i < 4; i++) {
            imageViews[i] = new ImageView(images[i]);
            imageViews[i].setX(dinosaurX);
            imageViews[i].setY(450);
            root.getChildren().add(imageViews[i]);
        }
        imageViews[0].setVisible(true);
        imageViews[1].setVisible(false);
        imageViews[2].setVisible(false);
        imageViews[3].setVisible(false);

        root.getChildren().add(scoreLabel);
        root.getChildren().add(imageView1);
        root.getChildren().add(imageView2);
        root.getChildren().add(imageView3);
        root.getChildren().add(imageView4);
        root.getChildren().add(imageView5);
        root.getChildren().add(imageView6);



        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT, A:
                    dinosaurX -= 10;
                    if(imageViews[3].isVisible()){
                        imageViews[0].setVisible(false);
                        imageViews[1].setVisible(false);
                        imageViews[2].setVisible(true);
                        imageViews[3].setVisible(false);
                    }else{
                        imageViews[0].setVisible(false);
                        imageViews[1].setVisible(false);
                        imageViews[2].setVisible(false);
                        imageViews[3].setVisible(true);
                    }
                    break;
                case RIGHT, D:
                    dinosaurX += 10;
                    if(imageViews[0].isVisible()){
                        imageViews[0].setVisible(false);
                        imageViews[1].setVisible(true);
                        imageViews[2].setVisible(false);
                        imageViews[3].setVisible(false);
                    }else{
                        imageViews[0].setVisible(true);
                        imageViews[1].setVisible(false);
                        imageViews[2].setVisible(false);
                        imageViews[3].setVisible(false);
                    }
                    break;
            }
            if(dinosaurX < 0) {
                dinosaurX = 0;
            }
            if(dinosaurX > 450) {
                dinosaurX = 450;
            }
            imageViews[0].setX(dinosaurX);
            imageViews[1].setX(dinosaurX);
            imageViews[2].setX(dinosaurX);
            imageViews[3].setX(dinosaurX);

        });

        for (int i = 0; i < fils.length; i++){
            Metorit metorit = new Metorit((i + 1) + "");
            metorit.addObserver(this);
            fils[i] = new Thread(metorit);
            fils[i].start();
        }

        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                String punt = "Puntuación: " + puntucio;
                scoreLabel.setText(punt);

                if (perdut){
                    alert.show();
                    System.exit(0);
                }
            }
        };
        animationTimer.start();

    }

    public void update(Observable o, Object arg) {
        Metorit c = (Metorit) o;
        Posicio poscions = (Posicio) arg;

        int posicioY = poscions.getPosicioY();
        int posicioX = poscions.getPosicioX();

        String nomDino = c.getNombre();
        Image newImage = new Image("https://i.imgur.com/pppQ6wV.png");

        System.out.println("Dinosaura: X->" + dinosaurX);
        if ((poscions.getPosicioY() >= 400) && ((poscions.getPosicioX() >= dinosaurX - 8) && (poscions.getPosicioX() <= dinosaurX + 8))){
            for (Thread fil : fils) {
                fil.interrupt();
            }
            perdut = true;
        }


        System.out.println(puntucio);

        switch (nomDino) {
            case "1" -> {
                if (posicioY >= 450) {
                    imageView1.setX(posicioX);
                    imageView1.setY(posicioY);
                    puntucio++;
                } else {
                    imageView1.setY(posicioY);
                    imageView1.setImage(newImage);
                    System.out.println("Metorit 1 -> Y ->" + posicioY + " X: " + posicioX);
                }
            }
            case "2" -> {
                if (posicioY >= 450) {
                    imageView2.setX(posicioX);
                    imageView2.setY(posicioY);
                    puntucio++;
                } else {
                    imageView2.setY(posicioY);
                    imageView2.setImage(newImage);
                    System.out.println("Metorit 2 -> Y ->" + posicioY + " X: " + posicioX);
                }
            }
            case "3" -> {
                if (posicioY >= 450) {;
                    imageView3.setX(posicioX);
                    imageView3.setY(posicioY);
                    puntucio++;
                } else {
                    imageView3.setY(posicioY);
                    imageView3.setImage(newImage);
                    System.out.println("Metorit 3 -> Y ->" + posicioY + " X: " + posicioX);
                }
            }
            case "4" -> {
                if (posicioY >= 450) {
                    imageView4.setX(posicioX);
                    imageView4.setY(posicioY);
                    puntucio++;
                } else {
                    imageView4.setY(posicioY);
                    imageView4.setImage(newImage);
                    System.out.println("Metorit 4 -> Y ->" + posicioY + " X: " + posicioX);
                }
            } case "5" -> {
                if (posicioY >= 450) {
                    imageView5.setX(posicioX);
                    imageView5.setY(posicioY);
                    puntucio++;
                } else {
                    imageView5.setY(posicioY);
                    imageView5.setImage(newImage);
                    System.out.println("Metorit 5 -> Y ->" + posicioY + " X: " + posicioX);
                }
            }case "6" -> {
                if (posicioY >= 450) {
                    imageView6.setX(posicioX);
                    imageView6.setY(posicioY);
                    puntucio++;
                } else {
                    imageView6.setY(posicioY);
                    imageView6.setImage(newImage);
                    System.out.println("Metorit 6 -> Y ->" + posicioY + " X: " + posicioX);
                }
            }
        }

    }

    public static int generarAleatori(int minimo, int maximo){
        int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }



}

