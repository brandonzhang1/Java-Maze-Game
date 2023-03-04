import javax.swing.*;
//import screens.ScreenManager;
import screens.Frame;

/**
 * Entry point for game
 */
public class Main {
	/**
     * Initializes an object of the {@link Frame} Class, and {@link ScreenManager} class.
     * @param args for command-line argument
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Frame frame = new Frame();
                //ScreenManager manager = new ScreenManager(frame);
                frame.setVisible(true);
            }
        });
    }
}