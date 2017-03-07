package not.amazon.echo.states;

import not.amazon.echo.IEcho;

/**
 * Mock object for State.
 * Created by AliciaDaurignac on 07/03/2017.
 */
public class MockState implements State {

    public boolean buttonPressed = false;
    public boolean enteredState = false;


    @Override
    public void onButtonPressed(IEcho echo) {
        buttonPressed = true;
    }

    @Override
    public void onEnterState(IEcho echo) {
        enteredState = true;

    }
}
