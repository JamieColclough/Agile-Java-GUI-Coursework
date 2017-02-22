package not.amazon.echo;

/**
 * Basic interface for states contained within the state machine
 *
 * @author James Colclough
 * @version 1.2
 */
public interface State {

    /**
     * Method that performs a specific action depending on the implementation when the button is pressed
     *
     * @param stateMachine
     */
    void onButtonPressed(StateMachine stateMachine);

    /**
     * Method that is called when the new state has been set up, will vary on implementation
     *
     * @param stateMachine
     */
    void onEnterState(StateMachine stateMachine);
}
