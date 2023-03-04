package screens;

import misc.GameLoop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The win screen is a subclass of {@link Screen}.
 * It is also a sub-sub class of the Java library class JPanel
 * The screen will appear when pausing the game.
 * It will be displayed on the {@link Frame} window.
 */
public class WinScreen extends Screen{

    //Button attributes
    JButton backButton, replayButton, nextButton;
    /**
     * Constructs the win screen.
     * It also calls the createButton() method from {@link Screen} to initialize all the screen's button.
     * The background image is also set within the constructor.
     * @param manager  The manager contains all possible screens and
     *                 is used to help determine which screen should be displayed.
     */
    public WinScreen(Frame manager) {
        super(manager); //pass the pointers to the superclass
        //this.level = level;
        //setbackground
        setBackGroundImg("screens/won.png");



    }

    /**
     * This method allows painting the score on the win screen.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // paint score
        g.drawString("Points: " + level.getScore(), Frame.FRAME_WIDTH/2 -130, Frame.FRAME_HEIGHT/2);
        // paint playtime score
        g.drawString("Playtime: " + String.format("%03d" ,level.getPlaytime()) + " seconds", Frame.FRAME_WIDTH/2 , Frame.FRAME_HEIGHT/2);
        //Total Score
        int totalScore = level.getScore() + (GameLoop.MAX_PLAYTIME - level.getPlaytime())/10;
        g.drawString("Total Score: "+  String.format("%03d" , totalScore), Frame.FRAME_WIDTH/2 -80, Frame.FRAME_HEIGHT/2 +50);

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
            case "Next Level" -> {
                gameLoop.initializeLevel(level.getLevelID() + 1);
                cl.show(screenContainer, "GAME");
            }
            case "Menu" -> cl.show(screenContainer, "MENU");
            case "Replay" -> {
                gameLoop.initializeLevel(level.getLevelID());
                cl.show(screenContainer, "GAME");
            }
        }
    }

    /**
     *Helper method to set create the buttons on the Win Screen depending on the
     * level the player has just completed and the maximum number of levels.
     */
    public void createWinButtons(){
        if(level.getLevelID()< GameLoop.MAX_LEVEL) {
            this.removeAll();
            int buttonW = 180;
            int buttonH = 40;

            backButton = createButtons("Menu",  "/buttons/home.png");
            nextButton = createButtons("Next Level",  "/buttons/nextLevel.png");
            replayButton = createButtons("Replay", "/buttons/restart.png");

            backButton = setButtonBounds(backButton, Frame.FRAME_WIDTH/2 - buttonW/2, Frame.FRAME_HEIGHT/2 + buttonH, buttonW, buttonH);
            nextButton = setButtonBounds(nextButton, Frame.FRAME_WIDTH/2 - buttonW/2, Frame.FRAME_HEIGHT/2 + buttonH*5/2, buttonW, buttonH);
            replayButton = setButtonBounds(replayButton,Frame.FRAME_WIDTH/2 - buttonW/2,Frame.FRAME_HEIGHT/2 + buttonH*8/2,buttonW, buttonH);
        }
        else{
            this.removeAll();

            backButton = createButtons("Menu",  "/buttons/home.png");
            backButton = setButtonBounds(backButton, Frame.FRAME_HEIGHT*5/7,Frame.FRAME_HEIGHT*6/7, 73, 23);
        }
    }

    /**
     * Method used for testing button functionality
     * @return the button object
     */
    public JButton getBackButton() {
        return backButton;
    }

    /**
     * Method used for testing button functionality
     * @return the button object
     */
    public JButton getReplayButton() {
        return replayButton;
    }

    /**
     * Method used for testing button functionality
     * @return the button object
     */
    public JButton getNextButton() {
        return nextButton;
    }

}
