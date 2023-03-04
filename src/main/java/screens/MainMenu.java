package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The main menu is a subclass of {@link Screen}. It is also a sub-sub class of the Java library class JPanel
 * The screen will appear once starting the application.
 * It will be displayed on the {@link Frame} window.
 */
public class MainMenu extends Screen {
    //Button attributes
    private JButton playButton;
    private JButton helpButton;
    private JButton creditsButton;
    private JButton levelButton;


    /**
     * Constructs the main menu.
     * It also calls the createButton() method from {@link Screen} to initialize all the screen's button.
     * The background image is also set within the constructor.
     * @param manager  The manager contains all possible screens and
     *                 is used to help determine which screen should be displayed.
     */
    public MainMenu(Frame manager) {
        super(manager); //pass the pointers to the superclass

        //Create buttons
        int playW = 220;
        int playH = 80;
        playButton = createButtons("Play", "/buttons/play.png");
        playButton = setButtonBounds(playButton, Frame.FRAME_WIDTH/2 - playW/2,Frame.FRAME_HEIGHT/2-150,playW,playH);

        int buttonW = 220;
        int buttonH = 40;
        helpButton = createButtons("Help", "/buttons/instructions.png");
        helpButton = setButtonBounds(helpButton, Frame.FRAME_WIDTH/2 - buttonW/2,Frame.FRAME_HEIGHT*2/3+30,buttonW,buttonH);

        creditsButton = createButtons("Credits", "/buttons/about.png");
        creditsButton = setButtonBounds(creditsButton, Frame.FRAME_WIDTH/2- buttonW/2, Frame.FRAME_HEIGHT*2/3 + buttonH*4/3+30,buttonW,buttonH);

        levelButton = createButtons("Levels", "/buttons/levels.png");
        levelButton = setButtonBounds(levelButton, Frame.FRAME_WIDTH/2- buttonW/2, Frame.FRAME_HEIGHT*2/3 + buttonH*8/3+30,buttonW,buttonH);

        //set background
        setBackGroundImg("screens/menu.png");
    }

    /**
     * This method is called when the user presses one of the buttons on screen.
     * Pressing the buttons will cause the screen to change.
     * @param e The button-pressing event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // for each different button pressed at the start screen, the screen will change
        //there is a layout of the screens (card layout). the screen container contains all different kinds of screens
        CardLayout cl = (CardLayout) screenContainer.getLayout();

        //If user clicks button, they will be taken to the appropriate screen.
        String action = e.getActionCommand();
        switch (action) {
            case "Play" -> {
                manager.getGameLoop().initializeLevel(1);
                cl.show(screenContainer, "GAME");
            }
            case "Credits" -> cl.show(screenContainer, "CREDITS");
            case "Help" -> cl.show(screenContainer, "HELP");
            case "Levels" -> cl.show(screenContainer, "LEVELS");
        }
    }

    /**
     * Method used for testing button functionality
     * @return the button object
     */
    public JButton getPlayButton() {
        return playButton;
    }

    /**
     * Method used for testing button functionality
     * @return the button object
     */
    public JButton getHelpButton() {
        return helpButton;
    }

    /**
     * Method used for testing button functionality
     * @return the button object
     */
    public JButton getCreditsButton() {
        return creditsButton;
    }

    /**
     * Method used for testing button functionality
     * @return the button object
     */
    public JButton getLevelButton() {
        return levelButton;
    }
}
