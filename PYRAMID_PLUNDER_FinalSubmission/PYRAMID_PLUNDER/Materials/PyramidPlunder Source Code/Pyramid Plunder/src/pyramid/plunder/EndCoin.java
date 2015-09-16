/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;

/**
 * This EndCoin Class extends Coin; this class
 * implements the images and values for the various 
 * coins the game.
 */
public class EndCoin extends Coin {
/**
 * This EndCoin receives the int array images and
 * int value, then uses super to implement them.
     * @param images
     * @param value
 */
    public EndCoin(int[] images, int value) {
        super(images, value);
    }
 /**
 * This collide method receives the GameObject g; here
 * the functions and playability of the coin is made. 
     * @return if there was a collision
 */   
    @Override
    public boolean collide(GameObject g)
    {
        try
        {
        if(active && g.getKey() == GameRunner.getPlayerKey())
        {
            //add money
            super.collide(g);
            //end level
            LevelMenu u = new LevelMenu();
            u.openLevelMenu();
            GameRunner.frame.dispose();
            SaveLoad.save(false);
            setKeyTime(20);
        }
        return false;
        }
        catch(Exception e)
        {
            ErrorLogger.logError("Can't collide with end coin " + e);
        }
        return false;
    }
    
}
