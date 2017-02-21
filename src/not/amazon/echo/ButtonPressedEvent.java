package not.amazon.echo;

import java.util.EventObject;

/**
 * Created by AliciaDaurignac on 15/02/2017.
 * The event passed by the button to the listeners.
 */
public class ButtonPressedEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ButtonPressedEvent(Object source) {
        super(source);
    }
}
