package not.amazon.echo;

import not.amazon.echo.gui.EchoGUI;
import not.amazon.echo.sound.PlaySound;
import not.amazon.echo.states.OnOff;
import not.amazon.echo.states.State;

/**
 * Class used for identifying the current state of the program and calling
 * various methods
 *
 * @author James Colclough
 * @version 1.2
 */
public class Echo implements IEcho
{

    private final EchoGUI gui;
    private State state;
    private float volume = 1;

    /**
     * Constructor for stateMachine, default constructor as when turned on should always start in standby mode.
     *
     * Deleted the Button, ButtonPressedEvent and ButtonPressedListener classes in favour of builtin ActionListener.
     */
    public Echo()
    {
        this.state = new OnOff();

        gui = new EchoGUI(this);
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
    public State currentState() {
        return this.state;
    }

    /**
     * Returns the GUI interface.
     *
     * @return gui
     */
    public EchoGUI getGUI() {
        return gui;
    }

    /**
     * @author Alicia Daurignac
     * Method that increases the volume by 10%.
     */
    @Override
    public void increaseVolume() {
        volume += 0.1;
        if (volume > 1) volume = 1;
        PlaySound.volume = volume;
    }

    /**
     * @author Alicia Daurignac
     * Method that decreases the volume by 10%.
     */
    @Override
    public void decreaseVolume() {
        volume -= 0.1;
        if (volume < 0) volume = 0;
        PlaySound.volume = volume;
    }

    @Override
    public void onButtonPressed() {
        state.onButtonPressed(this);
    }


}
