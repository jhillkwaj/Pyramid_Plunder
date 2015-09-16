/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;


public class Ghost extends NPC{
 /**
 * This Ghost Class extends NPC; the location, size, health, attack, speed, damage, 
 * flight, and sightDistance of the Bat is implemented here in the game. The 
 * various sprite are also called here according to the game. 
     * @param locX
     * @param locY
 */      
    public Ghost(int locX, int locY) {
        
        super(new int[] {49,11,4});
        try
        {
        this.moveImages = new int[]  {0,0,0,0,0,2,1,1,1,2,1,1,1,2,0,0,0};
        this.setLoc(new int[] {locX,locY,0,0});
        this.setSize(new int[] {52,62});
        
        this.restImage = 0;
        
        this.mass = 15;
        this.attackKnockback = 1000;
        
        this.armor = 30;
        this.health = 25;
        this.speed = 4;
        dammage = 6;
        
        this.jumpToAttack = true;
        this.attackCooldown = 10;
            this.sightDistance = 1000;
        
        moveAtRest = false;
        
        isPlayer = true;
        
        jumpNumber = 3;
        maxJumpTime = 0;
        
        moveInAir = false;
        
        name = "Ghost";
        
        sound = "Walk3.wav";
        }
        catch(Exception e)
        {
            ErrorLogger.logError("Can't make a Ghost " + e);
        }
    }
}
