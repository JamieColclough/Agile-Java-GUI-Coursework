package not.amazon.echo.states;

import not.amazon.echo.IEcho;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by AliciaDaurignac on 28/02/2017.
 */
public class ListeningTest {

    private Listening test;

    /**
     * Constructor for the test.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        test = new Listening();

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

    public void onEnterStateTest(IEcho echo) {
        //

    }
}
