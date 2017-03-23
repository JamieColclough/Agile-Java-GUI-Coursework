package not.amazon.echo.sound;

import not.amazon.echo.ErrorHandler;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Static procedures for recording the microphone
 * Derived from David Wakeling, 2017.
 * @author Adam Mitchell
 */
public class RecordSound {
    private static final int RATE = 5; // Data chunks per second
    private static final int TIMEOUT = 5; // Timeout of audio listening in chunks
    private static final int MIN_QUERY = 5; // Minimum length of a recording in chunks (not including the timeout tail)
    private static final float RMS_THRESH = 0.025f; // The RMS threshold

    private static volatile boolean stopListeningFlag = false; // Flag to cancel the thread recording

    /**
     * Set up stream.
     * @param af the AudioFormat to use when recording
     * @return The TargetDataLine of the Microphone
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

    /**
     * Record the microphone until a sound is detected or stopListening() has been called.
     * @param stm the stream to read from
     * @return a ByteArrayOutputStream containing the sample recorded
     * @throws IOException when there is an error reading the stream
     */
    private static ByteArrayOutputStream readStream(AudioInputStream stm) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        AudioFormat format = stm.getFormat();
        int bufferSize = FormatManager.SAMPLE_RATE * format.getFrameSize() / RATE;
        byte buffer[] = new byte[bufferSize];

        boolean recording = false;
        boolean listening = true;
        int timeoutCounter = 0;
        int recordingLength = 0;

        while (listening && !stopListeningFlag) {
            int n = stm.read(buffer, 0, buffer.length);
            if (n <= 0) break;
            float[] samples = convertToSamples(buffer);

            float rms = calculateRMS(samples);

            if (rms > RMS_THRESH) {
                timeoutCounter = 0; // started detecting a sound
                recording = true;
            } else {
                if (recording) {
                    timeoutCounter++;
                    if (timeoutCounter >= TIMEOUT) { // recording timeout from silence
                        recording = false;
                        if (recordingLength >= MIN_QUERY + TIMEOUT) {
                            listening = false; // success
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

        if (stopListeningFlag) return null; // recording was cancelled

        return bos;
    }

    /**
     * Convert signed 16bit, bigEndian wav to raw float samples
     *
     * @param buffer the input data
     * @return an array of samples
     */
    private static float[] convertToSamples(byte[] buffer) {
        float[] samples = new float[buffer.length / 2];
        for (int i = 0; i < buffer.length; i += 2) {
            int hiByte = (buffer[i]);
            int loByte = (buffer[i + 1]);
            short shortVal = (short) hiByte;
            shortVal = (short) ((shortVal << 8) | (byte) loByte);
            samples[i / 2] = (float) shortVal / Short.MAX_VALUE;
        }

        return samples;
    }

    /**
     * Calculates the RMS (root mean square) of an array of samples
     * @param samples an array of floating point samples
     * @return the RMS level of this sample array
     */
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

    /**
     * Find the maximum peak (largest magnitude) of an array of samples
     * @param samples an array of floating point samples
     * @return the peak of the sample array
     */
    private static float calculatePeak(float[] samples) {
        float max = 0f;
        for (int i = 0; i < samples.length; i++) {
            float a = Math.abs(samples[i]);
            if (a > max) max = a;
        }
        return max;
    }

    /**
     * Format a raw byte array stream into a WAV file byte stream
     * @param bos The data to format
     * @return A formatted WAV file of the input data
     */
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
        }
        return output;
    }

    /**
     * Public method to record the microphone until speech is detected
     * @return A byte array of detected speech in WAV format
     * @throws LineUnavailableException when the microphone line is unavailable
     * @throws IOException when an IO error occurs
     */
    public static byte[] recordSoundData() throws LineUnavailableException, IOException {
        stopListeningFlag = false;
        AudioFormat af = FormatManager.getAudioFormat();
        TargetDataLine line = setupLine(af);
        AudioInputStream stm = new AudioInputStream(line);
        line.open(af);
        line.start();
        ByteArrayOutputStream bos = readStream(stm);
        line.stop();
        line.close();
        if (bos == null) return null; //recording was stopped
        return formatStream(bos).toByteArray();
    }

    /**
     * Stop all current listening and return null to indicate cancelled recording
     */
    public static void stopListening() {
        stopListeningFlag = true;
    }
}

