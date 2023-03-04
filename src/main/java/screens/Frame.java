package screens;

import misc.GameLoop;
import javax.swing.*;
import java.awt.*;

/**
 * One gameplay session will have one frame throughout the game. The frame will be a container for the various
 * different types of {@link Screen} objects such as the {@link MainMenu}, the gameplay screen {@link Level} etc.
 * Manages which {@link Screen} subclasses are displayed at any given point in the game.
 * Also facilitates the passing of information between screens.
 * ScreenManager distributes a pointer to the {@link Frame} object to all the screens
 * through a getter method so that they can be displayed on the window frame.
 * The class also distributes a pointer to the {@link Level} object through a getter method
 * so the other screens can react accordingly to the state of the gameplay.
 * Lastly, the class also distributes a pointer to the {@link Container} screenContainer object through a getter method.
 * This allows different screens to be displayed while within another Screen class.
 */
public class Frame extends JFrame {
    //The window width in pixels
    public static final int FRAME_WIDTH = 600;
    //The window height in pixels
    public static final int FRAME_HEIGHT = 630;

    //Declare all screens----------------------------------------------
    private final MainMenu mainMenu;
    private final HelpMenu helpMenu;
    private final Level level;
    private final LoseScreen loseScreen;
    private final WinScreen winScreen;
    private final PauseMenu pauseMenu;
    private final CreditsMenu creditsMenu;
    private final SelectLevel selectLevel;

    // screenContainer is a container for all the Screens.  It is set to have a CardLayout
    private final JPanel screenContainer;
    private final CardLayout cardLayout;

    private final GameLoop gameLoop;
    /**
     * Constructor of the Frame class initializes a Frame and sets the settings.
     * The size, location, closing operation, resizable settings and title are defined here.
     */
    public Frame() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT); //set the size
        this.setLocationRelativeTo(null); // frameLocation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // closeFrame when click exit
        this.setResizable(false);           // frameResize
        this.setTitle("Project");         // frameTitle

        //Instantiate a new CardLayout. Each of the Screens can be considered
        // Cards which will be shown on the window frame one at a time
        cardLayout = new CardLayout(0, 0); //initialize cardLayout
        screenContainer = new JPanel(cardLayout);
        screenContainer.setLayout(cardLayout);

        //Instantiate the Game Loop
        gameLoop = new GameLoop();

        //Instantiate all the screens:------------------------------------
        mainMenu = new MainMenu(this);
        level = new Level(this);
        helpMenu = new HelpMenu(this);
        creditsMenu = new CreditsMenu(this);
        pauseMenu = new PauseMenu(this);
        loseScreen = new LoseScreen(this);
        winScreen = new WinScreen(this);
        selectLevel = new SelectLevel(this);

        
        //add the screens to the container---------------------------------------------------------
        screenContainer.add(mainMenu, "MENU");
        screenContainer.add(helpMenu, "HELP");
        screenContainer.add(level, "GAME");
        screenContainer.add(creditsMenu, "CREDITS");
        screenContainer.add(pauseMenu, "PAUSE");
        screenContainer.add(loseScreen, "LOSE");
        screenContainer.add(winScreen, "WIN");
        screenContainer.add(selectLevel, "LEVELS");
        //cardLayout.show(screenContainer, "MENU");

        /*
        //add the screens to the CardLayout---------------------------------------------------------
        cardLayout.addLayoutComponent(mainMenu, "MENU");
        cardLayout.addLayoutComponent(helpMenu, "HELP");
        cardLayout.addLayoutComponent(level, "GAME");
        cardLayout.addLayoutComponent(creditsMenu, "CREDITS");
        cardLayout.addLayoutComponent(pauseMenu, "PAUSE");
        cardLayout.addLayoutComponent(loseScreen, "LOSE");
        cardLayout.addLayoutComponent(winScreen, "WIN");
        cardLayout.addLayoutComponent(selectLevel, "LEVELS");
        */

        //Allows screens in screen container to be displayed in window when cardlayout.show is invoked
        this.setContentPane(screenContainer);

        //add the screenManager to the gameLoop object
        gameLoop.addScreenManager(this);
    }

    /**
     * Getter method for {@link Frame} object
     * @return frame a pointer to the window on which the panels will be displayed
     */
    public Frame getFrame(){
        return this;
    }

    /**
     * Getter method for {@link Container } object
     * @return level a pointer to the screen container
     */
    public Container getScreenContainer() {
        return this.screenContainer;
    }

    //Testing

    /**
     * Getter method for {@link Level } object
     * @return level a pointer to the level object from which the game is
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * Getter method for {@link CreditsMenu} object
     * @return level a pointer to the credit menu object from which the game is
     */
    public CreditsMenu getCreditsMenu(){
        return this.creditsMenu;
    }
    /**
     * Getter method for {@link HelpMenu } object
     * @return level a pointer to the help menu object from which the game is
     */
    public HelpMenu getHelpMenu(){
        return this.helpMenu;
    }
    /**
     * Getter method for {@link WinScreen } object
     * @return level a pointer to the win screen object from which the game is
     */
    public WinScreen getWinScreen() {
        return this.winScreen;
    }
    /**
     * Getter method for {@link MainMenu } object
     * @return level a pointer to the main menu object from which the game is
     */
    public MainMenu getMainMenu() {
        return mainMenu;
    }
    /**
     * Getter method for {@link LoseScreen } object
     * @return level a pointer to the lose screen object from which the game is
     */
    public LoseScreen getLoseScreen() {
        return loseScreen;
    }
    /**
     * Getter method for {@link PauseMenu } object
     * @return PauseMenu a pointer to the pause menu object from which the game is
     */
    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }
    /**
     * Getter method for {@link SelectLevel } object
     * @return level a pointer to the select level object from which the game is
     */
    public SelectLevel getSelectLevel() {
        return selectLevel;
    }

    /**
     * Getter method fo r{@link GameLoop} object
     * @return gameLoop a pointer to the gameLoop object
     */
    public GameLoop getGameLoop() {
        return this.gameLoop;
    }
}