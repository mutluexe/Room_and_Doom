package map;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import sample.*;

public class Cell {

    int type;
    double x,y,width,height;
    Rectangle rect;

    Image groundImage;
    Image borderImage;

    NonMovingObstacle nonMv;


    public Cell(double x,double y,double width,double height,int type){

        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.type = type;

        rect=new Rectangle(x,y,width,height);
        rect.setFill(Color.PINK);//DEFAULT RECTANGLE



        //groundImage=new Image(getClass().getResource("/medevil.jpg").toExternalForm());
        borderImage = new Image(getClass().getResource("/border.jpg").toExternalForm());

    }

    public Rectangle getRect(){


        if (type == Settings.BORDER_CONSTANT){//1 represents there are obstacles

            nonMv=new NonMovingObstacle(x,y,width,height,borderImage);

            this.rect=nonMv.getRect();

        }
        else if (type == Settings.TILES_CONSTANT){
			rect=new Rectangle(x,y,width,height);
			rect.setFill(Color.DARKGREEN);


            /*nonMv=new NonMovingObject(x,y,width,height,groundImage);
            this.rect=nonMv.getRect();*/

        }
        return rect;

    }

    public void UpdateUI(){
        rect.relocate(rect.getTranslateX(),rect.getLayoutY());
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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



}
