package com.swamp.tank.sprite;

import com.swamp.tank.scene.GameScene;
import com.swamp.tank.util.Direction;
import com.swamp.tank.util.Group;
import javafx.scene.image.Image;


public class Tank extends Role{
    
    Direction direction;

    public Tank(double x, double y, double width, double height, GameScene gameScene, Group group,
            Direction direction) {
        super(x, y, 60, 60, gameScene, group, direction);
        this.direction = direction;
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





    @Override
    public void move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public void impactChecking(Sprite sprite) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'impactChecking'");
    }

}
