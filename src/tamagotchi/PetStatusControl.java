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
public class PetStatusControl extends TimerTask {
    private Pet pet;
    
    public PetStatusControl(Pet pet) {
        this.pet = pet;
    }
    
    public void run() {
        if (pet.getHunger()-1 >= 0)
            pet.setHunger(pet.getHunger()-1);
        else
            pet.setHunger(0);
        
        if (pet.getHappiness()-1 >=0)
            pet.setHappiness(pet.getHappiness()-1);
        else
            pet.setHappiness(0);
        
        if (pet.getClean()-1 >= 0)
            pet.setClean(pet.getClean()-1);
        else
            pet.setClean(0);
    }
}

//Timer timer = new Timer();
//timer.schedule(new LifeControl(), 0, Constants.SECONDSTODECREASE*1000);
