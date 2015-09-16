/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid.plunder;

/**
 * This Platform Class extends the PhysicsObject Class;
 * this class passes down the image and creates the platform in the game.
 */
public class Platform extends PhysicsObject {
/**
 * This Platform method is passed the int images; the super is given images and false, 
 * the name is assigned to "Platform."
     * @param images
 */    
    public Platform(int[] images)
    {
       super(images, false);
       name = "Platform";
    }
}
