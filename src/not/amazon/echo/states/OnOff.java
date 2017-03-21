package not.amazon.echo.states;

import not.amazon.echo.ErrorHandler;
import not.amazon.echo.IEcho;
import not.amazon.echo.gui.EchoLights;
import not.amazon.echo.sound.PlaySound;
import not.amazon.echo.sound.SoundException;

/**
 * State representing the product in its On/Off phase
 * waiting for the power button to be pressed
 *
 * @author James Colclough
 * @version 1.2
 */
public class OnOff implements State
{
    @Override
    public String toString() {
        return "On/Off";
    }

    public void onButtonPressed(IEcho echo) {
        //hello sound played when device turned on and advanced to listening state with
        //corresponding image
        try {
            PlaySound.createClip("res/hello.wav");
        } catch (SoundException e) {
            ErrorHandler.log(e);
        }
        echo.setState(new Listening());
    }

    @Override
    public void onEnterState(IEcho echo)
    {
        //Code for lights changing etc here
        echo.getGUI().setLights(EchoLights.OFF);

		//Off image of echo will be shown when device is turned off followed by a goodbye sound
        try {
            PlaySound.createClip("res/goodbye.wav");
        } catch (SoundException e) {
            ErrorHandler.log(e);
        }
    }
}
