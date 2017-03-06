package not.amazon.echo.states;

import not.amazon.echo.IEcho;

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
     * @param echo The context of the state.
     */
    void onButtonPressed(IEcho echo);

    /**
     * Method that is called when the new state has been set up, will vary on implementation
     *
     * @param echo The context of the state.
     */
    void onEnterState(IEcho echo);
}
