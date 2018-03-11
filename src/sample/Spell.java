package sample;

import javafx.animation.*;
import javafx.scene.image.Image;

import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Spell extends SpriteBase {
    TranslateTransition translateTransition;

    public Spell(Pane layer, Image image, double x, double y, double damage) {
        super(layer, image, x, y, 1, 1,1, damage);

    }
    public void translate(Player player){
        translateTransition = new TranslateTransition(Duration.millis(200), imageView);
        translateTransition.setToX(player.getX() - x);
        translateTransition.setToY(player.getY() - y);
        translateTransition.setCycleCount(Timeline.INDEFINITE);
        translateTransition.play();


    }

    @Override
    public void checkRemovability() {

    }

}