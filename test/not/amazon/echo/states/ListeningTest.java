package not.amazon.echo.states;

import not.amazon.echo.MockEcho;
import not.amazon.echo.gui.EchoLights;
import not.amazon.echo.gui.MockEchoGUI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the Listening class.
 * Created by AliciaDaurignac on 28/02/2017.
 */
public class ListeningTest {

    private MockEcho mockEcho;
    private MockEchoGUI mockEchoGUI;
    private Listening test;

    /**
     * Constructor for the test.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        test = new Listening();
        mockEchoGUI = new MockEchoGUI();
        mockEcho = new MockEcho(mockEchoGUI);

    }

    /**
     * Tears down the test.
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        test = null;

    }

    /**
     * Assert the toString() method returns "Listening".
     */
    @Test
    public void testToString() {
        assertEquals("Listening", test.toString());
    }

    /**
     * Asserts that Listening changes the lights to the listening state.
     */
    @Test
    public void onEnterStateTest() {
        test.onEnterState(mockEcho);
        assertEquals(mockEchoGUI.currentLights, EchoLights.LISTENING);


    }
}
