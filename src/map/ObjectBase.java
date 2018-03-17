package map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import sample.Settings;
import sample.SpriteBase;

public abstract class ObjectBase {

    private double x,y,width,height;

    private int dY,dX;//Player's speed

    public Rectangle rect;

    private Image image;

    private ImageView imageView;

    private Pane layer;

    private boolean canMove=true;

    public ObjectBase(Pane layer,double x, double y, double width, double height, Image image){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.image=image;
        this.layer=layer;

        rect=new Rectangle(x,y,width,height);
        this.imageView=new ImageView(image);
        this.imageView.relocate(rect.getX(),rect.getY());

        addToLayer();
    }

    public void addToLayer() {
        this.layer.getChildren().addAll(this.imageView);
    }

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.rect);
    }

    public void updateUI() {
        imageView.relocate(rect.getX(),rect.getY());
    }

    public void move() {

        if (!canMove)
            return;

        rect.setX(rect.getX()+dX);//x ve y değişimi
        rect.setY(rect.getY()+dY);

    }

    public boolean collidesWithCell(Cell cell) {

        return (cell.getRect().getX() + cell.getRect().getWidth() >= getRect().getX() && cell.getRect().getY() + cell.getRect().getHeight() >= getRect().getY() && cell.getRect().getX() <= getRect().getX() + Settings.TILE_WIDTH && cell.getRect().getY() <= getRect().getY() + Settings.TILE_HEIGHT);

    }

    public boolean collidesWithEnemy(SpriteBase otherSprite) {

        return (otherSprite.getX() + otherSprite.getWidth() >= getRect().getX() && otherSprite.getY() + otherSprite.getHeight() >= getRect().getY() && otherSprite.getX() <= getRect().getX() + Settings.TILE_WIDTH && otherSprite.getY() <= getRect().getY() + Settings.TILE_HEIGHT);

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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Pane getLayer() {
        return layer;
    }

    public void setLayer(Pane layer) {
        this.layer = layer;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public int getdY() {
        return dY;
    }

    public void setdY(int dY) {
        this.dY = dY;
    }

    public int getdX() {
        return dX;
    }

    public void setdX(int dX) {
        this.dX = dX;
    }
}
