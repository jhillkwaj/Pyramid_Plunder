/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;

import java.awt.Graphics;

/**
 * This Sword class extends Weapons class; the various characteristics of the 
 * sword are implemented, the sword is drawn here through the Draw method and
 * Graphics g.
 */
public class Sword extends Weapon {
/**
 * Sword here is passed down the int damage, used for the attack; other characteristics
 * like range and hits are implemented. 
     * @param damage
 */      
    public Sword(int damage)
    {
        damageEnemies = true;
    
        rangeX = 25;
        rangeY = 60;
    
        this.damage = damage;
    
        multiHit = true;
    
        cooldownTime = 20;
        cooldown = 0;
    
        graphics = new int[] {50,51,52};
        graphicToDraw = -1;
        
        type = "Sword";
    }
/**
 * This Sword is passed down int graphis, int damage, int rangeX, int rangeY, int
 * speed, and String name; through the use of these, the sword is given more 
 * characteristics such as range, type, damage, multihit, and cool down time.
     * @param graphics
     * @param damage
     * @param rangeX
     * @param rangeY
     * @param speed
     * @param name
 */      
    public Sword(int[] graphics, int damage, int rangeX, int rangeY, int speed, String name)
    {
        damageEnemies = true;
    
        this.rangeX = rangeX;
        this.rangeY = rangeY;
    
        this.damage = damage;
    
        multiHit = true;
    
        cooldownTime = speed;
        cooldown = 0;
    
        this.graphics = graphics;
        graphicToDraw = -1;
        
        type = "Sword";
        
        this.name = name;
    }
/**
 * This Draw method draws the sword in the specific locations and implements 
 * the boundaries for the location. The sword is also mirrored with the sprite for the sword.
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
                g.drawImage(GraphicsManager.getGraphics(graphics[graphicToDraw/3]), drawLocationX, drawLocationY, null);
            }
            else
            {
                drawLocationX = p.getLoc()[0] - 20 - frameLoc[0];
                drawLocationY = p.getLoc()[1] - frameLoc[1] + 7;
                g.drawImage(GraphicsManager.getGraphics(graphics[graphicToDraw/3]), drawLocationX, drawLocationY, drawLocationX+33, drawLocationY+33, 33, 0, 0 , 33,null);
            }

            //g.drawImage(GraphicsManager.getGraphics(graphics[graphicToDraw]), drawLocationX, drawLocationY, null);
            
            graphicToDraw++;
            
            if(graphicToDraw/3 == 3)
            {
               graphicToDraw = -2; 
            }
    }
    }
    
/**
 * This Weapon randomWeapon randomly generates the various swords across the 
 * game and in the shop; there are 50 Types of Sword from which a random selection
 * can occur. In addition to this, the damage, speed, and range are also random to
 * an extent.
     * @return the random sword
 */     
    public static Weapon randomWeapon()
    {
        //prefix type suffix
        String[][] names = { {"Broken", "Wooden", "Bent", "Bronze","Rusted", "Gold", "Weak", "Discarded", "Subpar", "Old",  "Ceremonial", "Dull", "Repaired",
            "Dirty", "Iron", "Average", "Worldly", "Ornate","Blessed", "Guilded", "Steel","Sharp", "Well Made","Strong", "Pure", "Superb", "Stunning", "Proven",
            "Holy", "Flaming", "Mythic", "The" ,"Legendary", "Magic", "Magical", "Infernal", "Demonic","Godlike"},
            {"Dagger", "Shortsword", "Sword", "Broadsword", "Longsword"},
            {"Of The Gods", "Of Ra", "Of Egypt", "Of the Nile", "Of Hours"} };
        Sword s = null;
        String name = "";
        int damage = (int) (Math.random()*16);
        int rangeX = 25;
        int rangeY = 60;
        int speed = 20;
                
        
        if(Math.random()<.65)
        {
            int size = names[0].length;
            int random = (int)(size*Math.random());
            damage += random-15;
            name+=names[0][random]+" ";
        }
        
        rangeX+=(Math.random()*11)-5;
        rangeY+=(Math.random()*21)-10;
        
        if(true)
        {
        int size = names[1].length;
        int random = (int)(size*Math.random());
        rangeX += (random-2)*7;
        rangeY += (random-2)*7;
        speed += (random-2)*5;
        name+=names[1][random];
        }
        
        speed+= (int)(Math.random()*11)-5;

        
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
        
        int[] graphics = null;
        double randomGraphics = Math.random();
        if(name.contains(" ")==false)
        {
            graphics = new int[]{50,51,52};
        }
        else if(randomGraphics < (1.0/6.0)*1)
        {
            graphics = new int[]{43,44, 45};
        }
        else if(randomGraphics < (1.0/6.0)*2)
        {
            graphics = new int[]{46,47,48};
        }
        else if(randomGraphics < (1.0/6.0)*3)
        {
            graphics = new int[]{50,51,52};
        }
        else if(randomGraphics < (1.0/6.0)*4)
        {
            graphics = new int[]{54,55,56};
        }
        else if(randomGraphics < (1.0/6.0)*5)
        {
            graphics = new int[]{57,58,59};
        }
        else
        {
            graphics = new int[]{50,51,52};
        }
        
        s = new Sword(graphics, damage,rangeX,rangeY,speed,name);
        return s;
    }
}

