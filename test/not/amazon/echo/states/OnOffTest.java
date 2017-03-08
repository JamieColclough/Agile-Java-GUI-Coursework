package not.amazon.echo.states;

import not.amazon.echo.MockEcho;
import not.amazon.echo.gui.EchoLights;
import not.amazon.echo.gui.MockEchoGUI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the OnOFF class.
 * Created by AliciaDaurignac on 06/03/2017.
 */
public class OnOffTest {

    private OnOff onOff;
    private MockEcho mockEcho;
    private MockEchoGUI mockEchoGUI;


    /**
     * Create a mock Echo and a mock EchoGui for the test.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        onOff = new OnOff();
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

    }

    /**
     * Test the method returns the string "On/Off".
     *
     * @throws Exception
     */
    @Test
    public void testToString() throws Exception {
        assertEquals(onOff.toString(), "On/Off");

    }

    /**
     * Assert that the state has been changed to Listening once the
     * button has been pressed.
     *
     * @throws Exception
     */
    @Test
    public void testOnButtonPressed() throws Exception {
        onOff.onButtonPressed(mockEcho);
        assertEquals(mockEcho.currentState().getClass(), Listening.class);

    }

    /**
     * Test that the lights have been changed to be off when the state
     * is entered.
     *
     * @throws Exception
     */
    @Test
    public void testOnEnterState() throws Exception {
        onOff.onEnterState(mockEcho);
        assertEquals(mockEchoGUI.currentLights, EchoLights.OFF);

    }
}