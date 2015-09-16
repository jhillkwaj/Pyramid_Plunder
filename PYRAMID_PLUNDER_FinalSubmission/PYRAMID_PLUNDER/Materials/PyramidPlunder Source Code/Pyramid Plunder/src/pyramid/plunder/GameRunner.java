/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid.plunder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
 */
public class GameRunner extends JPanel implements KeyListener {
     public static JFrame frame = new JFrame();
     
    private static Timer timer;
    
    private int timerSpeed = 20;
    
    private Shader shader = new Shader(0);
    
     //stores things to draw with a key to axcess them
    private static TreeMap<Integer,Drawable> drawables = new TreeMap<Integer,Drawable>();
    private BufferedImage bufferedImage;
    
    private static int[] worldFrame = {0,0};
    
    private static int playerKey;
    
    private static TreeMap<Integer,PhysicsObject> collidables = new TreeMap<Integer,PhysicsObject>();
    
    private boolean keys[] = new boolean[5];
    
    private int removePart = 0;
    
    long lastRunTime = 0;
    
    private static int money = 0;
    
    private static int startMoney = 0;
    
    private int coinBarSpinNum = 20;
    
    private int coinSpinTime = 0;
    
    private static Color c = new Color(123,174,222,255);
    private static int fadeBackTime = 255;
    
    private Background back;
    
    protected static ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    
    protected static String onScreenText = "";
    
    protected static boolean tutorial = false;
    
    protected static long startTime = 0;
    
/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
     * @param size
     * @param difficulty
 */    
    public GameRunner(int size, int difficulty)
    {
        try
        {
            int armor = 0;
            if(getPlayer()!=null)
            {getPlayer().getArmor();}
            tutorial = false;
            onScreenText = "";
            ErrorLogger.logMessage("Starting Generation");
        c = new Color(123,174,222,255);
        fadeBackTime = 255;
        frame = new JFrame();
            timer = null;
            drawables.clear();
            collidables.clear();
        frame.setVisible(false);
        frame.setTitle("Pyramid Plunder");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.MAXIMIZED_BOTH);  
        frame.isFocused();
        frame.setIconImage(GraphicsManager.getGraphics(69));
        frame.add(this);
        frame.setVisible(true); 
        frame.repaint();
        
       
        
        drawables.put(shader.getKey(), shader);
        
        Player p = new Player(new int[] {5,6,7,8,9,10});
        p.setArmor(armor);
        //p.setLoc(new int[] {0, 20, 0 , 0});
        drawables.put(p.getKey(), p);
        playerKey = p.getKey();
        collidables.put(p.getKey(), p);
        
        
             
        LevelRunner.generateLevel(size, (int) Math.ceil(size*1.75), difficulty);
        
        if(weapons.size()==0&&money<30)
        {
            Sword s = new Sword(6);
            s.name = "Trusty Sword";
            weapons.add(s);
        }

        p.setWeapon(weapons.get(0));
        
        shader.fadeIn(100,1f);
        
        p.setLoc(new int[] {-2050,1820,Integer.MIN_VALUE,Integer.MIN_VALUE});
        p.velocity = new float[] {0,0};
        
        back = new Background();

        frame.addKeyListener(this);
        
        timer = new Timer(timerSpeed, timerListener);
        timer.start();
        
        startMoney = money;
        
        startTime = System.currentTimeMillis();
        }
        catch(Exception e)
        {
            ErrorLogger.logNonRecoverableError("Can't Start Game: " + e);
        }
    }
/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
     * @param saveName
 */    
    public GameRunner(String saveName)
    {
        if(SaveLoad.playerData(saveName))
        {
        try
        {
            tutorial = false;
            onScreenText = "";
            ErrorLogger.logMessage("Starting Game");
            drawables.clear();
            c = new Color(123,174,222,255);
        fadeBackTime = 255;
            timer = null;
        frame = new JFrame();
        frame.setVisible(false);
        frame.setTitle("Pyramid Plunder");
        frame.setIconImage(GraphicsManager.getGraphics(69));
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.MAXIMIZED_BOTH);  
        frame.isFocused();
        
        frame.add(this);

        drawables.put(shader.getKey(), shader);

             
        if(!SaveLoad.load(saveName))
        {
            LevelMenu m = new LevelMenu();
            m.openLevelMenu();
        }
        else
        {
        frame.setVisible(true); 
        frame.repaint();
        shader.fadeIn(50,1f);
        
        ((Player)collidables.get(playerKey)).setWeapon(weapons.get(0));
        
        back = new Background();

        frame.addKeyListener(this);
        
        timer = new Timer(timerSpeed, timerListener);
        timer.start();
        
        startMoney = money;
        
        startTime = System.currentTimeMillis();
        }
        }
        catch(Exception e)
        {
            ErrorLogger.logNonRecoverableError("Can't Start Game: " + e);
        }
        }
        else
        {
            LevelMenu m = new LevelMenu();
            m.openLevelMenu();
        }
    }
    
/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
     * @param tutorial
 */    
        public GameRunner(boolean tutorial)
        {
        try
        {
            this.tutorial = tutorial;
        onScreenText = "Press " + Character.toString((char)8592) + " and " + Character.toString((char)8594) + " to move";
        ErrorLogger.logMessage("Starting tutorial");
        c = new Color(123,174,222, 255);
        fadeBackTime = 255;
        frame = new JFrame();
        timer = null;
        drawables.clear();
        frame.setIconImage(GraphicsManager.getGraphics(69));
        collidables.clear();
        frame.setVisible(false);
        frame.setTitle("Pyramid Plunder");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.MAXIMIZED_BOTH);  
        frame.isFocused();
        back = new Background();
        
        
        
       
        
        drawables.put(shader.getKey(), shader);
        
        Player p = new Player(new int[] {5,6,7,8,9,10});
        
        drawables.put(p.getKey(), p);
        playerKey = p.getKey();
        collidables.put(p.getKey(), p);
        
        
             
        LevelRunner.generateTutorial();
        Sword s = new Sword(1);
        s.graphics = new int[] {57,58,58};
        s.name = "Training Sword";
        
        weapons.add(s);
        p.setWeapon(weapons.get(0));
        
        shader.fadeIn(200,1f);
        
        p.setLoc(new int[] {-2050,1820,Integer.MIN_VALUE,Integer.MIN_VALUE});
        p.velocity = new float[] {0,0};
        
        frame.add(this);
        frame.setVisible(true); 
        frame.repaint();
        frame.addKeyListener(this);
        
        timer = new Timer(timerSpeed, timerListener);
        timer.start();
        
        money = 0;
        }
        catch(Exception e)
        {
            ErrorLogger.logNonRecoverableError("Can't Start Game: " + e);
        }
    }
/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
     * @param lines
 */    
    public static void loadPlayerData(String[] lines)
    { 
        Player p = new Player(new int[] {5,6,7,8,9,10});
        
        //location
        String[] locationParts = lines[0].split(",");
        int[] location = new int[] {Integer.parseInt(locationParts[0]),
            Integer.parseInt(locationParts[1]),p.getLoc()[2],p.getLoc()[3]};
        
        p.setLoc(location);
        
        //health
        String[] healthParts = lines[1].split(",");
        
        p.setHealth(Integer.parseInt(healthParts[0]));
        
        //armor
        p.setArmor(Integer.parseInt(healthParts[1]));
        
        //jumps
        int jumps = Integer.parseInt(lines[2]);
        while(jumps>0)
        {
            p.addJump();;
            jumps--;
        }
        
        //velocity
        String[] velocityParts = lines[3].split(",");
        
        p.setVelocity( new float[] {Float.parseFloat(velocityParts[0]),
            Float.parseFloat(velocityParts[1])} );
        
        
        p.setWeapon(new Sword(10));
        
        drawables.put(p.getKey(), p);
        playerKey = p.getKey();
        collidables.put(p.getKey(), p);
    }
    
    ActionListener timerListener = new ActionListener() 
   	{
                   @Override
   		public void actionPerformed(ActionEvent e)
   		{
                    long time = System.currentTimeMillis();
                    
                    if(timer.getDelay()>5 || Math.random()>.2)
                        repaint();
                    
                    keyDown();
                    
                    if(timer.getDelay()>5 || Math.random()>.2)
                        centerFrame();
                    

                    if(timer.getDelay()>5 || Math.random()>.2)
                       LevelRunner.addObjects(worldFrame[0]+400, worldFrame[1]+300, 2000f); 
                       
                       if(timer.getDelay()>5 || Math.random()>.2)
                           cleanDrawables(worldFrame[0]+400, worldFrame[1]+300, 2000f);

                    
                    int timeGap = (int)(System.currentTimeMillis() - lastRunTime);
                    if(timeGap>22 && timer.getDelay()>1 )
                    { timer.setDelay(timer.getDelay()-1); }
                    else if(timeGap<22)
                    { timer.setDelay(timer.getDelay()+1); }
                   // System.out.println(timeGap + "    " + timer.getDelay());
                    lastRunTime = System.currentTimeMillis();
                    
                    //imer.setDelay(timer.getDelay()-((int)lastRunTime));
   		}

   	};
/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
     * @param g
 */    
     @Override
    public void paintComponent(Graphics g)
    { 
        g.setColor(c);
        
        
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if(!tutorial&&fadeBackTime > 0 && getPlayer().getLoc()[0] > 30 && getPlayer().getLoc()[1] > 30)
        {
            fadeBackToBlack();
            fadeBackTime--;
        }
        
        
        Graphics2D g2d = (Graphics2D) g;
        if(bufferedImage == null)
        {
        bufferedImage =  new BufferedImage(800, 600, 
                         BufferedImage.BITMASK);
        }
        else
        {
            //bufferedImage.flush();
        }
        Graphics t = bufferedImage.getGraphics();
        back.Draw(t);
        
        drawColor(t);
        
        
        
        for(Drawable draw : drawables.values())
        {
            draw.Draw(t);
        }
        
       t.drawImage(GraphicsManager.getGraphics(1), 0, 560, 800, 40, this);
        
        
        for(int i = 0; (i < ((Player)drawables.get(playerKey)).getHealth() && i < 110); i += 10)
        {
            t.drawImage(GraphicsManager.getGraphics(2), (int)(10+(1.25*i)), 568, 10, 10, this);
        }
        //System.out.println(""+getPlayer().getArmor());
        for(int i = 0; (i < getPlayer().getArmor() && i < 22); i += 2)
        {
             t.drawImage(GraphicsManager.getGraphics(3), (int)(10+(5*i*1.25)), 582, 10, 10, this);
        }
        
        t.drawImage(GraphicsManager.getGraphics(coinBarSpinNum), 730, 570, 18, 18, this);
        coinSpinTime++;
        
        if(coinSpinTime > 5)
        {
            coinSpinTime = 0;
            coinBarSpinNum++;
        }
        
        if(coinBarSpinNum>27)
        { coinBarSpinNum = 20; }
        
        
        t.setFont(new Font("Papyrus", Font.PLAIN, 20));
        t.setColor(new Color(255,144,0));
        
        t.drawString(""+money, 750, 585);
        
        t.drawImage(GraphicsManager.getGraphics(weapons.get(0).graphics[0]),200, 565, 30, 30,this);
        
        for(int i = 1; (i < 10 && i < weapons.size());i++)
        {
           t.drawImage(GraphicsManager.getGraphics(weapons.get(i).graphics[0]),300+(40*i), 565, 30, 30,this);
        }
        
        g.drawImage(bufferedImage, 0,0,this.getWidth(),this.getHeight(), this);
        
        for(int i = 0; (i < 10 && i < weapons.size());i++)
        {
            g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
            g.setColor(new Color(230,230,230));
            if(i!=0 )
            {
                g.drawString(""+(i), (int)(((float)(300f+(40f*i))/800.0)*this.getWidth()), (int)((((float)(565f+5))/600.0)*this.getHeight()));
                if(weapons.get(i).name.length()<18)
                {
                    g.drawString(weapons.get(i).name, (int)(((float)(300f+(40f*i))/800.0)*this.getWidth()), (int)((((float)(565f+25f))/600.0)*this.getHeight()));
                }
                else
                {
                    g.drawString(weapons.get(i).name.substring(0, 16), (int)(((float)(300f+(40f*i))/800.0)*this.getWidth()), (int)((((float)(565f+25f))/600.0)*this.getHeight()));
                }
            }
            else
            {
                g.drawString(weapons.get(i).name, (int)(((float)(240f+(40f*i))/800.0)*this.getWidth()), (int)((((float)(565f+5f))/600.0)*this.getHeight()));
                g.drawString("Dmg: "+weapons.get(i).damage, (int)(((float)(240f+(40f*i))/800.0)*this.getWidth()), (int)((((float)(565f+14f))/600.0)*this.getHeight()));
                g.drawString("Rng: "+(int)(weapons.get(i).rangeX*weapons.get(i).rangeY/100), (int)(((float)(240f+(40f*i))/800.0)*this.getWidth()), (int)((((float)(565f+23f))/600.0)*this.getHeight()));
                g.drawString("Spd: "+(int)((1f/((float)weapons.get(i).cooldownTime))*500), (int)(((float)(240f+(40f*i))/800.0)*this.getWidth()), (int)((((float)(565f+32f))/600.0)*this.getHeight()));
                g.drawString("Val: "+Weapon.priceWeapon(weapons.get(i)), (int)(((float)(270f+(40f*i))/800.0)*this.getWidth()), (int)((((float)(565f+14f))/600.0)*this.getHeight()));
            }
        }
        helpText();
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.setColor(new Color(255,144,0));
        g.drawString(onScreenText, (int)(((float)(400f)/800.0)*this.getWidth())-(7*onScreenText.length()), (int)((((float)(100f))/600.0)*this.getHeight()));
    }
/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
 */    
    private void centerFrame()
    {
        int[] loc = ((GameObject)drawables.get(playerKey)).getLoc();
        worldFrame[0] = loc[0]-400+12;
        worldFrame[1] = loc[1]-300+19;
    }
/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
 */    
    private void cleanDrawables(int locX, int locY, double distance)
    {
        for(int i = (drawables.size()/100)*removePart; i < (drawables.size()/100)*(removePart+1); i++)
        {
            Drawable d = (Drawable)(drawables.values().toArray()[i]);
            if(d.isGameObject())
            {
                GameObject g = (GameObject)d;
                if(distance<Math.sqrt(Math.pow(locX-g.getLoc()[0], 2) + Math.pow(locY-g.getLoc()[1], 2)))
                {
                    drawables.remove(g.getKey());
                    i--;
                }
            }
        }
       
        
 
        for(int i = (collidables.size()/100)*removePart; i < (collidables.size()/100)*(removePart+1); i++)
        {
            PhysicsObject c = (PhysicsObject)(collidables.values().toArray()[i]);
                if(distance<Math.sqrt(Math.pow(locX-c.getLoc()[0], 2) + Math.pow(locY-c.getLoc()[1], 2)))
                {
                    collidables.remove(c.getKey());
                    i--;
                }
                
            }
        
         if(++removePart>99)
        {
            removePart=0;
        }
         
         
    }
/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
     * @param w
 */    
    public static void addWeapon(Weapon w)
    {
        weapons.add(w);
    }
/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
     * @param num
 */    
    public static void removeWeapon(int num)
    {
        weapons.remove(num);
    }
/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
     * @return the width of the frame
 */    
    public static int getFrameWidth()
    {
         return frame.getWidth();
    }
/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
     * @return the height of the frame
 */    
    public static int getFrameHeight()
    {
        return frame.getHeight();
    }
/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
     * @return the location of the frame
 */    
    public static int[] getFrameLoc()
    {
        return worldFrame;
    }
/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
     * @return the list of collidables
 */        
    public static ArrayList<PhysicsObject> getCollidables()
    {
        return new ArrayList<PhysicsObject>(collidables.values());
    }
/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
     * @param d
 */    
    public static void addDrawable(Drawable d)
    {
        drawables.put(d.getKey(), d);
    }
/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
     * @param d
 */    
    public static void removeDrawable(Drawable d)
    {
        drawables.remove(d.getKey());
        
        if(collidables.containsKey(d.getKey()))
        {
            collidables.remove(d);
        }
    }
/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
     * @param collidable
 */    
    public static void addCollidable(PhysicsObject collidable)
    {
        collidables.put(collidable.getKey(),collidable);
    }


    @Override
/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
 */    
    public void keyTyped(KeyEvent e) {
    }

    @Override
/**
 * This GameRunner Class extends JPanel, which is used for the screens, and 
 * implements KeyListener, an important facet for the whole game.
 */    
    public void keyReleased(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
	{
            keys[0]=false;
           ((Player)drawables.get(playerKey)).jump();
	}
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
	{
            keys[4]=false;
            ((Player)drawables.get(playerKey)).useWeapon();
	}
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
        {
            keys[3]=false;
             ((Player)drawables.get(playerKey)).stopMove();
	}
	if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
	{
            keys[2]=false;
             ((Player)drawables.get(playerKey)).stopMove();
	}
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
	{
            keys[1]=false;
            ((Player)drawables.get(playerKey)).useWeapon();
	}
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
	{
            OptionsMenu u = new OptionsMenu();
	}
        
        int weapon = 0;
        if (e.getKeyCode() == KeyEvent.VK_1)
	{
            weapon = 1;
	}
        if (e.getKeyCode() == KeyEvent.VK_2)
	{
            weapon = 2;
	}
        if (e.getKeyCode() == KeyEvent.VK_3)
	{
            weapon = 3;
	}
        if (e.getKeyCode() == KeyEvent.VK_4)
	{
            weapon = 4;
	}
        if (e.getKeyCode() == KeyEvent.VK_5)
	{
            weapon = 5;
	}
        if (e.getKeyCode() == KeyEvent.VK_6)
	{
            weapon = 6;
	}
        if (e.getKeyCode() == KeyEvent.VK_7)
	{
            weapon = 7;
	}
        if (e.getKeyCode() == KeyEvent.VK_8)
	{
            weapon = 8;
	}
        if (e.getKeyCode() == KeyEvent.VK_9)
	{
            weapon = 9;
	}
        if(weapon!=0&&weapon<weapons.size())
        {
            swapWeapons(weapon);
        }
    }

    @Override
/**
 * This keyPressed method is passed down KeyEvent e; the 
 */    
    public void keyPressed(KeyEvent e) {
        
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
		{
                    keys[3]=true;
                    
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
		{
                    keys[2]=true;
                    
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
		{
			keys[1]=true;
		}
    }
/**
 * This keyDown method is set through various if-statements and keys 0, 1, 2, 3, and 4. 
 */    
    private void keyDown()
    {
        if(keys[0] == true)
        {
            
        }
        if(keys[1] == true)
        {
            
        }
        if(keys[2] == true)
        {
            ((Player)drawables.get(playerKey)).move(1);
        }
        if(keys[3] == true)
        {
            ((Player)drawables.get(playerKey)).move(-1);
        }
        if(keys[4] == true)
        {
            
        }
    }
 /**
 * This addMoney method is passed down int amount, the money is instantiated from
 * + and - amount.
     * @param amount
     * @return if the transaction was successfull
 */   
    public static boolean addMoney(int amount)
    {
        money += amount;
        if(money >= 0)
        { return true; }
        money -= amount;
        return false;
    }
/**
 * This this getPlayerKey method returns playerKey.
     * @return the player's key
 */    
    public static int getPlayerKey()
    {
        return playerKey;
    }
/**
 * This getPlayer method returns the player drawables and playerKey.
     * @return the player
 */    
    public static Player getPlayer()
    {
        return (Player)drawables.get(playerKey);
    }
/**
 * This GameRunner fadebacktoBlack method defines int r, g, b, and a; the variables
 * are also defined in color.
 */    
    public static void fadeBackToBlack()
    {

        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        int a = c.getAlpha();
        
                
        r-=r/(fadeBackTime);
        g-=g/(fadeBackTime);
        b-=b/(fadeBackTime);
        a-=a/(fadeBackTime);
        
        if(r<20) {r=20;}
        if(g<20) {g=20;}
        if(b<20) {b=20;}
        if(a<50) {a=50;}
        
        
        //System.out.println(r+"  "+g+"  "+b+"  "+a);
        
        c = new Color(r,g,b,a);

    }
/**
 * This drawColor is passed down Graphics g; the set color is made, and the 
 * rectangles are set along with the height and width.
 */    
    private void drawColor(Graphics g)
    {
        g.setColor(c);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
/**
 * This exit method removes frames and sets the time, collidables, and frame dispose.
 */    
    public static void exit()
    {
        frame.removeAll();
        HighScore.saveScore(StartMenu.name, money-startMoney);
        timer.stop();
        collidables.clear();
        frame.dispose();
        frame = null;
    }
/**
 * This arraylist getWeaponList returns weapons for the user in the game.
     * @return the list of the players weapons
 */    
    public static ArrayList<Weapon> getWeaponList()
    {
        return weapons;
    }
/**
 * This swapWeapons method is passed down int newWeapon; the playerKey is set 
 * according to the weapons.
     * @param newWeapon
 */    
    public static void swapWeapons(int newWeapon)
    {
        ((Player)drawables.get(playerKey)).setWeapon(weapons.get(newWeapon));
        Weapon temp = weapons.get(0);
        weapons.set(0, weapons.get(newWeapon));
        weapons.set(newWeapon, temp);
    }
/**
 * This int getMoney returns money for the user.
     * @return the players number of coins
 */    
    public static int getMoney()
    {
        return money;
    }    
/**
 * This setMoney method is passed down int money and money is defined as GameRunner money. 
     * @param money
 */
    public static void setMoney(int money)
    {
        GameRunner.money = money;
    }
/**
 * This reset method defines various items, such as frame, timer, timerSpeed, shader,
 * lastRunTime, money, startMoney, coinBarSpinNum, coinSpinTime, fadeBackTime, weapons, 
 * and bufferedImage.
 */    
    public void reset()
    {
    frame = new JFrame();
    timer = null;
    timerSpeed = 20;
    shader = new Shader(0);
    drawables = new TreeMap<Integer,Drawable>();
    bufferedImage = null;
    worldFrame = new int[] {0,0};
    playerKey = 0;
    collidables = new TreeMap<Integer,PhysicsObject>();
    boolean keys[] = new boolean[5];
    removePart = 0;
    lastRunTime = 0;
    money = 0;
    startMoney = 0;
    coinBarSpinNum = 20;
    coinSpinTime = 0;
    c = new Color(123,174,222);
    fadeBackTime = 255;
    back = null;
    weapons = new ArrayList<Weapon>();
    }
 /**
 * This resetAll method defines various items, such as frame, time, drawables, worldFrame,
 * playerkey, collidables, money, startMoney, fadeBackTime, and weapons.
 */   
    public static void resetAll()
    {
    frame = new JFrame();
    timer = null;
    drawables = new TreeMap<Integer,Drawable>();
    worldFrame = new int[] {0,0};
    playerKey = 0;
    collidables = new TreeMap<Integer,PhysicsObject>();
    money = 0;
    startMoney = 0;
    c = new Color(123,174,222);
    fadeBackTime = 255;
    weapons = new ArrayList<Weapon>();
    }
/**
 * This helpText method creates the tutorial for the user in the game, as the 
 * Tutorial is clicked by the user; these help the user learn aspects of the 
 * game for playability; these happen in a time sequence.
 */    
    private void helpText()
    {
        if(tutorial)
        {
            int x = collidables.get(playerKey).getLoc()[0];
            
            if(x>3300)
            {
                MainMenu m = new MainMenu();
                m.start();
                exit();
                reset();
            }
            else if(x>3100)
            {
                onScreenText = "expect the unexpected" ;
            }
            else if(x>2900)
            {
                onScreenText = "every pyramid is different" ;
            }
            else if(x>2700)
            {
                onScreenText = "One more thing to keep in mind..." ;
            }
            else if(x>2500)
            {
                onScreenText = "You can buy and sell weapons at the shop in town" ;
            }
            else if(x>2300)
            {
                onScreenText = "You get coins from finding them in pyramids and by killing creatures" ;
            }
            else if(x>1400)
            {
                onScreenText = "Try both of them out on these creatures" ;
            }
            else if(x>1200)
            {
                onScreenText = "See how much better it is?" ;
            }
            else if(x>1000)
            {
                onScreenText = "Compare the stats shown in the menu bar to your old weapon" ;
            }
            else if(x>800)
            {
                onScreenText = "Select the new weapon you picked up" ;
            }
            else if(x>600)
            {
                onScreenText = "Press the number keys to change weapons" ;
            }
            else if(x>400)
            {
                onScreenText = "Press Escape to open the options menu. If you get stuck or die you can exit the pyramid" ;
            }
            else if(x>200)
            {
                onScreenText = "It shouws your health, equipped weapon, avalible weapons, and coins" ;
            }
            else if(x>0)
            {
                onScreenText = "See the stats bar on the bottom part of the screen?" ;
            }
            else if(x>-200)
            {
                onScreenText = "Press SPACEBAR or " + Character.toString((char)8595) + " to attack";
            }
            else if(x>-400)
            {
                onScreenText = "Pyramids are full of hostile creatures";
            }
            else if(x>-700)
            {
                onScreenText = "Tap the jump key twice to double jump";
            }
            else if(x>-1000)
            {
                onScreenText = "Press " + Character.toString((char)8593) + " to single jump";
            }
            else if(x>-1150)
            {
                onScreenText = "Let me teach you a few things";
            }
            else if(x>-1300)
            {
                onScreenText = "I wouldn't go without knowing the skills necessary to survive";
            }
            else if(x>-1450)
            {
                onScreenText = "That sounds dangerous!";
            }
            else if(x>-1600)
            {
                onScreenText = "I heard you are going to explore the pyramids";
            }
            else if(x>-1800)
            {
                onScreenText = "I'll be your guide";
            }
            else if(x>-2000)
            {
                onScreenText = "Welcome to Egypt, traveler! You will encounter "
                        + "upon great adventures and mysteries";
            }
            else
            {
                onScreenText = "Press " + Character.toString((char)8592) + " and " + Character.toString((char)8594) + " to move";
            }
                
                
        }
    }
}
