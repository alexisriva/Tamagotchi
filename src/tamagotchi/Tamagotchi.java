/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author USER
 */
public class Tamagotchi extends Application {
    
    private static HomeSceneOrganizer homeScene;
    private static PickerSceneOrganizer pickerScene;
    private static GameSceneOrganizer gameScene;
    private static FoodShopOrganizer shopScene;
    private static MiniGameOrganizer miniGameScene;
    private static Scene scene;
    private static Thread game;
    
    @Override
    public void start(Stage primaryStage) {
        homeScene = new HomeSceneOrganizer(primaryStage);
        
        scene = new Scene(homeScene.getRoot(), 900, 600);
//        scene.getStylesheets().add("style.css");
        primaryStage.setTitle("Virtual Pet");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { launch(args); }
    
    public static void changePickerScene() {
        pickerScene = new PickerSceneOrganizer();
        scene.setRoot(pickerScene.getRoot());
    }
    
    public static void changeGameScene() {
        gameScene = new GameSceneOrganizer();
        scene.setRoot(gameScene.getRoot());
        game = new Thread(gameScene);
        game.setDaemon(true);
        game.start();
        
    }
    
    public static void changeShopScene() {
        shopScene = new FoodShopOrganizer();
        scene.setRoot(shopScene.getRoot());
    }
    
    public static void returnGameScene() {
        scene.setRoot(gameScene.getRoot());
    }
    public static void changeMiniGameScene(){
        miniGameScene = new MiniGameOrganizer();
        scene.setRoot(miniGameScene.getRoot());
    }
    
}
