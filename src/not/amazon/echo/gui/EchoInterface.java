package not.amazon.echo.gui;

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

	/**
	 * Constructor that sets the image of the JFrame to that passed to the constructor and also
	 * adds an on/off button to the JFrame.
	 *
	 * @param img the name of the image file to be used in the JFrame
	 */
	public EchoInterface(String img) {
		setTitle("Echo");
		setContentPane(new JLabel(new ImageIcon(img)));
		setLayout(null);

		btn1.setBounds(290, 560, 20, 20);
		add(btn1);
	}

	/**
	 * Runs checks to see if the input img is a valid image file.
	 *
	 * @param img The image file to be checked for any errors
	 */
	public static boolean validImage(String img) {
		String endImg = img.substring(img.length() - 4); //Obtains last 4 characters of the input image file string
		String[] validImgs = new String[3]; // Creates an array containing the only valid image files
		validImgs[0] = "echoOff.jpg";
		validImgs[1] = "echoListen.jpg";
		validImgs[2] = "echoAnswer.jpg";
		if (img.length() > 4) { //Check for acceptable length of image file
			if (endImg.equals(".jpg")) { //Check that image file is a .jpg file
				if (img.equals(validImgs[0]) || img.equals(validImgs[1]) || img.equals(validImgs[2])) {
					//Checks the file is one of the accepted files
					return true;
				} else { //File is of correct length and is a .jpg but is not an accepted file
					System.out.println("ERROR: File not found.");
					return false;
				}
			} else { //File is of a suitable length but is not a .jpg file
				System.out.println("ERROR: Invalid file name provided, image must be a .jpg file");
				return false;
			}
		} else { //File is not of a suitable length
			System.out.println("ERROR: Invalid image file provided, image must be a .jpg file!");
			return false;
		}
	}

	/**
	 * Creates a new JFrame containing the image provided to the main method
	 *
	 * @param argv[0] contains the image file that will be used to set the main image in the
	 *                JFrame
	 */
	public static void main(String[] argv) {
		try {
			String img = argv[0];
			if (validImage(img) == true) {
				//Creats JFrame if image file is accepted
				JFrame frame = new EchoInterface(img);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setSize(600, 800);
				frame.setResizable(false);
				frame.setVisible(true);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("ERROR: No image file provided.");
		}
	}

	/**
	 * Generates an on/off button for the JFrame and sets the image to that of an on/off image file.
	 */
	private class OnButton extends JButton {
		OnButton() {
			setIcon(new ImageIcon("onoff.jpg"));
			setBorder(null);
		}
	}

}