package sample;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Enemy extends SpriteBase {

    //We need to set enemy's attack
    public Enemy(Pane layer, Image image, double x, double y, double health, double damage) {
        super(layer, image, x, y, 0, 0, health, damage);
    }

    @Override
    public void checkRemovability() {
        //Here we will set when the sprite remove
    }
}
