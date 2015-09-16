/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid.plunder;

import java.awt.Graphics;

/**
 * The class here creates the coin image in the game;
 * Coin extends the PhysicsObject Class; this implements the size and the
 * random locations for the game.
 */
public class Coin extends PhysicsObject {
    
    protected boolean active = true;
    
    private int value = 1;
/**
 * The Coin image is imported in the Coin Class. 
 * It uses the 'super' for the image.
     * @param images
 */     
    public Coin(int[] images)
    {
       super(images, false);
       setInteractPhysics(false);
       name = "Coin";
    }
/**
 * The method here imports the location of coin one and two; it also implements the 
 * size of the coins.
     * @param images
     * @param value
 */      
    public Coin(int[] images, int value)
    {
       super(images, false);
       setInteractPhysics(false);
       name = "Coin";
       this.value = value;
    }
/**
 * This collide method receives the GameObject g, the user is able to use the
 * keys on the board, the Pickup_Coin.wav sound plays when the user uses the board.
     * @param g
     * @return 
 */        
    @Override
    public boolean collide(GameObject g)
    {
        try
        {
        if(active && g.getKey() == GameRunner.getPlayerKey())
        {
            //add money
            GameRunner.addMoney(value);
            active = false;
            Music.playSound("Pickup_Coin.wav", .5f);
        }
        return false;
        }
        catch(Exception e)
        {
            ErrorLogger.logError("Can't make collision " + e);
        }
        return false;
    }
    
    /*public boolean collidesBox(int[] locOne, int[] sizeOne, int[] locTwo, int[] sizeTwo)
    {
        return false;
    }*/
/**
 * The Draw here is passed the Graphics g, necessary to create the graphics.
     * @param g
 */      
    @Override
    public void Draw(Graphics g) {
        if(active)
            super.Draw(g);
    }


    
}
