/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid.plunder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * This HighScore Class stores high scores in a txt file using an arraylist; they
 * can be retrieved and saved on the HighScores file.
 */
public class HighScore {
/**
 * This saveScore method is passed down String name and Int score 
     * @return a list of hight scores and player names
 */         
     public static TreeMap<Integer,String> getHighScore()
     {
         try
         {
         TreeMap<Integer,String> scores = new TreeMap<Integer,String>();
         
         
          File file = new File("HighScores.dat");
          if(!file.isFile())
          {
              return scores;
          }
          //read file
          Scanner in = new Scanner(new BufferedReader(new FileReader(file)));
          String line = "";
          
          while(in.hasNext())
          {
              line = in.nextLine();
              String[] parts = line.split(" ");
              //System.out.print(parts[0]);
              scores.put(Integer.parseInt(parts[0]), parts[1]);
          }
          return scores;
         }
         catch(Exception e)
         { ErrorLogger.logError("Could not load high scores " + e);}
         return null;
     }
/**
 * This saveScore method is passed down String name and Int score 
     * @param name
     * @param score
 */          
     public static void saveScore(String name,int score)
     {
         try
        {
            File file = new File("HighScores.dat");
            if(!file.isFile())
            {
              file.createNewFile();
            }
            
            Scanner in = new Scanner(new FileReader(file));
            ArrayList<String> lines = new ArrayList<String>();
            
            while(in.hasNext())
            {lines.add(in.nextLine());}
            
            lines.add("" +score+ " " + name );
            
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            
            for(String line : lines)
            {
                out.write(line);
                out.newLine();
            }
            out.close();
        }
         catch(Exception e)
         {
             ErrorLogger.logError("Could not save high score " + e);
         }
     }
    
}
