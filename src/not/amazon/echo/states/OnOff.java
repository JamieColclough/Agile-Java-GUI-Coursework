package not.amazon.echo.states;

import not.amazon.echo.Echo;
import not.amazon.echo.sound.PlaySound;

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

    @Override
    public void onButtonPressed(Echo echo)
    {
		//hello sound played when device turned on and advanced to listening state with
		//corresponding image
		PlaySound.playSoundAsync("res/hello.wav");
        echo.setState(new Listening());
    }

    @Override
    public void onEnterState(Echo echo)
    {
		//Off image of echo will be shown when device is turned off followed by a goodbye sound
	    echo.gui.setBackground(echo.gui.iconEchoOff);
		PlaySound.playSoundAsync("res/goodbye.wav");
    }
}
