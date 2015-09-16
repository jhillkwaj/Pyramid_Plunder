/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid.plunder;

import java.awt.Color;
import java.awt.Graphics;
import java.util.TreeMap;

/**
 * This Shader Class extends the Drawable Method; this class uses various for loops 
 * to create the background; the fade and time is also passed to this class.
 */
public class Shader extends Drawable {
    private float a;
    private boolean fadeIn = false;
    private int fadeTime;
    private float fadeTo = 0;
            
    //stores things to draw with a key to axcess them
    //private TreeMap<Integer,Drawable> drawables = new TreeMap<Integer,Drawable>();
    
    public Shader(float a)
    {
        super(1,false);
        ErrorLogger.logMessage("Adding Shader");
        this.a = a;
    }
   
    @Override
/** 
 *  Using a 'for' loop, the background is created with 
 *  specified coordinates i, j, 64, 64
 */    
    public void Draw(Graphics g) {
        //fade in or out
        if(fadeIn&&fadeTime>0)
         { a+=((fadeTo-a))/((float)fadeTime); 
         fadeTime--;}
        
        
        //draw the shader
        g.setColor(new Color(0,0,0,1-a));
        g.fillRect(0, 0, 800, 600);
       //  System.out.print(a);
    }
    
    public void Draw(Graphics g,int width, int height) {
        //fade in or out
        if(fadeIn&&fadeTime>0)
         { a+=((fadeTo-a))/((float)fadeTime); 
         fadeTime--;}
        
        
        //draw the shader
        g.setColor(new Color(0,0,0,1-a));
        g.fillRect(0, 0, width, height);
       //  System.out.print(a);
    }
    
    public float getA()
    {
        return 1-a;
    }
/** 
 *  The int time and float fadeTo is passed down in this fadeIn method.
     * @param time
     * @param fadeTo
 */      
    public void fadeIn(int time, float fadeTo)
    {
        fadeIn = true;
        fadeTime = time;
        this.fadeTo = fadeTo;
    }

    
    
}
