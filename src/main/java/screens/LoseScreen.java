package screens;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The lose screen is a subclass of {@link Screen}. It is also a sub-sub class of the Java library class JPanel
 * The screen will appear if the player loses the game and will be displayed on the {@link Frame} window.
 */
public class LoseScreen extends Screen{
    //Button attributes
    private JButton backButton;
    private JButton replayButton;

    /**
     * Constructs the lose screen.
     * It also calls the createButton() method from {@link Screen} to initialize all the screen's button.
     * The background image is also set within the constructor.
     * @param manager  The manager contains all possible screens and
     *                 is used to help determine which screen should be displayed.
     */
    public LoseScreen(Frame manager) {
        super(manager); //The screen is added to the frame in the super-class
        //CreateButtons method is defined in the Screen super-class.
        //This method adds the JButton object to the JPanel (aka the Screen)

        int buttonW = 180;
        int buttonH = 40;
        replayButton = createButtons("Replay", "/buttons/restart.png");
        replayButton = setButtonBounds( replayButton, Frame.FRAME_WIDTH/2 - buttonW/2,Frame.FRAME_HEIGHT/2 + buttonH,buttonW, buttonH);

        backButton = createButtons("Back", "/buttons/home.png");
        backButton = setButtonBounds(backButton,Frame.FRAME_WIDTH/2 - buttonW/2,Frame.FRAME_HEIGHT/2 + buttonH*5/2,buttonW, buttonH);

        //setBackGroundImg is defined in the Screen super-class.
        setBackGroundImg("screens/lost.png");
    }

    /**
     * This method allows painting the score on the lose screen.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // paint score
        g.drawString("Score: " + level.getScore(), Frame.FRAME_WIDTH/2 -130, Frame.FRAME_HEIGHT/2);
        // paint playtime score
        g.drawString("Playtime: " + String.format("%03d" ,level.getPlaytime()) + " seconds", Frame.FRAME_WIDTH/2 , Frame.FRAME_HEIGHT/2);
    }

    /**
     * This method is called when the user presses one of the buttons on screen.
     * Pressing the buttons will cause the screen to change.
     * @param e The button-pressing event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //The layout of the screens is a CardLayout from the Java library
        CardLayout cl = (CardLayout) screenContainer.getLayout();

        //If user clicks button, they will be taken to the appropriate screen.
        String action = e.getActionCommand();
        switch (action) {
            case "Replay" -> {
                cl.show(screenContainer, "GAME");
                gameLoop.initializeLevel(level.getLevelID());
            }
            case "Back" -> cl.show(screenContainer, "MENU");
        }
    }

    /**
     * Method used for testing button functionality
     * @return the button object
     */
    public JButton getReplayButton() {
        return this.replayButton;
    }

    /**
     * Method used for testing button functionality
     * @return the button object
     */
    public JButton getBackButton(){
        return this.backButton;
    }
}
