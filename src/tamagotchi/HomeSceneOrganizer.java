/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author USER
 */
public class HomeSceneOrganizer {
    private BorderPane root;
    
    public HomeSceneOrganizer(Stage stage) {
        root = new BorderPane();
        root.setId("root");
        
        Label game = new Label("Your Virtual Pet");
        game.setTextAlignment(TextAlignment.CENTER);
        
        VBox container = new VBox();
        container.setAlignment(Pos.CENTER);
        container.setSpacing(20);
        
        Button btnPlay = new Button("Play");
        Button btnQuit = new Button("Quit");
        
        container.getChildren().addAll(game, btnPlay, btnQuit);
        
        btnPlay.setOnMouseClicked((event) -> {
            Tamagotchi.changePickerScene();
        });
        
        btnQuit.setOnMouseClicked( (event) -> {
            stage.close();
        });
        
        root.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.S)
                Tamagotchi.changePickerScene();
            else if (event.getCode() == KeyCode.Q)
                stage.close();
        });
        
        root.setCenter(container);
    }
    
    public BorderPane getRoot() { return root; }
}
