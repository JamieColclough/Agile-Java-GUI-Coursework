package not.amazon.echo.states;

import not.amazon.echo.Echo;
import not.amazon.echo.network.SpeechRecognition;
/**
 * State representing the phase in which the product responds to the user
 * performs response then returns to the listening state
 *
 * @author James Colclough
 * @version 1.2
 */
public class Responding implements State
{
    @Override
    public String toString() {
        return "Responding";
    }

    @Override
    public void onButtonPressed(Echo echo)
    {
        //Can't press the button here, method performs no action        
    }

    @Override
    public void onEnterState(Echo echo)
    {

        echo.gui.setBackground(echo.gui.iconEchoAnswer);

        String text = SpeechRecognition.speechRecognition("res/output.wav");
        System.out.println(text);
        echo.setState(new OnOff());
    }
}
