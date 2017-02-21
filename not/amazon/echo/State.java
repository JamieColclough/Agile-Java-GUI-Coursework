package notAmazonEcho;

/**
 * Basic interface for states contained within the state machine
 * @author James Colclough
 * @version 1.1
 */
public interface State {

    /**
     * Method that performs a specific action depending on the implementation
     * 
     * @param stateMachine
     */
    public void doAction(StateMachine stateMachine);
}
