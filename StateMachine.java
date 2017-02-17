package notAmazonEcho;

/**
 * Class used for identifying the current state of the program and calling
 * various methods
 * @author James Colclough
 * @version 1.1
 */
public class StateMachine {

    private State state;

    /**
     * Constructor for stateMachine, default constructor as when turned on should always start in standby mode
     */
    public StateMachine() {
        this.state = new OnOff();
    }
    
    /**
     * Method to change the machine's current state
     * 
     * @param state
     * @version 1.1
     */
    public void setState(State state){
        this.state = state;
    }
    
    /**
     * Method used for identifying the current state on the machine
     * used for conditionals and testing
     * 
     * @return The String describing the current state of the machine
     */
    public String currentState(){
        return this.state.toString();
    }
    
        public static void main(String[] args) {
    }
}
