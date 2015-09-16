/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;

/**
 * This Bat Class extends NPC; the location, size, health, attack, speed, damage, 
 * flight, and sightDistance of the Bat is implemented here in the game. 
 */
public class Bat extends NPC {
  
/**
 * This Bat Class extends NPC; the location, size, health, attack, speed, damage, 
 * flight, and sightDistance of the Bat is implemented here in the game. The 
 * various sprite are also called here according to the game. 
     * @param locX
     * @param locY
 */
    
    public Bat(int locX, int locY) {
        
        super(new int[] {39,40});
        try
        {
        this.setLoc(new int[] {locX,locY,0,0});
        this.setSize(new int[] {41,18});
        
        this.restImage = 0;
        this.moveImages = new int[] {0,1};
        
        this.mass = 15;
        this.attackKnockback = 1000;
        
        this.armor = 0;
        this.health = 10;
        this.speed = 6;
        dammage = 3;
        
        this.jumpToAttack = true;
        this.attackCooldown = 5;
        this.sightDistance = 200;
        
        moveAtRest = false;
        
        isPlayer = true;
        
        jumpNumber = 3;
        maxJumpTime = 0;
        
        moveInAir = true;
        
        name = "Bat";
        
        sound = "Bat_Fly.wav";
        }
        catch(Exception e)
        {
            ErrorLogger.logError("Can't make a bat " + e);
        }
    }
}