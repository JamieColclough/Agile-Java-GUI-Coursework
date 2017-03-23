package not.amazon.echo.states;

import not.amazon.echo.IEcho;
import not.amazon.echo.gui.EchoLights;
import not.amazon.echo.sound.RecordSound;

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
        RecordSound.stopListening();
    }

    @Override
    public void onEnterState(IEcho echo)
    {

        echo.getGUI().setLights(EchoLights.LISTENING);

        new Thread(() -> {
            try {
                byte[] data = RecordSound.recordSoundData();
                if (data != null) {
                    echo.setState(new Responding(data));
                } else {
                    echo.setState(new OnOff());
                }
            } catch (Exception e) {
                echo.setState(new OnOff());
            }
        }).start();

    }
}
