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
    private static FoodLoadShopOrganizer shopScene2;
    private static MiniGameOrganizer miniGameScene;
    private static MiniLoadGameOrganizer miniGameScene2;
    private static LoadGameOrganizer loadGameScene;
    private static Scene scene;
    private static Thread game;
    private static Thread gameLoad;
    
    @Override
    public void start(Stage primaryStage) {
        homeScene = new HomeSceneOrganizer(primaryStage);
        
        scene = new Scene(homeScene.getRoot(), 800, 650);
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
    public static void changeLoadGameScene() {
        loadGameScene = new LoadGameOrganizer();
        scene.setRoot(loadGameScene.getRoot());
        gameLoad = new Thread(loadGameScene);
        gameLoad.setDaemon(true);
        gameLoad.start();
            
    }
    public static void returnLoadGameScene() {
        scene.setRoot(loadGameScene.getRoot());
    }
    public static void changeLoadShopScene() {
        shopScene2 = new FoodLoadShopOrganizer();
        scene.setRoot(shopScene2.getRoot());
    }
    public static void changeMiniLoadGameScene(){
        miniGameScene2 = new MiniLoadGameOrganizer();
        scene.setRoot(miniGameScene2.getRoot());
    }
}
