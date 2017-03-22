package not.amazon.echo.sound;

import not.amazon.echo.ErrorHandler;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/*
 * Record sound. Original by David Wakeling, 2017.
 * Derived by Adam Mitchell
 */
public class RecordSound {
    private static final int TIMER = 5;     /* secs */

    /*
     * Set up stream.
     */
    private static AudioInputStream setupStream() {
        try {
            AudioFormat af = FormatManager.getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, af);
            TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
            AudioInputStream stm = new AudioInputStream(line);
            line.open(af);
            line.start();
            return stm;
        } catch (Exception ex) {
            ErrorHandler.log(ex);
            System.exit(1);
            return null;
        }
    }

    /*
     * Read stream.
     */
    private static ByteArrayOutputStream readStream(AudioInputStream stm) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            AudioFormat format = stm.getFormat();
            int bufferSize = FormatManager.SAMPLE_RATE * format.getFrameSize();
            byte buffer[] = new byte[bufferSize];

            for (int counter = TIMER; counter > 0; counter--) {
                int n = stm.read(buffer, 0, buffer.length);
                //TODO call this
                //float rms = detectRMS(buffer, format);
                if (n > 0) {
                    bos.write(buffer, 0, n);
                } else {
                    break;
                }
            }

            return bos;
        } catch (Exception ex) {
            ErrorHandler.log(ex);
            System.exit(1);
            return null;
        }
    }

    private static float detectRMS(byte buffer[], AudioFormat format) {
        float rms = 0f;

        int frameSize = format.getFrameSize();
        boolean isBigEndian = format.isBigEndian();
        for (int i = 0; i < buffer.length; i += frameSize) {
            //only works for PCM signed
            long sample = 0;
            for (int j = 0; j < frameSize; j++) {
                // decode bytes into a single sample
                // & 0xff masks the byte to avoid sign extension when byte is cast to int
                if (isBigEndian) {
                    sample = sample | ((buffer[i + j] & 0xffL) << ((frameSize - j) * 8L));
                } else {
                    sample = sample | ((buffer[i + j] & 0xffL) << (j * 8L));
                }
            }
        }

        return rms;
    }

    private static ByteArrayOutputStream formatStream(ByteArrayOutputStream bos) {
        ByteArrayOutputStream output = null;
        try {
            AudioFormat af = FormatManager.getAudioFormat();
            byte[] ba = bos.toByteArray();
            InputStream is = new ByteArrayInputStream(ba);
            AudioInputStream ais = new AudioInputStream(is, af, ba.length);
            output = new ByteArrayOutputStream();
            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, output);
        } catch (Exception ex) {
            ErrorHandler.log(ex);
            System.exit(1);
        }
        return output;
    }

    public static byte[] recordSoundData() {
        return formatStream(readStream(setupStream())).toByteArray();
    }
}

