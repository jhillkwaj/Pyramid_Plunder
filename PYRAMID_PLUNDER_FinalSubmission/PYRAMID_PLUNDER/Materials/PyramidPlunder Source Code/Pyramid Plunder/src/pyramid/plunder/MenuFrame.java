/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

/**
 * This MenuFrame Class extends JFrame and implements the WindowFocusListener, 
 * and WindowListener which are necessary for the many functions and uses of 
 * the window. 
 */
public class MenuFrame extends JFrame implements WindowFocusListener,  WindowListener {
/**
 * This MenuFrame Class extends JFrame and implements the WindowFocusListener, 
 * and WindowListener which are necessary for the many functions and uses of 
 * the window. 
 */
    public MenuFrame()
    {
        super();
        addWindowFocusListener(this);
        addWindowListener(this);
    }
/**
 * This windowLostFocus method is passed down the WindowEvent and is able to 
 * dispose of it. 
     * @param e
 */     
    @Override
   public void windowLostFocus(WindowEvent e) {
        this.dispose();
    }
/**
 * This windowOpened method is passed down the WindowEvent e.
     * @param e
 */ 
    @Override
    public void windowOpened(WindowEvent e) {
    }
/**
 * This windowClosing method is passed down the WindowEvent e.
     * @param e
 */ 
    @Override
    public void windowClosing(WindowEvent e){
    }
/**
 * This windowClosed method is passed down the WindowEvent e.
     * @param e
 */  
    @Override
    public void windowClosed(WindowEvent e) {
    }
/**
 * This windowGainedFocus method is passed down the WindowEvent e.
     * @param e
 */    
    @Override
    public void windowGainedFocus(WindowEvent e) {
    }
/**
 * This windowIconified method is passed down the WindowEvent e.
     * @param e
 */      
    @Override
    public void windowIconified(WindowEvent e) {
    }
/**
 * This windowDeiconified method is passed down the WindowEvent e.
     * @param e
 */  
    @Override
    public void windowDeiconified(WindowEvent e) {
    }
/**
 * This windowActivated method is passed down the WindowEvent e.
     * @param e
 */ 
    @Override
    public void windowActivated(WindowEvent e) {
    }
/**
 * This windowDeactivated method is passed down the WindowEvent e.
     * @param e
 */
    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    
    
}
