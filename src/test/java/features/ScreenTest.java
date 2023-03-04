package features;


import misc.GameLoop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screens.*;
import screens.Frame;

import java.awt.*;


import static org.junit.jupiter.api.Assertions.*;

public class ScreenTest {
    private Frame frame;
    //private ScreenManager screenManager;
    private CreditsMenu creditsMenu;
    private HelpMenu helpMenu;
    private LoseScreen loseScreen;
    private MainMenu mainMenu;
    private Level level;
    private PauseMenu pauseMenu;
    private WinScreen winScreen;
    private SelectLevel selectLevel;
    private Container screenContainer;
    private GameLoop gameLoop;
    private CardLayout cl;

    /**
     * Set up test, initialize screens
     */
    @BeforeEach
    void setUp(){
        frame = new Frame();
        //screenManager = new ScreenManager(frame);
        creditsMenu = frame.getCreditsMenu();
        helpMenu = frame.getHelpMenu();
        loseScreen = frame.getLoseScreen();
        mainMenu = frame.getMainMenu();
        level = frame.getLevel();
        pauseMenu = frame.getPauseMenu();
        winScreen = frame.getWinScreen();
        selectLevel = frame.getSelectLevel();
        screenContainer = frame.getScreenContainer();
        gameLoop = frame.getGameLoop();
        cl = (CardLayout) screenContainer.getLayout();
    }

    /**
     * test that the frame is not null
     */
    @Test
    void testFrame() {
        assertNotNull(frame);
    }

    /**
     * test buttons from the credit screen
     */
    @Test
    void testCreditButtons(){
        assertDoesNotThrow(() -> creditsMenu.getBackButton().doClick());
        assertEquals(mainMenu, frame.getScreenContainer().getComponent(0));
    }

    /**
     * test buttons from help screen
     */
    @Test
    void testHelpButtons(){
        assertDoesNotThrow(() -> helpMenu.getBackButton().doClick());
        assertEquals(mainMenu, frame.getScreenContainer().getComponent(0));
    }


    /**
     * test buttons from lose screen
     */
    @Test
    void testLoseButtons(){
        mainMenu.getPlayButton().doClick();
        //after the replay button is clicked, go back to game play
        assertDoesNotThrow(() -> loseScreen.getReplayButton().doClick());
        assertTrue(frame.getScreenContainer().getComponent(2).isVisible());
        //after the back button is clicked, go back to main menu
        assertDoesNotThrow(() -> loseScreen.getBackButton().doClick());
        assertEquals(mainMenu, frame.getScreenContainer().getComponent(0));
    }

    /**
     * test buttons from main menu screen
     */
    @Test
    void testMainMenuButtons(){
        //check if credit button takes to credit menu
        assertDoesNotThrow(() -> mainMenu.getCreditsButton().doClick());
        assertTrue(frame.getScreenContainer().getComponent(3).isVisible());
        //check if help button takes to instruction page
        assertDoesNotThrow(() -> mainMenu.getHelpButton().doClick());
        assertTrue(frame.getScreenContainer().getComponent(1).isVisible());
        //check if select level button takes to select level menu
        assertDoesNotThrow(() -> mainMenu.getLevelButton().doClick());
        assertTrue(frame.getScreenContainer().getComponent(7).isVisible());
        //check if play button takes to game play
        assertDoesNotThrow(() -> mainMenu.getPlayButton().doClick());
        assertTrue(frame.getScreenContainer().getComponent(2).isVisible());

    }

    /**
     * test buttons from level screen
     */
    @Test
    void testLevelButtons(){
        mainMenu.getPlayButton().doClick();
        //after the pause button is clicked, go to pause page
        assertDoesNotThrow(() -> level.getPauseButton().doClick());
        assertTrue(frame.getScreenContainer().getComponent(4).isVisible());
        //after the back button is clicked, go back to main menu
        assertDoesNotThrow(() -> level.getBackButton().doClick());
        assertTrue(frame.getScreenContainer().getComponent(0).isVisible());

    }

    /**
     * test buttons from pause screen
     */
    @Test
    void testPauseButtons(){
        mainMenu.getPlayButton().doClick();
        //after the exit button is clicked, go back to main menu
        assertDoesNotThrow(() -> pauseMenu.getExitButton().doClick());
        assertTrue(frame.getScreenContainer().getComponent(0).isVisible());
        //after the restart button is clicked, go back to game play
        assertDoesNotThrow(() -> pauseMenu.getRestartButton().doClick());
        assertTrue(frame.getScreenContainer().getComponent(2).isVisible());
        //after the resume button is clicked, go back to game play
        assertDoesNotThrow(() -> pauseMenu.getResumeButton().doClick());
        assertTrue(frame.getScreenContainer().getComponent(2).isVisible());

    }

    /**
     * test buttons from select level screen
     */
    @Test
    void testSelectLevelButtons(){
        //after selected level, go to game play
        assertDoesNotThrow(() -> selectLevel.getLevelOneButton().doClick());
        assertDoesNotThrow(() -> selectLevel.getLevelTwoButton().doClick());
        assertTrue(frame.getScreenContainer().getComponent(2).isVisible());
        //after the back button is clicked, go back to main menu
        assertDoesNotThrow(() -> selectLevel.getBackButton().doClick());
        assertTrue(frame.getScreenContainer().getComponent(0).isVisible());

    }

    /**
     * test buttons from win screen
     */
    @Test
    void testWinButtons(){
        mainMenu.getPlayButton().doClick();
        winScreen.createWinButtons();
        //after the next button is checked, go to next level
        assertDoesNotThrow(() -> winScreen.getNextButton().doClick());
        assertTrue(frame.getScreenContainer().getComponent(2).isVisible());
        //after the replay button is clicked, go to game play
        assertDoesNotThrow(() -> winScreen.getReplayButton().doClick());
        assertTrue(frame.getScreenContainer().getComponent(2).isVisible());
        //after the back button is clicked, go back to main menu
        assertDoesNotThrow(() -> winScreen.getBackButton().doClick());
        assertTrue(frame.getScreenContainer().getComponent(0).isVisible());
        gameLoop.initializeLevel(2);
        winScreen.createWinButtons();
        assertDoesNotThrow(() -> winScreen.getBackButton().doClick());
    }

    /**
     * test paint functionality across all screens
     */
    @Test
    void paintTest() throws InterruptedException {
        frame.setVisible(true);
        assertDoesNotThrow(() -> mainMenu.repaint());
        mainMenu.getPlayButton().doClick();
        cl.show(screenContainer, "GAME");
        level.repaint();
        assertDoesNotThrow(() ->level.repaint());
        Thread.sleep(200);
        cl.show(screenContainer,"LOSE");
        loseScreen.repaint();
        Thread.sleep(200);
        assertDoesNotThrow(() ->loseScreen.repaint());
        cl.show(screenContainer,"WIN");
        Thread.sleep(200);
        assertDoesNotThrow(() ->winScreen.repaint());
        assertDoesNotThrow(() ->selectLevel.repaint());
        assertDoesNotThrow(() ->helpMenu.repaint());
        assertDoesNotThrow(() ->creditsMenu.repaint());
        assertDoesNotThrow(() ->pauseMenu.repaint());
        frame.setVisible(false);
    }
}
