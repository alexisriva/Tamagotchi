/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import java.util.LinkedList;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Button;

/**
 *
 * @author steevenrodriguez
 */
public class MiniGameOrganizer {

    private BorderPane root;
    private LinkedList<TreasureChest> tesoros;
    private Pet pet;
    private Arco bow;
    private Pane middle;
    private Label nombre;
    public Label descripcion;
    private Label dinero;
    private boolean condicion;
    private Button goBack;

    public MiniGameOrganizer() {
        this.tesoros = new LinkedList<>();
        root = new BorderPane();
        pet = PickerSceneOrganizer.getPet();
        nombre = new Label("Pet: "+pet.getName());
        descripcion = new Label("");
        dinero = new Label("Dinero: " + pet.getMoney());
 
        HBox top = new HBox();
        top.getChildren().addAll(nombre, descripcion, dinero);
        top.setSpacing(100);
        root.setTop(top);
        middle = new Pane();
        bow = new Arco(middle, this.tesoros);
        root.setCenter(middle);
        this.root.setStyle("-fx-background-image: url(/tamagotchi/background_mini.png);"
                + "-fx-background-position: center center; ");
        this.condicion=this.pet.isCondicion();
        this.setUpTreasures(this.condicion);
        this.controlGame();
        middle.setFocusTraversable(true);
        middle.setOnMouseClicked((event) -> {
            bow.shoot(middle);
            
        });
        middle.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.RIGHT && this.bow.getArcoView().getTranslateX() < 700) {
                this.bow.getArcoView().setTranslateX(this.bow.getArcoView().getTranslateX() + 40);

            } else if (event.getCode() == KeyCode.LEFT && this.bow.getArcoView().getTranslateX() > 20) {
                this.bow.getArcoView().setTranslateX(this.bow.getArcoView().getTranslateX() + 40 * -1);

            }
            else if(event.getCode() == KeyCode.ESCAPE) {
                Tamagotchi.returnGameScene();
                
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
                            MiniGameOrganizer.this.tesoros.add(tesoropunto);
                        } else if (randomNum >= 45 && randomNum < 70) {
                            TreasureChest tesoroMultiplier = new MultiplierChest(middle);
                            MiniGameOrganizer.this.tesoros.add(tesoroMultiplier);
                        } else if (randomNum >= 70 && randomNum < 90) {
                            TreasureChest tesoroPenalty = new PenaltyChest(middle);
                            MiniGameOrganizer.this.tesoros.add(tesoroPenalty);
                        } else if (randomNum >= 90 && randomNum < 100) {
                            TreasureChest tesoroBomb = new BombChest(middle);
                            MiniGameOrganizer.this.tesoros.add(tesoroBomb);
                        }
                    });
                    
                    Thread.sleep(9000);
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
                while (MiniGameOrganizer.this.pet.isCondicion()) {
                    try {
                        Platform.runLater(() -> {
                            MiniGameOrganizer.this.nombre.setText("Pet: "+pet.getName());
                            MiniGameOrganizer.this.dinero.setText("Dinero: " + pet.getMoney());
                            MiniGameOrganizer.this.condicion= MiniGameOrganizer.this.pet.isCondicion();
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
