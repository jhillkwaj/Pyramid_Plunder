/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid.plunder;

import java.awt.Graphics;

/**
 * This Sword class extends PhysicsObject class; the various characteristics of the 
 * player are implemented, the player is drawn here through the Draw method and
 * Graphics g, other important characteristics are also developed.
 */
public class Player extends PhysicsObject {
    
    private boolean walking = false;
    
    protected int health = 100;
    
    protected int armor = 0;
    
    protected boolean inAttack = false;
    
    protected int attackKnockback = 2000;
    
    protected int mass = 100;
    
    protected int attackCooldown = 5;
    protected int cooldown = 0;
    
    protected int dammage = 10;
    
    protected int jumpNumber = 2;
    protected int jumpTime = 0;
    protected int maxJumpTime = 0;
    
    private Weapon weapon;
/**
 * This Player class extends Weapons class; the various characteristics of the 
 * sword are implemented, the sword is drawn here through the Draw method and
 * Graphics g.
     * @param imageKeys
 */   
    public Player(int[] imageKeys)
    {
        super(imageKeys);
        setSize(new int[] {24,38});
        //setLoc(new int[] {0,-10,Integer.MIN_VALUE,Integer.MIN_VALUE});
        setLoc(new int[] {-2050,1820,Integer.MIN_VALUE,Integer.MIN_VALUE});
        isPlayer = true;
    }
    
    public Player(int[] imageKeys, int sizeX, int sizeY)
    {
        super(imageKeys);
        setSize(new int[] {sizeX,sizeY});
    }
    
    public void setWeapon(Weapon w)
    {
        weapon = w;
    }
    
 /**
 * This Player Class extends the PhysicsObject class.
 */    
    @Override
    protected void switchImage()
    {
        if(walking==true)
        {
        if(walking&&Math.abs(getVelocity()[1])<=Math.abs(.8f)&&getJumps()==0)
        {
        if(++drawKey>=5)
            { drawKey=2; }

        }
        else if(getVelocity()[1]<0)
        {
            drawKey=1;
        }
        else
        {
            drawKey = 5;
        }
        }
        
    }
   /**
 * The user is able to jump through his/her avatar; the properties of velocity
 * and jumpTime are used.
 */    
    public void jump()
    {
       
       float[] velocity = getVelocity();
       if(getJumps()<jumpNumber&&jumpTime<=0)
       {
           if(getJumps()==0)
           { velocity[1]=0f; }
           float jumpVelocity = -4.0f - (2*getJumps());
           velocity[1] = jumpVelocity;
           addJump();
           setVelocity(velocity);
           jumpTime = maxJumpTime;
       }
    }
/**
 * This move method receives the int direction; the user is able to move his avatar 
 * through set and getVelocity.
     * @param direction
 */     
    public void move(int direction)
    {
        if(System.currentTimeMillis()-GameRunner.startTime > 1000)
        {
        if(direction<0)
        { mirror(true); }
        else
        { mirror(false); }
        
        float[] velocity = getVelocity();
       
       
           
           float moveVelocity = 2.0f*direction;
           velocity[0] = moveVelocity;

           setVelocity(velocity);
          
          walking = true;
        }
    }
 /**
 * This stopMove method uses the velocity for a stop and walking.
 */     
    public void stopMove()
    {
        float[] velocity = getVelocity();
       
        setVelocity(velocity);
        walking = false;
        drawKey=0;
        
    }
 /**
 * This stopMove method uses the velocity for a stop and walking.
     * @return the players health
 */      
    public int getHealth()
    {
        return health;
    }
 /**
 * This stopMove method uses the velocity for a stop and walking.
     * @param newHealth
 */      
    public void setHealth(int newHealth)
    {
         health = newHealth;
    }
 /**
 * This stopMove method uses the velocity for a stop and walking.
     * @param amount
 */      
    public void addHealth(int amount)
    {
        if(health>0)
        {
        while(amount<0 && Math.random() > Math.pow(1.0f/((double)(armor+1)),(1.0/3.0)))
        {
            amount=amount/2;
        }
            
        health += amount;
            if(health <= 0)
            {
                drop();
                if(this.getKey()==GameRunner.getPlayerKey())
                {
                    GameRunner.onScreenText = "DEAD";
                    OptionsMenu m = new OptionsMenu();
                }
            }
        }
        if(amount < 0 && this.getKey()==GameRunner.getPlayerKey())
        {
            Music.playSound("Attack_Other.wav", .7f);
        }
        
    }
 /**
 * This stopMove method uses the velocity for a stop and walking.
 */      
    public void drop()
    {
        if(this.getKey()!=GameRunner.getPlayerKey())
        {
        double rand = Math.random();
        if(rand < .5f)
        {
            Coin c = new Coin(new int[] {60,61,62,63,64,65,66,67},5);
            c.setLoc(new int[]{this.getLoc()[0],this.getLoc()[1]-10,0,0});
            c.setSize(new int[]{18,18});
            GameRunner.addCollidable(c);
            GameRunner.addDrawable(c);
        }
        else if(rand < .9f)
        {
            HealthOrb h = new HealthOrb(new int[]{this.getLoc()[0],this.getLoc()[1]-20,0,0});
            GameRunner.addCollidable(h);
            GameRunner.addDrawable(h);
        }
        else
        {
            Loot l = new Loot(new int[]{this.getLoc()[0],this.getLoc()[1]-20,0,0},LevelRunner.difficulty*75);
            GameRunner.addCollidable(l);
            GameRunner.addDrawable(l);
        }
        }
    }
 /**
 * This stopMove method uses the velocity for a stop and walking.
     * @return the players armor value
 */      
    public int getArmor()
    {
        return armor;
    }
  /**
 * This stopMove method uses the velocity for a stop and walking.
     * @param newArmor
 */     
    public void setArmor(int newArmor)
    {
         armor = newArmor;
    }
 /**
 * This stopMove method uses the velocity for a stop and walking.
     * @param amount
 */      
    public void addArmor(int amount)
    {
        armor += amount;
    }
  /**
 * This stopMove method uses the velocity for a stop and walking.
     * @return if the objects collided
 */    
    @Override
    public boolean collide(GameObject g)
    {
        if(this.health<=0 || (g.isPlayer && ((Player)g).getHealth()<=0))
        {
            return false;
        }
        if(g.isPlayer)
        {
            ((Player)g).collide(this);
        }
        return true;
    }
 /**
 * This stopMove method uses the velocity for a stop and walking.
 */      
    @Override
    public void Draw(Graphics g) {
        if(health > 0)
        {
            super.Draw(g);
            if(weapon != null)
            {
                weapon.Draw(g);
            }
        }
        
    }
  /**
 * This stopMove method uses the velocity for a stop and walking.
 */     
    public void useWeapon()
    {
        if(weapon != null)
        { 
            weapon.use();
        }
    }
}
