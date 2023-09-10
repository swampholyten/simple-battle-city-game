package com.swamp.tank;

import com.swamp.tank.scene.GameScene;
import com.swamp.tank.scene.Index;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Director
 */
public class Director {

    public static final double WIDTH = 960;
    public static final double HEIGHT = 640;

    private static Director instance = new Director();
    private Stage stage;
    private GameScene gameScene = new GameScene();

    private Director() {}

    public static Director getInstance() {
        return instance;
    }

    public void init(Stage stage) {
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle(("Tank Game"));
        stage.getIcons().add(new Image("/image/logo.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        this.stage = stage;
        toIndex();
        stage.show();
    }

    public void toIndex() {
        Index.load(stage);
    }

    public void gameOver() {
        
    }

    public void gameStart() {

    }

}
