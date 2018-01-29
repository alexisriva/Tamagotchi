/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import java.util.Timer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author USER
 */
class GameSceneOrganizer {
    private BorderPane root;
    private static Player player;
    private static Pet pet;
    
    public GameSceneOrganizer() {
        root = new BorderPane();
        root.setId("rootGame");
        
        setUpPet();
        
        setUpPlayerInfo();
        setUpPetInfo();
        this.root.setStyle("-fx-background-image: url(/tamagotchi/house.jpg);" + 
                "-fx-background-position: center center; ");
        
        
//        Label lbl = new Label("HOLA!");
    }
    
    public void setUpPet() {
        pet = PickerSceneOrganizer.getPet();
        Pane petAvatar = new Pane();
        pet.setRoot(petAvatar);
        Thread petThread = new Thread(pet);
        petThread.setDaemon(true);
        petThread.start();
        
        root.setBottom(petAvatar);
    }
    
    public void setUpPlayerInfo() {
        player = PickerSceneOrganizer.getPlayer();
        GridPane playerInfo = new GridPane();
        playerInfo.setPadding(new Insets(10));
        playerInfo.getColumnConstraints().addAll(new ColumnConstraints(160),
                new ColumnConstraints(160), new ColumnConstraints(160),
                new ColumnConstraints(160), new ColumnConstraints(160));
        
        HBox btns = new HBox();
        Button shop = new Button("Shop");
        shop.setOnMouseClicked((event) -> {
            Tamagotchi.changeShopScene();
        });
        btns.getChildren().add(shop);
        
        Label petName = new Label(pet.getName());
        Label username = new Label(player.getName());
        
        playerInfo.add(username, 0, 0);
        playerInfo.add(petName, 1, 0);
        playerInfo.add(btns, 4, 0);
        
        
        root.setTop(playerInfo);
    }
    
    public void setUpPetInfo() {
        VBox petInfo = new VBox();
        
        Label hunger = new Label("Hunger: " + String.valueOf(pet.getHunger()));
        Label happiness = new Label("Happiness: " + String.valueOf(pet.getHappiness()));
        Label money = new Label("Money: " + String.valueOf(pet.getMoney()));
        Label clean = new Label("Clean: " + String.valueOf(pet.getClean()));
        petInfo.getChildren().addAll(hunger, happiness, money, clean);
        petInfo.setAlignment(Pos.TOP_CENTER);
        
        Timer timer = new Timer();
        timer.schedule(new LifeControl(pet), 0, Constants.SECONDSTODECREASE*1000);
        
        
        root.setLeft(petInfo);
    }
    
    public BorderPane getRoot() { return root; }
    
    public static Player getPlayer() { return player; }
    
    public static Pet getPet() { return pet; }
}
