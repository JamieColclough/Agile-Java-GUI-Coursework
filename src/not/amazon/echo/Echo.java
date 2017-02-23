package not.amazon.echo;

import not.amazon.echo.gui.EchoInterface;
import not.amazon.echo.states.OnOff;
import not.amazon.echo.states.State;

/**
 * Class used for identifying the current state of the program and calling
 * various methods
 *
 * @author James Colclough
 * @version 1.2
 */
public class Echo
{

    public final EchoInterface gui;
    private State state;

    /**
     * Constructor for stateMachine, default constructor as when turned on should always start in standby mode
     */
    public Echo()
    {
        this.state = new OnOff();
        Button button = new Button();
        button.addListener(event -> state.onButtonPressed(this));

        gui = new EchoInterface(button);
    }

    /**
     * Method to change the machine's current state
     *
     * @param state Instance of the new state
     */
    public void setState(State state) {
        this.state = state;

        state.onEnterState(this); //Performs this when new state is set up
    }

    /**
     * Method used for identifying the current state on the machine
     * used for conditionals and testing
     *
     * @return The String describing the current state of the machine
     */
    public String currentState() {
        return this.state.toString();
    }
}
