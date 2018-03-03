package sample;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Player extends SpriteBase {
    //EKLENECEK ŞEYLER VAR (İNT,STR,VİT VB... )
    //HEALTH DAMAGE DEĞERLERİ DEĞİŞECEK.STR,INT,GİBİ DEĞERLERLE BELİRLENECEK.
    //SINIFLARI AYARLAMAK İÇİN CLASS LAZIM VE BU CLASS I KULLANMAK

    double characterMinX;
    double characterMaxX;
    double characterMinY;
    double characterMaxY;

    Input input;


    double speed;
    static SpriteAnimation animation;
    static SpriteAnimation attackAnimation;

    public Player(Pane layer, Image image, double health, double damage, double speed, Input input) {

        super(layer, image, 100, 100, 4, 4, health, damage);

        this.speed = speed;
        this.input = input;

        //Here we crop the style sheet's specific part
        this.imageView.setViewport(new Rectangle2D(0, 0, 64, 64));

        //Setting animations
        animation = new SpriteAnimation(imageView, Duration.millis(800), 9, 9, 0, 0, 64, 64);
        attackAnimation = new SpriteAnimation(imageView, Duration.millis(1000), 8, 8, 0, 0, 64, 64);
        animation.setCycleCount(Animation.INDEFINITE);
        attackAnimation.setCycleCount(1);
        attackAnimation.setOnFinished(e -> Input.setIsAttacking(false)); //Everytime when cycle finished it set's the value 0
        init();

    }


    private void init() {
        // calculate movement bounds of the character
        // allow character can not to be outside of the screen
        characterMinX = 0;
        characterMaxX = Settings.SCENE_WIDTH - Settings.TILE_WIDTH;
        characterMinY = 0;
        characterMaxY = Settings.SCENE_HEIGHT - Settings.TILE_HEIGHT;

    }


    public void processInput() {

        // ------------------------------------
        // movement
        // ------------------------------------

        // vertical direction
        if (input.isMoveUp()) {
            animation.play();
            animation.setOffSetY(512);
            dy = -speed;
        } else if (input.isMoveDown()) {
            animation.play();
            animation.setOffSetY(640);
            dy = speed;
        } else {
            dy = 0d;
        }

        // horizontal direction
        if (input.isMoveLeft()) {
            animation.play();
            animation.setOffSetY(576);
            dx = -speed;
        } else if (input.isMoveRight()) {
            animation.play();
            animation.setOffSetY(704);
            dx = speed;
        } else {
            dx = 0d;
        }
        //Attack statements
        if (input.isAttack()) {
            if (animation.getOffSetY() == 512) {
                //Up attack.
                attackAnimation.play();
                attackAnimation.setOffSetY(256);
            }
            if (animation.getOffSetY() == 640) {
                //Bot attack.
                attackAnimation.play();
                attackAnimation.setOffSetY(384);
            }
            if (animation.getOffSetY() == 576) {
                //Left attack.
                attackAnimation.play();
                attackAnimation.setOffSetY(320);
            }
            if (animation.getOffSetY() == 704) {
                //Right attack.
                attackAnimation.play();
                attackAnimation.setOffSetY(448);
            }

        }

    }

    @Override
    public void move() {

        super.move();

        //ensure the character can't move outside of the screen
        checkBounds();

    }

    private void checkBounds() {

        // vertical
        if (Double.compare(y, characterMinY) < 0) {
            y = characterMinY;
        } else if (Double.compare(y, characterMaxY) > 0) {
            y = characterMaxY;
        }
        // horizontal
        if (Double.compare(x, characterMinX) < 0) {
            x = characterMinX;
        } else if (Double.compare(x, characterMaxX) > 0) {
            x = characterMaxX;
        }

    }


    @Override
    public void checkRemovability() {
        // TODO Auto-generated method stub
    }

}