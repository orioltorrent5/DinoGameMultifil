package com.example.dinogamemultifil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelloApplication extends Application implements Observer {
    private double dinosaurX = 250;

    private final ImageView imageView1 = new ImageView();
    private final ImageView imageView2 = new ImageView();
    private final ImageView imageView3 = new ImageView();
    private final ImageView imageView4 = new ImageView();

    private Thread[] fils = new Thread[4];

    public static void main(String[] args) {
        launch(args);
    }

    Pane root = new Pane();
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Dino Game");
        Image image = new Image("https://i.imgur.com/EK2sGjw.png");
        Image imatgeMateorit = new Image("https://cdn-icons-png.flaticon.com/512/560/560911.png");
        ImageView dinosaur = new ImageView(image);

        imageView1.setImage(image);
        imageView2.setImage(image);
        imageView3.setImage(image);
        imageView4.setImage(image);

        imageView1.setX(0);
        imageView2.setX(150);
        imageView3.setX(300);
        imageView4.setX(400);


        dinosaur.setX(dinosaurX);
        dinosaur.setY(450);
        root.getChildren().add(dinosaur);
        root.getChildren().add(imageView1);
        root.getChildren().add(imageView2);
        root.getChildren().add(imageView3);
        root.getChildren().add(imageView4);


        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    dinosaurX -= 10;
                    break;
                case RIGHT:
                    dinosaurX += 10;
                    break;
                case A:
                    dinosaurX -= 10;
                    break;
                case D:
                    dinosaurX += 10;
                    break;
            }
            if(dinosaurX < 0) {
                dinosaurX = 0;
            }
            if(dinosaurX > 450) {
                dinosaurX = 450;
            }
            dinosaur.setX(dinosaurX);
        });

        for (int i = 0; i < fils.length; i++){
            Metorit metorit = new Metorit((i + 1) + "");
            metorit.addObserver(this);
            fils[i] = new Thread(metorit);
            fils[i].start();
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    Alert alert = new Alert(Alert.AlertType.INFORMATION, "HAS PERDUT!!", ButtonType.OK);
    public void update(Observable o, Object arg) {
        Metorit c = (Metorit) o;
        int posicio = (int) arg;
        String nomDino = c.getNombre();

        switch (nomDino) {
            case "1" -> {
                if (posicio >= 439) {
                    c.setPosicionY(0);
                } else {
                    imageView1.setY(posicio);
                    System.out.println("1 ->" + posicio);
                }
            }
            case "2" -> {
                if (posicio >= 439) {
                    c.setPosicionY(0);
                } else {
                    imageView2.setY(posicio);
                    System.out.println("2 ->" + posicio);
                }
            }
            case "3" -> {
                if (posicio >= 439) {;
                    c.setPosicionY(0);
                } else {
                    imageView3.setY(posicio);
                    System.out.println("3 ->" + posicio);
                }
            }
            case "4" -> {
                if (posicio >= 439) {
                    c.setPosicionY(0);
                } else {
                    imageView4.setY(posicio);
                    System.out.println("4 ->" + posicio);
                }
            }
        }

    }

}

