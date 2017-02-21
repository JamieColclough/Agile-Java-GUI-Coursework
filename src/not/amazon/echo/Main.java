package not.amazon.echo;

/**
 * Created by AliciaDaurignac on 21/02/2017.
 * Main method that creates a new Echo which is by default on the On/Off mode and press it to be in Listening mode.
 */
public class Main {
    /**
     * Point of entry.
     *
     * @param args
     */
    public static void main(String[] args) {
        StateMachine stateMachine = new StateMachine();
        System.out.println(stateMachine.currentState());
        stateMachine.getButton().pressButton();
        System.out.println(stateMachine.currentState());


    }
}
