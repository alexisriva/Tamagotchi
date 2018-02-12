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
                
                while (this.imgPane.getLayoutX() < 400 && this.imgPane.getLayoutY() <= 400) {
                    Platform.runLater(() -> {
                        this.imgPane.setLayoutY(this.imgPane.getLayoutY() + 20);
                    });
                    
                    Thread.sleep(325);
                    //Thread.yield();

                }
            } catch (InterruptedException ex) {
                
            }
        });
        
        
    }

    
    
    
}
