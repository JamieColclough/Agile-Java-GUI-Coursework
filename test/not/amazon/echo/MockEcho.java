package not.amazon.echo;

import not.amazon.echo.gui.IEchoGUI;
import not.amazon.echo.states.State;

/**
 * Created by AliciaDaurignac on 28/02/2017.
 * Mock Echo object for tests.
 */
public class MockEcho implements IEcho {

    public IEchoGUI gui;
    private State state;

    public MockEcho(IEchoGUI GUI) {
        this.gui = GUI;
    }

    @Override
    public void setState(State state) {
        this.state = state;

    }

    @Override
    public State currentState() {
        return state;
    }

    @Override
    public IEchoGUI getGUI() {
        return gui;
    }
}
