/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import java.util.LinkedList;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author steevenrodriguez
 */
public class Flecha implements Runnable {
    private Pane flechaPane;
    private Pet pet;
    private int multiplier;
    private boolean gameOn= true;
    private LinkedList<TreasureChest> tesoros;
    
    
    public Flecha(Pane rootPane,Arco arco, LinkedList<TreasureChest> tesoro){
        this.tesoros=new LinkedList<>();
        this.tesoros=tesoro;
        this.multiplier=1;
        this.pet=GameSceneOrganizer.getPet();
        flechaPane= new Pane();
        Image img = new Image(Constants.ARROWIMGSRC);
        ImageView arcoView = new ImageView(img);
        arcoView.setFitHeight(50);
        arcoView.setFitWidth(20);
        arcoView.setTranslateX(arco.getArcoView().getTranslateX()+25);
        arcoView.setTranslateY(arco.getArcoView().getTranslateY()-35);
        flechaPane.getChildren().add(arcoView);
        rootPane.getChildren().add(flechaPane);
        
    }

    @Override
    public void run() {
        final double sub;
        sub = -15;
        while (this.flechaPane.getTranslateY() > -820 && this.gameOn) {
            try {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                      Flecha.this.flechaPane.setTranslateY(Flecha.this.flechaPane.getTranslateY()+sub);
                      //Flecha.this.tesoros.forEach((chest) -> {
                          //Flecha.this.checkColision(chest);
                      //});
                    }
                        
                });
                Thread.sleep(80);
            }catch(Exception e){
                
            }
        }
    
    }
    public void checkColision(TreasureChest chest) {
        Bounds a = this.flechaPane.localToScene(this.flechaPane.getBoundsInLocal());
        Bounds b = chest.imgPane.localToScene(chest.imgPane.getBoundsInLocal());

        if (a.intersects(b)) {
            if (chest instanceof PointChest ){
                this.pet.setMoney((this.pet.getMoney()+ ((PointChest) chest).money)*this.multiplier);
            }
            else if (chest instanceof PenaltyChest ){
                this.pet.setMoney((this.pet.getMoney()- ((PenaltyChest) chest).money)*this.multiplier);
            }
            else if (chest instanceof MultiplierChest ){
                this.multiplier= ((MultiplierChest)chest).multiplier;
            }
            
            else{
                BombChest bc;
                bc = (BombChest) chest;
                this.gameOn=bc.isStop();
            }
            

        }

    }
    
}
