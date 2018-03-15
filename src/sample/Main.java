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

    public Grid grid;//Obstacle's grid

    Pane playfieldLayout;
    Pane scoreLayout;

    Image playerImage;
    Image enemyImage;
    Image bulletImage;
    Image treasureImage;
    Image treasureOpenedImage;


    Scene scene;

    //Creating lists
    List<Player> players = new ArrayList<>();
    List<Enemy> enemies = new ArrayList<>();
    List<Cell> Cells = new ArrayList<>();
    List<Bullet> bullets = new ArrayList<>();
    List<Treasure> treasures = new ArrayList<>();

    ArrayList<Position> obstacles = new ArrayList<>();

    /*
    Here collision text we will change it later and
    Make it health bar and whenever we attack an enemy
    it's health bar will be decrease if it attacks vice versa*/

    Text collisionText = new Text();
    boolean collision = false;
    boolean attackCollision = false;
    boolean cellCollision = false;
    boolean bulletCollision = false;

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();

        grid=new Grid();//Obstacle's grid

        // create layers
        playfieldLayout = new Pane();
        scoreLayout = new Pane();

        root.getChildren().add(playfieldLayout);
        root.getChildren().add(scoreLayout);


        initObstacles();//first initiliaze after create
        createObstacles();

        scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        primaryStage.setTitle("Room & Doom");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Calling our methods here
        loadGame();
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


                // check collisions
                checkCollisionWithEnemy();
                enemyBlock();
                checkAttackCollisionWithEnemy();
                checkCollisionWithCell();
                cellBlock();
                checkCollisionWithBullet();

                // update sprites in scene
                players.forEach(sprite -> sprite.updateUI());
                enemies.forEach(sprite -> sprite.updateUI());
                bullets.forEach(sprite -> sprite.updateUI());

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
        treasureImage = new Image(getClass().getResource("/treasure.png").toExternalForm());
        treasureOpenedImage = new Image(getClass().getResource("/treasureOpened.png").toExternalForm());

    }

    public void enemyBlock(){
        if(collision){
            player.rectangle.setX(player.rectangle.getX()-player.getDx());
            player.rectangle.setY(player.rectangle.getY()-player.getDy());
        }
    }
    public void cellBlock(){
        if(cellCollision){
            player.rectangle.setX(player.rectangle.getX()-player.getDx());
            player.rectangle.setY(player.rectangle.getY()-player.getDy());
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
    private void checkCollisionWithCell(){
        cellCollision = false;

        for (Player player: players){
            for(Position obstacle: obstacles){
                    if(player.collidesWithCell(obstacle)){
                    cellCollision = true;
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


    public void createObstacles(){

        for (int i =0;i< Settings.COLUMN_CELL_COUNT;i++) {

            for (int j = 0; j < Settings.ROW_CELL_COUNT; j++) {

                Position position = new Position(i, j);

                int type = 0;

                //Check if not boundary

                if (i != Settings.COLUMN_CELL_COUNT - 1 && j != Settings.ROW_CELL_COUNT - 1 && i != 0 && j != 0) {
                    if (i == 1 && j == 1)
                        type = 0;
                    else if (isObstacle(position))
                        type = 1;

                }

                Cell cell = new Cell(position, type);
                Cells.add(cell);
                grid.addCell(cell);//Cell was added to grid

                playfieldLayout.getChildren().add(cell.getNode());
            }

        }
    }


    public  void initObstacles(){//Randomly filled but we must think about that

            //Generate Left Obstacles
        for(int i=0;i<Settings.ROW_CELL_COUNT;i++){
            obstacles.add(new Position(i, 1));
        }

        //Generate Reflection
        int loopSize = obstacles.size();
        for (int i =0;i< loopSize;i++){

            Position tmpPosition = obstacles.get(i);
            Position newPosition = new Position(tmpPosition.getRow(), Settings.COLUMN_CELL_COUNT-1-tmpPosition.getColumn());
            obstacles.add(newPosition);

        }

        //Generate Center Obstacles
        obstacles.add(new Position(0,0));
        obstacles.add(new Position(6, 6));
        obstacles.add(new Position(7, 6));
        obstacles.add(new Position(8, 6));
        obstacles.add(new Position(8, 7));
        obstacles.add(new Position(8, 8));

        obstacles.add(new Position(7, 8));
        obstacles.add(new Position(6, 8));

        obstacles.add(new Position(10, 7));
        obstacles.add(new Position(11, 7));
        obstacles.add(new Position(12, 7));

        obstacles.add(new Position(2, 7));
        obstacles.add(new Position(3, 7));
        obstacles.add(new Position(4, 7));

    }

    public  boolean isObstacle(Position position){
        for (int i =0;i< obstacles.size();i++){
            Position tmpPosition = obstacles.get(i);
            if (position.getRow() == tmpPosition.getRow() && position.getColumn() == tmpPosition.getColumn())
                return true;
        }

        return false;

    }//Controlling obstacle where the position

    public static void main(String[] args) {
        launch(args);
    }


}