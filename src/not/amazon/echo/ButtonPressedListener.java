package not.amazon.echo;

/**
 * Created by AliciaDaurignac on 15/02/2017.
 * The interface for the listeners.
 */
public interface ButtonPressedListener {

    /**
     * Method called when the button is pressed.
     *
     * @param event Event of the button being pressed.
     */
    void buttonPressed(ButtonPressedEvent event);
}
