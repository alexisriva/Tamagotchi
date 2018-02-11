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
public class MultiplierChest extends TreasureChest {
    
    public MultiplierChest(Pane rootPane) {
        super(rootPane);
        int randomNum = ThreadLocalRandom.current().nextInt(1,3 + 1);
        this.multiplier=randomNum;
        this.description = "Multiplier: "+this.multiplier+"X";
    }
    
}
