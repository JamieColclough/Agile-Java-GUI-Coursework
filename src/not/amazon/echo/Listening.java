package not.amazon.echo;

/**
 * State representing the listening phase of the product
 * waiting until sound is detected
 * @author James Colclough
 * @version 1.2
 */
public class Listening implements State{
    @Override
    public String toString(){
        return "Listening";
    }

    @Override
    public void onButtonPressed(StateMachine stateMachine){
        //Code involving listening for speech
        
        stateMachine.setState(new OnOff());
    }

    @Override
    public void onEnterState(StateMachine stateMachine){
        
        new Thread(() -> RecordSound.recordSound()).start(); //starts recording sound during construction

    }
}
