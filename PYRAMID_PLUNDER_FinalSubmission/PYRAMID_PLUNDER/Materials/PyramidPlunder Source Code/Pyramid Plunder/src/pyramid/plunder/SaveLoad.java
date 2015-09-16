/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * In this SaveLoad Class, a BufferedWriter is implemented in a try-catch statement
 * for the user to save his/her game, the specific location and health is saved onto 
 * the .dat file in another try-catch statement, the game objects location are saved  
 * as well as their specific information, such as health and location.
 */    
public class SaveLoad {
/**
 * In this save method, a BufferedWriter is implemented in a try-catch statement
 * for the user to save his/her game, the specific location and health is saved onto 
 * the .dat file in another try-catch statement, the game objects location are saved  
 * as well as their specific information, such as health and location.
     * @param inLevel
 */    
    public static void save(boolean inLevel)
    {
       ErrorLogger.logMessage("Saveing game");
       BufferedWriter w = null;
       try
       {
           w = new BufferedWriter(new FileWriter(new File(StartMenu.name+".dat")));
       }
       catch(Exception e)
       { ErrorLogger.logError("Can't make save file " + e);}
       
       Player p = GameRunner.getPlayer();
       
       try 
       {
           w.write("--Player--");
           w.write("\n"+p.getLoc()[0]+","+p.getLoc()[1]);
           w.write("\n"+p.getHealth()+","+p.getArmor());
           w.write("\n"+p.getJumps());
           w.write("\n"+p.getVelocity()[0]+","+p.getVelocity()[1]);
           
           //write items
           w.write("\n"+GameRunner.getMoney());
           ArrayList<Weapon> weapons = GameRunner.getWeaponList();
           for(int i = 0; i < weapons.size(); i++)
           {
               w.write("\n" + "--Weapon--");
               w.write("\n"+weapons.get(i).type);
               w.write("\n"+weapons.get(i).name);
               w.write("\n"+weapons.get(i).damage);
               w.write("\n"+weapons.get(i).rangeX);
               w.write("\n"+weapons.get(i).rangeY);
               w.write("\n"+weapons.get(i).cooldownTime+"\n");
               if(weapons.get(i).multiHit)
               {
                   w.write("True"+"\n");
               }
               else
               {
                   w.write("False"+"\n");
               }
               for(int j = 0; j < weapons.get(i).graphics.length; j++)
               {
                   w.write(weapons.get(i).graphics[j]+",");
               }
           }
           w.write("\n");
       } 
        catch(Exception e) 
        { ErrorLogger.logError("Can't save player data " + e); } 
       
       
       if(inLevel)
       {
       //write opbjects
        ArrayList<GameObject> objectList = LevelRunner.getAllObjects();
        for(int i = 0; i < objectList.size(); i++)
        {
            PhysicsObject object = (PhysicsObject)objectList.get(i);
          
            if(!object.isPlayer)
            {
                if(object.getName().equals("Platform")||object.getName().equals("Coin"))
                {
                    try
                    {
                        w.write("--------------------------\n");
                        //name
                        w.write(object.name + "\n" );
                        
                        //location
                        w.write(object.getLoc()[0] + "," + object.getLoc()[1] 
                                + "," + object.getLoc()[2] + "," + 
                                object.getLoc()[3] + "\n" );
                        
                        //size
                        w.write(object.getSize()[0]+","+object.getSize()[1]+"\n");
                        
                        //image
                        for(int key = 0; key < object.getImageKeys().length; key++)
                        {
                            w.write(object.getImageKeys()[key]+",");
                        }
                         w.write("\n");
                        
                    }
                    catch(Exception e)
                    {
                        ErrorLogger.logError("Can't save one object " + e);        
                    }
                    
                }
                else
                {
                    System.out.println(object.getName());
                }
                
                
                
            }
            else
            {
                Player player = (Player)objectList.get(i);
                try
                    {
                        w.write("--------------------------\n");
                        //name
                        w.write(player.name + "\n" );
                        
                        //location
                        w.write(object.getLoc()[0] + "," + object.getLoc()[1] 
                                + "," + object.getLoc()[2] + "," + 
                                object.getLoc()[3] + "\n" );
                        
                        //size
                        w.write(object.getSize()[0]+","+object.getSize()[1]+"\n");
                        
                        //image
                        for(int key = 0; key < object.getImageKeys().length; key++)
                        {
                            w.write(object.getImageKeys()[key]+",");
                        }
                         w.write("\n");
                         
                         //info
                         w.write(player.getHealth()+","+player.getArmor()+
                                 player.getJumps() + "\n");
                        
                    }
                    catch(Exception e)
                    {
                        ErrorLogger.logError("Can't save one object " + e);        
                    }
            }
            
        }
       }
       else
       {
           try
           {
           w.write("--end--");
           }
           catch(Exception e)
           { ErrorLogger.logError("Could not write to file " + e);}
       }
        try {w.close(); } 
        catch(Exception e) 
        { ErrorLogger.logError("Can't close file " + e); } 
        ErrorLogger.logMessage("Game Saved");
       
       
    }
/**
 * In this load method, the String fileName is passed. A try-catch statement is 
 * implemented below in order for the user to access his/her saved .dat file. 
 */       
    public static boolean load(String fileName)
    {
        try
        {
        ErrorLogger.logMessage("Loading game");
        ArrayList<String> lines = new ArrayList<String>();
        Scanner in = new Scanner(new File(fileName+".dat"));
        
        
        while(in.hasNextLine())
        {
            lines.add(in.nextLine());
        }
        
        return LevelRunner.runFromFile(lines);
        }
        catch(Exception e)
        {
            ErrorLogger.logError("Save file is corrupted of can't be found: "+e);     
        }
        return false;
    }
/**
 * In this boolean playerData the String fileName is passed; the user's data is
 * saved onto a .dat file, which the user can access. The try-catch statement is 
 * implemented below for this. 
 */      
    public static boolean playerData(String fileName)
    {
        try
        {
        ErrorLogger.logMessage("Looking for player data");
        File file = new File(fileName+".dat");
        
        
         if(file.isFile())
         {
             ErrorLogger.logMessage("Player data found");
             return true;
         }
         ErrorLogger.logMessage("Could not find player data");
         return false;               
        
        }
        catch(Exception e)
        {
            ErrorLogger.logError("Save file is corrupted of can't be found: "+e);     
        }
        return false;
    }
}
