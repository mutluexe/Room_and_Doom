package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {

    Input input;
    Player player;
    Enemy enemy;

    Pane playfieldLayout;
    Pane scoreLayout;

    Image playerImage;
    Image enemyImage;

    Scene scene;

    //Creating lists
    List<Player> players = new ArrayList<>();
    List<Enemy> enemies = new ArrayList<>();

    /*
    Here collision text we will change it later and
    Make it health bar and whenever we attack an enemy
    it's health bar will be decrease if it attacks vice versa*/

    Text collisionText = new Text();
    boolean collision = false;


    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();

        // create layers
        playfieldLayout = new Pane();
        scoreLayout = new Pane();

        root.getChildren().add(playfieldLayout);
        root.getChildren().add(scoreLayout);

        scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        primaryStage.setTitle("Room & Doom");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Calling our methods here
        loadGame();
        createScoreLayer();
        createPlayers();
        createEnemy();


        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {

                // player input
                players.forEach(sprite -> sprite.processInput());

                // movement
                players.forEach(sprite -> sprite.move());

                // check collisions
                checkCollisionWithEnemy();
                EnemyBlock();

                // update sprites in scene
                players.forEach(sprite -> sprite.updateUI());
                enemies.forEach(sprite -> sprite.updateUI());

                // check if sprite can be removed
                enemies.forEach(sprite -> sprite.checkRemovability());

                // remove removables from list, layer, etc
                removeSprites(enemies);
                // update score, health, etc
                updateScore();
            }

        };
        gameLoop.start();

    }

    //Image resources
    private void loadGame() {
        playerImage = new Image(getClass().getResource("/spritesheet.png").toExternalForm());
        enemyImage = new Image(getClass().getResource("/enemy.png").toExternalForm());

    }
    public void EnemyBlock(){
        if(collision){
            player.setX(player.getX()-player.getDx());
            player.setY(player.getY()-player.getDy());
        }
    }

    //Here just a temporary method we will change it to a health bar
    private void createScoreLayer() {

        // We need to set health bar here

    }

    //Creating a player here
    private void createPlayers() {
        //For inputs
        input = new Input(scene);
        input.addListeners();

        Image image = playerImage;
        //Setting players' qualities
        player = new Player(playfieldLayout, image, 100, 10, 2, input);
        //Add all players in a list so it will be easier to work
        players.add(player);

    }

    //Creating an enemy here
    private void createEnemy() {

        Image image = enemyImage;
        //Setting enemies' qualities
        enemy = new Enemy(playfieldLayout, image, 300, 200, 100, 0);
        //Add all enemies in a list so it will be easier to work
        enemies.add(enemy);

    }


    private void removeSprites(List<? extends SpriteBase> spriteList) {
        Iterator<? extends SpriteBase> iterator = spriteList.iterator();
        while (iterator.hasNext()) {
            SpriteBase sprite = iterator.next();

            if (sprite.isRemovable()) {

                // remove from layer
                sprite.removeFromLayer();

                // remove from list
                iterator.remove();
            }
        }
    }

    private void checkCollisionWithEnemy() {
        collision = false;

        for (Player player : players) {
            for (Enemy enemy : enemies) {
                if (player.collidesWith(enemy)) {
                    collision = true;
                }
            }
        }
    }

    private void updateScore() {
        if (collision && input.isAttack() && !Input.getIsAttacking()) {
            Input.setIsAttacking(true);
            System.out.println(enemy.health);
            enemy.getDamagedBy(player);//Enemy's health decreasing

        } else {
            collisionText.setText("");
        }

    }

    public static void main(String[] args) {
        launch(args);
    }


}