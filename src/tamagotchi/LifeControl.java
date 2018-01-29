/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import java.util.TimerTask;
//import java.util.Timer;

/**
 *
 * @author USER
 */
public class LifeControl extends TimerTask {
    private Pet pet;
    
    public LifeControl(Pet pet) {
        this.pet = pet;
    }
    
    public void run() {
        pet.setHunger(pet.getHunger()-1);
        pet.setHappiness(pet.getHappiness()-1);
        pet.setClean(pet.getClean()-1);
    }
}

//Timer timer = new Timer();
//timer.schedule(new LifeControl(), 0, Constants.SECONDSTODECREASE*1000);
