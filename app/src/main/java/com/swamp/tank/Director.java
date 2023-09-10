package com.swamp.tank;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Director
 */
public class Director {

    public static final double WIDTH = 960;
    public static final double HEIGHT = 960;

    private static Director instance = new Director();

    private Director() {}

    public static Director getInstance() {
        return instance;
    }

    public void init(Stage stage) {
        AnchorPane root = new AnchorPane();
        stage.setTitle(("Tank Game"));
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.getIcons().add(new Image("/image/logo.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


}