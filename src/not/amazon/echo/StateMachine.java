package not.amazon.echo;

/**
 * Class used for identifying the current state of the program and calling
 * various methods
 * @author James Colclough
 * @version 1.1
 */
public class StateMachine {

    private State state;
    private Button button;

    /**
     * Constructor for stateMachine, default constructor as when turned on should always start in standby mode
     */
    public StateMachine() {
        this.state = new OnOff();
        button = new Button();
        button.addListener(event -> state.doAction(this));
    }
    
    /**
     * Method to change the machine's current state
     * 
     * @param state
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

    /**
     * @return the button
     */
    public Button getButton() {
        return button;
    }
}
