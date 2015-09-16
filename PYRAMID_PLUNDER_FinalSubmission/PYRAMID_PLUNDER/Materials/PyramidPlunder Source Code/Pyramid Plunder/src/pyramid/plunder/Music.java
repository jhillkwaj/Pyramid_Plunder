/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 * In this Music Class, the sound file is played for the duration of the background. 
 */
public class Music {

         private static Clip backClip = null;
    private static float volume = 1;
/**
 * In this playBackgrounds method, the sound file located and named as, 
 * src/JustinPPSong.wav, is implemented in a try-catch loop and played. 
 * The logerror is also defined. 
 */
   public static void playBackgrounds()
   {
       try 
		{
                    if(backClip==null || backClip.isActive()==false)
                    {
                    AudioInputStream audio = null;
                    if(GameRunner.getCollidables().size()<5) 
                        audio = AudioSystem.getAudioInputStream(Music.class.getResource("sound/mainsong.wav"));
                    else
                        audio = AudioSystem.getAudioInputStream(Music.class.getResource("sound/JustinPPSong.wav"));
                    DataLine.Info clip=new DataLine.Info(Clip.class,audio.getFormat());
                    backClip=(Clip)AudioSystem.getLine(clip);
                    backClip.open(audio);
                    FloatControl gainControl = 
                    (FloatControl) backClip.getControl(FloatControl.Type.MASTER_GAIN);
                    if(volume > 0)
                    {gainControl.setValue(volume); }
                    backClip.addLineListener(new LineListener() {
                    public void update(LineEvent event) {
                        if (event.getType() == LineEvent.Type.STOP) 
                        {
                            backClip.drain();
                            playBackgrounds();
                        }
                    }
                    }
                    );
                   backClip.start();
                    }
		}
		catch(Exception e)
		{
			ErrorLogger.logError("Could not play background music    " + e);		
		}  
   }
/**
 * In this playSound method, String name and float volume is passed down; 
 * a try-catch statement is defined and the logerror is defined.
     * @param name
     * @param volume
 */      
          public static void playSound(String name, float volume)
          {
              try
              {
                    AudioInputStream audio = AudioSystem.getAudioInputStream(Music.class.getResource("sounds/"+name));
                    DataLine.Info clip=new DataLine.Info(Clip.class,audio.getFormat());
                    final Clip backClip=(Clip)AudioSystem.getLine(clip);
                    backClip.open(audio);
                    FloatControl gainControl = 
                    (FloatControl) backClip.getControl(FloatControl.Type.MASTER_GAIN);
                    gainControl.setValue(-volume/Music.volume);
                    backClip.addLineListener(new LineListener() {
                    public void update(LineEvent event) {
                        if (event.getType() == LineEvent.Type.STOP) 
                        {
                            backClip.drain();
                        }
                    }
                    }
                    );
                   backClip.start();
		}
		catch(Exception e)
		{
			ErrorLogger.logError("Could not play sound   " + e);		
		}  
         } 
}
