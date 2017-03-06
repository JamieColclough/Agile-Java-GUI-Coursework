package not.amazon.echo.gui;

/**
 * Created by AliciaDaurignac on 28/02/2017.
 */
public class MockEchoGUI implements IEchoGUI {

    public EchoLights currentLights;

    @Override
    public void setLights(EchoLights colour) {
        currentLights = colour;

    }
}
