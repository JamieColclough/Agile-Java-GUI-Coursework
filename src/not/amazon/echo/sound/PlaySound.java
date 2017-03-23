package not.amazon.echo.sound;


import not.amazon.echo.ErrorHandler;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Static procedures for playing sound.
 * @author Adam Mitchell
 */
public class PlaySound {

    public final static String FORMAT = "riff-16khz-16bit-mono-pcm";

    public static FloatControl control; // cache of speaker volume control

    /**
     * Attempts to access the speaker port of the AudioSystem for the purpose of controlling the volume.
     *
     * @return The FloatControl used to change the volume of the speaker Port
     */
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

    /**
     * Gets the volume of the speakers
     * @return the volume of the speakers
     */
    public static float getVolume() {
        FloatControl ctrl = getSpeakerControl();
        if (ctrl == null) return 0f;
        return ctrl.getValue();
    }

    /**
     * Sets the volume of the speaker output
     */
    public static void setVolume(float volume) {
        FloatControl ctrl = getSpeakerControl();
        if (ctrl == null) return;
        if (volume > ctrl.getMaximum()) volume = ctrl.getMaximum();
        if (volume < ctrl.getMinimum()) volume = ctrl.getMinimum();
        ctrl.setValue(volume);
    }

    /**
     * Create a Clip from an InputStream
     * @param input the InputStream containing the audio data
     * @return the Clip to play the audio contained in input
     * @throws SoundException when the InputStream is invalid
     */
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

    /**
     * Create a Clip from a file by passing the filename
     * @param fileName the name of the file to load the Clip from
     * @return a Clip that can be played to play the contents of the file
     * @throws SoundException when the file is not found or invalid
     */
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

    /**
     * Create a clip from an array of bytes
     * @param data the array of bytes containing audio data
     * @return a new Clip to play the array of bytes
     * @throws SoundException when the data is invalid
     */
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
