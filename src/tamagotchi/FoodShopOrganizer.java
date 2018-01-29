/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import javafx.scene.layout.BorderPane;

/**
 *
 * @author USER
 */
public class FoodShopOrganizer {
    private BorderPane root;
    
    public FoodShopOrganizer() {
        root = new BorderPane();
    }
    
    public BorderPane getRoot() { return root; }
}
