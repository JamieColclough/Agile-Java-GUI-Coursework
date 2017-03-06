package not.amazon.echo.states;

import not.amazon.echo.IEcho;
import not.amazon.echo.gui.EchoLights;
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
    public void onButtonPressed(IEcho echo) {
        PlaySound.playSoundAsync("res/startup.wav");
        echo.setState(new Listening());
    }

    @Override
    public void onEnterState(IEcho echo)
    {
        //Code for lights changing etc here
        echo.getGUI().setLights(EchoLights.OFF);

    }
}
