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
public class FlechaLoad implements Runnable {
    private Pane flechaPane;
    private Pet pet;
    private int multiplier;
    private boolean gameOn= true;
    private LinkedList<TreasureChest> tesoros;
    private ImageView arcoView;
    
    
    public FlechaLoad(Pane rootPane,ArcoLoad arco, LinkedList<TreasureChest> tesoro,Pet pet){
        this.tesoros=new LinkedList<>();
        this.tesoros=tesoro;
        this.multiplier=1;
        this.pet=pet;
        flechaPane= new Pane();
        Image img = new Image(Constants.ARROWIMGSRC);
        arcoView = new ImageView(img);
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
                      FlechaLoad.this.flechaPane.setTranslateY(FlechaLoad.this.flechaPane.getTranslateY()+sub);
                      FlechaLoad.this.tesoros.forEach((TreasureChest chest) -> {
                          FlechaLoad.this.checkColision(chest);
                          
                      });
                    }
                        
                });
                Thread.sleep(80);
            }catch(Exception e){
                
            }
        }
    
    }
    public void checkColision(TreasureChest chest) {
        Bounds a = this.arcoView.localToScene(this.arcoView.getBoundsInLocal());
        Bounds b = chest.chestView.localToScene(chest.chestView.getBoundsInLocal());

        if (a.intersects(b)) {
            if (chest instanceof PointChest ){
                this.pet.setMoney((this.pet.getMoney()+ ((PointChest) chest).money)*this.multiplier);
                this.arcoView.setVisible(false);
                ((PointChest) chest).chestView.setVisible(false);
                ((PointChest) chest).chestView.setTranslateY(605);
                System.out.println(((PointChest) chest).description);
            }
            else if (chest instanceof PenaltyChest ){
                this.pet.setMoney((this.pet.getMoney()- ((PenaltyChest) chest).money)*this.multiplier);
                    if(this.pet.getMoney()<0){
                    this.pet.setMoney(0);
                }
                this.arcoView.setVisible(false);
                ((PenaltyChest) chest).chestView.setVisible(false);
                ((PenaltyChest) chest).chestView.setTranslateY(605);
                System.out.println(((PenaltyChest) chest).description);
            }
            else if (chest instanceof MultiplierChest ){
                this.multiplier= ((MultiplierChest)chest).multiplier;
                this.arcoView.setVisible(false);
                ((MultiplierChest) chest).chestView.setVisible(false);
                ((MultiplierChest) chest).chestView.setTranslateY(605);
                System.out.println(((MultiplierChest) chest).description);
            }
            
            else if (chest instanceof BombChest ){
                this.arcoView.setVisible(false);
                ((BombChest) chest).chestView.setVisible(false);
                ((BombChest) chest).chestView.setTranslateY(605);
                System.out.println("Game Over");
                Tamagotchi.returnLoadGameScene();
            }
            

        }

    }
}
