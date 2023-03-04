package screens;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


/**
 * The pause menu is a subclass of {@link Screen}. It is also a sub-sub class of the Java library class JPanel
 * The screen will appear when pausing the game.
 * It will be displayed on the {@link Frame} window.
 */
public class PauseMenu extends Screen{

    //Button attributes
    JButton restartButton, exitButton, resumeButton;
    /**
     * Constructs the pause menu.
     * It also calls the createButton() method from {@link Screen} to initialize all the screen's button.
     * The background image is also set within the constructor.
     * @param manager  The manager contains all possible screens and
     *                 is used to help determine which screen should be displayed.
     */
    public PauseMenu(Frame manager) {
        super(manager); //pass the pointers to the superclass
        //Create buttons
        int buttonW = 150;
        //int buttonH = 45;

        restartButton = createButtons("Restart Level",  "/buttons/restart.png");
        restartButton = setButtonBounds(restartButton, Frame.FRAME_WIDTH/3 - buttonW/2,Frame.FRAME_HEIGHT/2,150,43);

        exitButton = createButtons("Exit",  "/buttons/house.png");
        exitButton = setButtonBounds(exitButton, Frame.FRAME_WIDTH -80,40,31,30);

        resumeButton = createButtons("Resume",  "/buttons/resume.png");
        resumeButton = setButtonBounds(resumeButton, Frame.FRAME_WIDTH*2/3 - buttonW/2, Frame.FRAME_HEIGHT/2,139,44);

        //set background
        setBackGroundImg("screens/paused.png");
    }

    /**
     * This method is called when the user presses one of the buttons on screen.
     * Pressing the buttons will cause the screen to change.
     * @param e The button-pressing event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cl = (CardLayout) screenContainer.getLayout();
        String action = e.getActionCommand();
        switch (action) {
            case "Restart Level" -> {
                cl.show(screenContainer, "GAME");
                gameLoop.initializeLevel(level.getLevelID());
            }
            case "Resume" -> {
                cl.show(screenContainer, "GAME");
                gameLoop.start();
            }
            case "Exit" -> cl.show(screenContainer, "MENU");
        }
    }

    /**
     * Method used for testing button functionality
     * @return the button object
     */
    public JButton getRestartButton() {
        return restartButton;
    }

    /**
     * Method used for testing button functionality
     * @return the button object
     */
    public JButton getExitButton() {
        return exitButton;
    }

    /**
     * Method used for testing button functionality
     * @return the button object
     */
    public JButton getResumeButton() {
        return resumeButton;
    }
}
