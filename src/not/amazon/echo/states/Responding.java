package not.amazon.echo.states;

import not.amazon.echo.IEcho;
import not.amazon.echo.gui.EchoLights;
import not.amazon.echo.network.NoSpeechException;
import not.amazon.echo.network.SpeechToText;
import not.amazon.echo.network.TextToSpeech;
import not.amazon.echo.network.WolframAPI;
import not.amazon.echo.sound.PlaySound;

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
        try {
            String text = SpeechToText.recognizeSpeech(data);
            PlaySound.playSound(TextToSpeech.say(WolframAPI.answer(text)));
        } catch (NoSpeechException exception) {
            PlaySound.playSound(TextToSpeech.say("I'm sorry, I didn't hear what you said."));
        }
        echo.setState(new OnOff());
    }
}
