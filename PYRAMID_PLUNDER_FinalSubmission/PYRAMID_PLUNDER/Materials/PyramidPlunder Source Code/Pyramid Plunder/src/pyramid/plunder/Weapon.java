/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;

import java.awt.Graphics;
import java.util.ArrayList;
import static pyramid.plunder.GameRunner.weapons;

/**
 * In this weapon class, the multiple randomly generated weapons
 * are selected and available for the user to use; gives characteristics for the weapons being used; 
 * defines the size and locations of the weapons while setting the attack damage.
 */     
public abstract class Weapon {
    
    protected boolean damageEnemies = true;
    
    protected int rangeX = 20;
    protected int rangeY = 50;
    
    protected int damage = 100;
    
    protected boolean multiHit = false;
    
    protected int cooldownTime = 20;
    protected int cooldown = 0;
    
    protected int[] graphics;
    int graphicToDraw = -1;
    
    protected String name = "";
    protected String type = "";
/**
 * This boolean method use gives characteristics for the weapons being used; 
 * it defines the keys needed to be used in order for the user to use the spirte 
 * of the weapon. It defines the size and locations of the weapons while setting
 * the attack damage.
     * @return 
 */     
    public boolean use()
    {
        try
        {
        if(cooldown <= 0)
        {
            graphicToDraw = 0;
        cooldown = cooldownTime;
        Player player = GameRunner.getPlayer();
        ArrayList<PhysicsObject> c = GameRunner.getCollidables();
        for(int i = 0; i < c.size(); i++)
        {
            if(c.get(i).isPlayer && damageEnemies && c.get(i).getKey()!=player.getKey() && ((!player.isMirrored() &&
                    inRange(c.get(i),new int[] {player.getLoc()[0]+
                            (player.getSize()[0]), player.getLoc()[1] +
                                    (player.getSize()[1]/2) - (rangeY/2)},
                            new int[] {rangeX,rangeY})) || (player.isMirrored()
                    && inRange(c.get(i),new int[] {player.getLoc()[0] - (rangeX/2),
                        player.getLoc()[1] + (player.getSize()[1]/2) -
                                (rangeY/2)}, new int[] {rangeX,rangeY})) ))
            {
                ((Player)c.get(i)).addHealth(damage*-1);
                Music.playSound("Attack_Main.wav", .6f);
                if(!multiHit)
                { return true; }
            }
        }
        }
        return false;
        }
        catch(Exception e)
        {
            ErrorLogger.logError("Could not use weapon");
        }
        return false;
    }
/**
 * This boolean method is passed down PhysicsObbject p, int playerLoc, and int
 * playerSize; here the location and size of the weapon are
     * @param p given boundaries.
     * @param playerLoc
     * @param playerSize
     * @return if the target is in range
 */     
    public boolean inRange(PhysicsObject p, int[] playerLoc, int[] playerSize)
    {
        int[] locOne = p.getLoc();
        int[] sizeOne = p.getSize();
                
        if(locOne[0]+sizeOne[0]>playerLoc[0]&&locOne[0]<playerLoc[0]+playerSize[0])
        {
            if(locOne[1]+sizeOne[1]>playerLoc[1]&&locOne[1]<playerLoc[1]+playerSize[1])
            {
                return true;
            }
        }
        return false;
    }
/**
 * This Draw Method is passed down Graphics g; cooldown is used.
     * @param g
 */        
    public void Draw(Graphics g)
    {
        cooldown--;
    }
/**
 * This Draw Method is passed down Graphics g; various mathematical computations
 * are made for the random weapon to be selected.
     * @return a random weapon
 */    
    public static Weapon randomWeapon()
    {
        double rand = Math.random();
        Weapon w = null;
        if(Math.random()<.5)
        {
        w = Sword.randomWeapon();
        }
        else
        {
            w = SmallGun.randomWeapon();
        }
        return w;

    }
    
/**
 * This int priceWeapon method is passed down Weapon w; various mathematical 
 * computations are made through the float and pow; when the numbers correctly 
 * match the weapon, the specific weapon is selected.
     * @param w
     * @return the value of the weapon
 */    
    public static int priceWeapon(Weapon w)
    {
        int price = 0;
        price = (int)Math.pow(w.damage,1.5)*(int)((1f/((float)w.cooldownTime))*200)*(int)(w.rangeX*w.rangeY/200);
        if(w.type.equals("Sword"))
        {
            price*=2;
        }
        return price/50;
    }
}
