package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

    public class Treasure {
    Pane layer;
    Image treasure_image;
    Image treasure_openedImage;
    ImageView treasure_imageView;
    Input input;

    public Treasure(Pane layer, Image treasure_image, Image treasure_openedImage, double treasure_X, double treasure_Y, Input input){

        this.layer = layer;
        this.treasure_image = treasure_image;
        this.treasure_openedImage = treasure_openedImage;
        this.treasure_imageView = new ImageView(treasure_image);
        this.treasure_imageView.relocate(treasure_X, treasure_Y);
        this.input = input;

        addToLayer();
    }

    public void processInput(){
        if(input.isInteract()){
            this.treasure_imageView.setImage(treasure_openedImage);
        }

    }

    public void addToLayer(){
        layer.getChildren().add(treasure_imageView);

    }
}
