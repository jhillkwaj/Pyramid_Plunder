/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid.plunder;

import java.awt.Graphics;

/**
 * This class creates guidelines for the physics behind movement in the game.
 * It includes velocity along with gravity.
 */
public abstract class PhysicsObject extends GameObject {
    public float[] velocity = {0,0};
    public boolean gravity = false;
    private int jumps = 0;
    
 /**
 * This method creates the an object in the game that is effected by gravity.
     * @param imageKeys
 */     
    public PhysicsObject(int[] imageKeys)
    {
        super(imageKeys,imageKeys.length);
        //GameRunner.addCollidable(this);
        gravity = true;
    }
 /**
 * This method creates turns gravity on for an object in the game.
     * @param imageKeys
     * @param gravityOn
 */    
    public PhysicsObject(int[] imageKeys, boolean gravityOn)
    {
        super(imageKeys,imageKeys.length);
       // GameRunner.addCollidable(this);
        gravity = gravityOn;
        
    }
 /**
 * This method draws the new frame for a moving object and updates it's velocity
 * and the effects gravity has on it.
 */      
    @Override
    public void Draw(Graphics g) {
        updatePhysics();
        super.Draw(g);
        
    }
    /**
 * This method updates the physics for the object and compares defines its new
 * location along with its old location. Also the object is given an updated 
 * velocity based on how it's moving. Jumping is also taken into account.
 */        
    public void updatePhysics()
    {
        if(System.currentTimeMillis()-GameRunner.startTime > 1000)
        {
        //if this object moves and is effected by gravity
        if(gravity)
        {
            if(Math.abs(velocity[0])<.3)
            {
                velocity[0] = 0;
            }
            //the objects location before moving and the location it will move to
            int[] oldLoc = {getLoc()[0],getLoc()[1]};
            int[] newLoc = {getLoc()[0],getLoc()[1]};
             
            //and the velocity of the object to its location. Add gravity to the y velocity and friction to the x
            //gravity
            velocity[1] += .2f;
            newLoc[1] += velocity[1];
            newLoc[0] += velocity[0];
            //friction
            if(velocity[0]>0)
            {
                velocity[0]-=.2f;
            }
            else if(velocity[0]<0)
            {
                velocity[0]+=.2f;
            }
           // System.out.println(velocity[0]);
            
            
            //look at each game collidable to see if the object has collided with it
            for(PhysicsObject g : GameRunner.getCollidables())
            {
                if(g.isPlayer)
                {
                    
                }
                //if this collidable is not this same objecct
                if(g.getKey()!=this.getKey())
                {
                    //see if the objects intersect
                    if(g.collidesBox(newLoc, getSize(), g.getLoc(), g.getSize()))
                    {
                        this.collide(g);
                        if(g.collide(this))
                        {
                        //if the object hasn't moved in the x or y directions
                        
                        if(g.getInteractPhysics()&&this.getInteractPhysics())
                        {
                        //x hasn't moved
                        if(oldLoc[0]==newLoc[0]) 
                        {
                            //see which direction the object is moving
                            
                            //if object is moving down
                            if(velocity[1]>0) 
                            {
                                //move the objet to the top of the object it collided with
                                newLoc[1] = g.getLoc()[1] - this.getSize()[1];
                                
                                //reset jump count
                                jumps=0;
                            }
                            //if object is moving up (shoud be true if no errors)
                            else if(velocity[1]<0)
                            {
                                //move the objet to the bottom of the object it collided with
                                newLoc[1] = g.getLoc()[1] + g.getSize()[1];
                            }
                            
                            //set the y velocity to zero
                            velocity[1]=0;
                        }
                        //y hasn't moved
                        else if(oldLoc[1]==newLoc[1]) 
                        {
                             //see which direction the object is moving
                            
                            //if object is moving right
                            if(velocity[0]>0) 
                            {
                                //move the objet to the left of the object it collided with
                                newLoc[0] = g.getLoc()[0] - this.getSize()[0];
                            }
                            //if object is moving left (shoud be true if no errors)
                            else if(velocity[0]<0)
                            {
                                //move the objet to the right of the object it collided with
                                newLoc[0] = g.getLoc()[0] + g.getSize()[0];
                            }
                            
                            //set the x velocity to zero
                            velocity[0]=0;
                        }
                        else //it moved in both directions
                        {
                            //look to see if it would have hit if it had moved in just one of the directions
                            
                            //moving in the y direction made it hit
                            if(collidesBox(new int[] {oldLoc[0],newLoc[1]}, getSize(), g.getLoc(), g.getSize())) 
                            {
                                //moving in the either direction would made it hit
                                if(collidesBox(new int[] {newLoc[0],oldLoc[1]}, getSize(), g.getLoc(), g.getSize()))
                                {
                                    //see which direction the object is moving
                            
                                    //if object is moving right
                                    if(velocity[0]>0) 
                                    {
                                        //move the objet to the left of the object it collided with
                                        newLoc[0] = g.getLoc()[0] - this.getSize()[0];
                                    }
                                    //if object is moving left (shoud be true if no errors)
                                    else if(velocity[0]<0)
                                    {
                                        //move the objet to the right of the object it collided with
                                        newLoc[0] = g.getLoc()[0] + g.getSize()[0];
                                    }
                                    //if object is moving down
                                    if(velocity[1]>0) 
                                    {
                                        //move the objet to the top of the object it collided with
                                        newLoc[1] = g.getLoc()[1] - this.getSize()[1]; 
                                        
                                        //reset jump count
                                        jumps=0;
                                    }
                                    //if object is moving up (shoud be true if no errors)
                                    else if(velocity[1]<0)
                                    {
                                        //move the objet to the bottom of the object it collided with
                                        newLoc[1] = g.getLoc()[1] + g.getSize()[1];
                                        
                                        
                                    }
                            
                                    //set the y velocity to zero
                                    velocity[1]=0;
                            
                                    //set the x velocity to zero
                                    velocity[0]=0;  
                                }
                                //only moving in the y direction made it hit
                                else
                                {
                                    //see which direction the object is moving
                            
                                    //if object is moving down
                                    if(velocity[1]>0) 
                                    {
                                        //move the objet to the top of the object it collided with
                                        newLoc[1] = g.getLoc()[1] - this.getSize()[1];
                                        
                                        //reset jump count
                                        jumps=0;
                                    }
                                    //if object is moving up (shoud be true if no errors)
                                    else if(velocity[1]<0)
                                    {
                                        //move the objet to the bottom of the object it collided with
                                        newLoc[1] = g.getLoc()[1] + g.getSize()[1];
                                    }
                            
                                    //set the y velocity to zero
                                    velocity[1]=0;
                                }
                                
                            }
                            //only moving in the x direction made it hit
                            else if(collidesBox(new int[] {newLoc[0],oldLoc[1]}, getSize(), g.getLoc(), g.getSize())) 
                            {
                                //see which direction the object is moving
                            
                                //if object is moving right
                                if(velocity[0]>0) 
                                {
                                    //move the objet to the left of the object it collided with
                                    newLoc[0] = g.getLoc()[0] - this.getSize()[0];
                                }
                                //if object is moving left (shoud be true if no errors)
                                else if(velocity[0]<0)
                                {
                                    //move the objet to the right of the object it collided with
                                    newLoc[0] = g.getLoc()[0] + g.getSize()[0];
                                }
                            
                                //set the x velocity to zero
                                velocity[0]=0;   
                            }
                            //it had to move in both directions to hit
                            else 
                            {
                                //System.out.println("Well to bad...");
                                //newLoc[1] -= velocity[1];
                                //newLoc[0] -= velocity[0];
                               
                            }
                        }
                        }
                        }
                    }
                }
            }
            //if(this.isPlayer) {System.out.println(velocity[0] + "   " + velocity[1]);}
            setLoc(new int[] {newLoc[0],newLoc[1],getLoc()[2],getLoc()[3]});
        }
        }
    }
    
    /**
 * This method creates a collision box for the object and considers whether or 
 * not it is in the same box as a another object on screen.
     * @param locOne
     * @param sizeOne
     * @param locTwo
     * @param sizeTwo
     * @return if the boxes intersect
 */       
    public boolean collidesBox(int[] locOne, int[] sizeOne, int[] locTwo, int[] sizeTwo)
    {
        if(locOne[0]+sizeOne[0]>locTwo[0]&&locOne[0]<locTwo[0]+sizeTwo[0])
        {
            if(locOne[1]+sizeOne[1]>locTwo[1]&&locOne[1]<locTwo[1]+sizeTwo[1])
            {
                return true;
            }
        }
        return false;
    }

 /**
 * This method returns the objects velocity.
     * @return the objects velocity
 */     
    public float[] getVelocity()
    {
        return velocity;
    }
 /**
 * This method sets the objects velocity.
     * @param velocity
 */     
    public void setVelocity(float[] velocity)
    {
        this.velocity = velocity;
    }
 /**
 * This method adds one to the jump indicator for the object, since the main
 * character only has a limited number of jumps. 
 */       
    public void addJump()
    {
        jumps++;
    }
 /**
 * This method returns the objects number of jumps. 
     * @return the number of jumps used
 */     
    public int getJumps()
    {
        return jumps;
    }
 /**
 * This method tests whether or not the object has collided with another 
 * on screen object. 
     * @param g
     * @return true
 */    
    public boolean collide(GameObject g)
    {
        return true;
    }
    
     
}