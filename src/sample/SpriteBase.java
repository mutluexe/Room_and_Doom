package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class SpriteBase extends Pane {
    //VARİABLE LAR DÜZENLENMELİ ÇOK KARIŞIK.

    Image image;
    ImageView imageView;


    Pane layer;

    double x;
    double y;

    double dx;
    double dy;

    double health;
    double damage;

    boolean removable = false;

    double width;
    double height;

    boolean canMove = true;


    public SpriteBase(Pane layer, Image image, double x, double y, double dx, double dy, double health, double damage) {

        this.layer = layer;
        this.image = image;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;

        this.health = health;
        this.damage = damage;

        this.imageView = new ImageView(image);
        this.imageView.relocate(x, y);

        this.width = image.getWidth();
        this.height = image.getHeight();

        addToLayer();

    }

    public void addToLayer() {
        this.layer.getChildren().addAll(this.imageView);

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

        x += dx;
        y += dy;

    }

    public boolean isAlive() {
        return Double.compare(health, 0) > 0;
    }

    public ImageView getView() {
        return imageView;
    }

    public void updateUI() {

        imageView.relocate(x, y);

    }

    public double getCenterX() {
        return x + width * 0.5;
    }

    public double getCenterY() {
        return y + height * 0.5;
    }

    // TODO: per-pixel-collision
    public boolean collidesWith(SpriteBase otherSprite) {

        return (otherSprite.x + otherSprite.width >= x && otherSprite.y + otherSprite.height >= y && otherSprite.x <= x + Settings.TILE_WIDTH && otherSprite.y <= y + Settings.TILE_HEIGHT);

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