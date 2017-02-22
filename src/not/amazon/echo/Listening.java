package not.amazon.echo;

/**
 * State representing the listening phase of the product
 * waiting until sound is detected
 *
 * @author James Colclough
 * @version 1.2
 */
public class Listening implements State {

    private Thread recordThread = null;

    @Override
    public String toString() {
        return "Listening";
    }

    @Override
    public void onButtonPressed(StateMachine stateMachine) {
        //Code involving listening for speech

        //if(recordThread != null) recordThread.interrupt();

        //stateMachine.setState(new OnOff());
    }

    @Override
    public void onEnterState(StateMachine stateMachine) {

        stateMachine.gui.setBackground(stateMachine.gui.iconEcho);

        new Thread(() -> {
            RecordSound.recordSound();
            stateMachine.setState(new Responding());
        }).start();

    }
}
