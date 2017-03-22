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
    private static final int RATE = 5;
    private static final int TIMEOUT = 3;
    private static final int MIN_QUERY = 5;
    private static final float RMS_THRESH = 0.025f;

    /*
     * Set up stream.
     */
    private static TargetDataLine setupLine(AudioFormat af) {
        try {
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, af);
            TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
            return line;
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
            int bufferSize = FormatManager.SAMPLE_RATE * format.getFrameSize() / RATE;
            byte buffer[] = new byte[bufferSize];

            boolean recording = false;
            boolean listening = true;
            int timeoutCounter = 0;
            int recordingLength = 0;

            while (listening) {
                int n = stm.read(buffer, 0, buffer.length);
                if (n <= 0) break;
                float[] samples = convertToSamples(buffer, format);
                float rms = calculateRMS(samples);
                //float peak = calculatePeak(samples);

                if (rms > RMS_THRESH) {
                    timeoutCounter = 0;
                    recording = true;
                } else {
                    if (recording) {
                        timeoutCounter++;
                        if (timeoutCounter >= TIMEOUT) {
                            recording = false;
                            if (recordingLength >= MIN_QUERY) { // success
                                listening = false;
                            } else {
                                recordingLength = 0;
                                bos.reset(); // discard the current recording
                            }
                        }
                    }
                }
                if (recording) {
                    bos.write(buffer, 0, n);
                    recordingLength++;
                }
            }

            return bos;
        } catch (Exception ex) {
            ErrorHandler.log(ex);
            System.exit(1);
            return null;
        }
    }

    private static float[] convertToSamples(byte[] buffer, AudioFormat format) {
        int max = 0;

        float[] samples = new float[buffer.length / 2];
        for (int i = 0; i < buffer.length; i += 2) {
            int hiByte = (buffer[i]);
            int loByte = (buffer[i + 1]);
            short shortVal = (short) hiByte;
            shortVal = (short) ((shortVal << 8) | (byte) loByte);
            samples[i / 2] = (float) shortVal / Short.MAX_VALUE;
        } // for

        return samples;
    } // calculateLevel

    private static float calculateRMS(float[] samples) {
        float sum = 0f;
        for (int i = 0; i < samples.length; i++) {
            sum += samples[i];
        }

        float average = samples.length > 0 ? sum / (float) samples.length : 0f;

        float sumMeanSquare = 0f;
        for (int i = 0; i < samples.length; i++) {
            sumMeanSquare += (float) Math.pow(samples[i] - average, 2);
        }

        float averageMeanSquare = sumMeanSquare / (float) samples.length;

        return (float) Math.sqrt(averageMeanSquare);
    }

    private static float calculatePeak(float[] samples) {
        float max = 0f;
        for (int i = 0; i < samples.length; i++) {
            float a = Math.abs(samples[i]);
            if (a > max) max = a;
        }
        return max;
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

    public static byte[] recordSoundData() throws LineUnavailableException {
        AudioFormat af = FormatManager.getAudioFormat();
        TargetDataLine line = setupLine(af);
        AudioInputStream stm = new AudioInputStream(line);
        line.open(af);
        line.start();
        ByteArrayOutputStream bos = readStream(stm);
        line.stop();
        line.close();
        return formatStream(bos).toByteArray();
    }
}

