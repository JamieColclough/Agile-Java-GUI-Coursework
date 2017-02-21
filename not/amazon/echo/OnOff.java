package not.amazon.echo;

/**
 * State representing the product in its On/Off phase
 * waiting for the power button to be pressed
 * @author James Colclough
 * @version 1.1
 */
public class OnOff implements State{
    @Override
    public String toString(){
        return "On/Off";
    }
    
    @Override
    public void doAction(StateMachine stateMachine){
        //Code involving turning on here- e.g. lights and sound
        
        
        stateMachine.setState(new Listening());
    }
}
