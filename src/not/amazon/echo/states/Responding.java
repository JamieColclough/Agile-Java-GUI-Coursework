package not.amazon.echo.states;

import not.amazon.echo.IEcho;
import not.amazon.echo.gui.EchoLights;
import not.amazon.echo.network.NoSpeechException;
import not.amazon.echo.network.SpeechToText;

/**
 * State representing the phase in which the product responds to the user
 * performs response then returns to the listening state
 *
 * @author James Colclough
 * @version 1.2
 */
public class Responding implements State
{
    private byte[] data;

    public Responding(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Responding";
    }

    @Override
    public void onButtonPressed(IEcho echo)
    {
        //Can't press the button here, method performs no action        
    }

    @Override
    public void onEnterState(IEcho echo) {

        echo.getGUI().setLights(EchoLights.RESPONDING);

        String text = null;
        try {
            text = SpeechToText.recognizeSpeech(data);
        } catch (NoSpeechException e) {
            e.printStackTrace();
        }
        System.out.println(text);
        echo.setState(new OnOff());
    }
}
