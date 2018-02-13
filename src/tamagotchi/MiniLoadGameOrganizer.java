/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author steevenrodriguez
 */
public class MiniLoadGameOrganizer {
    private BorderPane root;
    private LinkedList<TreasureChest> tesoros;
    private Pet pet;
    private ArcoLoad bow;
    private Pane middle;
    private Label nombre;
    public Label descripcion;
    private Label dinero;
    private boolean condicion;
    private Button goBack;

    public MiniLoadGameOrganizer() {
        this.tesoros = new LinkedList<>();
        root = new BorderPane();
        pet = LoadGameOrganizer.getPet();
        nombre = new Label("Pet: "+pet.getName());
        descripcion = new Label("");
        dinero = new Label("Dinero: " + pet.getMoney());
 
        HBox top = new HBox();
        top.getChildren().addAll(nombre, descripcion, dinero);
        top.setSpacing(100);
        root.setTop(top);
        middle = new Pane();
        bow = new ArcoLoad(middle, this.tesoros);
        root.setCenter(middle);
        this.root.setStyle("-fx-background-image: url(/tamagotchi/background_mini.png);"
                + "-fx-background-position: center center; ");
        this.condicion=this.pet.isCondicion();
        this.setUpTreasures(this.condicion);
        this.controlGame();
        middle.setFocusTraversable(true);
        middle.setOnMouseClicked((event) -> {
            bow.shoot(middle,this.pet);
            
        });
        middle.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.RIGHT && this.bow.getArcoView().getTranslateX() < 700) {
                this.bow.getArcoView().setTranslateX(this.bow.getArcoView().getTranslateX() + 40);

            } else if (event.getCode() == KeyCode.LEFT && this.bow.getArcoView().getTranslateX() > 20) {
                this.bow.getArcoView().setTranslateX(this.bow.getArcoView().getTranslateX() + 40 * -1);

            }
            else if(event.getCode() == KeyCode.ESCAPE) {
                Tamagotchi.returnLoadGameScene();
                
            }
        });

    }

    /**
     * @return the root
     */
    public BorderPane getRoot() {
        return root;
    }

    public void setUpTreasures(boolean condicion) {
        Thread create = new Thread(() -> {
            while (condicion) {
                try {
                    
                    Platform.runLater(() -> {
                        int randomNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                        if (randomNum >= 0 && randomNum < 45) {
                            TreasureChest tesoropunto = new PointChest(middle);
                            MiniLoadGameOrganizer.this.tesoros.add(tesoropunto);
                        } else if (randomNum >= 45 && randomNum < 70) {
                            TreasureChest tesoroMultiplier = new MultiplierChest(middle);
                            MiniLoadGameOrganizer.this.tesoros.add(tesoroMultiplier);
                        } else if (randomNum >= 70 && randomNum < 90) {
                            TreasureChest tesoroPenalty = new PenaltyChest(middle);
                            MiniLoadGameOrganizer.this.tesoros.add(tesoroPenalty);
                        } else if (randomNum >= 90 && randomNum < 100) {
                            TreasureChest tesoroBomb = new BombChest(middle);
                            MiniLoadGameOrganizer.this.tesoros.add(tesoroBomb);
                        }
                    });
                    
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MiniGameOrganizer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        create.setDaemon(true);
        create.start();
    }

    public void controlGame() {
        Thread control = new Thread(new Runnable() {
            @Override
            public void run() {
                while (MiniLoadGameOrganizer.this.pet.isCondicion()) {
                    try {
                        Platform.runLater(() -> {
                            MiniLoadGameOrganizer.this.nombre.setText("Pet: "+pet.getName());
                            MiniLoadGameOrganizer.this.dinero.setText("Dinero: " + pet.getMoney());
                            MiniLoadGameOrganizer.this.condicion= MiniLoadGameOrganizer.this.pet.isCondicion();
                        });
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MiniGameOrganizer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });
        control.setDaemon(true);
        control.start();
        
        
    }
}
