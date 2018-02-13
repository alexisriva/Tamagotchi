/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import java.util.LinkedList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author steevenrodriguez
 */
public class ArcoLoad {
    private Pane arcoPane;
    private LinkedList<TreasureChest> tesoro;
    private ImageView arcoView;
    
    public ArcoLoad (Pane rootPane, LinkedList<TreasureChest> tesoro){
        this.tesoro=new LinkedList<>();
        this.tesoro= tesoro;
        arcoPane= new Pane();
        Image img = new Image(Constants.BOWIMGSRC);
         arcoView= new ImageView(img);
        arcoView.setFitHeight(100);
        arcoView.setFitWidth(100);
        arcoView.setTranslateX(500);
        arcoView.setTranslateY(500);
        this.arcoPane.getChildren().add(arcoView);
        rootPane.getChildren().add(arcoPane);
    }

    /**
     * @return the arcoPane
     */
    public Pane getArcoPane() {
        return arcoPane;
    }

    /**
     * @param arcoPane the arcoPane to set
     */
    public void setArcoPane(Pane arcoPane) {
        this.arcoPane = arcoPane;
    }
    public void shoot(Pane rootPane,Pet pet){
        Thread shootThread = new Thread((Runnable) new FlechaLoad(rootPane,this,this.tesoro,pet));
        shootThread.setDaemon(true);
        shootThread.start();
        
        
    }

    /**
     * @return the arcoView
     */
    public ImageView getArcoView() {
        return arcoView;
    }
}
