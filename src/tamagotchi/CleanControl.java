/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import java.util.LinkedList;
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
    private LinkedList list;
    
    public CleanControl(Pet pet, Pane pane, LinkedList list) {
        this.pet = pet;
        this.pane = pane;
        this.list = list;
    }
    
    public void run () {
        Platform.runLater(() -> {
            pet.poop(pane, list);
            System.out.println("poop");
        });
    }
}
