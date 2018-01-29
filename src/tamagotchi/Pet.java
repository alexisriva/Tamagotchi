/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Random;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

/**
 *
 * @author USER
 */
public class Pet implements Runnable {
    private final String name;
    private final String birthDate;
    private String deathDate;
    private int hunger;
    private int happiness;
    private int money;
    private int clean;
    private Pane pet;
    private Pane poop;
    private LinkedList inventory = new LinkedList();
    private ImageView imgView;
    private int i=-60;
    
    public Pet(String name) {
        this.name = name;
        this.hunger = 10;
        this.happiness = 10;
        this.money = 100;
        this.clean = 10;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.birthDate = dtf.format(now);
        pet = new Pane();
        Image img = new Image(Constants.PETIMGSRC);
        imgView = new ImageView(img);
        imgView.setTranslateZ(imgView.getBoundsInLocal().getWidth() / 2.0);
        imgView.setRotationAxis(Rotate.Y_AXIS);
        pet.getChildren().add(imgView);
//        System.out.println(this.birthDate);
    }
    
    public void moveRight() {
        pet.setLayoutX(pet.getLayoutX()+Constants.MOVE);
    }
    
    public void moveLeft() {
        pet.setLayoutX(pet.getLayoutX()-Constants.MOVE);
    }
    
    public void rotate(int degrees) {
        imgView.setRotate(degrees);
    }
    
    public void run() {
        while (true) {
            try {
                for (int i=1; i<=50; i++) {
                    Platform.runLater(() -> { moveRight(); });
                    Thread.sleep(Constants.SPEED);
                }
                rotate(180);
                Thread.sleep(Constants.SPEED);
                for (int i=1; i<=50; i++) {
                    Platform.runLater(() -> { moveLeft(); });
                    Thread.sleep(Constants.SPEED);
                }
                rotate(0);
                Thread.sleep(Constants.SPEED);
            } catch (Exception e) {}
        }
    }
    
    public void poop(Pane pane) {
        poop = new Pane();
        Image img = new Image(Constants.POOPIMGSRC);
        ImageView poopView = new ImageView(img);
        poopView.setFitHeight(40);
        poopView.setFitWidth(40);
        poop.getChildren().add(poopView);
        poop.setTranslateY(80);
        poop.setLayoutX(new Random().nextInt(500)+100);
        poop.setTranslateX(i);
        pane.getChildren().add(poop);
        i+=10;
    }
    
    public void setRoot(Pane root) { root.getChildren().add(pet); }
    
    public String getName() { return name; }
    
    public String getBirthInfo() { return birthDate; }
    
    public int getHunger() { return hunger; }
    
    public void setHunger(int hunger) { this.hunger = hunger; }
    
    public int getHappiness() { return happiness; }
    
    public void setHappiness(int happiness) { this.happiness = happiness; }
    
    public int getMoney() { return money; }
    
    public void setMoney(int money) { this.money = money; }
    
    public int getClean() { return clean; }
    
    public void setClean(int clean) { this.clean = clean; }
    
    public String getDeathDate() { return deathDate; }
    
    public void setDeathDate(String deadDate) { this.deathDate = deadDate; }
    
    public LinkedList getInventory() { return inventory; }
    
    public void addInventory(Object o) { inventory.add(o); }
    
//    public String toString() {
//        return name + "-" + birthDate;
//    }
}
