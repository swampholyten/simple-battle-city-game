package com.swamp.tank.scene;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Index {
    
    public static void load(Stage stage){
        try {
            
            Parent root = FXMLLoader.load(Index.class.getResource("/fxml/index.fxml"));
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
