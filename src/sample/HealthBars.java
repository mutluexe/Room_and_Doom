package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HealthBars {
    double currentHealth;
    double maxHealth;
    double bar_X_Position;
    double bar_Y_Position;
    Image image;
    ImageView imageView;

    public HealthBars(Pane layer, double maxHealth, double currentHealth, double bar_X_Position, double bar_Y_Position){
        image = new Image (getClass().getResource("/sp_bar_health_strip12.png").toExternalForm());
        imageView = new ImageView(image);
        imageView.setViewport(new Rectangle2D(0, 0, 64, 16));
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.bar_X_Position = bar_X_Position;
        this.bar_Y_Position = bar_Y_Position;

        layer.getChildren().add(imageView);
        imageView.setX(bar_X_Position);
        imageView.setY(bar_Y_Position);
    }
    public void UpdateUI(double currentHealth, double maxHealth){
        if(currentHealth/maxHealth <= 1 && currentHealth/maxHealth >0.9) imageView.setViewport(new Rectangle2D(704, 0, 64, 16));
        if(currentHealth/maxHealth <= 0.9 && currentHealth/maxHealth >0.8) imageView.setViewport(new Rectangle2D(640, 0, 64, 16));
        if(currentHealth/maxHealth <= 0.8 && currentHealth/maxHealth >0.7) imageView.setViewport(new Rectangle2D(576, 0, 64, 16));
        if(currentHealth/maxHealth <= 0.7 && currentHealth/maxHealth >0.6) imageView.setViewport(new Rectangle2D(512, 0, 64, 16));
        if(currentHealth/maxHealth <= 0.6 && currentHealth/maxHealth >0.5) imageView.setViewport(new Rectangle2D(448, 0, 64, 16));
        if(currentHealth/maxHealth <= 0.5 && currentHealth/maxHealth >0.4) imageView.setViewport(new Rectangle2D(384, 0, 64, 16));
        if(currentHealth/maxHealth <= 0.4 && currentHealth/maxHealth >0.3) imageView.setViewport(new Rectangle2D(320, 0, 64, 16));
        if(currentHealth/maxHealth <= 0.3 && currentHealth/maxHealth >0.2) imageView.setViewport(new Rectangle2D(256, 0, 64, 16));
        if(currentHealth/maxHealth <= 0.2 && currentHealth/maxHealth >0.1) imageView.setViewport(new Rectangle2D(192, 0, 64, 16));
        if(currentHealth/maxHealth <= 0.1 && currentHealth/maxHealth >0) imageView.setViewport(new Rectangle2D(128, 0, 64, 16));
        if(currentHealth/maxHealth <= 0) imageView.setViewport(new Rectangle2D(64, 0, 64, 16));


    }

}
