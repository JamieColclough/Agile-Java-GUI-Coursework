package not.amazon.echo;

import not.amazon.echo.gui.IEchoGUI;
import not.amazon.echo.states.State;

/**
 * MockEcho object for tests.
 * Created by AliciaDaurignac on 28/02/2017.
 */
public class MockEcho implements IEcho {

    public IEchoGUI gui;
    private State state;
    public MockEcho(IEchoGUI GUI) {
        this.gui = GUI;
    }

    /**
     * Setting the state does not call onEnterState for the new state
     * so that we don't start a stateMachine lifecycle.
     *
     * @param state state of the Echo.
     */
    @Override
    public void setState(State state) {
        this.state = state;

    }

    /**
     * @return the current state of the mock Echo.
     */
    @Override
    public State currentState() {
        return state;
    }

    /**
     * @return the GUI of the mock Echo.
     */
    @Override
    public IEchoGUI getGUI() {
        return gui;
    }
}
