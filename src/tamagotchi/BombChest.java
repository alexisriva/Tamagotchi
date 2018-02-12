/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import javafx.scene.layout.Pane;

/**
 *
 * @author steevenrodriguez
 */
public class BombChest extends TreasureChest {
    private boolean stop;
    
    public BombChest(Pane rootPane) {
        super(rootPane);
        this.stop=true;
    }

    /**
     * @return the stop
     */
    public boolean isStop() {
        return stop;
    }

    /**
     * @param stop the stop to set
     */
    public void setStop(boolean stop) {
        this.stop = stop;
    }
    
}
