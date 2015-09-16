/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;

/**
 * This Snake Class extends the NPC; the random locations and characteristics of 
 * sprite images, health, armor, speed, attack, moveAtRest, and jump are implemented
 * in the game.
 */
public class Snake extends NPC{
 /**
 * The Snake is passed down locX and locY, which are used to define the location 
 * of the Mummy. The size is also defined.
 */
    public Snake(int locX, int locY) {
        super(new int[] {37,38});
        this.setLoc(new int[] {locX,locY,0,0});
        this.setSize(new int[] {44,18});
        
        this.restImage = 0;
        this.moveImages = new int[] {0,1};
        this.jumpImage = 0;
        this.attackImages = new int[] {1};
        
        this.mass = 15;
        this.attackKnockback = 1000;
        
        this.armor = 0;
        this.health = 20;
        this.speed = 2;
        dammage = 5;
        
        this.jumpToAttack = true;
        this.attackCooldown = 10;
        this.sightDistance = 300;
        
        moveAtRest = false;
        
        isPlayer = true;
        
        jumpNumber = 1;
        maxJumpTime = 50;
        
        name = "Snake";
        
        sound = "snake.wav";
    }
 
}
