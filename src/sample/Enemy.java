package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Enemy extends SpriteBase {
    static SpriteAnimation attackAnimation;

    //We need to set enemy's attack
    public Enemy(Pane layer, Image image, double x, double y, double health, double damage) {
        super(layer, image, x, y, 0, 0, health, damage);
        this.imageView.setViewport(new Rectangle2D(0, 0, 36, 32));
        rectangle.setHeight(imageView.getViewport().getHeight());
        rectangle.setWidth(imageView.getViewport().getWidth());

        attackAnimation = new SpriteAnimation(imageView, Duration.millis(1000), 6, 6, 0, 0, 36, 37.5);
    }

    @Override
    public void checkRemovability() {

        //Here we will set when the sprite remove
        if(this.health <= 0) {
            setRemovable(true);
        }
    }
    public boolean isAlive(){
        return true;
    }
    public void attackAnimation(Player player){
        //BOT ATTACK
        if(rectangle.getY() < player.rectangle.getY() && rectangle.getX()-90 < player.rectangle.getX() && rectangle.getX()+90 > player.rectangle.getX()){
            attackAnimation.setOffSetY(0);
            attackAnimation.play();
        }
        //TOP ATTACK
        else if(rectangle.getY() > player.rectangle.getY() && rectangle.getX()-90 < player.rectangle.getX() && rectangle.getX()+90 > player.rectangle.getX()){
            attackAnimation.setOffSetY(37.5);
            attackAnimation.play();
        }
        //LEFT ATTACK
        else if(rectangle.getX() > player.rectangle.getX()){
            attackAnimation.setOffSetY(112.5);
            attackAnimation.play();
        }
        //RIGHT ATTACK
        else if(rectangle.getX() < player.rectangle.getX()){
            attackAnimation.setOffSetY(75);
            attackAnimation.play();
        }


    }
}