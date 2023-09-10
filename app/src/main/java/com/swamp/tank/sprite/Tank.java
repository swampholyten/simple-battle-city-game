package com.swamp.tank.sprite;

import com.swamp.tank.scene.GameScene;
import com.swamp.tank.util.Direction;
import com.swamp.tank.util.Group;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;


public class Tank extends Role{
    
    Direction pDirection;
    boolean keyUP, keyDOWN, keyLEFT, keyRIGHT;

    public Tank(double x, double y, GameScene gameScene, Group group,
            Direction direction, Direction pDirection) {
        super(x, y, 60, 60, gameScene, group, direction);
        this.pDirection = pDirection;
        speed = 20;
        if (group.equals(Group.GREEN)) {
            imageMap.put("UP", new Image("/image/tank-green-up.png"));
            imageMap.put("DOWN", new Image("/image/tank-green-down.png"));
            imageMap.put("LEFT", new Image("/image/tank-green-left.png"));
            imageMap.put("RIGHT", new Image("/image/tank-green-right.png"));
        } else {
            imageMap.put("UP", new Image("/image/tank-red-up.png"));
            imageMap.put("DOWN", new Image("/image/tank-red-down.png"));
            imageMap.put("LEFT", new Image("/image/tank-red-left.png"));
            imageMap.put("RIGHT", new Image("/image/tank-red-right.png"));
        }
    }

    public void pressed(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                keyUP = true;
                break;
            case DOWN:
                keyDOWN = true;
                break;
            case LEFT:
                keyLEFT = true;
                break;
            case RIGHT:
                keyRIGHT = true;
                break;
            default:
                break;
        }
        redirect();
    }

    public void released(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                keyUP = false;
                break;
            case DOWN:
                keyDOWN = false;
                break;
            case LEFT:
                keyLEFT = false;
                break;
            case RIGHT:
                keyRIGHT = false;
                break;
            default:
                break;
        }
        redirect();
    } 

    public void redirect() {
        if (keyUP && !keyDOWN && !keyLEFT && !keyRIGHT) {
            direction = Direction.UP;
        } else if (!keyUP && keyDOWN && !keyLEFT && !keyRIGHT) {
            direction = Direction.DOWN;
        } else if (!keyUP && !keyDOWN && keyLEFT && !keyRIGHT) {
            direction = Direction.LEFT;
        } else if (!keyUP && !keyDOWN && !keyLEFT && keyRIGHT) {
            direction = Direction.RIGHT;
        } else if (!keyUP && !keyDOWN && !keyLEFT && !keyRIGHT) {
            direction = Direction.STOP;
        }
    }


    @Override
    public void move() {
        switch (direction) {
            case UP:
                y -= speed;
                System.out.println("GO UP");
                break;
            case DOWN:
                y += speed;
                System.out.println("GO DOWN");
                break;
            case LEFT:
                x -= speed;
                System.out.println("GO LEFT");
                break;
            case RIGHT:
                x += speed;
                System.out.println("GO RIGHT");
                break;
            default:
                break;
        }

        if (direction != Direction.STOP) {
            pDirection = direction;
        }

    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        switch (pDirection) {
            case UP:
                image = imageMap.get("UP");
                break;
            case DOWN:
                image = imageMap.get("DOWN");
                break;
            case LEFT:
                image = imageMap.get("LEFT");
                break;
            case RIGHT:
                image = imageMap.get("RIGHT");
                break;
            default:
                break;
        }

        super.paint(graphicsContext);
        move();
    }

    @Override
    public void impactChecking(Sprite sprite) {

    }

}
