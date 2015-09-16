/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;

/**
 * This abstract NPC Class extends Player; the various characteristics for the 
 * player are implemented, such as speed, sightDistance, attack, movement, jumps, and
 * rest, move, and attack Images. 
 */
public abstract class NPC extends Player{

    protected float speed = 1f;
    protected float sightDistance = 300f;
    
    protected boolean attack = true;
    
    protected boolean moveAtRest = true;
    protected boolean moveToAttack = true;
    
    protected boolean fallAtRest = false;
    protected boolean fallToAttack = true;
    
    protected boolean jumpMove = false;
    protected boolean jumpToAttack = false;
    
    protected int restImage = -1;
    protected int[] moveImages = null;
    protected int[] attackImages = null;
    protected int jumpImage = -1;
    protected int imageSwichTime = 5;
    protected int imageSwich = 0;
    
    protected int imageNumber = 0;
    
    protected boolean attacking = false;
    
    protected boolean moveInAir = false;
    
    protected String sound = "";
    
/**
 * In this, the int imageKeys are passed and given to the super; the isPlayer is 
 * true.
     * @param imageKeys
 */    
    public NPC(int[] imageKeys) {
        super(imageKeys);
        isPlayer = true;
    }
/**
 * In this switchImage method, the player and multiple enemies are implemented to
 * attack and jump. The restImages and moveImages are implemented.
 */       
    @Override
    protected void switchImage()
    {
        move();
        if(!inAttack || attackImages == null)
        {
        if(Math.abs(getVelocity()[1]) >= 1f && jumpImage != -1) //if falling
        {
            this.drawKey = jumpImage;
        }
        else if(!moveAtRest && !attacking && Math.abs(getVelocity()[1]) < 1f&& restImage != -1) //if at rest
        {
            this.drawKey = restImage;
        }
        else if(moveImages != null) // moveing
        {
            //System.out.println(imageSwich + "  " + imageNumber);
            if(imageSwich == 0)
            {
            if(++imageNumber >= moveImages.length)
            {
                imageNumber = 0;
            }
            
            this.drawKey = moveImages[imageNumber];
            
            imageSwich = imageSwichTime;
            }
            else
            {
                imageSwich--;
                
            }
        }
        }
        else
        {
            if(attackImages != null && imageNumber + 1 < attackImages.length)
            {
                this.drawKey = attackImages[++imageNumber];
            }
            else
            {
                inAttack = false;
            }
            
        }
        
    }
 /**
 * In this move method, the player is implemented to move and jump across locations. 
 */       
    protected void move()
    {
        //System.out.println(getLoc()[0]+"  "+getLoc()[1]);
        cooldown--;
        if(!attacking) //if the player hasn't been seen
        {
            Player p = GameRunner.getPlayer();
            if(Math.sqrt(Math.pow(p.getLoc()[0] - this.getLoc()[0], 2) + Math.pow(p.getLoc()[0] - this.getLoc()[0], 2)) <= sightDistance) //look for the player
            {
                attacking = true;
                Music.playSound(sound, .5f);
            }
            
            if(moveAtRest) //move
            {
                
            }
        }
        else
        {
            Player p = GameRunner.getPlayer();
            //System.out.println(jumpTime);
            if(jumpToAttack && --jumpTime <= 0 && this.velocity[1] < 1f && Math.sqrt(Math.pow(p.getLoc()[0] + (p.getSize()[0]/2) - this.getLoc()[0] - (this.getSize()[0]/2), 2) + Math.pow(p.getLoc()[1] + (p.getSize()[1]/2) - this.getLoc()[1] - (this.getSize()[1]/2), 2)) <= 100)
            {
                jump();
            }
            else if(moveToAttack && (Math.abs(this.velocity[1]) < 1f || moveInAir))
            {
                //double d = Math.sqrt(Math.pow(p.getLoc()[0] - this.getLoc()[0], 2) + Math.pow(p.getLoc()[0] - this.getLoc()[0], 2));
                double dx = p.getLoc()[0] + (p.getSize()[0]/2) - this.getLoc()[0] - (this.getSize()[0]/2);
                double dy = p.getLoc()[1] + (p.getSize()[1]/2) - this.getLoc()[1] - (this.getSize()[0]/2);
                
                double angle = Math.atan(dy/dx);
                //System.out.println(dy+"/"+dx+" = "+angle);
                if(dx<0)
                {
                    this.mirror(false);
                    velocity[0] = -1*speed;
                }
                else
                {
                    this.mirror(true);
                    velocity[0] = speed;
                }
                           
            }
            if(Math.sqrt(Math.pow(p.getLoc()[0] - this.getLoc()[0], 2) + Math.pow(p.getLoc()[0] - this.getLoc()[0], 2)) > 400 + sightDistance) //look for the player
            {
                attacking = false;
            }
        }
    }
 /**
 * In this boolean collide method, the GameObject g is passed down. The player 
 * is implemented to jump and to attack through enemies and objects. 
     * @param g
     * @return if the object collided
 */      
    @Override
    public boolean collide(GameObject g)
    {
        if(this.health<=0 || (g.isPlayer && ((Player)g).getHealth()<=0))
        {
            return false;
        }
        if(g.getKey() == GameRunner.getPlayerKey() && cooldown <= 0)
        {
            if(g.isPlayer)
            {
                Music.playSound(sound, .7f);
                ((Player)g).addHealth(-1*dammage);
                cooldown = attackCooldown;
                inAttack = true;
                imageNumber = -1;
                //((Player)g).setLoc(new int[] {g.getLoc()[0]+(2*(int)this.getVelocity()[0]),g.getLoc()[1]+(2*(int)this.getVelocity()[1]),0,0});
                ((Player)g).setVelocity(new float[] {0,.4f});
                return false;
            }
        }
        if(!g.isPlayer&&(jumpToAttack||jumpMove))
        {
            if(Math.random()<.001)
            {
                jump();
            }
        }
        return true;
    }
/**
 * Int amount is passed to this addHealth method; the amount of health is added 
 * for the player through an if-statement.
     * @param amount
 */        
    @Override
    public void addHealth(int amount)
    {
        super.addHealth(amount);
        if(amount < 0)
        {
            attacking = true;
            //Music.playSound("Walk1.wav", .5f);
        }
    }
    
}
