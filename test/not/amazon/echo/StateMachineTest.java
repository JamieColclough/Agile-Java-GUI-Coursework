package not.amazon.echo;

import not.amazon.echo.states.Listening;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the Echo Class
 * @author James Colclough
 * @version 1.1
 */
public class StateMachineTest {
    Echo testMachine;
    String expectedState;
    String testState;


    @Before
    public void setUp() {
        testMachine = new Echo();
    }

    /**
     * Test of setState method, changes state and asserts it is equal to the expected result
     * returned from currentState()
     */
    @Test
    public void testSetState() {
        testMachine.setState(new Listening());
        expectedState = "Listening";
        testState = testMachine.currentState().toString();
        assertEquals(expectedState,testState);
        
    }

    /**
     * Test of currentState method, asserts that the state machine in it's original state returns
     * the expected result
     */
    @Test
    public void testCurrentState() {
        expectedState = "On/Off";
        testState = testMachine.currentState().toString();
        assertEquals(expectedState,testState);        
    }
    
}
