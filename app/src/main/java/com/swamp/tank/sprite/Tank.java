package com.swamp.tank.sprite;

import java.util.List;
import java.util.Random;

import com.swamp.tank.Director;
import com.swamp.tank.scene.GameScene;
import com.swamp.tank.util.Direction;
import com.swamp.tank.util.Group;
import com.swamp.tank.util.SoundEffect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;


public class Tank extends Role{
    
    Direction pDirection;
    boolean keyUP, keyDOWN, keyLEFT, keyRIGHT;
    double oldX, oldY;
    public static Random random = new Random();

    public Tank(double x, double y, GameScene gameScene, Group group,
            Direction direction, Direction pDirection) {
        super(x, y, 60, 60, gameScene, group, direction);
        this.pDirection = pDirection;
        speed = 5;
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
            case F:
                SoundEffect.play("/sound/attack.mp3");
                fire();
                break;
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

        oldX = x;
        oldY = y;

        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            default:
                break;
        }

        if (direction != Direction.STOP) {
            pDirection = direction;
        }

        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }

        if (x > Director.WIDTH - width) {
            x = Director.WIDTH - width;
        }
        if (y > Director.HEIGHT - height - 30) {
            y = Director.HEIGHT - height - 30;
        }

        if (group.equals(Group.RED)) {
            int i = random.nextInt(60);
            switch (i) {
                case 15:
                    Direction dir[] = Direction.values();
                    direction = dir[random.nextInt(dir.length)];
                    break;
                case 30:
                    fire();
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (group.equals(Group.RED) && !alive) {
            gameScene.tanks.remove(this);
            return;
        }

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

    public void fire() {

        double bulletX = 0;
        double bulletY = 0;

        switch (pDirection) {
            case UP:
                bulletX = x + 25;
                bulletY = y;
                break;

            case DOWN:
                bulletX = x + 25;
                bulletY = y + height;
                break;

            case LEFT:
                bulletX = x;
                bulletY = y + 25;
                break;

            case RIGHT:
                bulletX = x + width;
                bulletY = y + 25;
                break;
        
            default:
                break;
        }

        Bullet bullet = new Bullet(bulletX, bulletY, gameScene, group, pDirection);
        gameScene.bullets.add(bullet);

    }

    @Override
    public void impactChecking(Sprite sprite) {

    }


    public boolean impact(Tank tank) {
        if (tank != null && getContour().intersects(tank.getContour())) {
            x = oldX;
            y = oldY;
            return true;
        }
        return false;
    }
    
    public void impact(List<Tank> tanks) {
        for (Tank tank : tanks) {
            impact(tank);
        }
    }

}
