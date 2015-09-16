/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid.plunder;

import java.awt.Graphics;

/**
 * This abstract GameObject class extends the Drawable Class, major facets of
 * the game are made and implemented here.
 */
public abstract class GameObject extends Drawable {
   
    //absolute x, absolute y, relative x, relavite y
    private int[] location = new int[4];
    private int size[] = new int[2];
    protected int[] imageKeys;
    protected int keyTime = 30;
    private int maxKeyTime = 30;
    protected int drawKey = 0;
    private boolean mirror = false;
    private boolean isPhysicsObject = false;
    protected boolean isPlayer = false;
    private boolean interactPhysics = true;
    
    protected String name = "";
    
    
/**
 * This GameObject class is passed down the int imageKeys and the int keyCount.
 * the images 
     * @param imageKeys
     * @param keyCount
 */
    public GameObject(int[] imageKeys, int keyCount)
    {
        super(1, true);
        try
        {
        this.imageKeys = imageKeys;
        keyTime = keyCount;
        maxKeyTime = keyCount;
        }
        catch(Exception e)
        {
            ErrorLogger.logError("Can't Make Game Object " + e);
        }
    }
 /**
 * This GameObject class is passed down int imageKeys, int keyCount, and boolean
 * physicsObject. 
     * @param imageKeys
     * @param keyCount
     * @param physicsObject
 */      
    public GameObject(int[] imageKeys, int keyCount, boolean physicsObject)
    {
        super(1, true);
        try
        {
        
        this.imageKeys = imageKeys;
        keyTime = keyCount;
        maxKeyTime = keyCount;
        isPhysicsObject = physicsObject;
        }
        catch(Exception e)
        {
            ErrorLogger.logError("Can't Make Game Object " + e);
        }
        
    }
/**
 * This Draw Class uses Graphics g in order to use the image in the specific location.
     * @return name of the object
 */     
    public String getName()
    {
        return name;
    }
/**
 * This Draw Class uses Graphics g in order to use the image in the specific location.
     * @return the image keys of the object
 */     
    public int[] getImageKeys()
    {
        return imageKeys;
    }
    
    @Override
/**
 * This Draw Class uses Graphics g in order to use the image in the specific location.
 */    
    public void Draw(Graphics g) {
        try
        {
        calculateLocation();
        //System.out.println(drawKey);
        if(--keyTime<=0)
        { 
            keyTime=maxKeyTime;
            switchImage();
        }
        
        if(!mirror)
        {
        
        if(location[3]!=Integer.MIN_VALUE)
        {
            g.drawImage(GraphicsManager.getGraphics(imageKeys[drawKey]), location[2], location[3],size[0],size[1], null);
        }
        else
        { g.drawImage(GraphicsManager.getGraphics(imageKeys[drawKey]), 0-(size[0]/2)+400, 0-(size[1]/2)+300,size[0],size[1], null); }
        
                
                }
        else
        {
            if(location[3]!=Integer.MIN_VALUE)
            {
                g.drawImage(GraphicsManager.getGraphics(imageKeys[drawKey]), location[2], location[3],size[0]+location[2], size[1]+location[3],size[0], 0,0,size[1], null);
            }
            else
            { g.drawImage(GraphicsManager.getGraphics(imageKeys[drawKey]), 0-(size[0]/2)+400, 0-(size[1]/2)+300,0+(size[0]/2)+400, 0+(size[1]/2)+300,size[0], 0,0,size[1], null);
            }
        }
        }
        catch(Exception e)
       {
          //  ErrorLogger.logError("Can't Draw Game Object " + e);
        }
        
    }
/**
 * This switchImage method uses a try-catch statement in order to switch the images
 * for the various sprite. 
 */     
    protected void switchImage()
    {
        try
        {
        if(++drawKey>=imageKeys.length)
            { drawKey=0; }
        }
        catch(Exception e)
        {
            ErrorLogger.logError("Can't swich images " + e);
        }
    }
/**
 * This calculateLocation method varies the location of the image and this occurs 
 * with the arrays; the min.value and location are used.
 */     
    public void calculateLocation()
    {
        try
        {
        if(location[3]!=Integer.MIN_VALUE)
        {
            int[] frameLoc = GameRunner.getFrameLoc();
            location[2]=location[0]-frameLoc[0];
            location[3]=location[1]-frameLoc[1];
        }
        }
        catch(Exception e)
        {
            ErrorLogger.logError("Can't calculate object location " + e);
        }

    }
 /**
 * This getLoc method returns the location.
     * @return the location of the object
 */    
    public int[] getLoc()
    {return location;}
  /**
 * This setLoc method returns the location as newLoc and receives the int newLoc.
     * @param newLoc
 */    
    public void setLoc(int[] newLoc)
    {
        location = newLoc;
    }
  /**
 * This setSize method returns the size.
     * @return 
 */ 
    public int[] getSize()
    {
        return size;
    }
  /**
 * This setSize method returns the int size and follows a 'this size.'
     * @param size
 */    
    public void setSize(int[] size)
    {
        this.size = size;
    }
  /**
 * This mirror method assigns the mirror to mirrored and receives the boolean 
 * mirrored.
     * @param mirrored
 */   
    public void mirror(boolean mirrored)
    {
        mirror = mirrored;
    }   
 /**
 * This isMirrored method returns the mirror.
     * @return if it is mirrored
 */    
    public boolean isMirrored()
    {
        return mirror;
    }
 /**
 * This isPhysicsObject method returns the isPhysicsObject.
     * @return if it is a physics object
 */    
    public boolean isPhysicsObject()
    {
        return isPhysicsObject;
    }
 /**
 * This getImageKey returns the imageKeys with drawKey.
     * @return the image key being drawn
 */      
    public int getImageKey()
    {
        return imageKeys[drawKey];
    }
 /**
 * This boolean getInteractPhysics method returns interactPhysics.
     * @return if it interacts with physics
 */      
    public boolean getInteractPhysics()
    {
        return interactPhysics;
    }
  /**
 * This setInteractPhysics method assigns the interactPhysics to newValue, it
 *  is passed down the boolean newValue.
     * @param newValue
 */     
    public void setInteractPhysics(boolean newValue)
    {
        interactPhysics = newValue;
    }
 /**
 * This setKeyTime assigns maxKeyTime to time and keyTime to time, it receives the int
 * time.
     * @param time
 */     
    public void setKeyTime(int time)
    {
        maxKeyTime = time;
        keyTime = time;
    }
}
