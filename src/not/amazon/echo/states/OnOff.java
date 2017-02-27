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
        PlaySound.playSoundAsync("res/startup.wav");
        echo.setState(new Listening());
    }

    @Override
    public void onEnterState(Echo echo)
    {
        //Code for lights changing etc here
        echo.gui.setBackground(echo.gui.iconEchoOff);

    }
}
