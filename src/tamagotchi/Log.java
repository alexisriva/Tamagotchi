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
public class Log {
    String player;
    String petName;
    String petBirthInfo;
    String petDeathInfo;
    
    public Log(String player, String petName, String petBirthInfo, String petDeathInfo) {
        this.player = player;
        this.petName = petName;
        this.petBirthInfo = petBirthInfo;
        this.petDeathInfo = petDeathInfo;
    }
    
    public String toString() {
        return this.player + "-" + this.petName + ":" + this.petBirthInfo + "-" + this.petDeathInfo;
    }
}
