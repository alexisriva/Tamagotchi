/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
class GameSceneOrganizer implements Runnable {

    private BorderPane root;
    private static Player player;
    private static Pet pet;
    private Pane petContainer;
    private Label life;
    private Label hunger;
    private Label happiness;
    private Label money;
    private Label clean;
    private Label inventory;
    private LinkedList<Pane> poopStack = new LinkedList<>();

    public GameSceneOrganizer() {
        root = new BorderPane();
        root.setId("rootGame");
        
//        pet = PickerSceneOrganizer.getPet();
//        petContainer = new Pane();
//        pet.setRoot(petContainer);
        
        deserializeGame();

//        setUpPlayerActionButtons();

        this.root.setStyle("-fx-background-image: url(/tamagotchi/house.jpg);"
                + "-fx-background-position: center center; ");

        root.setBottom(petContainer);
    }

    public void setUpPetAnimation() {
        Thread petThread = new Thread(pet);
        petThread.setDaemon(true);
        petThread.start();
    }

    public void setUpPlayerInfo() {
//        player = PickerSceneOrganizer.getPlayer();
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
        inventory = new Label("Inventory items: " + String.valueOf(pet.getInventory().size()));

        playerInfo.add(username, 0, 0);
        playerInfo.add(petName, 1, 0);
        playerInfo.add(inventory, 2, 0);
        playerInfo.add(btns, 4, 0);

        root.setTop(playerInfo);
    }

    public void setUpPetInfo() {
        VBox petInfo = new VBox();
        
        life = new Label("Life: " + String.valueOf(pet.getLife()));
        hunger = new Label("Hunger: " + String.valueOf(pet.getHunger()));
        happiness = new Label("Happiness: " + String.valueOf(pet.getHappiness()));
        money = new Label("Money: " + String.valueOf(pet.getMoney()));
        clean = new Label("Clean: " + String.valueOf(pet.getClean()));
        petInfo.getChildren().addAll(life, hunger, happiness, money, clean);
        petInfo.setAlignment(Pos.TOP_CENTER);

        root.setLeft(petInfo);
    }

    public void setUpPlayerActionButtons() {
        VBox playerActions = new VBox();

        Button clean = new Button("Clean pet");
        clean.setOnMouseClicked((event) -> {
            player.cleanPet(poopStack, petContainer);
        });

        Button feed = new Button("Feed pet");
        feed.setOnMouseClicked((event) -> {
            player.feedPet();
        });

        Button play = new Button("Play");
        play.setOnMouseClicked((event) -> {
            player.playWithPet();
            Tamagotchi.changeMiniGameScene();
        });
        
        playerActions.getChildren().addAll(clean, feed, play);
        playerActions.setAlignment(Pos.TOP_CENTER);

        root.setRight(playerActions);
    }

    public BorderPane getRoot() {
        return root;
    }

    public static Player getPlayer() {
        return player;
    }

    public static Pet getPet() {
        return pet;
    }

    public void run() {
        Platform.runLater(() -> {
            setUpPetAnimation();
            Timer timer = new Timer();
            timer.schedule(new PetLifeControl(pet), Constants.SECONDSTODECREASELIFE*1000, Constants.SECONDSTODECREASELIFE*1000);
            timer.schedule(new PetStatusControl(pet), Constants.SECONDSTODECREASESTATUS * 1000, Constants.SECONDSTODECREASESTATUS * 1000);
            int rnd = new Random().nextInt(5) + 1;
//            System.out.println(rnd);
            timer.schedule(new CleanControl(pet, petContainer, poopStack), 60000, rnd*60*1000);
        });
        try { Thread.sleep(1000); }
        catch (InterruptedException ex) {}
        while (pet.getLife() > 0) {
            Platform.runLater(() -> {
                life.setText("Life: " + String.valueOf(pet.getLife()));
                hunger.setText("Hunger: " + String.valueOf(pet.getHunger()));
                happiness.setText("Happiness: " + String.valueOf(pet.getHappiness()));
                money.setText("Money: " + String.valueOf(pet.getMoney()));
                clean.setText("Clean: " + String.valueOf(pet.getClean()));
                inventory.setText("Inventory items: " + String.valueOf(pet.getInventory().size()));
                System.out.println(poopStack);
                System.out.println(poopStack.size());
                PetLifeControl.decreaseBcOfHunger(pet);
                PetLifeControl.decreaseBcOfHappiness(pet);
                PetLifeControl.decreseBcOfClean(pet);
                serializeGame(pet, player);
            });
            try { Thread.sleep(1000); } 
            catch (InterruptedException ex){}
        }
    }
    
    public void serializeGame(Pet pet, Player player) {
        try {
            FileOutputStream petFile = new FileOutputStream("pet.ser");
            FileOutputStream playerFile = new FileOutputStream("player.ser");
            
            ObjectOutputStream petOut = new ObjectOutputStream(petFile);
            petOut.writeObject(pet);
            petOut.close();
            petFile.close();
            
            ObjectOutputStream playerOut = new ObjectOutputStream(playerFile);
            playerOut.writeObject(player);
            playerOut.close();
            playerFile.close();
            
            System.out.println("Objects serialized");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    
    public void deserializeGame() {
        try {
            FileInputStream petFile = new FileInputStream("pet.ser");
            FileInputStream playerFile = new FileInputStream("player.ser");
            
            ObjectInputStream petIn = new ObjectInputStream(petFile);
            pet = (Pet) petIn.readObject();
            Pane p = new Pane();
            pet.setPane(p);
            pet.setImageView(pet.setUpPetAvatar(new Image(Constants.PETIMGSRC)), p);
            petIn.close();
            petFile.close();
            
            ObjectInputStream playerIn = new ObjectInputStream(playerFile);
            player = (Player) playerIn.readObject();
            playerIn.close();
            playerFile.close();
            
            System.out.println("Objects deserialized");
        } catch (IOException i) {
            System.out.println("Picker scene");
            pet = PickerSceneOrganizer.getPet();
            player = PickerSceneOrganizer.getPlayer();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        } finally {
            petContainer = new Pane();
            pet.setRoot(petContainer);
        
            setUpPetInfo();
            setUpPlayerInfo();
            setUpPlayerActionButtons();
        }
    }
}
