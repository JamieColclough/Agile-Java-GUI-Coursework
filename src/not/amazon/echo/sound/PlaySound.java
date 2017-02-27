package not.amazon.echo.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by adammitchell on 27/02/2017.
 */
public class PlaySound {
    public static void playSound(String fileName) {
        try {
            Clip clip = AudioSystem.getClip();
            InputStream audioSrc = new FileInputStream(fileName);
            //add buffer for mark/reset support
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void playSoundAsync(String fileName) {
        new Thread(() -> playSound(fileName)).start();
    }
}
