package not.amazon.echo;

/**
 * State representing the listening phase of the product
 * waiting until sound is detected
 * @author James Colclough
 * @version 1.1
 */
public class Listening implements State{
    @Override
    public String toString(){
        return "Listening";
    }

    public Listening()
    {
        new Thread(() -> RecordSound.recordSound()).start();
    }
    

    @Override
    public void doAction(StateMachine stateMachine){
        //Code involving listening for speech
        
        stateMachine.setState(new Responding());
    }
    
    /**
     * Additional Method in case the product is turned off before doAction() is called
     * 
     * @param stateMachine
     */
    public void turnOff(StateMachine stateMachine){
        //Code involving turning off- i.e. turn off sound, light change
        
        stateMachine.setState(new OnOff());
    }
}
