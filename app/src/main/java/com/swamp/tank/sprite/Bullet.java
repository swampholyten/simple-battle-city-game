package com.swamp.tank.sprite;


import java.util.List;

import com.swamp.tank.Director;
import com.swamp.tank.scene.GameScene;
import com.swamp.tank.util.Direction;
import com.swamp.tank.util.Group;
import com.swamp.tank.util.SoundEffect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet extends Role{

    public Bullet(double x, double y, GameScene gameScene, Group group,
            Direction direction) {
        super(x, y, 0, 0, gameScene, group, direction);
        speed = 10;
        if (direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) {
            width = 10;
            height = 22;
        } else {
            width = 22;
            height = 10;
        }

        if (group.equals(Group.GREEN)) {
            switch (direction) {
                case UP:
                    image = new Image("/image/bullet-green-up.png");
                    break;
                case DOWN:
                    image = new Image("/image/bullet-green-down.png");
                    break;
                case LEFT:
                    image = new Image("/image/bullet-green-left.png");
                    break;
                case RIGHT:
                    image = new Image("/image/bullet-green-right.png");
                    break;
                default:
                    break;
            }
        } else {
            
            switch (direction) {
                case UP:
                    image = new Image("/image/bullet-red-up.png");
                    break;
                case DOWN:
                    image = new Image("/image/bullet-red-down.png");
                    break;
                case LEFT:
                    image = new Image("/image/bullet-red-left.png");
                    break;
                case RIGHT:
                    image = new Image("/image/bullet-red-right.png");
                    break;
                default:
                    break;
            }

        }

    }

    @Override
    public void move() {
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

        if (x < 0 || y < 0 || x > Director.WIDTH || y > Director.HEIGHT) {
            gameScene.bullets.remove(this);
        }

    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (!alive) {
            gameScene.bullets.remove(this);
            gameScene.explodes.add(new Explode(x, y, gameScene));
            SoundEffect.play("/sound/explosion.wav");
            return;
        }
        
        super.paint(graphicsContext);
        move();
    }

    @Override
    public void impactChecking(Sprite sprite) {
        throw new UnsupportedOperationException("Unimplemented method 'impactChecking'");
    }

    public boolean impactTank(Tank tank) {
        if (tank != null && !tank.group.equals(this.group) && getContour().intersects(tank.getContour())) {
            tank.setAlive(false);
            alive = false;
            return true;
        }
        return false;
    }
    
    public void impactTank(List<Tank> tanks) {
        for (Tank tank : tanks) {
            impactTank(tank);
        }
    }

    public boolean impactCrate(Crate crate) {
        if (crate != null && getContour().intersects(crate.getContour())) {
            alive = false;
            gameScene.crates.remove(crate);
            return true;
        }
        return false;
    }

    public void impactCrates(List<Crate> crates) {
        for (int i = 0; i < crates.size(); i++) {
            Crate crate = crates.get(i);
            impactCrate(crate);
        }
    }

    public boolean impactRock(Rock rock) {
        if (rock != null && getContour().intersects(rock.getContour())) {
            alive = false;
            return true;
        }
        return false;
    }

    public void impactRocks(List<Rock> rocks) {
        for (int i = 0; i < rocks.size(); i++) {
            Rock rock = rocks.get(i);
            impactRock(rock);
        }
    }

}
