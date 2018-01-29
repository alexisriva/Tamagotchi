/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author USER
 */
public class PickerSceneOrganizer {
    private BorderPane root;
    private static TextField petName;
    private Label label;
    private static Player player;
    private static Pet pet;
    private Label userLabel;
    private static TextField username;
    private GridPane image;
    
    public PickerSceneOrganizer() {
        player = new Player();
        root = new BorderPane();
        image = new GridPane();
        image.setAlignment(Pos.CENTER);
        Image img = new Image(Constants.PETIMGSRC);
        image.getChildren().add(new ImageView(img));
//        root.setId("root");
        userLabel = new Label("Pick an username");
        username = new TextField();
        username.setMaxWidth(150);
        label = new Label("Pick a name for your pet");
        petName = new TextField();
        petName.setMaxWidth(150);
        VBox container = new VBox(image, userLabel, username, label, petName);
        container.setSpacing(20);
        container.setAlignment(Pos.CENTER);
        
        petName.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER && !petName.getText().trim().equals("")) {
                pet = new Pet(petName.getText());
                player.setPet(pet);
                player.setUserName(username.getText());
                Tamagotchi.changeGameScene();
            }
        });
        
        root.setCenter(container);
    }
    
    public BorderPane getRoot() { return root; }
    
    public static Player getPlayer() { return player; }
    
    public static Pet getPet() { return pet; }
}
