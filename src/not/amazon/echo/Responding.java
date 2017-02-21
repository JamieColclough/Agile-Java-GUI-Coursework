package not.amazon.echo;

/**
 * State representing the phase in which the product responds to the user
 * performs response then returns to the listening state
 * @author James Colclough
 * @version 1.1
 */
public class Responding implements State{
    @Override
    public String toString(){
        return "Responding";
    }
    
    @Override
    public void doAction(StateMachine stateMachine){
        //Code involving response here- speaking response etc.
        
        stateMachine.setState(new Listening());
    }
}
