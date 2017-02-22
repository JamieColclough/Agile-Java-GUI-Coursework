package not.amazon.echo.gui;

import not.amazon.echo.Button;

import javax.swing.*;

/**
 * Generates a JFrame that contains an image of the echo in the correct state (either off,
 * listening or answering mode) and an on/off button with a corresponding image.
 *
 * @author Matthew Hill
 * @version 21/02/2017
 */

public class EchoInterface extends JFrame {
	final OnButton btn1 = new OnButton();

    final ImageIcon iconEcho = new ImageIcon("res/echo.jpg");
    final ImageIcon iconEchoAnswer = new ImageIcon("iconEchoAnswer.jpg");
    final ImageIcon iconEchoOff = new ImageIcon("res/echoOff.jpg");

    final ImageIcon iconOnOffButton = new ImageIcon("res/onoff.jpg");

    private JLabel label;

	/**
	 * Constructor that sets the image of the JFrame to that passed to the constructor and also
	 * adds an on/off button to the JFrame.
	 *
	 * @param img the name of the image file to be used in the JFrame
	 */
    public EchoInterface(Button button) {
        setTitle("Echo");
        label = new JLabel(iconEchoOff);
        setContentPane(label);
        setLayout(null);

		btn1.setBounds(290, 560, 20, 20);
		add(btn1);

        //When the button is pressed, fire our Button event
        btn1.addActionListener(e -> button.pressButton());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //pack(); this just sets the size, which we set a bit later anyway
        setLocationRelativeTo(null);
        setSize(600, 800);
        setResizable(false);
        setVisible(true);
    }

    public void setBackground(ImageIcon icon) {
        label.setIcon(icon);
    }

	/**
	 * Generates an on/off button for the JFrame and sets the image to that of an on/off image file.
	 */
	private class OnButton extends JButton {
		OnButton() {
            setIcon(iconOnOffButton);
            setBorder(null);
		}
	}

}