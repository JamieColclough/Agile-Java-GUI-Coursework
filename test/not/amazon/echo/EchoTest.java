package not.amazon.echo;

import not.amazon.echo.states.MockState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Test the Echo class.
 * Created by AliciaDaurignac on 07/03/2017.
 */
public class EchoTest {


    Echo echo;
    MockState mockState;

    /**
     * Create a new Echo.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        echo = new Echo();
        mockState = new MockState();

    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Assert that setting the state works and the new state is entered.
     *
     * @throws Exception
     */
    @Test
    public void testSetState() throws Exception {
        echo.setState(mockState);
        assertSame(echo.currentState(), mockState);
        assertTrue(mockState.enteredState);
    }
}