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

/**
 *
 * @author steevenrodriguez
 */
public class FoodLoadShopOrganizer {
    private BorderPane root;
    
    public FoodLoadShopOrganizer() {
        root = new BorderPane();
        
        Button back = new Button("Back");
        back.setOnMouseClicked((event) -> {
            Tamagotchi.returnLoadGameScene();
        });
        back.setAlignment(Pos.CENTER_LEFT);
        VBox container = new VBox();
        container.setAlignment(Pos.CENTER);
        HBox innerContainer = new HBox();
        innerContainer.setAlignment(Pos.CENTER);
        Label pizza = new Label("Pizza");
        Button buy = new Button("Buy");
        buy.setOnMouseClicked((event) -> { 
            LoadGameOrganizer.getPlayer().buyFood(20); // valor de prueba
            System.out.println("Compra exitosa");
        });
        Label lasagna = new Label("Lasagna");
        Button buyLasagna = new Button("Buy");
        buyLasagna.setOnMouseClicked((event) -> {
            LoadGameOrganizer.getPlayer().buyFood(40);
        });
        HBox innerContainer2 = new HBox();
        innerContainer2.setAlignment(Pos.CENTER);
        innerContainer.getChildren().addAll(pizza, buy);
        innerContainer2.getChildren().addAll(lasagna,buyLasagna);
        container.getChildren().addAll(innerContainer,innerContainer2);
        
        root.setTop(back);
        root.setCenter(container);
    }
    
    public BorderPane getRoot() { return root; }
}
