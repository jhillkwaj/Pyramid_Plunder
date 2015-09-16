/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid.plunder;


public class Skeleton extends NPC {
    /**
 * This Ghost Class extends NPC; the location, size, health, attack, speed, damage, 
 * flight, and sightDistance of the Bat is implemented here in the game. The 
 * various sprite are also called here according to the game. 
     * @param locX
     * @param locY
 */      
    public Skeleton(int locX, int locY) {
        
        super(new int[] {73,74,75,76,77});
        try
        {
        this.moveImages = new int[]  {1,2};
        this.attackImages = new int[] {3,4};
        this.restImage = 0;
        this.setLoc(new int[] {locX,locY,0,0});
        this.setSize(new int[] {27,33});
        
        this.restImage = 0;
        
        this.mass = 15;
        this.attackKnockback = 1000;
        
        this.armor = 5;
        this.health = 25;
        this.speed = 4;
        dammage = 15;
        
        this.jumpToAttack = true;
        this.attackCooldown = 10;
        this.sightDistance = 300;
        
        moveAtRest = false;
        
        isPlayer = true;
        
        jumpNumber = 1;
        maxJumpTime = 0;
        
        moveInAir = false;
        
        name = "Skeleton";
        
        sound = "Walk3.wav";
        }
        catch(Exception e)
        {
            ErrorLogger.logError("Can't make a Skeleton " + e);
        }
    }
}
