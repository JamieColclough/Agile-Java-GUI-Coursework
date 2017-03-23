package not.amazon.echo.sound;


import not.amazon.echo.ErrorHandler;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by adammitchell on 27/02/2017.
 */
public class PlaySound {

    public final static String FORMAT = "riff-16khz-16bit-mono-pcm";

    public static FloatControl control;

    private static FloatControl getSpeakerControl() {
        if (control != null) return control;
        try {
            Port port = (Port) AudioSystem.getLine(Port.Info.SPEAKER);
            port.open();
            control = (FloatControl) port.getControl(FloatControl.Type.VOLUME);
        } catch (LineUnavailableException | IllegalArgumentException e) {
            ErrorHandler.log(e);
        }
        return control;
    }

    public static float getVolume() {
        return getSpeakerControl().getValue();
    }

    /**
     * @author Alicia Daurignac
     * Sets the volume of the speaker output Line
     */
    public static void setVolume(float volume) {
        if (volume > getSpeakerControl().getMaximum()) volume = getSpeakerControl().getMaximum();
        if (volume < getSpeakerControl().getMinimum()) volume = getSpeakerControl().getMinimum();
        getSpeakerControl().setValue(volume);
    }

    public static Clip createClip(InputStream input) throws SoundException {
        try {
            //Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(input);
            Clip clip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, inputStream.getFormat()));
            clip.open(inputStream);

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
