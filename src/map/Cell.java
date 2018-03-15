package map;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.*;

import java.util.ArrayList;

public class Cell {

    public int type;
    public Position position;
    public Node node;
    public ImageView BORDER;

    public Cell(Position position, int type) {

        this.position = position;
        this.type = type;

    }

    public Node getNode() {


        if (type == 1) {//1 represents there are obstacles

            Settings.BorderImage = new Image(getClass().getResource("/border.jpg").toExternalForm());

            BORDER = new ImageView(Settings.BorderImage);//Initializing imageView

            BORDER.setFitWidth(position.width);//burası engellerin oradaki oyuklara göre resmi uyduryor
            BORDER.setFitHeight(position.height);

            BORDER.setX(position.x + position.width / 2 - position.width / 2);
            BORDER.setY(position.y + position.height / 2 - position.height / 2);

            this.node = BORDER;

        } else if (type == 0) {//0 represent no obstacle

            this.node = new Rectangle(position.x, position.y, position.width, position.height);//which fill the non-obstacles area
            //
            //If we change position.height and width we coul hava kesik kesik lines
            //For example x=1 others same and position.height 15

            ((Rectangle) node).setFill(Color.PINK);//if it's null color greenyellow
            //If backround was prepared we could use that
        }

        return node;

    }

    public void setType(int type) {

        this.type = type;

    }

    public int getType() {
        return type;
    }

}
