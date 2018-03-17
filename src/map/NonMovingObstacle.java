package map;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class NonMovingObstacle {
    private double x,y,width,height;
    private Rectangle rect;
    private Image image;

    public NonMovingObstacle(double x, double y, double width, double height,Image image){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.image=image;

        rect=new Rectangle(x,y,width,height);
        rect.setFill(new ImagePattern(image));

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
}
