/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * This StartMenu extends the JPanel; this class sets the title and dimensions of the
 * start menu; this sets up the various options available for the user- such as, 
 * Log in as a New User, and Log in as an Existing User- the username and password 
 * text file and log error is also set up.
 */
public class StartMenu extends JPanel {
    public boolean loggedIn = false;
    JFrame frame = new JFrame();
    public static String name = null;
    public StartMenu()
    {
        ErrorLogger.logMessage("Starting start menu");
        frame.setTitle("Pyramid Plunder");
        this.setBackground(new Color(84,42,0));
        this.setPreferredSize(new Dimension(400, 300));
        frame.setLocation((MainMenu.getFrameWidth()/2)-200,(MainMenu.getFrameHeight()/2)-300);
        setUpOne();
        frame.add(this);
        frame.setIconImage(GraphicsManager.getGraphics(69));
        frame.setVisible(true);
        frame.pack();
        frame.repaint();
    }
/** 
 * In this setUpOne method, the first few messages and options are shown for the 
 * user.
 */      
    private void setUpOne()
    {
        this.removeAll();
        
        JLabel welcome = new JLabel("Welcome to Pyramid Plunder");
        welcome.setBounds(0,0,400,60);
        welcome.setFont(new Font("Papyrus", Font.BOLD, 25));
        welcome.setForeground(Color.yellow);
        welcome.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(welcome);
        
        //New User
        JButton s = new JButton("Log in as a New User",null);
        s.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    setUpThree();
		}    
	});
        s.setBounds(10, 220, 180, 40);
        s.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(s);
        
        //existing user
        JButton b = new JButton("Log in as an Existing User",null);
        b.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    setUpTwo();
		}    
	});
        b.setBounds(210, 220, 180, 40);
        b.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(b);
        
        this.repaint();
    }
/** 
 *  Here the Username and Password options are set up.
 */      
    private void setUpTwo()
    {
        this.removeAll();
        
        JLabel welcome = new JLabel("Welcome to Pyramid Plunder");
        welcome.setBounds(0,0,400,60);
        welcome.setFont(new Font("Papyrus", Font.BOLD, 25));
        welcome.setForeground(Color.yellow);
        welcome.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(welcome);
        
        JLabel un = new JLabel("Username:");
        un.setBounds(0,90,100,30);
        un.setFont(new Font("Papyrus", Font.BOLD, 16));
        un.setForeground(Color.yellow);
        un.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(un);
        
        JLabel pa = new JLabel("Password:");
        pa.setBounds(0,140,100,30);
        pa.setFont(new Font("Papyrus", Font.BOLD, 16));
        pa.setForeground(Color.yellow);
        pa.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(pa);
        
        final JTextField userName = new JTextField("");
        userName.setFont(new Font("Papyrus", Font.BOLD, 25));
        userName.setBounds(100,80,280,40);
        this.add(userName);
        
        final JPasswordField password = new JPasswordField();
        password.setFont(new Font("Papyrus", Font.BOLD, 25));
        password.setBounds(100,130,280,40);
        this.add(password);
        
        JButton s = new JButton("Back",null);
        s.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    //GameRunner g = new GameRunner();
                    setUpOne();
		}    
	});
        s.setBounds(10, 220, 180, 40);
        s.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(s);
        
        //existing user
        JButton b = new JButton("Login",null);
        b.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    existingUser(userName.getText() ,password.getPassword());
                    setUpTwo();
		}    
	});
        b.setBounds(210, 220, 180, 40);
        b.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(b);
        
        this.repaint();
    }
/** 
 *  In this method, a third option for setting up the game is made for the
 * user.
 */      
    private void setUpThree()
    {
        this.removeAll();
        
        JLabel welcome = new JLabel("Welcome to Pyramid Plunder");
        welcome.setBounds(0,0,400,60);
        welcome.setFont(new Font("Papyrus", Font.BOLD, 25));
        welcome.setForeground(Color.yellow);
        welcome.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(welcome);
        
        JLabel un = new JLabel("Username:");
        un.setBounds(0,80,100,30);
        un.setFont(new Font("Papyrus", Font.BOLD, 16));
        un.setForeground(Color.yellow);
        un.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(un);
        
        JLabel pa = new JLabel("Password:");
        pa.setBounds(0,130,100,30);
        pa.setFont(new Font("Papyrus", Font.BOLD, 16));
        pa.setForeground(Color.yellow);
        pa.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(pa);
        
        JLabel pa2 = new JLabel("Confirm");
        pa2.setBounds(0,180,100,15);
        pa2.setFont(new Font("Papyrus", Font.BOLD, 12));
        pa2.setForeground(Color.yellow);
        pa2.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(pa2);
        
        JLabel pa22 = new JLabel("Password:");
        pa22.setBounds(0,195,100,15);
        pa22.setFont(new Font("Papyrus", Font.BOLD, 12));
        pa22.setForeground(Color.yellow);
        pa22.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(pa22);
        
        final JTextField userName = new JTextField("");
        userName.setFont(new Font("Papyrus", Font.BOLD, 25));
        userName.setBounds(100,70,280,40);
        this.add(userName);
        
        final JPasswordField password = new JPasswordField();
        password.setFont(new Font("Papyrus", Font.BOLD, 25));
        password.setBounds(100,120,280,40);
        this.add(password);
        
        final JPasswordField password2 = new JPasswordField();
        password2.setFont(new Font("Papyrus", Font.BOLD, 25));
        password2.setBounds(100,170,280,40);
        this.add(password2);
        
        JButton s = new JButton("Back",null);
        s.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    setUpOne();
		}    
	});
        s.setBounds(10, 220, 180, 40);
        s.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(s);
        
        //existing user
        JButton b = new JButton("Create Login",null);
        b.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    newUser(userName.getText() ,password.getPassword(), password2.getPassword());
		}    
	});
        b.setBounds(210, 220, 180, 40);
        b.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(b);
        
        this.repaint();
    }
 /** 
 *  In this newUser method, the String username, char password, and char confirm 
 * password is passed; the user is prompted with the right messages.
 */     
    private void newUser(String username, char[] password, char[] confirmPassword)
    {
        
        name = username;
        if(password.length==confirmPassword.length)
        {
            if(password.length==0 && username.length()==0)
            {
                ErrorLogger.logError("Please enter a username and a password");
            }
            else if(password.length==0)
            {
                ErrorLogger.logError("Please enter a password");
            }
            else if(username.length()==0)
            {
                ErrorLogger.logError("Please enter a username");
            }
            else
            {
                boolean samePassword = true;
                for(int i = 0; i < password.length; i++)
                {
                    if(password[i]!=confirmPassword[i])
                    {
                        samePassword = false;
                    }
                }
                
                if(samePassword)
                {
                    try
                    {
                        BufferedWriter w = null;
                        File file = new File(username+"PLAYERDATA");
                        
                        if(!file.isFile())
                        {
                            w = new BufferedWriter(new FileWriter(file));
                            float passwordKey = 1;
                            for(int i = 0; i < password.length; i++)
                            {
                                passwordKey *= (String.valueOf(password[i]).hashCode()%127)+1;
                                for(int j = 0; j < password.length; j++)
                                {
                                    passwordKey-=3;
                                }
                            }
                            passwordKey = passwordKey % 103619;
                            w.write(String.valueOf(passwordKey));
                            w.close();
                            startGame(null);
                            
                        }
                        else
                        {
                            ErrorLogger.logError(""
                                    + "             This username already exists.\n"
                                    + "Try a diffrent username or logon as an existing user.");
                        }
                    
                        
                    }
                    catch(Exception e)
                    { ErrorLogger.logError("Can't save login " + e);}
                }
                else
                {
                    ErrorLogger.logError("The Passwords you have entered don't match");
                }
            }
        }
        else
        {
            ErrorLogger.logError("The Passwords you have entered don't match");
        }
    }
  /** 
 *  In this existingUser method, the String username, and char password are passed; 
 * the username and password are checked through the text file and if 
 * they already exist the user is prompted with the right message.
 */    
    private void existingUser(String username, char[] password)
    {
        name = username;
            if(password.length==0 && username.length()==0)
            {
                ErrorLogger.logError("Please enter a username and a password");
            }
            else if(password.length==0)
            {
                ErrorLogger.logError("Please enter a password");
            }
            else if(username.length()==0)
            {
                ErrorLogger.logError("Please enter a username");
            }
            else
            {

                    try
                    {
                        Scanner w = null;
                        File file = new File(username+"PLAYERDATA");
                        
                        if(file.isFile())
                        {
                            w = new Scanner(file);
                            float passwordKey = 1;
                            
                            for(int i = 0; i < password.length; i++)
                            {
                                passwordKey *= (String.valueOf(password[i]).hashCode()%127)+1;
                                for(int j = 0; j < password.length; j++)
                                {
                                    passwordKey-=3;
                                }
                            }
                            passwordKey = passwordKey % 103619;
                            System.out.println(passwordKey);
                            if(passwordKey == w.nextFloat())
                            {
                                startGame(username);
                            }
                                
                            w.close();
                            
                        }
                        else
                        {
                            ErrorLogger.logError("This username dosen't exist");
                        }
                    
                        
                    }
                    catch(Exception e)
                    { ErrorLogger.logError("Can't get player data " + e);}

        }

    }
   /** 
 *  In this startGame method, the String name is passed; the frames are able to be
 * reset for the mainmenu and startgame.
 */   
    private void startGame(String name)
    {
        ErrorLogger.logMessage(name+ "...starting");
        GameRunner.resetAll();
        frame.dispose();
        MainMenu.startGame(name);
    }
}
