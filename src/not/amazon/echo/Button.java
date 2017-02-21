package not.amazon.echo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AliciaDaurignac on 15/02/2017.
 * <p>
 * Button holds a list of listeners that will be notified when the button is pressed.
 */
public class Button {

    private List<ButtonPressedListener> listeners = new ArrayList<>();

    /**
     * Fires the event when button is pressed.
     */
    public void pressButton() {
        for (ButtonPressedListener listener : listeners) {
            listener.buttonPressed(new ButtonPressedEvent(this));
        }
    }

    /**
     * Registers the listeners to the button.
     *
     * @param listener Listener for the event.
     */
    public void addListener(ButtonPressedListener listener) {
        listeners.add(listener);


    }
}
