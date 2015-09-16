/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid.plunder;

import java.awt.Graphics;

/**
 * This is the abstract Drawable Class; int keyLevel and boolean isGameObject are 
 * implemented; the getNextkey and isGameObject is defined as well.
 */
public abstract class Drawable {
    private int key = -1;
    private static int nextKey = Integer.MAX_VALUE;
    private static int midKey = 0;
    private boolean gameObject = false;
/**
 * In this class, the int keyLevel and boolean isGameObject are implemented.
 * The getNextkey and isGameObject is defined as well.
     * @param keyLevel
     * @param isGameObject
 */     
    public Drawable(int keyLevel, boolean isGameObject)
    {
        key = getNextKey(keyLevel);
        gameObject = isGameObject;
    }
/**
 * In this getNextKey method the key on the keyboard is chosen and the nextKey returned.
     * @return key
 */     
     public static int getNextKey()
    {
             nextKey--;
            return nextKey;
    }
/**
 * In this getNextKey method the int level is implemented. An else-if statement is used 
 * in order to implement the level.
     * @param level
     * @return next key
 */    
    public static int getNextKey(int level)
    {
        try
        {
        if(level==1)
        {
             nextKey--;
            return nextKey;
        }
        else if(level==0)
        {
            midKey--;
            return midKey;
        }
        else return 999;
        }
        catch(Exception e)
        {
            ErrorLogger.logError("Can't get key: " + e);
        }
        return 999;
    }
  /**
 * This getKey method returns the key being selected.
 *
     * @return  key
 */   
    public int getKey()
    {
        return key;
    }
 /**
 * This isGameObject method returns the gameObject.
 *
     * @return if it is a game object
 */    
    public boolean isGameObject()
    {
        return gameObject;
    }
 /**
 * This abstract void Draw method uses the Graphics method.
     * @param g
 */       
    public abstract void Draw(Graphics g);
    
}
