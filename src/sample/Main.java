package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import map.*;

import java.util.*;

public class Main extends Application {

    Input input;
    Player player;
    Enemy enemy;
    Bullet bullet;
    Treasure treasure;
    MovingObstacle movingObstacle1,movingObstacle2;


    Pane playfieldLayout;
    Pane scoreLayout;

    Image playerImage;
    Image enemyImage;
    Image borderImage;
    Image bulletImage;
    Image treasureImage;
    Image treasureOpenedImage;


    Group root;
    Stage primaryStage;
    Scene scene;
    Grid map;

    int[][] gameMap ={
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//araylist elemanları sütunsal olarak sayıyor obstacle hareketinde farkedersiniz 48.eleman bi border
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,1,0,0,1,0,0,0,1,0,0,0,1,0,0,1,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//hareketsiz elemalar için bunun daha uygun olduğunu düşündüm
    };

    //Creating lists
    List<Player> players = new ArrayList<>();
    List<Enemy> enemies = new ArrayList<>();
    List<Cell> Cells = new ArrayList<>();
    List<Bullet> bullets = new ArrayList<>();
    List<Treasure> treasures = new ArrayList<>();
    List<MovingObstacle> movingObstacles=new ArrayList<>();

    /*
    Here collision text we will change it later and
    Make it health bar and whenever we attack an enemy
    it's health bar will be decrease if it attacks vice versa*/

    Text collisionText = new Text();
    boolean player_EnemyCollision = false;
    boolean player_MovingObstacleCollision=false;
    boolean player_MapObstacleCollision=false;
    boolean movingObstacle_EnemyCollision=false;
    boolean movingObstacle_MapObstacleCollision=false;
    boolean attackCollision = false;
    boolean cellCollision = false;
    boolean bulletCollision = false;



    @Override
    public void start(Stage primaryStage) {
        this.primaryStage=primaryStage;
        root = new Group();


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
        createMap();
        loadGame();
        createMovingObstacles();
        createPlayers();
        createEnemy();
        createTreasure();

        AnimationTimer gameLoop = new AnimationTimer() {
             private long lastUpdate =0;

            @Override

            public void handle(long now) {

                //input
                players.forEach(sprite -> sprite.processInput());
                treasures.forEach(sprite -> treasure.processInput());
                // movement
                players.forEach(sprite -> sprite.move());

                if(player_MovingObstacleCollision){
                    movingObstacles.forEach(movingObstacle->movingObstacle.processInput());
                    movingObstacles.forEach(movingObstacle->movingObstacle.move());
                }


                // check collisions
                player_CheckCollisionWithEnemy();
                player_enemyBlock();

                player_CheckCollisionWithMovingObstacle();
                player_movingObstacleBlock();

                player_CheckCollisionWithMapObstacle();
                player_mapObstacleBlock();

                movingObstacle_CheckCollisionWithEnemy();
                movingObstacle_EnemyBlock();

                //movingObstacle_CheckCollisionWithMapObstacle(); burdaki algoritmada sıkıntı var açarsak adam gitmiyor
                //movingObstacle_mapObstacleBlock();

                checkAttackCollisionWithEnemy();
                checkCollisionWithBullet();

                // update sprites in scene
                players.forEach(sprite -> sprite.updateUI());
                enemies.forEach(sprite -> sprite.updateUI());
                bullets.forEach(sprite -> sprite.updateUI());
                movingObstacles.forEach(mv->mv.updateUI());

                // for enemy's attack (it shouldn't be called every frame so we should restrict it
                   if(now-lastUpdate >= 1000000000){
                       enemies.forEach(Enemy -> createBullet());
                       lastUpdate=now;
                   }
                bulletRemove();

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
    //Creating spell here


    //Image resources
    private void loadGame() {
        playerImage = new Image(getClass().getResource("/spritesheet.png").toExternalForm());
        enemyImage = new Image(getClass().getResource("/mageattack.png").toExternalForm());
        bulletImage = new Image(getClass().getResource("/plasmaball.png").toExternalForm());
        borderImage = new Image(getClass().getResource("/border.jpg").toExternalForm());
        treasureImage = new Image(getClass().getResource("/treasure.png").toExternalForm());
        treasureOpenedImage = new Image(getClass().getResource("/treasureOpened.png").toExternalForm());

    }
    private void createMovingObstacles(){
        Image image= borderImage;
        input = new Input(scene);
        input.addListeners();

        movingObstacle1=new MovingObstacle(playfieldLayout,512,128,64,64,image,input);
        movingObstacle2=new MovingObstacle(playfieldLayout,256,512,64,64,image,input);

        movingObstacles.add(movingObstacle1);
        movingObstacles.add(movingObstacle2);
    }

    private void createMap(){
        map=new Grid(gameMap);//for special map
        map.Draw(playfieldLayout);//which draws map to our pane
    }
    //blocks for player
    public void player_enemyBlock(){
        if(player_EnemyCollision){
            getAfterCollision();
        }
    }
    public void player_movingObstacleBlock(){
        if(player_MovingObstacleCollision){
            getAfterCollision();
        }
    }
    public void player_mapObstacleBlock(){
        if(player_MapObstacleCollision){
            getAfterCollision();
        }
    }
    //blocks for moving obstacle
    public void movingObstacle_EnemyBlock(){
        if(movingObstacle_EnemyCollision){
            getAfterCollision();
        }
    }
    public void movingObstacle_mapObstacleBlock(){
        if(movingObstacle_MapObstacleCollision){
            getAfterCollision();
        }
    }

    public void bulletRemove() {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()){
            Bullet bullet = bulletIterator.next();

            if (bullet.BulletRemove) {
                player.setHealth(player.getHealth()-enemy.getDamage());
                System.out.println(player.getHealth());
                bulletIterator.remove();
            }}

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
        enemy = new Enemy(playfieldLayout, image, 300, 200, 100, 10);
        //Add all enemies in a list so it will be easier to work
        enemies.add(enemy);

    }
    public void createBullet(){
       {
            Image image = bulletImage;
            bullet = new Bullet(image, new Circle(enemy.rectangle.getX()+enemy.rectangle.getWidth()/2,enemy.rectangle.getY()+enemy.rectangle.getHeight()/2, 1, Color.RED), enemy, player);
            playfieldLayout.getChildren().add(bullet.bulletImageView);
            bullet.layer = playfieldLayout;
            bullets.add(bullet);
            bullet.pathTransitionBullets(bullet).play();
            enemy.attackAnimation(player);
            bullet.bullet_Animation.play();

        }
    }

    //Creating treasures here
    private void createTreasure(){
        //For inputs
        input = new Input(scene);
        input.addListeners();

        Image image = treasureImage;
        Image image_Opened = treasureOpenedImage;

        treasure = new Treasure(playfieldLayout, image, image_Opened, 300, 500, input);

        treasures.add(treasure);
    }


    private void removeSprites(List<? extends SpriteBase> spriteList) {
        Iterator<? extends SpriteBase> iterator = spriteList.iterator();
        while (iterator.hasNext()) {
            SpriteBase sprite = iterator.next();

            if (sprite.isRemovable()) {

                // remove from layer
                sprite.removeFromLayer();
                sprite.layer.getChildren().remove(sprite.healthBar.imageView);

                // remove from list
                iterator.remove();
            }
        }
    }

    private void player_CheckCollisionWithEnemy() {
        player_EnemyCollision = false;

        for (Player player : players) {
            for (Enemy enemy : enemies) {
                if (player.collidesWith(enemy)) {
                    player_EnemyCollision = true;
                }
            }
        }
    }
    private void checkAttackCollisionWithEnemy(){
        attackCollision = false;

        for (Player player: players){
            for(Enemy enemy : enemies){
                if(player.attackCollides(enemy)){
                    attackCollision = true;
                }
            }
        }
    }
    private void player_CheckCollisionWithMovingObstacle() {
        player_MovingObstacleCollision = false;

        for (Player player : players) {
            for (MovingObstacle obstacle : movingObstacles) {
                if (player.collidesWithMovingObstacle(obstacle)) {
                    player_MovingObstacleCollision = true;
                }
            }
        }
    }
    private void player_CheckCollisionWithMapObstacle() {
        player_MapObstacleCollision = false;

        for (Player player : players) {
            for (Cell cell:map.mapArraylist) {
                if (cell.getType()==1&&player.collidesWithCell(cell)) {
                    player_MapObstacleCollision = true;
                }
            }
        }
    }
    private void movingObstacle_CheckCollisionWithEnemy() {
        movingObstacle_EnemyCollision = false;

        for (MovingObstacle obstacle:movingObstacles) {
            for (Enemy enemy : enemies) {
                if (obstacle.collidesWithEnemy(enemy)) {
                    movingObstacle_EnemyCollision = true;
                }
            }
        }
    }
    private void movingObstacle_CheckCollisionWithMapObstacle() {
        movingObstacle_MapObstacleCollision = false;

        for (MovingObstacle obstacle:movingObstacles) {
            for (Cell cell : map.mapArraylist) {
                if (cell.getType()==1&&obstacle.collidesWithCell(cell)) {
                    movingObstacle_MapObstacleCollision = true;
                }
            }
        }
    }

    private void checkCollisionWithBullet(){
        bulletCollision = false;

        for(Player player : players){
            for(Bullet bullet : bullets){
                if(bullet.checkBulletCollision(player)){
                    bulletCollision = true;
                    bullet.BulletRemove =true;
                }
            }
        }
    }


    private void updateScore() {
        if (attackCollision && input.isAttack() && !Input.getIsAttacking()) {
            Input.setIsAttacking(true);
            System.out.println(enemy.health);
            enemy.getDamagedBy(player);//Enemy's health decreasing

        } else {
            collisionText.setText("");
        }
    }

    private void getAfterCollision(){
        player.rectangle.setX(player.rectangle.getX()-player.getDx());
        player.rectangle.setY(player.rectangle.getY()-player.getDy());
    }
    public static void main(String[] args) {
        launch(args);
    }


}