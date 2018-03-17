package map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import sample.Input;

public class MovingObstacle extends ObjectBase{
    Input input;

    public MovingObstacle(Pane layer,double x, double y, double width, double height, Image image,Input input){
        super(layer,x,y,width,height,image);

        this.input=input;

        setdX(4);//x ve y kordinatları değişimi
        setdY(4);
    }
    public void processInput() {
        // vertical direction
        if (input.isMoveUp()) {
            setdY(4);
            setdY(-getdY());
        } else if (input.isMoveDown()) {
            setdY(4);
            setdY(getdY());
        }else
            setdY(0);
        // horizontal direction
        if (input.isMoveLeft()) {
            setdX(4);
            setdX(-getdX());
        } else if (input.isMoveRight()) {
            setdX(4);
            setdX(getdX());
        }
        else
            setdX(0);
    }


    @Override
    public void move() {
        super.move();
    }

}
