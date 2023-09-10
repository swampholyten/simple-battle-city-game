package com.swamp.tank.controller;

import com.swamp.tank.Director;
import com.swamp.tank.util.SoundEffect;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class OverController {

    @FXML
    private ImageView flag;

    @FXML
    private ImageView toIndex;

    @FXML
    void mouseClickedToIndex(MouseEvent event) {
        SoundEffect.play("/sound/done.wav");
        Director.getInstance().toIndex();
    }

    @FXML
    void mouseEnteredToIndex(MouseEvent event) {
        toIndex.setOpacity(0.8);
        SoundEffect.play("/sound/button.wav");        
    }

    @FXML
    void mouseExitedToIndex(MouseEvent event) {
        toIndex.setOpacity(1);
    }

    public void ifSuccess() {
        flag.setImage(new Image("/image/success.png"));
    }

}
