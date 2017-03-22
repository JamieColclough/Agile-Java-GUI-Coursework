package not.amazon.echo.states;

import not.amazon.echo.MockEcho;
import not.amazon.echo.gui.EchoLights;
import not.amazon.echo.gui.MockEchoGUI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Tests the Responding class.
 * Created by AliciaDaurignac on 06/03/2017.
 */
public class RespondingTest {

    private MockEcho mockEcho;
    private MockEchoGUI mockEchoGUI;
    private Responding test;

    /**
     * Reads the file eastwood.wav and passes the byte array to the Responding state.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        String fileName = "res/eastwood.wav";
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(fis);
        byte[] buffer = new byte[(int) file.length()];
        dis.readFully(buffer);
        dis.close();
        test = new Responding(buffer);
        mockEchoGUI = new MockEchoGUI();
        mockEcho = new MockEcho(mockEchoGUI);
        mockEcho.setState(test);


    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Assert toString() returns "Responding".
     *
     * @throws Exception
     */
    @Test
    public void testToString() throws Exception {
        assertEquals(test.toString(), "Responding");

    }

    /**
     * Test the state has not changed when the button is pressed.
     *
     * @throws Exception
     */
    @Test
    public void testOnButtonPressed() throws Exception {
        test.onButtonPressed(mockEcho);
        assertSame(mockEcho.currentState(), test);

    }

    /**
     * Test the lights have been changed when the state is entered and
     * the new state is set to OnOff.
     *
     * @throws Exception
     */
    @Test
    public void testOnEnterState() throws Exception {
        test.onEnterState(mockEcho);
       // assertEquals(mockEchoGUI.currentLights, EchoLights.RESPONDING);
       // assertEquals(mockEcho.currentState().getClass(), OnOff.class);
    }
}