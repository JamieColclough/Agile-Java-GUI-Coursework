package not.amazon.echo.sound;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by adammitchell on 27/02/2017.
 */
public class PlaySound {

    public final static String FORMAT = "riff-16khz-16bit-mono-pcm";

    public static float volume = 1f;

    public static Clip createClip(InputStream input) throws SoundException {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(input);
            clip.open(inputStream);
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue((volume - 1f) * 40f);
            return clip;
        } catch (Exception e) {
            throw new SoundException("Encountered an error playing sound");
        }
    }

    public static Clip createClip(String fileName) throws SoundException {
        InputStream bufferedIn;
        try {
            InputStream audioSrc = new FileInputStream(fileName);
            //add buffer for mark/reset support
            bufferedIn = new BufferedInputStream(audioSrc);
        } catch (Exception e) {
            throw new SoundException("Encountered an error playing sound");
        }
        return createClip(bufferedIn);
    }


    public static Clip createClip(byte[] data) throws SoundException {
        InputStream bufferedIn;
        try {
            bufferedIn = new ByteArrayInputStream(data);
        } catch (Exception e) {
            throw new SoundException("Encountered an error playing sound");
        }
        return createClip(bufferedIn);
    }
}
