/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import javafx.scene.layout.Pane;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author steevenrodriguez
 */
public class PointChest extends TreasureChest {
    
    public PointChest(Pane rootPane) {
        super(rootPane);
        int randomNum = ThreadLocalRandom.current().nextInt(50,150 + 1);
        this.money= randomNum;
        this.description= "Haz Ganado "+ this.money+" Dolares!";
    }
    
}
