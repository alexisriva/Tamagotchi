/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import java.util.LinkedList;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import java.util.Random;


/**
 *
 * @author steevenrodriguez
 */
public class MiniGameOrganizer {

    private BorderPane root;
    private LinkedList<TreasureChest> tesoros;
    private Pet pet;
    private Arco bow;
    
    public MiniGameOrganizer() {
        root = new BorderPane();
        pet = GameSceneOrganizer.getPet();
        Label nombre = new Label(pet.getName());
        Label descripcion = new Label("");
        Label dinero = new Label("" + pet.getMoney());
        HBox top = new HBox();
        top.getChildren().addAll(nombre, descripcion, dinero);
        root.setTop(top);
        
        Pane middle = new Pane();
        
        
        bow = new Arco(middle, this.tesoros);
        root.setCenter(middle);
        
        root.setOnMouseClicked((event) -> {
            bow.shoot(middle);
            System.out.println("Click");
        });
        root.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.RIGHT){
                System.out.println("Derecha");
            }
            else if (event.getCode() == KeyCode.LEFT){
                System.out.println("Izquierda");
            }
        });
    
    }

    /**
     * @return the root
     */
    public BorderPane getRoot() {
        return root;
    }
    //NO ESTA LISTO
    public TreasureChest randomlyCreate(java.lang.Class<? extends TreasureChest>... classes){
        TreasureChest chest = randomlyCreate(
                PenaltyChest.class,
                PointChest.class,
                BombChest.class,
                MultiplierChest.class
        );
        
        return chest;
    }
    public void setUpTreasures(){
          
        
        
    }
    
}
