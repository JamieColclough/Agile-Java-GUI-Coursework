package not.amazon.echo;

/**
 * State representing the product in its On/Off phase
 * waiting for the power button to be pressed
 *
 * @author James Colclough
 * @version 1.2
 */
public class OnOff implements State {
    @Override
    public String toString() {
        return "On/Off";
    }

    @Override
    public void onButtonPressed(StateMachine stateMachine) {
        stateMachine.setState(new Listening());
    }

    @Override
    public void onEnterState(StateMachine stateMachine) {
        //Code for lights changing etc here
        stateMachine.gui.setBackground(stateMachine.gui.iconEchoOff);

    }
}
