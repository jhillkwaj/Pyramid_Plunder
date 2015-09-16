/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid.plunder;

import java.awt.Graphics;

/** 
 * The background of the game is drawn and imported; 
 *  this Background Class extends the Drawable Class; it uses
 *  a for-loop to create the background with 
 *  specified coordinates.
 */
public class Background extends Drawable {
/** 
 * Here the background is set through the use of 'super'
 */      
    public Background()
    {
        super(0, false);
    }

/** 
 *  Using a 'for' loop, the background is created with 
 *  specified coordinates i, j, 64, 64
     * @param g
 */
    @Override
    public void Draw(Graphics g) {
        try
        {
        int[] offset = GameRunner.getFrameLoc();
        for(int i = 0-((offset[0])%64)-64; i < 800; i+=64)
        {
            for(int j = 0-((offset[1])%64)-64; j < 600; j+=64)
            {
                g.drawImage(GraphicsManager.getGraphics(0), i, j,64,64, null);
            }
        }
        }
        catch(Exception e)
        {
            ErrorLogger.logError("Can't Draw Background: " + e);
        }
       
    }
    
}
