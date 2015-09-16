/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;

import java.awt.Graphics;

/**
 * This HealthOrb Class extends PhysicsObjecs; sets the location, implements game usage, 
 * and draws the orb. 
 */
public class HealthOrb extends PhysicsObject {
     private boolean active = true;
/**
 * HealthOrb is passed down the location, from an array, and sets the size and 
 * location of the orb according to the game. 
     * @param location
 */     
    public HealthOrb(int[] location)
    {
       super(new int[] {2}, false);
       setInteractPhysics(false);
       this.setLoc(new int[] {location[0], location[1]-20,0,0});
       this.setSize(new int[] {10,10});
    }
 /**
 * This collide boolean method is passed down GameObject, which makes the orb
 * usable in the game.
     * @param g
     * @return if there was a collision
 */       
     @Override
    public boolean collide(GameObject g)
    {
        if(active && g.getKey() == GameRunner.getPlayerKey())
        {
            Music.playSound("Jump3.wav", .5f);
            //add health
            if(((Player)g).getHealth()<110)
            {
                ((Player)g).addHealth(10);
                active = false;
            }
        }
        return false;
    }
/**
 * This Draw method is passed down Graphics g and draws the orb. 
 */     
     @Override
    public void Draw(Graphics g) {
        if(active)
            super.Draw(g);
    }
}
