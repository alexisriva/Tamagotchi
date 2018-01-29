/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import java.util.TimerTask;

/**
 *
 * @author USER
 */
public class PetLifeControl extends TimerTask {
    private static Pet pet;
    private static boolean firstHungerZero = true;
    private static boolean firstHappinessZero = true;
    public static boolean firstCleanZero = true;
    
    public PetLifeControl(Pet pet) {
        this.pet = pet;
    }
    
    public void run() {
        pet.setLife(pet.getLife()-1);
    }
    
    public static boolean verifyHungerStatus() { return pet.getHunger() == 0; }
    
    public static void decreaseBcOfHunger(Pet pet) {
        if (verifyHungerStatus() && firstHungerZero) {
            pet.setLife(pet.getLife()-100);
            firstHungerZero = false;
        }
    }
    
    public static boolean verifyHappinessStatus() { return pet.getHunger() == 0; }
    
    public static void decreaseBcOfHappiness(Pet pet) {
        if (verifyHappinessStatus() && firstHappinessZero) {
            pet.setLife(pet.getLife()-100);
            firstHappinessZero = false;
        }
    }
    
    public static boolean verifyCleanStatus() { return pet.getHunger() == 0; }
    
    public static void decreseBcOfClean(Pet pet) {
        if (verifyCleanStatus() && firstCleanZero) {
            pet.setLife(pet.getLife()-100);
            firstCleanZero = false;
        }
    }
}
