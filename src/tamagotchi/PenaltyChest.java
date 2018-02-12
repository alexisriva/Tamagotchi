/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.layout.Pane;

/**
 *
 * @author steevenrodriguez
 */
public class PenaltyChest extends TreasureChest {
    
    public PenaltyChest(Pane rootPane) {
        super(rootPane);
        int randomNum = ThreadLocalRandom.current().nextInt(25,100 + 1);
        this.money= randomNum;
        this.description= "Haz Perdido "+ this.money+" Dolares!";
    }
    
}
