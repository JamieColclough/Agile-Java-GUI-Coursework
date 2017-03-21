package not.amazon.echo.gui;


import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Generates a JFrame that contains an image of the echo in the correct state (either off,
 * listening or answering mode) and an on/off button with a corresponding image.
 *
 * @author Matthew Hill
 * @version 21/02/2017
 */

public class EchoGUI extends JFrame implements IEchoGUI {
    private final ImageIcon iconEcho = new ImageIcon("res/echo.jpg");
    private final ImageIcon iconEchoAnswer = new ImageIcon("res/echoAnswer.jpg");
    private final ImageIcon iconEchoOff = new ImageIcon("res/echoOff.jpg");
    private final OnButton btn1 = new OnButton();
	private final VolButton turnUp1 = new VolButton();
	private final VolButton turnUp2 = new VolButton();
	private final VolButton turnDown = new VolButton();
    private final JLabel label;

    /**
     * Constructor that sets the image of the JFrame to that passed to the constructor and also
     * adds an on/off button to the JFrame.
     *
     * replaced internal eventListener with builtin ActionListener.
     * @param button ActionListener for when the button is pressed
     */
    public EchoGUI(ActionListener button) {
        setTitle("Echo");
        label = new JLabel(iconEchoOff);
        setContentPane(label);
        setLayout(null);

        btn1.setBounds(110, 150, 20, 20);
		turnDown.setBounds(60, 200, 40, 10);
		turnUp1.setBounds(155, 185, 10, 40);
		turnUp2.setBounds(140, 200, 40, 10);
        add(btn1);
		add(turnDown);
		add(turnUp1);
		add(turnUp2);
		
        //When the button is pressed, fire our Button event
        btn1.addActionListener(button);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //pack(); this just sets the size, which we set a bit later anyway
        setSize(240, 648);
		setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void setLights(EchoLights colour) {
        switch (colour) {
            case OFF:
                label.setIcon(iconEchoOff);
                break;
            case LISTENING:
                label.setIcon(iconEcho);
                break;
            case RESPONDING:
                label.setIcon(iconEchoAnswer);
                break;
        }
    }

    /**
     * Generates an on/off button for the JFrame and sets the image to that of an on/off image file.
     */
    private class OnButton extends JButton {
        final ImageIcon iconOnOffButton = new ImageIcon("res/onoff.jpg");

        OnButton() {
            setIcon(iconOnOffButton);
            setBorder(null);
        }
    }
	
	private class VolButton extends JButton{
		final ImageIcon iconVolButton = new ImageIcon("res/MinusSign");
		
		VolButton() {
			setIcon(iconVolButton);
			setBorder(null);
		}
	}

}