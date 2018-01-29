/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author USER
 */
public class FoodShopOrganizer {
    private BorderPane root;
    
    public FoodShopOrganizer() {
        root = new BorderPane();
        
        Button back = new Button("Back");
        back.setOnMouseClicked((event) -> {
            Tamagotchi.returnGameScene();
        });
        back.setAlignment(Pos.CENTER_LEFT);
        
        VBox container = new VBox();
        container.setAlignment(Pos.CENTER);
        HBox innerContainer = new HBox();
        innerContainer.setAlignment(Pos.CENTER);
        Label pizza = new Label("Pizza");
        Button buy = new Button("Buy");
        buy.setOnMouseClicked((event) -> { 
            GameSceneOrganizer.getPlayer().buyFood(20); // valor de prueba
            System.out.println("Compra exitosa");
        });
        innerContainer.getChildren().addAll(pizza, buy);
        container.getChildren().add(innerContainer);
        
        root.setTop(back);
        root.setCenter(container);
    }
    
    public BorderPane getRoot() { return root; }
}
