package not.amazon.echo;

import not.amazon.echo.gui.IEchoGUI;
import not.amazon.echo.states.State;

/**
 * Created by AliciaDaurignac on 28/02/2017.
 * Public facing interface of Echo.
 */
public interface IEcho {

    void setState(State state);

    State currentState();

    IEchoGUI getGUI();

    void increaseVolume();

    void decreaseVolume();

    void onButtonPressed();
}
