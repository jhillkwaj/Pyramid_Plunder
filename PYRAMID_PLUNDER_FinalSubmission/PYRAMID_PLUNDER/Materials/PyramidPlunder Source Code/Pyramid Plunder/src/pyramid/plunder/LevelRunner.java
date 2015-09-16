/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This LevelRunner Class creates multiple platforms for the game, the user is able to 
 * select the specific levels which he/she wishes to take part in; the various levels
 * are randomly generated (with the platforms) and six generations are made available 
 * for the user.
 */
public class LevelRunner {
    
    private static ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private static int list;
    
    private static ArrayList<ArrayList<GameObject>> objectLists = new ArrayList<ArrayList<GameObject>>();
    
    private static boolean[][] filled;
    private static int specialGen = 1;
    
    private static int genCount = 0;
    
    protected static int difficulty = 1;
    
    private static int endStart = -1;
    
    public static void generateLevel(int sizeX, int sizeY, int difficulty)
    {
        endStart = -1;
        genCount = 0;
        specialGen = 1;
        filled = null;
        objectLists.clear();
        objects.clear();
        LevelRunner.difficulty = difficulty;
        sizeX*=16;
        sizeY*=16;
        filled = new boolean[(sizeX/16)*2][(sizeY/16)];
        
        generateStart();
        
        for(int i = 0; i < sizeX; i+=16)
        {
            condenseGround();
            genCount++;
            int x = i/16;
            for(int j = 0; j < sizeY; j+=16)
            {
                
                int y = j/16;
                if(x>=5)
                {
                    if(x==5 && y <= 8) //no blocks placed in mineshaft
                    {
                        
                    }
                    else
                    {
                        if(specialGen==1)
                        {
                            generationOne(x,y,i,j);
                            
                        }
                        else if(specialGen==2)
                        {
                            generationTwo(x,y,i,j);
                            
                        }
                        else if(specialGen==3)
                        {
                            generationThree(x,y,i,j,getTop(x-1),getBottom(x-1));
                            
                        }
                        else if(specialGen==4)
                        {
                            generationFour(x,y,i,j,getTop(x-1),getBottom(x-1));
                            
                        }
                        else if(specialGen==5)
                        {
                            generationSix(x,y,i,j,getTop(x-1),getBottom(x-1));
                            
                        }
                        else
                        {
                            generationFive(x,y,i,j,getTop(x-1),getBottom(x-1));
                           
                        }
                    }
                }
                else
                {
                    makePlatform(i,j,x,y,13);
                }
                
                
            }
            
            randGen(x);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(LevelRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean done = false;
        int xloc = sizeX;
        
        while(!done)
        {
            int x = xloc / 16;
             for(int y = 0; y < sizeY; y+=16)
             {
                  done = generateEnd(x, y/16, xloc, y,getTop(x-1),getBottom(x-1));
             }
             xloc+=16;
             condenseGround();
        }
        
        condenseAllGround();
        splitList();
    }
    
    public static void generateTutorial()
    {
        filled = null;
        objectLists.clear();
        objects.clear();
        
        Platform pa = new Platform(new int[] {14});
         pa.setSize(new int[] {16,160});
         pa.setLoc(new int[] {-2270,1740,0,0});
         objects.add(pa);
        
       for(int i = 0; i < 1000; i++)
       {
         Platform pl = new Platform(new int[] {15});
         pl.setSize(new int[] {2200,500});
         pl.setLoc(new int[] {-2720+(2200*i),1900,0,0});
         objects.add(pl);
       }
       
       Platform phelp = new Platform(new int[] {15});
       phelp.setSize(new int[] {100,100});
       phelp.setLoc(new int[] {-2800+(2000),1833,0,0});
       objects.add(phelp);
       
       Platform phelp2 = new Platform(new int[] {15});
       phelp2.setSize(new int[] {100,100});
       phelp2.setLoc(new int[] {-2855+(2000),1866,0,0});
       objects.add(phelp2);
       

         Platform pl3 = new Platform(new int[] {15});
         pl3.setSize(new int[] {100,100});
         pl3.setLoc(new int[] {-2720+(2200),1800,0,0});
         objects.add(pl3);
         
         Snake s = new Snake(0,1700);
         s.setHealth(1);
         s.dammage = 0;
         objects.add(s);
         
      Weapon w = Weapon.randomWeapon();
      while(Weapon.priceWeapon(w)<100)
      {
          w = Weapon.randomWeapon();
      }
       Loot l = new Loot(new int[] {500,1860},new int[] {w.graphics[w.graphics.length/2]},w);
       objects.add(l);
      
      for(int i = 0; i < 4; i++)
      {
      Snake s2 = new Snake(1600 + (100*i),1700);
      s2.dammage = 0;
      objects.add(s2);
      }
      
      Ghost b = new Ghost(2100,1700);
      b.dammage = 0;
      objects.add(b);
      
      Skeleton sk = new Skeleton(2400,1650);
      sk.dammage = 0;
      objects.add(sk);
      
       splitList();
    }
    
    private static void posibleSpawnOnGround(int x, int y)
    {
        if(Math.random()<(.05f*difficulty) && y > 5  && !filled[x][y-1] && !filled[x][y-2] && !filled[x][y-3] ) //spawn coin
        {
            Coin c = new Coin(new int[] {20,21,22,23,24,25,26,27});
            c.setLoc( new int[] {x*16,(y-1)*16,0,0} );
            c.setSize(new int[] {9,9});
            objects.add(c);
        }
        else if(Math.random()<(.005f*difficulty) && y > 5  && !filled[x][y-1] && !filled[x][y-2] && !filled[x][y-3]) //spawn enemy
        {
            if(difficulty<2)
            {
            if(Math.random() < .5)
            {
                Bat b = new Bat(x*16,y*16);
                objects.add(b);
            }
            else
            {
                Snake s = new Snake(x*16,y*16);
                objects.add(s);
            }
            }
            else if (difficulty<4)
            {
                double rand = Math.random();
                if(Math.random() < .3f)
                {
                Bat b = new Bat(x*16,y*16);
                objects.add(b);
                }
                else if(Math.random() < .6f)
                {
                Snake s = new Snake(x*16,y*16);
                objects.add(s);
                }
                else if(Math.random() < .8f)
                {
                    Ghost s = new Ghost(x*16,y*16);
                    objects.add(s);
                }
                else
                {
                    Skeleton s = new Skeleton(x*16,y*16);
                    objects.add(s);
                }
            }
            else
            {
                double rand = Math.random();
                if(true||rand < .2f)
                {
                    Ghost s = new Ghost(x*16,y*16);
                    objects.add(s);
                }
                else if(rand < .4)
                {
                    Bat b = new Bat(x*16,y*16);
                    b.addHealth(((int)(Math.random()*(difficulty*b.getHealth()))/2)-(b.getHealth()/4));
                    objects.add(b);
                }
                else if(rand < .8)
                {
                    Snake s = new Snake(x*16,y*16);
                    s.addHealth(((int)(Math.random()*(difficulty*s.getHealth()))/2)-(s.getHealth()/4));
                    objects.add(s);
                }
                else
                {
                    Mummy m = new Mummy(x*16,y*16);
                    objects.add(m);
                }
            }
        }
    }
  
    
    private static void spawnOnGround(int x, int y)
    {
        if(Math.random()<difficulty/10.0f)
        {
        if(Math.random()<(.9f)) //spawn coin
        {
            Coin c = new Coin(new int[] {20,21,22,23,24,25,26,27},1);
            c.setLoc( new int[] {x,y,0,0} );
            c.setSize(new int[] {9,9});
            objects.add(c);
        }
        else //spawn enemy
        {
            if(Math.random() < .5)
            {
                Bat b = new Bat(x,y);
                b.addHealth(((int)(Math.random()*(difficulty*b.getHealth()))/2)-(b.getHealth()/4));
                objects.add(b);
            }
            else
            {
                Snake s = new Snake(x,y);
                s.addHealth(((int)(Math.random()*(difficulty*s.getHealth()))/2)-(s.getHealth()/4));
                objects.add(s);
            }
        }
        }
    }
    
    private static void spawnHard(int x, int y)
    {
        Mummy m = new Mummy(x,y);
        objects.add(m);
    }
    
    private static void condenseGround()
    {
        
        int changesMade = 1;
        while(changesMade!=0)
        {
            changesMade = 0;
            
            for(int i = 0; i < objects.size(); i++)
            {
                if(i==0&&objects.size()>100)
                { i = objects.size()-100; }
                
                GameObject g = objects.get(i);
           
                if(g.getSize()[0]%16 == 0 && g.getSize()[1]%16 == 0 )
                {
                    //look and see if a second object can but merged
                    for(int i2 = 0; (i2 < objects.size() && g!=null); i2++)
                    {
                        if(i2==0&&objects.size()>200)
                        { i2 = objects.size()-200; }
                        GameObject g2 = objects.get(i2);
           
                        if(g2.getSize()[0]%16 == 0 && g2.getSize()[1]%16 == 0 )
                        {
                            //adjacent in the x direction
                            if(g.getLoc()[1]==g2.getLoc()[1] && g.getSize()[1]==g2.getSize()[1])
                            {
                                //second is to the left
                                if(g2.getLoc()[0]+g2.getSize()[0]==g.getLoc()[0])
                                {
                                    Platform p = new Platform( getTile(g2.getLoc()[1]/16,g.getSize()[0]+g2.getSize()[0],g.getSize()[1]) );
                                    p.setSize(new int[] {g.getSize()[0]+g2.getSize()[0],g.getSize()[1]});
                                    p.setLoc(new int[] {g2.getLoc()[0],g2.getLoc()[1],0,0});
                                    objects.add(p);
                                    objects.remove(g);
                                    objects.remove(g2);
                                    g = null;
                                    changesMade++;
                                    
                                }
                                //second is to the right
                                else if(g.getLoc()[0]+g.getSize()[0]==g2.getLoc()[0])
                                {
                                    Platform p = new Platform(getTile(g2.getLoc()[1]/16,g.getSize()[0]+g2.getSize()[0],g.getSize()[1]));
                                    p.setSize(new int[] {g.getSize()[0]+g2.getSize()[0],g.getSize()[1]});
                                    p.setLoc(new int[] {g.getLoc()[0],g2.getLoc()[1],0,0});
                                    objects.add(p);
                                    objects.remove(g);
                                    objects.remove(g2);
                                    g = null;
                                    changesMade++;
                                }
                            }
                            //y direction
                            else if(g.getLoc()[0]==g2.getLoc()[0] && g.getSize()[0]==g2.getSize()[0] && g.getSize()[1] + g2.getSize()[1] < 1600)
                            {
                                //second is above
                                if(g2.getLoc()[1]+g2.getSize()[1]==g.getLoc()[1])
                                {
                                    Platform p = new Platform(getTile(g2.getLoc()[1]/16,g.getSize()[0],g.getSize()[1]+g2.getSize()[1]));
                                    p.setSize(new int[] {g.getSize()[0],g.getSize()[1]+g2.getSize()[1]});
                                    p.setLoc(new int[] {g2.getLoc()[0],g2.getLoc()[1],0,0});
                                    objects.add(p);
                                    objects.remove(g);
                                    objects.remove(g2);
                                    g = null;
                                    changesMade++;
                                }
                                //second is below
                                else if(g.getLoc()[1]+g.getSize()[1]==g2.getLoc()[1])
                                {
                                    Platform p = new Platform(getTile(g.getLoc()[1]/16,g.getSize()[0],g.getSize()[1]+g2.getSize()[1]));
                                    p.setSize(new int[] {g.getSize()[0],g.getSize()[1]+g2.getSize()[1]});
                                    p.setLoc(new int[] {g.getLoc()[0],g.getLoc()[1],0,0});
                                    objects.add(p);
                                    objects.remove(g);
                                    objects.remove(g2);
                                    g = null;
                                    changesMade++;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    private static void condenseAllGround()
    {
        
        int changesMade = 1;
        while(changesMade!=0)
        {
            changesMade = 0;
            
            for(int i = 0; i < objects.size(); i++)
            {
                
                GameObject g = objects.get(i);
           
                if(g.getSize()[0]%16 == 0 && g.getSize()[1]%16 == 0 )
                {
                    //look and see if a second object can but merged
                    for(int i2 = 0; (i2 < objects.size() && g!=null); i2++)
                    {
                        GameObject g2 = objects.get(i2);
           
                        if(g2.getSize()[0]%16 == 0 && g2.getSize()[1]%16 == 0 )
                        {
                            //adjacent in the x direction
                            if(g.getLoc()[1]==g2.getLoc()[1] && g.getSize()[1]==g2.getSize()[1])
                            {
                                //second is to the left
                                if(g2.getLoc()[0]+g2.getSize()[0]==g.getLoc()[0])
                                {
                                    Platform p = new Platform(new int[] {13});
                                    p.setSize(new int[] {g.getSize()[0]+g2.getSize()[0],g.getSize()[1]});
                                    p.setLoc(new int[] {g2.getLoc()[0],g2.getLoc()[1],0,0});
                                    objects.add(p);
                                    objects.remove(g);
                                    objects.remove(g2);
                                    g = null;
                                    changesMade++;
                                    
                                }
                                //second is to the right
                                else if(g.getLoc()[0]+g.getSize()[0]==g2.getLoc()[0])
                                {
                                    Platform p = new Platform(new int[] {13});
                                    p.setSize(new int[] {g.getSize()[0]+g2.getSize()[0],g.getSize()[1]});
                                    p.setLoc(new int[] {g.getLoc()[0],g2.getLoc()[1],0,0});
                                    objects.add(p);
                                    objects.remove(g);
                                    objects.remove(g2);
                                    g = null;
                                    changesMade++;
                                }
                            }
                            //y direction
                            else if(g.getLoc()[0]==g2.getLoc()[0] && g.getSize()[0]==g2.getSize()[0] && g.getSize()[1] + g2.getSize()[1] < 1600)
                            {
                                //second is above
                                if(g2.getLoc()[1]+g2.getSize()[1]==g.getLoc()[1])
                                {
                                    Platform p = new Platform(new int[] {13});
                                    p.setSize(new int[] {g.getSize()[0],g.getSize()[1]+g2.getSize()[1]});
                                    p.setLoc(new int[] {g2.getLoc()[0],g2.getLoc()[1],0,0});
                                    objects.add(p);
                                    objects.remove(g);
                                    objects.remove(g2);
                                    g = null;
                                    changesMade++;
                                }
                                //second is below
                                else if(g.getLoc()[1]+g.getSize()[1]==g2.getLoc()[1])
                                {
                                    Platform p = new Platform(new int[] {13});
                                    p.setSize(new int[] {g.getSize()[0],g.getSize()[1]+g2.getSize()[1]});
                                    p.setLoc(new int[] {g.getLoc()[0],g.getLoc()[1],0,0});
                                    objects.add(p);
                                    objects.remove(g);
                                    objects.remove(g2);
                                    g = null;
                                    changesMade++;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
  
    
    private static void makePlatform(int i, int j, int x, int y, int imageKey)
    {
        Platform p = new Platform(new int[] {imageKey});
        p.setSize(new int[] {16,16});
        p.setLoc(new int[] {i,j,0,0});
        objects.add(p);
        filled[x][y]=true;
    }
    
    private static void splitList()
    {
        for(int i = 0; i < 100; i++)
        {
            ArrayList<GameObject> shortList = new ArrayList<GameObject>();
            
            for(int j = (objects.size()/100)*i; j < (objects.size()/100)*(i+1); j++)
            {
                shortList.add(objects.get(j));
            }
            
            if(i==99)
            {
                for(int j = (objects.size()/100)*100; j < objects.size(); j++)
                {
                    shortList.add(objects.get(j));
                }
            }
            
            objectLists.add(shortList);
        }
        
    }
    
    public static ArrayList<GameObject> getAllObjects()
    {
        ArrayList<GameObject> allObjects = new ArrayList<GameObject>();
        for(ArrayList<GameObject> listPart : objectLists)
        {
            for(int i = 0; i < listPart.size(); i++)
            {
                allObjects.add(listPart.get(i));
            }
        }
        return allObjects;
    }
    
    public static void addObjects(int locX,int locY,double distance)
    {
        if(objectLists.size()>0)
        {
        ArrayList<GameObject> shortList = objectLists.get(list);
        for(int i = 0; i < shortList.size(); i++)
        {
             if(distance>Math.sqrt(Math.pow(locX-shortList.get(i).getLoc()[0], 2) + Math.pow(locY-shortList.get(i).getLoc()[1], 2)))
             {
                 GameRunner.addCollidable((PhysicsObject)(shortList.get(i)));
                 GameRunner.addDrawable(shortList.get(i));
             }
             
        }
        list++;
        if(list>99)
        {
            list=0;
        }
        }
    }
    
    private static int getTop(int x)
    {
        int y = 0;
        while(y < filled[x].length && filled[x][y])
        {
            y++;
        }
        return y - 1;
    }
    
    
    private static int getBottom(int x)
    {
        int bottom = -1;
        for(int y = 0; y < filled[x].length; y++)
        {
            if(!filled[x][y])
            {
                bottom = y;
            }
        }
        return bottom + 1;
    }
    
    private static void randGen(int x)
    {
        if(Math.random()<.04) //random chance to change genNum
        {
            if(x<30)
            {
                specialGen = 1;
            }
            else
            {
                genCount = 0;
                double rand = Math.random();
                if(rand < .325)
                {
                    specialGen = 1;
                }
                else if(rand < .475)
                {
                    specialGen = 2;
                }
                else if(rand < .65)
                {
                    specialGen = 3;
                }
                else if(rand<.775)
                {
                    specialGen = 4;
                }
                else
                {
                    specialGen = 5;
                }
                
                
            }
        }
    }
    
    private static void generationOne(int x, int y, int i, int j) //standard med platforms, mid down, slow top
    {
        
        if(y<3 && x > 10)  //if it's one of the top blocks, place a block
        {
                 makePlatform(i,j,x,y,13); 
        }
        else if( filled[x-1][y]==false) //last one wasn't filled don't make a block
         {
                 //random chance to make a platform if clear above
                 if(((Math.random()<.005f&&x>20)) && !filled[x][y-1] && !filled[x][y-2] && !filled[x][y-3]&& getBottom(x-1)-getTop(x-1)>=20)
                 {
                        makePlatform(i,j,x,y,13);
                        posibleSpawnOnGround(x,y-1);
                 }
                 //slope top down
                 else if(((Math.random()<.08f&&x>20)) && filled[x][y-1])
                 {
                     makePlatform(i,j,x,y,13);
                     posibleSpawnOnGround(x,y-1);
                 }
                        
         }
         else if(y>=1 && !filled[x-1][y-1] && filled[x-1][y] && filled[x-1][y+1] && Math.random()<.2f) //slope downward by not makeing bottom block
         {
             //random coins in air
                 posibleSpawnOnGround(x,y-1); 
         }
         else if(y>=1 && !filled[x-1][y-1] && Math.random()<.09f) //end platforms
         {
                            
         }
         else //place a block
         {
             makePlatform(i,j,x,y,13);
             posibleSpawnOnGround(x,y-1);
         }
    }
    
    
    private static void generationTwo(int x, int y, int i, int j) //short platforms, mid down, very slow top
    {
        
        if(y<3 && x > 10)  //if it's one of the top blocks, place a block
        {
                 makePlatform(i,j,x,y,13);
        }
        else if( filled[x-1][y]==false) //last one wasn't filled don't make a block
         {
                 //random chance to make a platform if clear above
                 if(((Math.random()<.03f&&x>20)) && !filled[x][y-1] && !filled[x][y-2] && !filled[x][y-3]&& getBottom(x-1)-getTop(x-1)>=20)
                 {
                        makePlatform(i,j,x,y,13);
                        posibleSpawnOnGround(x,y-1);
                 }
                 //slope top down
                 else if(((Math.random()<.05f&&x>20)) && filled[x][y-1] && filled[x][y-2] && filled[x][y-3])
                 {
                     makePlatform(i,j,x,y,13);
                     posibleSpawnOnGround(x,y-1);
                 }
                        
         }
         else if(y>=1 && !filled[x-1][y-1]  && Math.random()<.25f) //slope downward by not makeing bottom block
         {
                        
         }
         else if(y>=1 && !filled[x-1][y-1] && Math.random()<.2f) //end platforms
         {
                            
         }
         else //place a block
         {
             makePlatform(i,j,x,y,13);
             posibleSpawnOnGround(x,y-1);
         }
        
    }
    
    private static void generationThree(int x, int y, int i, int j, int top, int bottom) //fast down(top and bottom), few platforms, very long platforms
    {
        
        if(y<3 && x > 10)  //if it's one of the top blocks, place a block
        {
                 makePlatform(i,j,x,y,13);
        }
        else if( filled[x-1][y]==false) //last one wasn't filled don't make a block
         {
                 //random chance to make a platform if clear above
                 if(((Math.random()<.005f&&x>20)) && !filled[x][y-1] && !filled[x][y-2] && !filled[x][y-3]&& getBottom(x-1)-getTop(x-1)>=20)
                 {
                        makePlatform(i,j,x,y,13);
                        posibleSpawnOnGround(x,y-1);
                 }
                 //slope top down
                 else if(filled[x][y-1] && filled[x][y-2] && filled[x][y-3] && filled[x][y-3]&& filled[x-1][y-1]&&Math.random()<.9)
                 {
                     makePlatform(i,j,x,y,13);
                     posibleSpawnOnGround(x,y-1); 
                 }
                        
         }
         else if(y>=1 && !filled[x-1][y-1] && Math.random()<.08f) //end platforms
         {
                            
         }
         else if(y>=1 && !filled[x-1][y-1] && filled[x-1][y] && filled[x-1][y+1]) //slope downward by not makeing bottom block
         {
                        
         }
         else //place a block
         {
             makePlatform(i,j,x,y,13);
             posibleSpawnOnGround(x,y-1);
         }
    }
    
    
    private static void generationFour(int x, int y, int i, int j, int top, int bottom) //fast down(top only), few platforms, very long platforms
    {
        if(bottom-top>10)
        {
        
        if(y<3 && x > 10)  //if it's one of the top blocks, place a block
        {
                 makePlatform(i,j,x,y,13);
                 posibleSpawnOnGround(x,y-1);
        }
        else if( filled[x-1][y]==false) //last one wasn't filled don't make a block
         {
                 //random chance to make a platform if clear above
                 if(((Math.random()<.005f&&x>20)) && !filled[x][y-1] && !filled[x][y-2] && !filled[x][y-3]&& getBottom(x-1)-getTop(x-1)>=20)
                 {
                        makePlatform(i,j,x,y,13);
                        posibleSpawnOnGround(x,y-1);
                 }
                 //slope top down
                 else if(filled[x][y-1] && filled[x][y-2] && filled[x][y-3] && filled[x][y-3]&& filled[x-1][y-1]&&Math.random()<.9)
                 {
                     makePlatform(i,j,x,y,13);
                     posibleSpawnOnGround(x,y-1);
                 }
                        
         }
         else if(y>=1 && !filled[x-1][y-1] && Math.random()<.08f) //end platforms
         {
                            
         }
         else //place a block
         {
             makePlatform(i,j,x,y,13);
             posibleSpawnOnGround(x,y-1);
         }
        }
        else
        {
            generationThree(x,y,i,j,top,bottom);
        }
    }
    

     private static void generationFive(int x, int y, int i, int j, int top, int bottom) //drop
    {
         if((y<3 && x > 10)||y<top)  //if it's one of the top blocks, place a block
        {
                 makePlatform(i,j,x,y,13);
        }
        else if( filled[x-1][y]==false) //last one wasn't filled don't make a block
         {
                 //random chance to make a platform if clear above
                 if(((Math.random()<.005f&&x>20)) && !filled[x][y-1] && !filled[x][y-2] && !filled[x][y-3] && getBottom(x-1)-getTop(x-1)>=20)
                 {
                        makePlatform(i,j,x,y,13);
                        posibleSpawnOnGround(x,y-1);
                 }
                 //slope top down
                 else if(((Math.random()<.08f&&x>20)) && filled[x][y-1])
                 {
                     makePlatform(i,j,x,y,13);
                     posibleSpawnOnGround(x,y-1);
                 }
                        
         }
         else if(y>=1 && !filled[x-1][y-1] && filled[x-1][y] && filled[x-1][y+1] && Math.random()<.2f) //slope downward by not makeing bottom block
         {
                        
         }
         else if(y>=1 && !filled[x-1][y-1] && Math.random()<.09f) //end platforms
         {
                            
         }
         else if(y<bottom+30&& y>=bottom - 1&&x>top)
         {
             posibleSpawnOnGround(x,y-1);
         }
         else //place a block
         {
             specialGen = 1;
             makePlatform(i,j,x,y,13);
         }
    }
     
     
     
     private static void generationSix(int x, int y, int i, int j, int top, int bottom) //levels
    {
         if(y<3 && x > 10)  //if it's one of the top blocks, place a block
        {
                 makePlatform(i,j,x,y,13);
        }
        else if((genCount == 6 && y>bottom-40 && y < bottom-20)||y<top) //split the levels
        {
             makePlatform(i,j,x,y,14);
             posibleSpawnOnGround(x,y-1);
        }
        else if( filled[x-1][y]==false) //last one wasn't filled don't make a block
         {
                 //slope top down
                 if(((Math.random()<.08f&&x>20)) && filled[x][y-1])
                 {
                     makePlatform(i,j,x,y,13);
                     posibleSpawnOnGround(x,y-1);
                 }
                        
         }
         else if(y>=1 && !filled[x-1][y-1] && filled[x-1][y] && filled[x-1][y+1] && Math.random()<.2f) //slope downward by not makeing bottom block
         {
                        
         }
         else if(y>=1 && !filled[x-1][y-1] && Math.random()<.09f) //end platforms
         {
                            
         }
         else if(genCount == 1 && (y<bottom+40 && y>=bottom-1) && x>top) //make a pit
         {
             posibleSpawnOnGround(x,y-1);
         }
         else if(genCount > 6)
         {
             specialGen = 1;
             makePlatform(i,j,x,y,13);
             posibleSpawnOnGround(x,y-1); 
         }
         else //place a block
         {
             makePlatform(i,j,x,y,13);
             posibleSpawnOnGround(x,y-1);
         }
         
    }
     
     private static void generateStart()
     {

         Platform pa = new Platform(new int[] {14});
         pa.setSize(new int[] {16,160});
         pa.setLoc(new int[] {-2270,1740,0,0});
         objects.add(pa);
         
         for(int x = 1920; x > 0; x-=32)
         {
             Platform p = new Platform(new int[] {12});
             p.setSize(new int[] {32,1920-x});
             p.setLoc(new int[] {-x,x,0,0});
             objects.add(p);
             spawnOnGround(-x,x-30);
         }
         
         for(int x = 200; x < 2000; x+=32)
         {
             Platform p = new Platform(new int[] {12});
             p.setSize(new int[] {32,x});
             p.setLoc(new int[] {x-32+8,-x,0,0});
             objects.add(p);
             
         }
         
         Platform pl = new Platform(new int[] {15});
         pl.setSize(new int[] {2200,500});
         pl.setLoc(new int[] {-2720,1900,0,0});
         objects.add(pl);
     }
     
     private static boolean generateEnd(int x, int y, int i, int j,int top, int bottom)
     {
         if(bottom - top >= 20)
         {
             if(!(y < bottom - 1 && y > top + 1))
             {
                makePlatform(i,j,x,y,13);
             }
         }
         else if(x < endStart + (20*difficulty) || endStart == -1)
         {
             if(endStart == -1)
             {
                 endStart = x;
             }
             

             
             if(x == endStart+10 && !filled[x-1][y]&&filled[x-1][y+1])
             {
                 spawnHard(i,j+60);
             }
             else if(!filled[x-1][y]&&filled[x-1][y+1] && x+1 >= endStart + (20*difficulty))
             {
                 EndCoin c = new EndCoin(new int[] {68,69,68,69,68,69,68,69},difficulty*10);
                 c.setLoc( new int[] {i,j-9,0,0} );
                 c.setSize(new int[] {18,18});
                 objects.add(c);
             }
             else if(!filled[x-1][y]&&filled[x-1][y+1])
             {
                 double rand = Math.random();
                 if(rand<.4f)
                 {
                 Coin c = new Coin(new int[] {60,61,62,63,64,65,66,67},5);
                 c.setLoc( new int[] {i,j-9,0,0} );
                 c.setSize(new int[] {18,18});
                 objects.add(c);
                 }
                 else if(rand < .8f)
                 {
                 Coin c = new Coin(new int[] {20,21,22,23,24,25,26,27},1);
                 c.setLoc( new int[] {i,j,0,0} );
                 c.setSize(new int[] {9,9});
                 objects.add(c);
                 }
                 else
                 {
                     Loot l = new Loot(new int[]{i,j,0,0},difficulty*75);
                     objects.add(l);
                 }
             }
             
             if(filled[x-1][y]==true)
             {
                 makePlatform(i,j,x,y,13);
             }
         }
         else if(x < endStart + (20*difficulty) + 30)
         {
             makePlatform(i,j,x,y,13);
         }
         else
         {
             return true;
         }
         return false;
     }
     
     private static int[] getTile(int y, int sizeX, int sizeY)
     { 
         return new int[] {13}; 
     }
     
     public static boolean runFromFile(ArrayList<String> lines)
     {
         int line = 0;
         
         GameRunner.loadPlayerData(new String[] {lines.get(1),lines.get(2),
             lines.get(3),lines.get(4)});

         //player lines
         line = 5;
         
         //coins
         try
         {
         GameRunner.setMoney(Integer.parseInt(lines.get(line)));
         }
         catch(Exception e)
         {ErrorLogger.logError("Can't load coins..."+e);}
         line++;
         
         //load equipment
         while(lines.get(line).equals("--Weapon--"))
         {
             makeWeapon(lines, line);
             line+=9;
         }
         
         if(line+7<lines.size())
         {   
         //load physics objects
         while(line+4<lines.size() && lines.get(line).equals("--------------------------"))
         {
             try
             {
             String name = lines.get(line+1);
             if(name.equals("Platform") || name.equals("Coin"))
             {
                  makePlatformOrCoin(lines, line, name);
                  line+=5;
             }
             else if(name.equals("Snake") || name.equals("Bat") || name.equals("Mummy") || name.equals("Ghost") || name.equals("Skeleton"))
             {
                 makeNPC(lines, line, name);
                 line+=6;
             }

             }
             catch(Exception e)
             {
                 ErrorLogger.logError("Can't load object" + e);
             }
         }
         
         condenseAllGround();
         splitList();
         }
         else
         {
             return false;
         }
         return true;
     }
     
     private static void makeWeapon(ArrayList<String> lines, int startLine)
     {
         try
         {
         Weapon w = null;
         if(lines.get(startLine+1).equals("Sword"))
         {
             w = new Sword(Integer.parseInt(lines.get(startLine+3)));
         }
         else if(lines.get(startLine+1).equals("Gun"))
         {
             w = new SmallGun(Integer.parseInt(lines.get(startLine+3)));
         }
         else
         {
             ErrorLogger.logError("Can't find weapon " + lines.get(startLine+1).equals("Sword"));
         }
         w.name = lines.get(startLine+2);
         w.rangeX = Integer.parseInt(lines.get(startLine+4));
         w.rangeY = Integer.parseInt(lines.get(startLine+5));
         w.cooldownTime = Integer.parseInt(lines.get(startLine+6));
         //multihit load
         String[] parts = lines.get(startLine+8).split(",");
         int[] imageKeys = new int[parts.length];
         for(int i = 0; i < parts.length; i++)
         {
             imageKeys[i] = Integer.parseInt(parts[i]);
         }
         w.graphics = imageKeys;
         GameRunner.addWeapon(w);
         }
         catch(Exception e)
         { ErrorLogger.logError("Can't load weapon..." + e); }
                 
     }
     
     private static void makeNPC(ArrayList<String> lines, int startLine, String name)
     {

         //location
         String[] location = lines.get(startLine+2).split(",");
         int[] loc = new int[4];
         for(int i = 0; i < location.length; i++)
         {
             loc[i] = Integer.parseInt(location[i]);
         }
         
         GameObject p = null;
         if(name.equals("Bat"))
         {
            p = new Bat(loc[0],loc[1]);
         }
         else if(name.equals("Snake"))
         {
             p = new Snake(loc[0],loc[1]);
         }
         else if(name.equals("Mummy"))
         {
             p = new Mummy(loc[0],loc[1]);
         }
         else if(name.equals("Ghost"))
         {
             p = new Ghost(loc[0],loc[1]);
         }
         else if(name.equals("Skeleton"))
         {
             p = new Skeleton(loc[0],loc[1]);
         }
         
         //size
         String[] size = lines.get(startLine+3).split(",");
         int[] s = new int[2];
         for(int i = 0; i < size.length; i++)
         {
             s[i] = Integer.parseInt(size[i]);
         }
         p.setSize(s);
         
         
         String[] infoString = lines.get(startLine+5).split(",");
         int[] info = new int[3];
         for(int i = 0; i < infoString.length; i++)
         {
             info[i] = Integer.parseInt(infoString[i]);
         }
         ((Player)p).setHealth(info[0]);
         ((Player)p).setArmor(info[1]);
         
         objects.add(p);
     }
     
    private static void makePlatformOrCoin(ArrayList<String> lines, int startLine, String name)
     {
         //images
         String[] imageNum = lines.get(startLine+4).split(",");
         int[] images = new int[imageNum.length];
         for(int i = 0; i < imageNum.length; i++)
         {
             images[i] = Integer.parseInt(imageNum[i]);
         }
         
         GameObject p = null;
         if(name.equals("Platform"))
         {
            p = new Platform(images);
         }
         else if(name.equals("Coin"))
         {
             p = new Coin(images);
         }
         
         //location
         String[] location = lines.get(startLine+2).split(",");
         int[] loc = new int[4];
         for(int i = 0; i < location.length; i++)
         {
             loc[i] = Integer.parseInt(location[i]);
         }
         p.setLoc(loc);
         
         //size
         String[] size = lines.get(startLine+3).split(",");
         int[] s = new int[2];
         for(int i = 0; i < size.length; i++)
         {
             s[i] = Integer.parseInt(size[i]);
         }
         p.setSize(s);
         
         objects.add(p);
     }
}