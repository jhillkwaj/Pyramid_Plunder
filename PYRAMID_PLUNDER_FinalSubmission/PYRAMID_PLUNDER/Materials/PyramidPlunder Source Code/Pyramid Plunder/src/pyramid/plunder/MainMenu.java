/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 * This MainMenu Class extends JPanel, which is used for the screens; 
 * it creates the first menu being shown to the user; various items are defined
 * for the playability for the game and for the user.
 */
public class MainMenu extends JPanel implements KeyListener{
    private static JFrame frame = new JFrame();
    private Image back;
    private Image playBack;
 
    private JButton play;
    private JButton tutorial;
    private JButton credits;
    private JButton highScore;
    private JButton exit;
    
    private long time;
    
    private int yChange = 2300;
    
   private boolean buttonsOn=false;
   
   Shader s = new Shader(0);

/**
 * This method draws the initial background for the menu, and creates the frame; 
 * the title of Pyramid Plunder is defined and the Main Menu as well.
 * 
 */     
    public void start()
    {
        this.removeAll();
        frame = new JFrame();
        
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        s.fadeIn(900, 1);
        
        frame.setSize((int)width, (int)height);
        
        frame.setTitle("Pyramid Plunder");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.MAXIMIZED_BOTH);  
        
        time = System.currentTimeMillis();
        frame.setIconImage(GraphicsManager.getGraphics(69));
       
        frame.add(this);
        frame.setVisible(true);
         
        frame.repaint();
        
        frame.addKeyListener(this);
        Music.playBackgrounds();
    }
/**
 * This method adds the 5 buttons to the main menu, and draws all of them, the following
 * are made and are playable: Play, Tutorial, High Scores, Credits, and Exit. 
 * 
 */      
    public void addButtions()
    {
        UIManager UI=new UIManager();
        UI.put("OptionPane.background",new ColorUIResource(84,42,0));
        UI.put("OptionPane.messageForeground", Color.WHITE);
        UI.put("Panel.background",new ColorUIResource(84,42,0));
        
        Font font = new Font("Papyrus", Font.BOLD, 25);
        
        play = new JButton("Play",new ImageIcon(GraphicsManager.getGraphics(18).getScaledInstance(frame.getWidth()/2, frame.getHeight()/16, 0)));
        play.setBorder(BorderFactory.createEmptyBorder());
        play.setContentAreaFilled(false);
        play.setHorizontalTextPosition(JButton.CENTER);
        play.setVerticalTextPosition(JButton.CENTER);
        play.setFont(font);
        play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            StartMenu s = new StartMenu();
			}
		});
                
                
        tutorial = new JButton("Tutorial",new ImageIcon(GraphicsManager.getGraphics(18).getScaledInstance(frame.getWidth()/2, frame.getHeight()/16, 0)));
        tutorial.setBorder(BorderFactory.createEmptyBorder());
        tutorial.setContentAreaFilled(false);
        tutorial.setHorizontalTextPosition(JButton.CENTER);
        tutorial.setVerticalTextPosition(JButton.CENTER);
        tutorial.setFont(font);
        tutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tutorial();
                                close();
			}
		});
                
       highScore = new JButton("High Scores",new ImageIcon(GraphicsManager.getGraphics(18).getScaledInstance(frame.getWidth()/2, frame.getHeight()/16, 0)));
       highScore.setBorder(BorderFactory.createEmptyBorder());
       highScore.setContentAreaFilled(false);
       highScore.setHorizontalTextPosition(JButton.CENTER);
       highScore.setVerticalTextPosition(JButton.CENTER);
       highScore.setFont(font);
       highScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreeMap<Integer,String> scores = new TreeMap<Integer,String>();
                                scores = HighScore.getHighScore();
                                
                                String s = "          High Scores          \n";
                                
                                for(int i = scores.size() - 1; i >= 0 ; i--)
                                {
                                    s+=scores.get(scores.keySet().toArray()[i])+": "+scores.keySet().toArray()[i] +"\n";
                                    
                                }
                                
                                JOptionPane.showMessageDialog(null, s);
                                
			}
		});
                
                
       credits = new JButton("Credits",new ImageIcon(GraphicsManager.getGraphics(18).getScaledInstance(frame.getWidth()/2, frame.getHeight()/16, 0)));
       credits.setBorder(BorderFactory.createEmptyBorder());
       credits.setContentAreaFilled(false);
       credits.setHorizontalTextPosition(JButton.CENTER);
       credits.setVerticalTextPosition(JButton.CENTER);
       credits.setFont(font);
       credits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				credits();
			}
		});
                
                
        exit = new JButton("Exit",new ImageIcon(GraphicsManager.getGraphics(18).getScaledInstance(frame.getWidth()/2, frame.getHeight()/16, 0)));
        exit.setBorder(BorderFactory.createEmptyBorder());
        exit.setContentAreaFilled(false);
        exit.setHorizontalTextPosition(JButton.CENTER);
        exit.setVerticalTextPosition(JButton.CENTER);
        exit.setFont(font);
        exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                                if(confirm(""
                                    + "              Are you sure you want to exit Pyramid Plunder?\n"
                                    + "All unsaved progress will be lost forever, swept away into the dust."))
                                 System.exit(0);
			}
		});
                        
                
        
        play.setBounds(frame.getWidth()/10, frame.getHeight()/4, frame.getWidth()/8, frame.getHeight()/16);
        this.setLayout(null);
        this.add(play);
        
        tutorial.setBounds(frame.getWidth()/10, frame.getHeight()/4+(2*(frame.getHeight()/16)), frame.getWidth()/8, frame.getHeight()/16);
        this.setLayout(null);
        this.add(tutorial);
        
        highScore.setBounds(4*frame.getWidth()/10, frame.getHeight()/4, frame.getWidth()/8, frame.getHeight()/16);
        this.setLayout(null);
        this.add(highScore);
        
        credits.setBounds(4*frame.getWidth()/10, frame.getHeight()/4+(2*(frame.getHeight()/16)), frame.getWidth()/8, frame.getHeight()/16);
        this.setLayout(null);
        this.add(credits);
        
        exit.setBounds((int)(2.5*frame.getWidth()/10), frame.getHeight()/4+(4*(frame.getHeight()/16)), frame.getWidth()/8, frame.getHeight()/16);
        this.setLayout(null);
        this.add(exit); 
        
  
    }
    
 /**
 * This method paints the rectangular buttons that were drawn in the previous
 * method; the Graphics g is passed down.
 * 
     * @param g
 */      
    @Override
    public void paintComponent(Graphics g)
    {
        
        if(!buttonsOn && yChange > 0)
        { 
            yChange -= (int)(.1*(System.currentTimeMillis() - time));
            time += 10*(int)(.1*(System.currentTimeMillis() - time));
            if(yChange<=0)
            { 
                yChange=0;
                
            }
        }
        else if(!buttonsOn)
        {
            addButtions();
        }
     

        try{
            
            
            
            //draw the background
        g.setColor(new Color(255, 228, 94));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        Graphics2D g2d = (Graphics2D)g;
        
        
        
        //draw the main menu
        if(yChange < this.getHeight())
        {
         
         g2d.drawImage(GraphicsManager.getGraphics(17), 0,0 - yChange,this.getWidth(),this.getHeight(),this);
        }
        
        //draw steps
        if(yChange!=0)
        {
            for(int i = yChange + this.getHeight() - (yChange%64); (i + 48 > this.getHeight() && i > yChange - 64 ); i-= 64)
            {
                g2d.drawImage(GraphicsManager.getGraphics(28), (int)((this.getWidth()*.5734f)-((i-this.getHeight())/4.0f)), + (i - yChange ),this);
            }
        }
        
        //draw logos
        if(yChange!=0)
        {
            g2d.drawImage(GraphicsManager.getGraphics(29), this.getWidth()/9 ,1950 - yChange,(this.getHeight()/600)*460,(this.getHeight()/600)*144,this);
            g2d.drawImage(GraphicsManager.getGraphics(33), (5*this.getWidth())/7 ,1950 - yChange,(this.getHeight()/600)*400,(this.getHeight()/600)*120,this);
            g2d.drawImage(GraphicsManager.getGraphics(30), this.getWidth()/7 ,1450 - yChange,(this.getHeight()/600)*384,(this.getHeight()/600)*194,this);
            g2d.drawImage(GraphicsManager.getGraphics(31), (5*this.getWidth())/9 ,1215 - yChange,(this.getHeight()/600)*384,(this.getHeight()/600)*384,this);
            g2d.drawImage(GraphicsManager.getGraphics(32), this.getWidth()/5 ,950 - yChange,(this.getHeight()/600)*384,(this.getHeight()/600)*384,this);
        }
        
        
         
        //draw the shader
        if(yChange!=0)
        {
            s.Draw(g2d,this.getWidth(),this.getHeight());
        }
        
         Thread.sleep(10);
         this.repaint();
        } catch(Exception e)
        {
            ErrorLogger.logMessage("Can't draw main menu   " + e);
        }
        
    }
    
   
 /**
 * This method closes the frame after the user decides to exit the game. 
 */       
    public void close()
    {
	frame.setVisible(false);
        frame.dispose();
    }
/**
 * This method lists the credits for the user, when selected for viewing purposes.
 */     
    public void credits()
    {
        try
        {
         JOptionPane.showMessageDialog(null, "                    "
                 + " \nPublisher:                         BitBlit Interactive\n\n"
                 + "                     DEVELOPED BY \n"
                 + "                          The Techs\n\n"
                 + "Programer:                           Justin\n"
                 + "Art and Graphics:                Jeevan\n"
                 + "                                                 Mohit\n"
                 + "                                                 Justin\n"
                 + "Sound and Music:                Justin\n"
                 + "                                                 Jeevan\n"
                 + "Project Design:                    Justin\n"
                 + "                                                 Jeevan\n"
                 + "Documentation:                   Amir\n"
                 + "Presentation Design:          Amir\n"
                 + "                                                 Mohit\n\n"
                 + "                           Tools Used\n"
                 + "Netbeans:                      Programming Environment\n"
                 + "FamiTracker:                Sound Design\n"
                 + "Audacity:                        Sound Editing\n"
                 + "Google docs/drive:       Group Collaboration\n"
                 + "Google Hangouts:         Group Collaboration\n"
                 + "Paint:                               Graphics\n"
                 + "Paint Dot Net:                Graphics\n"
                 + "Photoshop:                    Graphics","Credits",
                 JOptionPane.PLAIN_MESSAGE);
        }
        catch(Exception e)
        {
            ErrorLogger.logError("Could not show credits " + e);
        }
    }
/**
 * This tutorial method uses a try-catch statement for the GameRunner to be playable.
 * 
 */     
    public void tutorial()
    {
        try
        {
         GameRunner g = new GameRunner(true);
        }
        catch(Exception e)
        {
            ErrorLogger.logNonRecoverableError("Error running the game " + e);
        }
    }
/**
 * This startGame method is passed down String name; a if-else statement is used
 * for the LevelMenu and GameRunner; the frame is able to be disposed.
 * 
     * @param name
 */     
    public static void startGame(String name)
    {
        if(name==null)
        {
            LevelMenu m = new LevelMenu();
            m.openLevelMenu();
         //GameRunner g = new GameRunner();
         frame.dispose();
        }
        else
        {
            GameRunner g = new GameRunner(name);
            frame.dispose();
        }
        
    }
    

    @Override
/**
 * This keyTyped method is passed down the keyEvent ke; the yChange is set to zero. 
 * 
 */     
    public void keyTyped(KeyEvent ke) {
       yChange = 0;
    }

    @Override
/**
 * This keyPressed method is passed down KeyEvent ke; the function is now sensitive 
 * for the key being pressed.
 * 
 */     
    public void keyPressed(KeyEvent ke) {
        
    }

    @Override
/**
 * This keyReleased method is passed down KeyEverny ke; the function is now
 * sensitive to the key being released.
 */     
    public void keyReleased(KeyEvent ke) {
        
    }
/**
 * This confirm method is passed down the String message; and messages for confirmation
 * are listed as 'Confirm Exit.'
 */     
    private boolean confirm(String message)
    {

        
       int reply = JOptionPane.showConfirmDialog(null, message, "Confirm Exit",
               JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
        
       if (reply == JOptionPane.YES_OPTION) {
          return true;
        }
        else {
           return false;
        }
   }
 /**
 * This int getFrameWidth returns the frame.getWidth.
     * @return the width of the frame
 */    
    public static int getFrameWidth()
    {
         return frame.getWidth();
    }
 /**
 * This getFrameHeight method returns the frame.getHeight. 
 * 
     * @return the height of the frame
 */    
    public static int getFrameHeight()
    {
        return frame.getHeight();
    }
}
