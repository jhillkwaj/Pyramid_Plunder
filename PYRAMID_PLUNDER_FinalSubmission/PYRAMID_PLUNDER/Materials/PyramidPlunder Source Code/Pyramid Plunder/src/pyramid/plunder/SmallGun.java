/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;

import java.awt.Graphics;

/**
 * This SmallGun class extends the Weapon Class; where the characteristics of 
 * the small gun are implemented and where the Draw Method draws the gun and 
 * defines the location with the sprite.
 */
public class SmallGun extends Weapon {
/**
 * The Small Gun is passed down int damage, the range of 300 by 300 is defined,
 * and other characteristics of multihit and and damage to enemies is implemented. 
     * @param damage
 */      
    public SmallGun(int damage)
    {
        damageEnemies = true;
    
        rangeX = 300;
        rangeY = 300;
    
        this.damage = damage;
    
        multiHit = false;
    
        cooldownTime = 100;
        cooldown = 0;
    
        graphics = new int[] {53};
        graphicToDraw = -1;
        
        type = "Gun";
    }
    
    public SmallGun(int damage, int x, int y, int speed, String name)
    {
        damageEnemies = true;
    
        rangeX = x;
        rangeY = y;
    
        this.damage = damage;
    
        multiHit = false;
    
        cooldownTime = speed;
        cooldown = 0;
    
        graphics = new int[] {53};
        graphicToDraw = -1;
        
        type = "Gun";
        
        this.name = name;
    }
/**
 * This Draw method is passed down the Graphics g, defines the location, and draws
 * the gun at the locations with sprite.
     * @param g
 */      
    @Override
    public void Draw(Graphics g)
    {
        super.Draw(g);
        if(graphicToDraw > -1)
        {
            Player p = GameRunner.getPlayer();
            int[] frameLoc = GameRunner.getFrameLoc();
            int drawLocationX = -999;
            int drawLocationY = -999;
        
            if(!p.isMirrored())
            {
                drawLocationX = p.getLoc()[0] + p.getSize()[0] - 10 - frameLoc[0];
                drawLocationY = p.getLoc()[1] - frameLoc[1] + 7;
                g.drawImage(GraphicsManager.getGraphics(graphics[graphicToDraw/5]), drawLocationX, drawLocationY, null);
            }
            else
            {
                drawLocationX = p.getLoc()[0] - 20 - frameLoc[0];
                drawLocationY = p.getLoc()[1] - frameLoc[1] + 7;
                g.drawImage(GraphicsManager.getGraphics(graphics[graphicToDraw/5]), drawLocationX, drawLocationY, drawLocationX+33, drawLocationY+33, 33, 0, 0 , 33,null);
            }

            //g.drawImage(GraphicsManager.getGraphics(graphics[graphicToDraw]), drawLocationX, drawLocationY, null);
            
            graphicToDraw++;
            
            if(graphicToDraw/5 == 1)
            {
               graphicToDraw = -1; 
            }

            
            
        
       
    }
    }
/**
 * This Weapon randomWeapon randomly generates the various guns across the 
 * game and in the shop; there are 50 Types of Guns from which a random selection
 * can occur. In addition to this, the damage, speed, and range are also random to
 * an extent.
     * @return the generated weapon
 */         
    public static Weapon randomWeapon()
    {
        //prefix type suffix
        String[][] names = { {"Broken", "Wooden", "Bent", "Antique","Rusted", "Gold", "Weak", "Discarded", "Subpar", "Old",  "Ceremonial", "Surplus", "Repaired",
            "Dirty", "Lightweight", "Average", "Worldly", "Ornate","Blessed", "Guilded", "Hefty","Powerful", "Well made","Strong", "Pure", "Superb", "Stunning", "Proven",
            "Holy", "Flaming", "Mythic", "The" ,"Legendary", "Magic", "Magical", "Infernal", "Demonic","Godlike"},
            {"Repeater", "Revolver", "Rifle", "Hand Cannon", "Sniper"},
            {"Of The Gods", "Of Ra", "Of Egypt", "Of the Nile", "Of Hours"} };
        SmallGun s = null;
        String name = "";
        int damage = (int) (Math.random()*12);
        int rangeX = 200;
        int rangeY = 200;
        int speed = 100;
                
        
        if(Math.random()<.65)
        {
            int size = names[0].length;
            int random = (int)(size*Math.random());
            damage += random-15;
            name+=names[0][random]+" ";
        }
        
        rangeX+=(Math.random()*201)-100;
        rangeY+=(Math.random()*201)-100;
        
        if(true)
        {
        int size = names[1].length;
        int random = (int)(size*Math.random());
        rangeX += (random-2)*50;
        rangeY += (random-2)*50;
        speed += (random-2)*30;
        name+=names[1][random];
        }
        
        speed+= (int)(Math.random()*61)-30;

        
        if(Math.random()<.2)
        {
            int size = names[2].length;
            int random = (int)(size*Math.random());
            damage += Math.random()*11;
            name+=" "+names[2][random];
        }
        
        if(damage <= 0)
        {
            damage = 2;
        }
        
        if(speed<0)
        {
            speed = 5;
        }
        
        s = new SmallGun(damage,rangeX,rangeY,speed,name);
        return s;
    }
}
