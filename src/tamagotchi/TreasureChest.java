/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import java.util.concurrent.ThreadLocalRandom;
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
    protected boolean condicion;
    protected ImageView chestView;
    
    public TreasureChest(Pane rootPane){
        this.condicion=true;
        double ejeY = ThreadLocalRandom.current().nextDouble(50,180 + 1);
        double ejeX = ThreadLocalRandom.current().nextDouble(30,650 + 1);
        this.multiplier=1;
        this.money=0;
        this.imgPane=new Pane();
        Image img = new Image(Constants.CHESTIMGSRC);
        chestView = new ImageView(img);
        chestView.setFitHeight(40);
        chestView.setFitWidth(40);
        chestView.setTranslateY(ejeY);
        chestView.setTranslateX(ejeX);
        imgPane.getChildren().add(chestView);
        rootPane.getChildren().add(imgPane);
        System.out.println("Creado");
        
        Thread hilo = new Thread(() -> {
            try {
                
                while (this.imgPane.getTranslateY()<= 600) {
                    Platform.runLater(() -> {
                        this.imgPane.setTranslateY(this.imgPane.getTranslateY()+ 20);
                    });
                    
                    Thread.sleep(325);
                    //Thread.yield();

                }
            } catch (InterruptedException ex) {
                
            }
        });
        hilo.setDaemon(true);
        hilo.start();
        
        
    }

    
    
    
}
