package com.swamp.tank.scene;

import java.io.IOException;

import com.swamp.tank.controller.OverController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class GameOver {
     public static void load(Stage stage, boolean success){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Index.class.getResource("/fxml/gameOver.fxml"));
            Parent root = fxmlLoader.load();

            OverController overController = fxmlLoader.getController();
            if (success) {
                overController.ifSuccess();
            }

            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
