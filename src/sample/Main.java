package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Stage;
<<<<<<< HEAD
import javafx.util.Duration;
=======
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09
import map.*;



import java.util.*;
import java.util.concurrent.ScheduledFuture;

public class Main extends Application {

    Input input;
    Player player;
    Enemy enemy;
<<<<<<< HEAD
    Bullet bullet;
=======
    Spell spell;
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09


    public Grid grid;//Obstacle's grid


    Pane playfieldLayout;
    Pane scoreLayout;

    Image playerImage;
    Image enemyImage;
<<<<<<< HEAD
    Image bulletImage;
=======
    Image spellImage;
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09

    Scene scene;

    //Creating lists
    List<Player> players = new ArrayList<>();
    List<Enemy> enemies = new ArrayList<>();
<<<<<<< HEAD
    ArrayList<Cell> Cells = new ArrayList<>();
    List<Bullet> bullets = new ArrayList<Bullet>();
    Iterator<Bullet> bulletIterator = bullets.iterator();
=======
    List<Spell> spells = new ArrayList<>();
    ArrayList<Cell> Cells = new ArrayList<>();
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09


    ArrayList<Position> obstacles = new ArrayList<>();

    /*
    Here collision text we will change it later and
    Make it health bar and whenever we attack an enemy
    it's health bar will be decrease if it attacks vice versa*/

    Text collisionText = new Text();
    boolean collision = false;
    boolean attackCollision = false;
    boolean cellCollision = false;
<<<<<<< HEAD
    boolean bulletCollision = false;

=======
    boolean spellCollision = false;
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09


    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();

<<<<<<< HEAD
        grid=new Grid();//Obstacle's grid
=======
        grid = new Grid();//Obstacle's grid
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09

        // create layers
        playfieldLayout = new Pane();
        scoreLayout = new Pane();



        root.getChildren().add(playfieldLayout);
        root.getChildren().add(scoreLayout);


<<<<<<< HEAD
        initObstacles();//first initiliaze after create
=======
        initObstacles();//first initialize after create
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09
        createObstacles();

        scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        primaryStage.setTitle("Room & Doom");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Calling our methods here
        loadGame();
        createScoreLayer();
        createPlayers();
        createEnemy();
        createSpell();






        AnimationTimer gameLoop = new AnimationTimer() {
             private long lastUpdate =0;

            @Override

            public void handle(long now) {

                // player input
                players.forEach(sprite -> sprite.processInput());

                // movement
                players.forEach(sprite -> sprite.move());


                // check collisions
                checkCollisionWithEnemy();
                enemyBlock();
                checkAttackCollisionWithEnemy();
                checkCollisionWithCell();
                cellBlcok();
<<<<<<< HEAD
                checkCollisionWithBullet();



=======
                //checkCollisionWithSpell();
                removeSpell();
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09

                // update sprites in scene
                players.forEach(sprite -> sprite.updateUI());
                enemies.forEach(sprite -> sprite.updateUI());
<<<<<<< HEAD
                bullets.forEach(sprite -> sprite.updateUI());

                   if(now-lastUpdate >= 1000000000){
                       enemies.forEach(Enemy -> createBullet());
                       lastUpdate=now;
                   }
                bulletRemove();


=======
                // spells.forEach(sprite -> sprite.translate(player));

                //check if player alive
                players.forEach(sprite -> sprite.checkAlive());
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09

                // check if sprite can be removed
                enemies.forEach(sprite -> sprite.checkRemovability());

                // remove removables from list, layer, etc
                removeSprites(enemies);

                // update score, health, etc
                updateScore();
<<<<<<< HEAD

=======
                player.health--;
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09

            }

        };
        gameLoop.start();

    }
    //Creating spell here
<<<<<<< HEAD

=======
    private void createSpell() {
        Image image = spellImage;
        //Setting spells' qualities
        spell = new Spell(playfieldLayout, image, enemy.getCenterX(), enemy.getCenterY(), 5);
        //Add all spells in a list so it will be easier to work
        spells.add(spell);
    }
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09

    //Image resources
    private void loadGame() {
        playerImage = new Image(getClass().getResource("/spritesheet.png").toExternalForm());
        enemyImage = new Image(getClass().getResource("/enemy.png").toExternalForm());
<<<<<<< HEAD
        bulletImage = new Image(getClass().getResource("/conjure_ball_lightning_old.png").toExternalForm());
=======
        spellImage = new Image(getClass().getResource("/conjure_ball_lightning_old.png").toExternalForm());
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09

    }

    public void enemyBlock(){
        if(collision){
            player.rectangle.setX(player.rectangle.getX()-player.getDx());
            player.rectangle.setY(player.rectangle.getY()-player.getDy());
<<<<<<< HEAD
=======
        }
    }
    public void cellBlcok(){
        if(cellCollision){
            player.rectangle.setX(player.rectangle.getX()-player.getDx());
            player.rectangle.setY(player.rectangle.getY()-player.getDy());
        }
    }
    public void removeSpell(){
        if(spellCollision){
            System.out.println(spellCollision);
            System.out.println(player.health);
            player.setHealth(player.getHealth()-spell.damage);
            spell.removeFromLayer();


>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09
        }

    }
    public void cellBlcok(){
        if(cellCollision){
            player.rectangle.setX(player.rectangle.getX()-player.getDx());
            player.rectangle.setY(player.rectangle.getY()-player.getDy());
        }
    }
    public void bulletRemove() {
        for (Bullet bullet : bullets) {
            if (bullet.BulletRemove) {
                player.setHealth(player.getHealth()-enemy.getDamage());
                System.out.println(player.getHealth());
                playfieldLayout.getChildren().removeAll(bullet.bullet_Imageview,bullet.bullet_Node);
                bullets.remove(bullet);
            }}

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
        enemy = new Enemy(playfieldLayout, image, 300, 200, 100, 10);
        //Add all enemies in a list so it will be easier to work
        enemies.add(enemy);

    }
    public void createBullet(){
       {
            Image image = bulletImage;
            bullet = new Bullet(image, new Circle(enemy.rectangle.getX(), enemy.rectangle.getY(), 15, Color.RED), enemy, player);
            playfieldLayout.getChildren().add(bullet.bullet_Imageview);
            bullets.add(bullet);
            bullet.pathTransitionBullets().play();


        }
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
<<<<<<< HEAD
    private void checkCollisionWithBullet(){
        bulletCollision = false;

        for(Player player : players){
            for(Bullet bullet : bullets){
                if(bullet.checkBulletCollision(player)){
                    bulletCollision = true;
                    bullet.BulletRemove =true;
=======
    private void checkCollisionWithSpell(){
        spellCollision = false;

        for (Player player: players){
            for(Spell spell:spells){
                if(player.spellCollides(spell,player)){
                    spellCollision = true;
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09
                }
            }
        }
    }


    private void updateScore() {
        if (attackCollision && input.isAttack() && !Input.getIsAttacking()) {
            Input.setIsAttacking(true);
            enemy.getDamagedBy(player);//Enemy's health decreasing
            System.out.println(enemy.health);
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

<<<<<<< HEAD
                //BURAYI TAM ANLAMADIM???????
                if (i != Settings.COLUMN_CELL_COUNT - 1 && j != Settings.ROW_CELL_COUNT - 1 && i != 0 && j != 0) {
                    if (i == 1 && j == 1)
                        type = 0;
                    else if (isObstacle(position))//Bu pozisyonda obtacle için oyuk varsa border ı koy demek
=======
                if (i != Settings.COLUMN_CELL_COUNT - 1 && j != Settings.ROW_CELL_COUNT - 1 && i != 0 && j != 0) {
                    if (i == 1 && j == 1)
                        type = 0;
                    else if (isObstacle(position))
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09
                        type = 1;

                }

<<<<<<< HEAD

=======
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09
                Cell cell = new Cell(position, type);
                Cells.add(cell);
                grid.addCell(cell);//Cell was added to grid

<<<<<<< HEAD
                playfieldLayout.getChildren().add(cell.getNode());//Mevcut Layouta gömdüm
=======
                playfieldLayout.getChildren().add(cell.getNode());//Added to the layout
>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09
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
<<<<<<< HEAD
=======

>>>>>>> 3321901106b7324d79bae8a346263d07b902ec09
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