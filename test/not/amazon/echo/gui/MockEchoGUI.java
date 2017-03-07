package not.amazon.echo.gui;

/**
 * MockEchoGUI object for tests.
 * Created by AliciaDaurignac on 28/02/2017.
 */
public class MockEchoGUI implements IEchoGUI {

    public EchoLights currentLights;

    /**
     * Sets the lights of the Echo.
     *
     * @param colour colour of the lights.
     */
    @Override
    public void setLights(EchoLights colour) {
        currentLights = colour;

    }
}
