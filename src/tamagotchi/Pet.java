/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Random;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

/**
 *
 * @author USER
 */
public class Pet implements Runnable, Serializable {
    private static final long serialVersionUID = 2436883548017479013L;
    
    private final String name;
    private final String birthDate;
    private String deathDate;
    private int life;
    private int hunger;
    private int happiness;
    private int money;
    private int clean;
    private transient Pane pet;
    private transient Pane poop;
    private LinkedList<Integer> inventory = new LinkedList<>();
    private transient ImageView imgView;
    private int i=-60;
    private boolean condicion;
    
    public Pet(String name) {
        this.condicion=true;
        this.name = name;
        this.life = 500;
        this.hunger = 10;
        this.happiness = 10;
        this.money = 100;
        this.clean = 10;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.birthDate = dtf.format(now);
        pet = new Pane();
        Image img = new Image(Constants.PETIMGSRC);
        imgView = setUpPetAvatar(img);
        pet.getChildren().add(imgView);
//        System.out.println(this.birthDate);
    }

    Pet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ImageView setUpPetAvatar(Image img) {
        ImageView imgView = new ImageView(img);
        imgView.setTranslateZ(imgView.getBoundsInLocal().getWidth() / 2.0);
        imgView.setRotationAxis(Rotate.Y_AXIS);
        return imgView;
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
    
    @Override
    public void run() {
        while (true) {
            try {
                for (int i=1; i<=37; i++) {
                    Platform.runLater(() -> { moveRight(); });
                    Thread.sleep(Constants.SPEED);
                }
                rotate(180);
                Thread.sleep(Constants.SPEED);
                for (int i=1; i<=40; i++) {
                    Platform.runLater(() -> { moveLeft(); });
                    Thread.sleep(Constants.SPEED);
                }
                rotate(0);
                Thread.sleep(Constants.SPEED);
            } catch (Exception e) {}
        }
    }
    
    public void poop(Pane pane, LinkedList list) {
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
        
        if (clean - 1 >=0 )
            clean -= 1;
        else
            clean = 0;
        
        if (PetLifeControl.firstCleanZero)
            PetLifeControl.decreseBcOfClean(this);
        
        list.add(poop);
    }
    
    public void setRoot(Pane root) { root.getChildren().add(pet); }
    
    public String getName() { return name; }
    
    public String getBirthInfo() { return birthDate; }
    
    public int getLife() { return life; }
    
    public void setLife(int life) { this.life = life; }
    
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
    
    public void addInventory(int n) { inventory.add(n); }
    
    public void setPane(Pane pet) {
        this.pet = pet;
    }
    
    public void setImageView(ImageView imgView, Pane pet) {
        this.imgView = imgView;
        pet.getChildren().add(imgView);
    }
    
//    public String toString() {
//        return name + "-" + birthDate;
//    }

    /**
     * @param condicion the condicion to set
     */
    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
    }

    /**
     * @return the condicion
     */
    public boolean isCondicion() {
        return condicion;
    }
}
