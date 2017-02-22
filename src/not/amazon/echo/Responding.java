package not.amazon.echo;

/**
 * State representing the phase in which the product responds to the user
 * performs response then returns to the listening state
 *
 * @author James Colclough
 * @version 1.2
 */
public class Responding implements State {
    @Override
    public String toString() {
        return "Responding";
    }

    @Override
    public void onButtonPressed(StateMachine stateMachine) {
        //Can't press the button here, method performs no action        
    }

    @Override
    public void onEnterState(StateMachine stateMachine) {

        stateMachine.gui.setBackground(stateMachine.gui.iconEchoAnswer);

        String text = SpeechRecognition.speechRecognition("res/output.wav");
        System.out.println(text);
        stateMachine.setState(new OnOff());
    }
}
