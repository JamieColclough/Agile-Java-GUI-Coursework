package not.amazon.echo.sound;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;

/**
 * Created by AliciaDaurignac on 23/03/2017.
 */
public class PlaySoundTest {


    /**
     * Asserts that the volume is correctly set.
     */
    @Test
    public void testSetVolume() {
        PlaySound.setVolume(1f);
        assertTrue(PlaySound.control.getValue() == 1f);
        PlaySound.setVolume(0f);
        assertTrue(PlaySound.control.getValue() == 0f);
    }

    /**
     * Test that the clip was created without errors.
     *
     * @throws SoundException
     * @throws FileNotFoundException
     */
    @Test
    public void testCreateClip() throws SoundException, FileNotFoundException {
        PlaySound.createClip("res/eastwood.wav");
        InputStream inputStream = new BufferedInputStream(new FileInputStream("res/eastwood.wav"));
        PlaySound.createClip(inputStream);
    }

}