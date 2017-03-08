package not.amazon.echo.sound;

import javax.sound.sampled.AudioFormat;

/**
 * Created by adammitchell on 27/02/2017.
 */
class FormatManager {
    static final int SAMPLE_RATE = 16000; /* MHz  */
    static final int SAMPLE_SIZE = 16;    /* bits */
    static final int SAMPLE_CHANNELS = 1;     /* mono */

    static AudioFormat getAudioFormat() {
        return new AudioFormat(SAMPLE_RATE
                , SAMPLE_SIZE
                , SAMPLE_CHANNELS
                , true /* signed */
                , true /* little-endian */
        );
    }
}
