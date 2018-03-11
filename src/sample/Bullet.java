package sample;

import javafx.animation.PathTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Bullet extends Node {
    Image bullet_Image;
    Node bullet_Node;
    Player target;
    Enemy attacker;
    Pane layer;
    double attacker_X;
    double attacker_Y;
    ImageView bullet_Imageview;
    boolean BulletRemove = false;

    public Bullet(Image bullet_Image,Node bullet_Node,Enemy attacker,Player target){
        this.attacker = attacker;
        this.bullet_Node = bullet_Node;
        this.target = target;


        this.bullet_Imageview = new ImageView(bullet_Image);
        bullet_Imageview.relocate(bullet_Node.getBoundsInParent().getMinX(),bullet_Node.getBoundsInParent().getMinY());






    }
    public Path createPathBullets(double x,double y){
        Path path = new Path();
        MoveTo spawn  = new MoveTo(x,y);
        LineTo line1 = new LineTo(target.rectangle.getX(),target.rectangle.getY());
        path.getElements().addAll(spawn,line1);
        return path;

    }
    public void updateUI(){
        bullet_Imageview.relocate(bullet_Node.getBoundsInParent().getMinX(),bullet_Node.getBoundsInParent().getMinY());
    }

    public PathTransition pathTransitionBullets(){
        Path path = createPathBullets(attacker.rectangle.getX(),attacker.rectangle.getY());
            PathTransition pt2 = new PathTransition(Duration.millis(2000),path,bullet_Node);
            return pt2;
    }
    public boolean checkBulletCollision(Player player){
        return player.rectangle.getBoundsInParent().intersects(bullet_Node.getBoundsInParent());

    }



}
