package com.swamp.tank.scene;

import java.util.ArrayList;
import java.util.List;

import com.swamp.tank.Director;
import com.swamp.tank.sprite.Background;
import com.swamp.tank.sprite.Bullet;
import com.swamp.tank.sprite.Explode;
import com.swamp.tank.sprite.Tank;
import com.swamp.tank.util.Direction;
import com.swamp.tank.util.Group;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameScene {
    
    private Canvas canvas = new Canvas(Director.WIDTH, Director.HEIGHT);
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

    private KeyProcess keyProcess = new KeyProcess();
    private Refresh refresh = new Refresh();
    private boolean running = false;

    private Background background = new Background();
    private Tank self = null;
    public List<Bullet> bullets = new ArrayList<>();
    public List<Tank> tanks = new ArrayList<>();
    public List<Explode> explodes = new ArrayList<>();


    private void paint() {
        background.paint(graphicsContext);
        self.paint(graphicsContext);
        self.impact(tanks);
        

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.paint(graphicsContext);
            bullet.impact(tanks);
        }

        for (int i = 0; i < tanks.size(); i++) {
            Tank tank = tanks.get(i);
            tank.paint(graphicsContext);
        }

        for (int i = 0; i < explodes.size(); i++) {
            Explode explode = explodes.get(i);
            explode.paint(graphicsContext);
        }

        // graphicsContext.setFill(Color.GREEN);
        graphicsContext.setFont(new Font(20));
        graphicsContext.fillText("敌军的数量： " + tanks.size(), 800, 60);
        graphicsContext.fillText("子弹的数量： " + bullets.size(), 800, 90);
    }

    public void init(Stage stage) {
        AnchorPane root = new AnchorPane(canvas);
        stage.getScene().setRoot(root);
        stage.getScene().setOnKeyReleased(keyProcess);
        stage.getScene().setOnKeyPressed(keyProcess);
        running = true;
        self = new Tank(400, 500, this, Group.GREEN, Direction.STOP, Direction.UP);
        initSprite();
        refresh.start();
    }

    public void clear(Stage stage) {
        stage.getScene().removeEventHandler(KeyEvent.KEY_RELEASED, keyProcess);
        refresh.stop();
    }

    private void initSprite() {
        for (int i = 0; i < 6; i++) {
            Tank tank = new Tank(200 + i * 80, 100, this, Group.RED, Direction.STOP, Direction.DOWN);
            tanks.add(tank);
        }
    }


    private class Refresh extends AnimationTimer {

        @Override
        public void handle(long arg0) {
            if (running) {
                paint();
            }
        }

    }

    private class KeyProcess implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            KeyCode keyCode = event.getCode();
            
            if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                if (keyCode.equals(KeyCode.SPACE)) {
                    pauseOrContinue();
                }
                self.released(keyCode);
            } else if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                self.pressed(keyCode);
            }
        }
    }

    public void pauseOrContinue() {
        if(running) {
            running = false;
        }else {
            running = true;
        }
    }

}
