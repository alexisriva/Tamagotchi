/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

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
    
    public Pet getPet() { return pet; }
    
    public void setPet(Pet pet) { this.pet = pet; }
    
    public String getName() { return username; }
    
    public void setUserName(String username) { this.username = username; }
}
