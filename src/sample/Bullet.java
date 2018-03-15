package sample;

import javafx.animation.Animation;
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

public class Bullet extends Circle {
    Image bullet_Image;
    Node bullet_Node;
    Player target;
    Enemy attacker;
    Pane layer;
    ImageView bulletImageView;
    boolean BulletRemove = false;
    boolean BulletGone = false;
    static SpriteAnimation bullet_Animation;

    public Bullet(Image bullet_Image,Node bullet_Node,Enemy attacker,Player target){
        this.attacker = attacker;
        this.bullet_Node = bullet_Node;
        this.target = target;
        this.bullet_Image = bullet_Image;
        this.bulletImageView = new ImageView(bullet_Image);

        //For bullet's animation
        bullet_Animation = new SpriteAnimation(bulletImageView, Duration.millis(600), 4, 4, 0, 0, 64, 64);
        bullet_Animation.setCycleCount(Animation.INDEFINITE);
        bulletImageView.relocate(bullet_Node.getBoundsInParent().getMinX(),bullet_Node.getBoundsInParent().getMinY());

    }
    public Path createPathBullets(double x,double y){
        Path path = new Path();
        MoveTo spawn  = new MoveTo(x,y);
        LineTo line1 = new LineTo(target.rectangle.getX()+target.rectangle.getWidth()/2,target.rectangle.getY()+target.rectangle.getHeight()/2);
        path.getElements().addAll(spawn,line1);
        return path;

    }
    public void updateUI(){
        bulletImageView.relocate(bullet_Node.getBoundsInParent().getMinX(),bullet_Node.getBoundsInParent().getMinY());
    }

    public PathTransition pathTransitionBullets(Bullet bullet){
            Path path = createPathBullets(attacker.rectangle.getX()+attacker.rectangle.getWidth()/2,attacker.rectangle.getY()+attacker.rectangle.getHeight()/2);
            PathTransition pt2 = new PathTransition(Duration.millis(2000),path,bullet_Node);
            pt2.setOnFinished(event -> {
                bullet.layer.getChildren().removeAll(bulletImageView, bullet_Node);
                bullet.BulletGone = true;
            });
            return pt2;
    }
    public boolean checkBulletCollision(Player player){
        if(!BulletGone)
        return player.rectangle.getBoundsInParent().intersects(bullet_Node.getBoundsInParent());
        return false;
    }



}
