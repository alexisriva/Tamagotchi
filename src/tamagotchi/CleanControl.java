/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

/**
 *
 * @author USER
 */
public class CleanControl extends TimerTask {
    private Pet pet;
    private Pane pane;
    
    public CleanControl(Pet pet, Pane pane) {
        this.pet = pet;
        this.pane = pane;
    }
    
    public void run () {
        Platform.runLater(() -> {
        pet.poop(pane);
            System.out.println("poop");
        });
    }
}
