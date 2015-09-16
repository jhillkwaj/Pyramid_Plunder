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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * This LevelMenu Class extends Jpanel; many functions and objectives of the game
 * are met through this class, for example the various options in the Main Menu
 * for the user are created, the pyramid difficulty are set, the shop for 
 * materials and resources is created, and the options to buy, sell, and statistics
 * for the materials are able to show-this is randomly selected, a unique feature.
 */
public class LevelMenu extends JPanel {
    private JFrame frame = new JFrame();
    private int weapon = 0;
/**
 * This openLevelMenu method creates the dimension and main menu start screen.
 */    
    public void openLevelMenu()
    {
        try
        {
        this.removeAll();
        frame.setTitle("Start Game");
        this.setBackground(new Color(255, 228, 94));
        this.setPreferredSize(new Dimension(400, 300));
        frame.setLocation((MainMenu.getFrameWidth()/2)-200,(MainMenu.getFrameHeight()/2)-300);
        setUpOne();
        frame.add(this);
        frame.setIconImage(GraphicsManager.getGraphics(69));
        frame.setVisible(true);
        frame.pack();
        frame.repaint();
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        }
        catch(Exception e)
        {
            ErrorLogger.logError("Could not open level menu " + e);
        }
    }
/**
 * This setUpOne method creates the opening messages for the user, it sets up the 
 * various options-such as, Explore Pyramid, Item Shop, Upgrades, and Exit-the 
 * functions and playability is also set here.
 */    
    private void setUpOne()
    {
        this.removeAll();
        
        JLabel welcome = new JLabel("Welcome to Pyramid Plunder");
        welcome.setBounds(0,0,400,60);
        welcome.setFont(new Font("Papyrus", Font.BOLD, 25));
        welcome.setForeground(Color.BLACK);
        welcome.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(welcome);
        
        JButton s = new JButton("Explore Pyramid",null);
        s.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                     setUpStart();
		}    
	});
        s.setBounds(10, 150, 180, 40);
        s.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(s);
        
        JButton b = new JButton("Item Shop",null);
        b.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    setUpShop();
		}    
	});
        b.setBounds(210, 150, 180, 40);
        b.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(b);
        
        this.repaint();
        
        
        
        JButton u = new JButton("Upgrades",null);
        u.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    setUpUpgrades();
		}    
	});
        u.setBounds(210, 220, 180, 40);
        u.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(u);
        
        
        
        JButton e = new JButton("Exit",null);
        e.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    ErrorLogger.logMessage("Goodbye");
                    System.exit(0);
		}    
	});
        e.setBounds(10, 220, 180, 40);
        e.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(e);
        this.repaint();
    }
    
    
    
    
/**
 * In this setUpStart method, the Pyramid Difficulty are set through the Explore
 * Pyramids Option; Easy, Medium, Hard, Pyramid of Khafre, Pyramid of Horus, 
 * Pyramid of Ra, and Pyramid of Ma'at are set here with distinct features.
 */    
    public void setUpStart()
    {
        this.removeAll();
        
        JLabel welcome = new JLabel("Select Pyramid Difficulty");
        welcome.setBounds(0,0,400,60);
        welcome.setFont(new Font("Papyrus", Font.BOLD, 25));
        welcome.setForeground(Color.BLACK);
        welcome.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(welcome);
        
        JButton s = new JButton("Easy Pyramid",null);
        s.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    JOptionPane.showConfirmDialog(null, "Generating Level", "Generating",
                    JOptionPane.OK_OPTION,JOptionPane.PLAIN_MESSAGE);
                    frame.dispose();
                    GameRunner g = new GameRunner(200,1);
                    
                    
		}    
	});
        s.setBounds(10, 100, 180, 40);
        s.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(s);
        
        JButton b = new JButton("Medium Pyramid",null);
        b.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    JOptionPane.showConfirmDialog(null, "Generating Level, this may take a minute", "Generating",
                    JOptionPane.OK_OPTION,JOptionPane.PLAIN_MESSAGE);
                    frame.dispose();
                    GameRunner g = new GameRunner(400,2);
		}    
	});
        b.setBounds(210, 100, 180, 40);
        b.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(b);
        
        this.repaint();
        
        
        
        JButton u = new JButton("Hard Pyramid",null);
        u.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    JOptionPane.showConfirmDialog(null, "Generating Level, this may take few minutes", "Generating",
                    JOptionPane.OK_OPTION,JOptionPane.PLAIN_MESSAGE);
                    frame.dispose();
                    GameRunner g = new GameRunner(600,3);
		}    
	});
        u.setBounds(10, 150, 180, 40);
        u.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(u);
        
        
        
        JButton e = new JButton("Pyramid of Khafre",null);
        e.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    JOptionPane.showConfirmDialog(null, "Generating Level, this may take a long time time", "Generating",
                    JOptionPane.OK_OPTION,JOptionPane.PLAIN_MESSAGE);
                    frame.dispose();
                    GameRunner g = new GameRunner(1500,3);
		}    
	});
        e.setBounds(210, 150, 180, 40);
        e.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(e);
        
        JButton h = new JButton("Pyramid of Horus",null);
        h.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    JOptionPane.showConfirmDialog(null, "Generating Level, this may take few minutes", "Generating",
                    JOptionPane.OK_OPTION,JOptionPane.PLAIN_MESSAGE);
                    frame.dispose();
                    GameRunner g = new GameRunner(700,4);
		}    
	});
        h.setBounds(10, 200, 180, 40);
        h.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(h);
        
        JButton r = new JButton("Pyramid of Ra",null);
        r.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    JOptionPane.showConfirmDialog(null, "Generating Level, this may take a very long time", "Generating",
                    JOptionPane.OK_OPTION,JOptionPane.PLAIN_MESSAGE);
                    frame.dispose();
                    GameRunner g = new GameRunner(1700,4);
		}    
	});
        r.setBounds(210, 200, 180, 40);
        r.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(r);
        
        JButton m = new JButton("Pyramid of Ma'at",null);
        m.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    JOptionPane.showConfirmDialog(null, "Generating Level, this may take a long time", "Generating",
                    JOptionPane.OK_OPTION,JOptionPane.PLAIN_MESSAGE); 
                    frame.dispose();
                    GameRunner g = new GameRunner(1000,5);   
		}    
	});
        m.setBounds(10, 250, 180, 40);
        m.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(m);
        
        

        
        JButton k = new JButton("Back",null);
        k.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    setUpOne();
		}    
	});
        k.setBounds(210, 250, 180, 40);
        k.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(k);
        this.repaint();
        
        
    }
/**
 * This setUpShop method creates the Resources Shop for randomly generated 
 * weapons and materials; this creates various messages to engage the user, 
 * options of Buy, Sell, and Coins are also made.
 */    
    public void setUpShop()
    {
        this.removeAll();
        
        JLabel welcome = new JLabel("Welcome to my humble shop");
        welcome.setBounds(0,0,400,60);
        welcome.setFont(new Font("Papyrus", Font.BOLD, 25));
        welcome.setForeground(Color.BLACK);
        welcome.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(welcome);
        
        JLabel doFor = new JLabel("What can I do for you?");
        doFor.setBounds(0,30,400,60);
        doFor.setFont(new Font("Papyrus", Font.BOLD, 25));
        doFor.setForeground(Color.BLACK);
        doFor.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(doFor);
        
        JButton s = new JButton("Buy",null);
        s.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                     setUpBuy();
		}    
	});
        s.setBounds(10, 150, 180, 40);
        s.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(s);
        
        JButton b = new JButton("Sell",null);
        b.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    setUpSell();
		}    
	});
        b.setBounds(210, 150, 180, 40);
        b.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(b);
        
        this.repaint();
        
        
        
        JLabel coin = new JLabel("Coins: " + GameRunner.getMoney());
        coin.setBounds(210,220,200,50);
        coin.setFont(new Font("Papyrus", Font.BOLD, 16));
        coin.setForeground(Color.BLACK);
        coin.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(coin);
        
        
        
        JButton e = new JButton("Back",null);
        e.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    setUpOne();
		}    
	});
        e.setBounds(10, 220, 180, 40);
        e.setBackground(Color.yellow);
        this.setLayout(null);
        this.add(e);
        this.repaint();
    }
/**
 * This setUpBuy method sets up the randomly generated weapons for the user to 
 * purchase with the number of coins earned; this also creates messages for the
 * user; it indicates the statistics for all the weapons being generated like 
 * speed, range, and damage. The only weapons which appear are the ones which 
 * the user can afford.
 */    
    private void setUpBuy()
    {
        this.removeAll();

        if(GameRunner.getMoney()>20)
        {
            Weapon wep = Weapon.randomWeapon();
        while(Weapon.priceWeapon(wep)*1.66>GameRunner.getMoney())
        {
            wep = Weapon.randomWeapon();
        }
            final Weapon w = wep;
            
        JLabel welcome = new JLabel("May I interest you in this");
        welcome.setBounds(0,0,400,60);
        welcome.setFont(new Font("Papyrus", Font.BOLD, 25));
        welcome.setForeground(Color.BLACK);
        welcome.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(welcome);
            
        JLabel doFor = new JLabel("Fine " + w.name);
        doFor.setBounds(0,30,400,60);
        doFor.setFont(new Font("Papyrus", Font.BOLD, 20));
        doFor.setForeground(Color.BLACK);
        doFor.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(doFor);
        
        JLabel coin = new JLabel("Coins: " + GameRunner.getMoney());
        coin.setBounds(210,220,200,50);
        coin.setFont(new Font("Papyrus", Font.BOLD, 16));
        coin.setForeground(Color.BLACK);
        coin.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(coin);
        
        JLabel image = new JLabel(new ImageIcon(GraphicsManager.getGraphics(w.graphics[0]).getScaledInstance(50, 50, 50)));
        image.setBounds(50,100,50,50);
        this.add(image);
        
        JLabel cost = new JLabel("Price: " + (int)((float)Weapon.priceWeapon(w)*1.66) + " coins");
        cost.setBounds(100,100,150,20);
        cost.setFont(new Font("Papyrus", Font.BOLD, 16));
        cost.setForeground(Color.BLACK);
        cost.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(cost);
        
        JLabel dmg = new JLabel("Dmg: " + w.damage);
        dmg.setBounds(100,120,150,20);
        dmg.setFont(new Font("Papyrus", Font.BOLD, 16));
        dmg.setForeground(Color.BLACK);
        dmg.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(dmg);
        
        JLabel rng = new JLabel("Rng: " + (int)(w.rangeX*w.rangeY/100));
        rng.setBounds(100,140,150,20);
        rng.setFont(new Font("Papyrus", Font.BOLD, 16));
        rng.setForeground(Color.BLACK);
        rng.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(rng);
        
        JLabel spd = new JLabel("Spd: " + (int)((1f/((float)w.cooldownTime))*500));
        spd.setBounds(100,160,150,20);
        spd.setFont(new Font("Papyrus", Font.BOLD, 16));
        spd.setForeground(Color.BLACK);
        spd.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(spd);
        
        this.setLayout(null);
        this.repaint();
        
        
        
        JButton next = new JButton("What else do you have?",null);
        next.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    setUpBuy();
		}    
	});
        next.setBounds(220, 170, 170, 40);
        next.setBackground(Color.yellow);
        this.add(next);
        
        JButton buy = new JButton("I'll take it!",null);
        buy.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    GameRunner.addMoney(-(int)((float)Weapon.priceWeapon(w)*1.66));
                    GameRunner.addWeapon(w);
                    SaveLoad.save(false);
                    setUpBuy();
		}    
	});
        buy.setBounds(220, 120, 170, 40);
        buy.setBackground(Color.yellow);
        this.add(buy);
        }
        else 
        {
        JLabel welcome = new JLabel("Come back when you have more coin");
        welcome.setBounds(0,0,400,60);
        welcome.setFont(new Font("Papyrus", Font.BOLD, 20));
        welcome.setForeground(Color.BLACK);
        welcome.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(welcome);
        }
        
        JButton e = new JButton("Back",null);
        e.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    setUpShop();
		}    
	});
        e.setBounds(10, 220, 180, 40);
        e.setBackground(Color.yellow);
        this.add(e);
        
        this.repaint();
    }
/**
 * This setUpSell method creates the selling function of the shop; it creates 
 * messages for the user and indicates the worth of the weapon being sold 
 * by the user as well as the damage, speed, and range.
 */    
    private void setUpSell()
    {
        this.removeAll();

        if(GameRunner.getWeaponList().size()>0)
        {
            final Weapon w = GameRunner.getWeaponList().get(weapon);
        
            
        JLabel welcome = new JLabel("Sell");
        welcome.setBounds(0,0,400,60);
        welcome.setFont(new Font("Papyrus", Font.BOLD, 25));
        welcome.setForeground(Color.BLACK);
        welcome.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(welcome);
            
        JLabel doFor = new JLabel("" + w.name);
        doFor.setBounds(0,30,400,60);
        doFor.setFont(new Font("Papyrus", Font.BOLD, 20));
        doFor.setForeground(Color.BLACK);
        doFor.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(doFor);
        
        JLabel coin = new JLabel("Coins: " + GameRunner.getMoney());
        coin.setBounds(210,220,200,50);
        coin.setFont(new Font("Papyrus", Font.BOLD, 16));
        coin.setForeground(Color.BLACK);
        coin.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(coin);
        
        JLabel image = new JLabel(new ImageIcon(GraphicsManager.getGraphics(w.graphics[0]).getScaledInstance(50, 50, 50)));
        image.setBounds(50,100,50,50);
        this.add(image);
        
        JLabel cost = new JLabel("Sell for: " + (int)((float)Weapon.priceWeapon(w)*.66) + " coins");
        cost.setBounds(100,100,150,20);
        cost.setFont(new Font("Papyrus", Font.BOLD, 16));
        cost.setForeground(Color.BLACK);
        cost.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(cost);
        
        JLabel dmg = new JLabel("Dmg: " + w.damage);
        dmg.setBounds(100,120,150,20);
        dmg.setFont(new Font("Papyrus", Font.BOLD, 16));
        dmg.setForeground(Color.BLACK);
        dmg.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(dmg);
        
        JLabel rng = new JLabel("Rng: " + (int)(w.rangeX*w.rangeY/100));
        rng.setBounds(100,140,150,20);
        rng.setFont(new Font("Papyrus", Font.BOLD, 16));
        rng.setForeground(Color.BLACK);
        rng.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(rng);
        
        JLabel spd = new JLabel("Spd: " + (int)((1f/((float)w.cooldownTime))*500));
        spd.setBounds(100,160,150,20);
        spd.setFont(new Font("Papyrus", Font.BOLD, 16));
        spd.setForeground(Color.BLACK);
        spd.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(spd);
        
        this.setLayout(null);
        this.repaint();
        
        
        
        JButton next = new JButton("Next",null);
        next.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    if(++weapon>=GameRunner.getWeaponList().size())
                    {
                        weapon=0;
                    }
                    setUpSell();
		}    
	});
        next.setBounds(220, 170, 170, 40);
        next.setBackground(Color.yellow);
        this.add(next);
        
        JButton buy = new JButton("Sell",null);
        buy.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    GameRunner.addMoney((int)((float)Weapon.priceWeapon(w)*.66));
                    GameRunner.removeWeapon(weapon);
                    SaveLoad.save(false);
                    weapon = 0;
                    setUpSell();
		}    
	});
        buy.setBounds(220, 120, 170, 40);
        buy.setBackground(Color.yellow);
        this.add(buy);
        }
        else 
        {
        JLabel welcome = new JLabel("You don't have anything to sell");
        welcome.setBounds(0,0,400,60);
        welcome.setFont(new Font("Papyrus", Font.BOLD, 20));
        welcome.setForeground(Color.BLACK);
        welcome.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(welcome);
        }
        
        JButton e = new JButton("Back",null);
        e.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    setUpShop();
		}    
	});
        e.setBounds(10, 220, 180, 40);
        e.setBackground(Color.yellow);
        this.add(e);
        
        this.repaint();
    }
    
    private void setUpUpgrades()
    {
         this.removeAll();
        if(GameRunner.getMoney()>=((int)Math.pow((2*GameRunner.getPlayer().armor)+1,2)+20))
        {
         JLabel welcome = new JLabel("Upgrade Armor to Level " + (GameRunner.getPlayer().armor + 1));
        welcome.setBounds(0,0,400,60);
        welcome.setFont(new Font("Papyrus", Font.BOLD, 25));
        welcome.setForeground(Color.BLACK);
        welcome.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(welcome);
         
         JLabel coin = new JLabel("Coins: " + GameRunner.getMoney());
        coin.setBounds(210,160,200,50);
        coin.setFont(new Font("Papyrus", Font.BOLD, 16));
        coin.setForeground(Color.BLACK);
        coin.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(coin);
        
        JLabel cost = new JLabel("Price: " + ((int)Math.pow((2*GameRunner.getPlayer().armor)+1,2)+20) + " coins");
        cost.setBounds(100,100,150,20);
        cost.setFont(new Font("Papyrus", Font.BOLD, 16));
        cost.setForeground(Color.BLACK);
        cost.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(cost);
        
        JButton buy = new JButton("I'll take it!",null);
        buy.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    GameRunner.getPlayer().addArmor(1);
                    GameRunner.addMoney(-(int)Math.pow((2*GameRunner.getPlayer().armor)+1,2)-20);
                    SaveLoad.save(false);
                    setUpUpgrades();
		}    
	});
        buy.setBounds(220, 220, 170, 40);
        buy.setBackground(Color.yellow);
        this.add(buy);
        }
        else
        {
        JLabel welcome = new JLabel("Come back when you have " + ((int)Math.pow((2*GameRunner.getPlayer().armor)+1,2)+200) + " coins");
        welcome.setBounds(0,0,400,60);
        welcome.setFont(new Font("Papyrus", Font.BOLD, 20));
        welcome.setForeground(Color.BLACK);
        welcome.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(welcome);
        }
        
         JButton e = new JButton("Back",null);
        e.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    setUpOne();
		}    
	});
        e.setBounds(10, 220, 170, 40);
        e.setBackground(Color.yellow);
        this.add(e);
        
        this.repaint();
    }
}
