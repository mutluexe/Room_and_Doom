package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
<<<<<<< HEAD
import javafx.scene.shape.Rectangle;
import map.Cell;
import map.Position;

=======
import map.Cell;
import map.Position;
import javafx.scene.shape.*;
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09


public abstract class SpriteBase extends Pane {
    //VARİABLE LAR DÜZENLENMELİ ÇOK KARIŞIK.

    Image image;
    ImageView imageView;


    Pane layer;

    double x;
    double y;
    Rectangle rectangle;

    double dx;
    double dy;

    double health;
    double damage;
    double maxHealth;

    boolean removable = false;

    double width;
    double height;

    boolean canMove = true;
    HealthBars healthBar;


    public SpriteBase(Pane layer, Image image, double x, double y, double dx, double dy, double health, double damage) {

        this.layer = layer;
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = image.getWidth();
        this.height = image.getHeight();
        rectangle = new Rectangle(x,y,width,height);
<<<<<<< HEAD

=======
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09
        this.dx = dx;
        this.dy = dy;

        this.health = health;
        this.damage = damage;
        maxHealth = health;


        this.imageView = new ImageView(image);
        this.imageView.relocate(rectangle.getX(), rectangle.getY());
<<<<<<< HEAD

        healthBar = new HealthBars(layer,maxHealth,health,x,y+10);
=======
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09

        addToLayer();

    }

    public void addToLayer() {
        this.layer.getChildren().addAll(imageView);

    }

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }

    public Pane getLayer() {
        return layer;
    }

    public void setLayer(Pane layer) {
        this.layer = layer;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }


    public double getHealth() {
        return health;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    public void move() {

        if (!canMove)
            return;

        rectangle.setX(rectangle.getX()+dx);
        rectangle.setY(rectangle.getY()+dy);

    }

    public boolean isAlive() {
        return health != 0;
    }

    public ImageView getView() {
        return imageView;
    }

    public void updateUI() {
        healthBar.UpdateUI(health,maxHealth,rectangle.getWidth());
        healthBar.healthBar.relocate(rectangle.getX(),rectangle.getY()+5);

        imageView.relocate(rectangle.getX(), rectangle.getY());

    }

    public double getCenterX() {
        return x + width * 0.5;
    }

    public double getCenterY() {
        return y + height * 0.5;
    }

    // TODO: per-pixel-collision
    public boolean collidesWith(SpriteBase otherSprite) {

<<<<<<< HEAD
        return rectangle.getBoundsInParent().intersects(otherSprite.rectangle.getBoundsInParent());
        //(otherSprite.rectangle.getX() + otherSprite.rectangle.getWidth() >= rectangle.getX() && otherSprite.rectangle.getY() + otherSprite.rectangle.getHeight() >= rectangle.getY() && otherSprite.rectangle.getX() <= rectangle.getX() + Settings.TILE_WIDTH && otherSprite.rectangle.getY() <= rectangle.getY() + Settings.TILE_HEIGHT);

    }
    public boolean collidesWithCell(Position cell) {
=======
        return (otherSprite.rectangle.getX() + otherSprite.rectangle.getWidth() >= rectangle.getX() && otherSprite.rectangle.getY() + otherSprite.rectangle.getHeight() >= rectangle.getY() && otherSprite.rectangle.getX() <= rectangle.getX() + Settings.TILE_WIDTH && otherSprite.rectangle.getY() <= rectangle.getY() + Settings.TILE_HEIGHT);
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09

        return (cell.getX() + cell.getWidth() >= rectangle.getX() && cell.getY() + cell.getHeight() >= rectangle.getY() && cell.getX() <= rectangle.getX() + rectangle.getWidth() && cell.getY() <= rectangle.getY() + rectangle.getHeight());
    }
    public boolean attackCollides( SpriteBase otherSprite) {
        //Right Attack Check
        if(Player.animation.getOffSetY() == 704) return ((rectangle.getY()+rectangle.getHeight())/2 <= otherSprite.rectangle.getY() && (rectangle.getY()+rectangle.getHeight())/2 <= (otherSprite.rectangle.getY()+otherSprite.rectangle.getHeight()) && (rectangle.getX()+rectangle.getWidth()) <otherSprite.rectangle.getX() && (rectangle.getX()+rectangle.getWidth()) > otherSprite.rectangle.getX()-rectangle.getWidth()-5 );
        //Left Attack Check
        if(Player.animation.getOffSetY() == 576) return ((rectangle.getY()+rectangle.getHeight())/2 <= otherSprite.rectangle.getY() && (rectangle.getY()+rectangle.getHeight())/2 <= (otherSprite.rectangle.getY()+otherSprite.rectangle.getHeight()) && rectangle.getX() > otherSprite.rectangle.getX()+otherSprite.rectangle.getWidth() && rectangle.getX() < otherSprite.rectangle.getX()+otherSprite.rectangle.getWidth()+rectangle.getWidth()+5);
        //Top Attack Check
        if(Player.animation.getOffSetY() == 512) return (rectangle.getY() >= otherSprite.rectangle.getY()+otherSprite.rectangle.getHeight() && rectangle.getY() <= otherSprite.rectangle.getY()+otherSprite.rectangle.getHeight()+rectangle.getHeight()+5 && (rectangle.getX()+(rectangle.getX()+rectangle.getWidth()))/2 <= (otherSprite.rectangle.getX()+otherSprite.rectangle.getWidth()) && (rectangle.getX()+(rectangle.getX()+rectangle.getWidth()))/2 >= otherSprite.rectangle.getX());
        //Bot Attack Check
        if(Player.animation.getOffSetY() == 640) return (rectangle.getY() <= otherSprite.rectangle.getY() && rectangle.getY() >= otherSprite.rectangle.getY()-rectangle.getHeight()-20 && (rectangle.getX()+(rectangle.getX()+rectangle.getHeight()))/2 <= (otherSprite.rectangle.getX()+otherSprite.rectangle.getWidth()) && (rectangle.getX()+(rectangle.getX()+rectangle.getWidth()))/2 >= otherSprite.rectangle.getX());
        else return false;
    }
    public boolean collidesWithCell(Position cell) {

        return (cell.getX() + cell.getWidth() >= rectangle.getX() && cell.getY() + cell.getHeight() >= rectangle.getY() && cell.getX() <= rectangle.getX() + Settings.TILE_WIDTH && cell.getY() <= rectangle.getY() + Settings.TILE_HEIGHT);

    }
    public boolean attackCollides( SpriteBase otherSprite) {
        //Right Attack Check
        if(Player.animation.getOffSetY() == 704) return ((rectangle.getY()+Settings.TILE_HEIGHT)/2 <= otherSprite.rectangle.getY() && (rectangle.getY()+Settings.TILE_HEIGHT)/2 <= (otherSprite.rectangle.getY()+otherSprite.rectangle.getHeight()) && (rectangle.getX()+Settings.TILE_WIDTH) <otherSprite.rectangle.getX() && (rectangle.getX()+Settings.TILE_WIDTH) > otherSprite.rectangle.getX()-Settings.TILE_WIDTH-5 );
        //Left Attack Check
        if(Player.animation.getOffSetY() == 576) return ((rectangle.getY()+Settings.TILE_HEIGHT)/2 <= otherSprite.rectangle.getY() && (rectangle.getY()+Settings.TILE_HEIGHT)/2 <= (otherSprite.rectangle.getY()+otherSprite.rectangle.getHeight()) && x > otherSprite.rectangle.getX()+otherSprite.rectangle.getWidth() && x < otherSprite.rectangle.getX()+otherSprite.rectangle.getWidth()+Settings.TILE_WIDTH+5);
        //Top Attack Check
        if(Player.animation.getOffSetY() == 512) return (rectangle.getY() >= otherSprite.rectangle.getY()+otherSprite.rectangle.getHeight() && rectangle.getY() <= otherSprite.rectangle.getY()+otherSprite.rectangle.getHeight()+Settings.TILE_HEIGHT+5 && (rectangle.getX()+(rectangle.getX()+Settings.TILE_WIDTH))/2 <= (otherSprite.rectangle.getX()+otherSprite.rectangle.getWidth()) && (rectangle.getX()+(rectangle.getX()+Settings.TILE_WIDTH))/2 >= otherSprite.rectangle.getX());
        //Bot Attack Check
        if(Player.animation.getOffSetY() == 640) return (rectangle.getY() <= otherSprite.rectangle.getY() && rectangle.getY() >= otherSprite.rectangle.getY()-Settings.TILE_HEIGHT-20 && (rectangle.getX()+(rectangle.getX()+Settings.TILE_WIDTH))/2 <= (otherSprite.rectangle.getX()+otherSprite.rectangle.getWidth()) && (rectangle.getX()+(rectangle.getX()+Settings.TILE_WIDTH))/2 >= otherSprite.rectangle.getX());
        else return false;
    }
    public boolean spellCollides(Spell spell,Player player){
        return ((player.getX() - spell.x+spell.x)+ spell.width >= x && (player.getY() - spell.y+spell.y) + spell.height >= y && (player.getX() - spell.x+spell.x) <= x + Settings.TILE_WIDTH && (player.getY() - spell.y+spell.y) <= y + Settings.TILE_HEIGHT);
    }


    /**
     * Reduce health by the amount of damage that the given sprite can inflict
     *
     * @param sprite
     */
    public void getDamagedBy(SpriteBase sprite) {
        health -= sprite.getDamage();
    }

    /**
     * Set health to 0
     */
    public void kill() {
        setHealth(0);
    }

    /**
     * Set flag that the sprite can be removed from the UI.
     */
    public void remove() {
        setRemovable(true);
    }

    /**
     * Set flag that the sprite can't move anymore.
     */
    public void stopMovement() {
        this.canMove = false;
    }

    public abstract void checkRemovability();

}