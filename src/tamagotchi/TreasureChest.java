/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author steevenrodriguez
 */
public class TreasureChest {
    protected String description;
    protected int money;
    protected int multiplier;
    protected Pane imgPane;
    
    public TreasureChest(Pane rootPane){
        this.multiplier=1;
        this.money=0;
        this.imgPane=new Pane();
        Image img = new Image(Constants.CHESTIMGSRC);
        ImageView chestView = new ImageView(img);
        chestView.setFitHeight(15);
        chestView.setFitWidth(15);
        imgPane.getChildren().add(chestView);
        rootPane.getChildren().add(imgPane);
        
        Thread hilo = new Thread(() -> {
            try {
                
                while (imgPane.getLayoutX() < 400 && imgPane.getLayoutY() <= 400) {
                    Platform.runLater(() -> {
                        imgPane.setLayoutY(imgPane.getLayoutY() + 20);
                    });
                    
                    Thread.sleep(325);
                    //Thread.yield();

                }
            } catch (InterruptedException ex) {
                
            }
        });
        
        
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    

    /**
     * @return the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * @return the multiplier
     */
    public int getMultiplier() {
        return multiplier;
    }

    /**
     * @param multiplier the multiplier to set
     */
    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }
    
    
    
}
