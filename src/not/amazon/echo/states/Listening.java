package not.amazon.echo.states;

import not.amazon.echo.IEcho;
import not.amazon.echo.gui.EchoLights;
import not.amazon.echo.sound.RecordSound;

import javax.sound.sampled.LineUnavailableException;

/**
 * State representing the listening phase of the product
 * waiting until sound is detected
 *
 * @author James Colclough
 * @version 1.2
 */
public class Listening implements State
{
    
    @Override
    public String toString() {
        return "Listening";
    }

    @Override
    public void onButtonPressed(IEcho echo)
    {
        //Code involving listening for speech

        //if(recordThread != null) recordThread.interrupt();

        //echo.setState(new OnOff());
    }

    @Override
    public void onEnterState(IEcho echo)
    {

        echo.getGUI().setLights(EchoLights.LISTENING);

        new Thread(() -> {
            byte[] data = new byte[0];
            try {
                data = RecordSound.recordSoundData();
            } catch (LineUnavailableException e) {
                echo.setState(new OnOff());
            }
            echo.setState(new Responding(data));

        }).start();

    }
}
