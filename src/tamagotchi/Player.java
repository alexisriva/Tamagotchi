/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import java.util.LinkedList;
import javafx.scene.layout.Pane;

/**
 *
 * @author USER
 */
class Player {
    private Pet pet;
    private String username;
    
    public Player() {}
    
    public Player(Pet pet, String username) {
        this.pet = pet;
        this.username = username;
    }
    
    public void cleanPet(LinkedList<Pane> poops) {
        poops.forEach((pane) -> {
            poops.remove(pane);
        });
        pet.setClean(Constants.MAXCLEAN);
    }
    
    public void playWithPet() {
        
    }
    
    public void feedPet() {
        if (pet.getHunger() < Constants.MAXHUNGER && pet.getInventory().size() > 0) {
            pet.getInventory().remove(0); //valor de prueba
            pet.setHunger(pet.getHunger() + 2); //valor de prueba
        } else { System.out.println("Your pet cant be feed"); }
    }
    
    public void buyFood(int fee) {
        if (pet.getMoney() - fee >= 0) {
            pet.setMoney( pet.getMoney() - fee);
            pet.addInventory("I bought something!");
        } else { System.out.println("Sorry, you dont have enough money"); }
    }
    
    public Pet getPet() { return pet; }
    
    public void setPet(Pet pet) { this.pet = pet; }
    
    public String getName() { return username; }
    
    public void setUserName(String username) { this.username = username; }
}
