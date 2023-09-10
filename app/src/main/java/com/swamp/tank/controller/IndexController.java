package com.swamp.tank.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class IndexController {

    @FXML
    private ImageView startGame;

    @FXML
    void mouseClickedStartGame(MouseEvent event) {

    }

    @FXML
    void mouseEnteredStartGame(MouseEvent event) {
    }

    @FXML
    void mouseExitedStartGame(MouseEvent event) {
        startGame.setOpacity(1);
    }

}
