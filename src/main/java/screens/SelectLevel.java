package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The select level menu is a subclass of {@link Screen}. It is also a sub-sub class of the Java library class JPanel
 * The screen can be accessed through the main menu and will be displayed on the {@link Frame} window.
 * The select level menu displays available level for player to choose from.
 */
public class SelectLevel extends Screen{

    //Button attributes
    JButton levelOneButton, levelTwoButton, backButton;

    /**
     * Constructs the select level screen.
     * It also calls the createButton() method from {@link Screen} to initialize all the screen's button.
     * The background image is also set within the constructor.
     * @param manager  The manager contains all possible screens and
     *                 is used to help determine which screen should be displayed.
     */

    public SelectLevel(Frame manager) {
        super(manager);  //The screen is added to the frame in the super-class

        //CreateButtons method is defined in the Screen super-class.
        //This method adds the JButton object to the JPanel (aka the Screen)
        levelOneButton = createButtons("LevelOne", "/buttons/level_1.png");
        levelOneButton = setButtonBounds(levelOneButton, Frame.FRAME_HEIGHT/3 - 65,Frame.FRAME_HEIGHT/2 - 150,
                100,100);

        levelTwoButton = createButtons("LevelTwo", "/buttons/level_2.png");
        levelTwoButton = setButtonBounds(levelTwoButton, Frame.FRAME_HEIGHT*2/3 - 65,Frame.FRAME_HEIGHT/2 - 150,
                100,100);
        //createButtons("LevelThree",Frame.FRAME_HEIGHT*5/7 - 30,Frame.FRAME_HEIGHT/2,100,100, "/buttons/levels.png");
        backButton = createButtons("Back", "/buttons/back.png");
        backButton = setButtonBounds(backButton, Frame.FRAME_HEIGHT*5/7,Frame.FRAME_HEIGHT*6/7,
                80,40);


        //setBackGroundImg is defined in the Screen super-class.
        // update on this
        setBackGroundImg("screens/menu.png");
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

        //If user clicks button, they will be taken to the menu.
        String action = e.getActionCommand();
        switch (action) {
            case "Back" -> cl.show(screenContainer, "MENU");
            // curently all levels are available, need to update on a lock
            case "LevelOne" -> {
                manager.getGameLoop().initializeLevel(1);
                cl.show(screenContainer, "GAME");
            }
            case "LevelTwo" -> {
                manager.getGameLoop().initializeLevel(2);
                cl.show(screenContainer, "GAME");
            }
        }
        /*
        else if(action.equals("LevelThree")){
            manager.getLevel().initializeLevel(3);
            cl.show(screenContainer, "GAME");
        }
         */

    }

    /**
     * Method used for testing button functionality
     * @return the button object
     */
    public JButton getLevelOneButton() {
        return levelOneButton;
    }

    /**
     * Method used for testing button functionality
     * @return the button object
     */
    public JButton getLevelTwoButton() {
        return levelTwoButton;
    }

    /**
     * Method used for testing button functionality
     * @return the button object
     */
    public JButton getBackButton() {
        return backButton;
    }
}