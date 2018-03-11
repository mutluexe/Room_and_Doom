package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HealthBars {
    double currentHealth;
    double maxHealth;
    double bar_X_Position;
    double bar_Y_Position;
    Rectangle healthBar;
    Pane layer;

    public HealthBars(Pane layer,double maxHealth,double currentHealth,double bar_X_Position,double bar_Y_Position){
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.bar_X_Position = bar_X_Position;
        this.bar_Y_Position = bar_Y_Position;

        healthBar = new Rectangle();
        layer.getChildren().add(healthBar);
    }
    public void UpdateUI(double currentHealth,double maxHealth,double width){
        healthBar.setHeight(5);
        healthBar.setWidth((currentHealth/maxHealth)*width);
        if(healthBar.getWidth() <=width && healthBar.getWidth() >= width/2) healthBar.setFill(Color.GREEN);
        if(healthBar.getWidth()<width/2 && healthBar.getWidth()>=width/4) healthBar.setFill(Color.ORANGE);
        if(healthBar.getWidth()<width/4)healthBar.setFill(Color.RED);
    }

}
