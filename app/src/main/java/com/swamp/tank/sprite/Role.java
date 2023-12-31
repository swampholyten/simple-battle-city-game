package com.swamp.tank.sprite;

import java.util.HashMap;
import java.util.Map;

import com.swamp.tank.scene.GameScene;
import com.swamp.tank.util.Direction;
import com.swamp.tank.util.Group;

import javafx.scene.image.Image;

public abstract class Role extends Sprite{

    boolean alive = true;
    
    Group group;
    Direction direction;
    double speed;
    Map<String, Image> imageMap = new HashMap<>();
    
    public Role(double x, double y, double width, double height, GameScene gameScene, Group group, Direction direction) {
        super(null, x, y, width, height, gameScene);
        this.group = group;
        this.direction = direction;
    }
    
    public abstract void move();
    
    public abstract void impactChecking(Sprite sprite);
    
    
    public boolean isAlive() {
        return alive;
    }
    
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
