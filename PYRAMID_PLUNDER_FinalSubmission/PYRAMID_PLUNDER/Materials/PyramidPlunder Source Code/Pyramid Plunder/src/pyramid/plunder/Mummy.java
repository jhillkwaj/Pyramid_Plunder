/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;

/**
 * This Mummy Class extends the NPC; the random locations and characteristics of 
 * sprite images, health, armor, speed, attack, moveAtRest, and jump are implemented
 * in the game.
 */
public class Mummy extends NPC {
    
/**
 * The Mummy is passed down locX and locY, which are used to define the location 
 * of the Mummy. The size is also defined.
     * @param locX
     * @param locY
 */
    public Mummy(int locX, int locY) {
        
        super(new int[] {41,42});
        this.setLoc(new int[] {locX,locY,0,0});
        this.setSize(new int[] {46,54});
        
        this.restImage = 0;
        this.moveImages = new int[] {0,1};
        
        
        this.armor = 10;
        this.health = 50;
        this.speed = 3;
        dammage = 30;
        
        this.jumpToAttack = false;
        jumpMove = true;
        this.attackCooldown = 50;
        this.sightDistance = 100;
        
        moveAtRest = false;
        
        isPlayer = true;
        
        jumpNumber = 1;
        maxJumpTime = 0;
        
        moveInAir = true;
        
        name = "Mummy";
        
        sound = "Mummy_Footsteps.wav";
    }
}
