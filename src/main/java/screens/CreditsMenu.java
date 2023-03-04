package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The credit menu is a subclass of {@link Screen}. It is also a sub-sub class of the Java library class JPanel
 * The screen can be accessed through the main menu and will be displayed on the {@link Frame} window.
 * The credits display the names of the group members contributing to this project.
 */
public class CreditsMenu extends Screen{
    //Button attributes
    private JButton backButton;

    /**
     * Constructs the credit menu screen.
     * It also calls the createButton() method from Screens to initialize all the screen's button.
     * The background image is also set within the constructor.
     * @param manager  The manager contains all possible screens and
     *                 is used to help determine which screen should be displayed.
     */
    public CreditsMenu(Frame manager) {
        super(manager);  //The screen is added to the frame in the super-class

        //CreateButtons method is defined in the Screen super-class.
        //This method adds the JButton object to the JPanel (aka the Screen)
        backButton = createButtons("Back", "/buttons/back.png");
        backButton = setButtonBounds(backButton,Frame.FRAME_HEIGHT*5/7,Frame.FRAME_HEIGHT*6/7,80,40);
        //setBackGroundImg is defined in the Screen super-class.
        setBackGroundImg("screens/credits.png");
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
        if(action.equals("Back")) {
            cl.show(screenContainer, "MENU");
        }
    }

    /**
     * Method used for testing button functionality
     * @return the button object
     */
    public JButton getBackButton(){
        return this.backButton;
    }
}
