/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 * In this OptionsMenu Class the background is implemented with a color and
 * dimension size, with the a specific location. ActionListener is a implemented
 * for the OptionsMenu, through which the user is able to exit the menu. OptionsPane
 * and text is also implemented.
 */
public class OptionsMenu extends JPanel {
    public JFrame frame=new MenuFrame();
    private final int optionsNum=4;
/**
 * In this OptionsMenu Class the background is implemented with a color and
 * dimension size, with the a specific location. ActionListener is a implemented
 * for the OptionsMenu, through which the user is able to exit the menu.
 */  
    public OptionsMenu()
    {
        ErrorLogger.logMessage("Opening Options Menu");
        frame.setTitle("Options");
        frame.setIconImage(GraphicsManager.getGraphics(69));
        this.setBackground(new Color(84,42,0));
        this.setPreferredSize(new Dimension(200, 20+(60*optionsNum)));
        frame.setLocation(-100 + (GameRunner.getFrameWidth()/2),-((20+(60*optionsNum))/2)+(GameRunner.getFrameHeight()/2));
        JButton s = new JButton("Save Game",null);
        s.addActionListener(new ActionListener() 
        {
                @Override
		public void actionPerformed(ActionEvent e) 
                {
			SaveLoad.save(true);
		}    
	}
        );
        s.setBounds(20, 20+(0*60), 160, 40);
        s.setBackground(Color.yellow);
        this.setLayout(null);
        if(!GameRunner.tutorial)
            this.add(s);
        JButton b=new JButton("Exit to Main Menu",null);
        
        b.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
                {
                    if(confirm(""
                            + "        Are you sure you want to exit to the main menu?\n"
                            + "All unsaved progress will be lost forever in the sands of time."))
                    {
			MainMenu menu = new MainMenu();
                        menu.start();
                        GameRunner.exit();
                    }
		}    
	});
        b.setBounds(20, 20+(1*60), 160, 40);
        b.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(b);
        JButton e=new JButton("Exit Game",null);
        e.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
                {
                    if(confirm(""
                            + "                                Are you sure you want to exit Pyramid Plunder?\n"
                            + "All unsaved progress will be lost forever, swallowed by Atum in the waters of creation."))
			System.exit(0);
		}    
	}
        );
        e.setBounds(20, 20+(2*60), 160, 40);
        e.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(e);
        JButton m=new JButton("Escape Pyramid",null);
        m.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
                {
                    if(confirm(""
                            + "Are you sure you want to exit The Pyramid?\n"
                            + "You will not be able to return again."))
                    {
			SaveLoad.save(true);
                        LevelMenu l = new LevelMenu();
                        l.openLevelMenu();
                        GameRunner.exit();
                        
                    }
		}    
	}
        );
        m.setBounds(20, 20+(3*60), 160, 40);
        m.setBackground(Color.yellow);
        this.setLayout(null);
        if(!GameRunner.tutorial)
            this.add(m);
        frame.add(this);
        frame.setVisible(true);
        frame.pack();
        frame.repaint();
    }
/**
 * In this private confirm boolean method, the String message is passed; the color
 * of the OptionPane and Panel are also defined. Text is also implemented in the
 * options. 
 */        
    private boolean confirm(String message)
    {
        UIManager UI=new UIManager();
        UI.put("OptionPane.background",new ColorUIResource(84,42,0));
        UI.put("Panel.background",new ColorUIResource(84,42,0));
        UI.put("OptionPane.messageForeground", Color.WHITE);
       int reply=JOptionPane.showConfirmDialog(null, message, "Confirm Exit",
               JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
       if (reply==JOptionPane.YES_OPTION) 
       {
          return true;
        }
        else 
       {
           OptionsMenu o=new OptionsMenu();
           return false;
        }
   }
    

    

}

