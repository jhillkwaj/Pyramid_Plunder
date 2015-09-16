/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid.plunder;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * This GraphicsManager Class loads the various images and is returned.
 */
public class GraphicsManager {
 /**
 * This method creates the images arraylist, so that it can be used
 * to return the specific image.
 */    
    private static ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();

 /**
 * This loagGraphics method lists all the images being used in the game. The 
 * images are set to a try-catch statement.
 */     
    public static void loadGraphics()
    {
        try {
            ErrorLogger.logMessage("Importing Graphics");
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/wall.png"))); //0
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/UI/UIBar.png"))); //1
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/UI/RedOrb.png"))); //2
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/UI/GrayOrb.png"))); //3
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Ghost2.png")));  //4
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/MainCharacter/Main_Standing.png"))); //5
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/MainCharacter/Main_Jump.png"))); //6
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/MainCharacter/Main_Running.png"))); //7
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/MainCharacter/Main_RunningTWO.png"))); //8
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/MainCharacter/Main_RunningTHREE.png"))); //9
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/MainCharacter/Jump_Alt.png"))); //10
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Ghost3.png")));  //11
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Stone.png"))); //12
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tile_Dirt.png"))); //13
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/brown.png"))); //14
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Sand.png")));  //15
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tile_Dark.png"))); //16
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/menuscreen.png"))); //17
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Menu_Button.png"))); //18
            images.add(null); //19
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/coins/Coin_One.png"))); //20
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/coins/Coin_Two.png"))); //21
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/coins/Coin_Three.png"))); //22
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/coins/Coin_Two.png"))); //23
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/coins/Coin_Four.png"))); //24
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/coins/Coin_Two.png"))); //25
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/coins/Coin_Three.png"))); //26
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/coins/Coin_Two.png"))); //27
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/footprints.png"))); //28
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/BitBlit.gif"))); //29
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/ThePyramidPlunderers.png"))); //30
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Names.png"))); //31
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Present.png"))); //32
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Interactive.png"))); //33
            images.add(null); //34
            images.add(null); //35
            images.add(null); //36
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Snake/snake1.png"))); //37
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Snake/snake2.png"))); //38
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Bat/bat1.png"))); //39
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Bat/bat2.png"))); //40
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Mummy1.png"))); //41
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Mummy2.png"))); //42
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/SwordUpB.png"))); //43
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/SwordRightB.png"))); //44
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/SwordDownB.png"))); //45
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/SwordUpD.png"))); //46
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/SwordRightD.png"))); //47
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/SwordDownD.png"))); //48
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Ghost.png"))); //49
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/SwordUp.png"))); //50
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/SwordRight.png"))); //51
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/SwordDown.png"))); //52
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/Pistol.png"))); //53
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/SwordUpG.png"))); //54
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/SwordRightG.png"))); //55
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/SwordDownG.png"))); //56
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/SwordUpO.png"))); //57
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/SwordRightO.png"))); //58
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/SwordDownO.png"))); //59
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/coins/Coin_One5.png"))); //60
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/coins/Coin_Two5.png"))); //61
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/coins/Coin_Three5.png"))); //62
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/coins/Coin_Two5.png"))); //63
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/coins/Coin_Four5.png"))); //64
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/coins/Coin_Two5.png"))); //65
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/coins/Coin_Three5.png"))); //66
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/coins/Coin_Two5.png"))); //67
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/coins/end.png"))); //68
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/coins/end2.png"))); //69
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/SwordUpR.png"))); //70
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/SwordRightR.png"))); //71
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/Tools/SwordDownR.png"))); //72
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/SkeletonS.png"))); //73 -Seleton-
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/SkeletonW1.png"))); //74
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/SkeletonW2.png"))); //75
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/SkeletonA1.png"))); //76
            images.add(ImageIO.read(GraphicsManager.class.getResource("Graphics/SkeletonA2.png"))); //77
            
            Thread.sleep(10);

        } catch (Exception ex) {
            ErrorLogger.logError("Can't import some or all graphics " + ex);
        }
    }
/**
 * In this getGraphics method, the graphicsNumber is passed down. An if-statement
 * is used to select the GraphicsNumber and is returned. 
     * @param graphicsNumber
     * @return the requested image
 */       
    public static BufferedImage getGraphics(int graphicsNumber)
    {
        if(images.size()==0)
        {loadGraphics();}
        return images.get(graphicsNumber);
    }
    
}
