package notAmazonEcho;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test class for the StateMachine Class
 * @author James Colclough
 * @version 1.1
 */
public class StateMachineTest {
    StateMachine testMachine;
    String expectedState;
    String testState;
    
    
    @Before
    public void setUp() {
        testMachine = new StateMachine();
    }

    /**
     * Test of setState method, changes state and asserts it is equal to the expected result
     * returned from currentState()
     */
    @Test
    public void testSetState() {
        testMachine.setState(new Listening());
        expectedState = "Listening";
        testState = testMachine.currentState();
        assertEquals(expectedState,testState);
        
    }

    /**
     * Test of currentState method, asserts that the state machine in it's original state returns
     * the expected result
     */
    @Test
    public void testCurrentState() {
        expectedState = "On/Off";
        testState = testMachine.currentState();
        assertEquals(expectedState,testState);        
    }
    
}
