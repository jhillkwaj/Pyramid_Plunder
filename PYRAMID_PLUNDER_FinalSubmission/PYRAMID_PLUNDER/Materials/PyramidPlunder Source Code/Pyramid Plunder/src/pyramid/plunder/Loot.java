/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;

import java.awt.Graphics;

/**
 * This Loot Class extends the PhysicsObject; the image, the weapon, the int location
 * and the int maxValue are implemented; the playability of the loot is also 
 * made.
 */
public class Loot extends PhysicsObject {
    private Weapon weapon = null;
    private boolean active = true;

/**
 * The int location, int image, Weapon weapon are passed down to Loot; the location
 * is make and the set size is also implemented.
     * @param location
     * @param image
     * @param weapon
 */
    public Loot(int[] location, int[] image, Weapon weapon)
    {
       super(image, false);
       this.weapon = weapon;
       setInteractPhysics(false);
       this.setLoc(new int[] {location[0], location[1]-20,0,0});
       this.setSize(new int[] {30,30});
    }
/**
 * This int location and int maxValue are passed down to Loot; the graphics, 
 * location, and size are implemented.
     * @param location
     * @param maxValue
 */    
    public Loot(int[] location, int maxValue)
    {
       super(new int[] {1}, false);
       this.weapon = Weapon.randomWeapon();
       while(Weapon.priceWeapon(weapon)>maxValue)
       {
           this.weapon = Weapon.randomWeapon();
       }
       this.imageKeys=new int[] {weapon.graphics[weapon.graphics.length/2]};
       setInteractPhysics(false);
       this.setLoc(new int[] {location[0], location[1]-20,0,0});
       this.setSize(new int[] {30,30});
    }
/**
 * The GameObjecrt g is passed down to this boolean collide method; the sound is 
 * played during the effect of the playability of the game. THe playability is
 * set.
     * @param g
     * @return if the object collided
 */    
    @Override
    public boolean collide(GameObject g)
    {
        if(active && g.getKey() == GameRunner.getPlayerKey())
        {
            Music.playSound("Jump.wav", .5f);
            //add weapon
            if(((Player)g).getHealth()>0)
            {
                GameRunner.addWeapon(weapon);
                active = false;
            }
        }
        return false;
    }
 /**
 * The Graphics g is passed down the Draw Method. 
 */   
    @Override
    public void Draw(Graphics g) {
        if(active)
            super.Draw(g);
    }
}
