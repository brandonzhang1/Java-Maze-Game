package screens;

import entities.*;
import misc.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;




/** Level Class is an object that contains
 * information about a particular level
 * The Level screen is a subclass of {@link Screen}.
 * It is also a sub-sub class of the Java library class JPanel
 * The screen will appear when playing the game.
 * It will be displayed on the {@link Frame} window.
 */
public class Level extends Screen implements ActionListener {
    //--------------------------------------------Constants:----------------------------------------------------
    //Map dimension constants
    public final static int SIDE_BORDER = 60; 
    public final static int TOP_BORDER = 94;
    public final static int CELL_LENGTH = 48;

    //--------------------------------------------Game State Attributes:----------------------------------------------------
    //The level number
    private int levelID;

    //Player's score from collected rewards
    private int score;

    //Elapsed time
    private int playtime;

    //Player's Character
    private PlayerCharacter playerCharacter;

    //basic Objectives
    private ArrayList<Objective> basicObjectives;

    //bonus Objectives
    private ArrayList<BonusObjective> bonusObjectives;

    //stationary enemies
    private ArrayList<Enemy>  stationaryEnemies;

    //moving enemies
    private ArrayList<MovingEnemy> movingEnemies;

    //Button attributes
    private JButton backButton,pauseButton;

    //--------------------------------------------------- Methods: ---------------------------------------------------

    /**
     * Constructor that is used to create a level
     * @param manager pointer to the container for the created strings
     */
    public Level(Frame manager){
        super(manager); //pass the pointers to the superclass

        //Create buttons
        pauseButton = createButtons("Pause",  "/buttons/pause.png");
        pauseButton = setButtonBounds(pauseButton, Frame.FRAME_WIDTH-120,40,26,32);

        backButton = createButtons("Back",  "/buttons/house.png");
        backButton = setButtonBounds(backButton,Frame.FRAME_WIDTH -80,40,31,30);

    }


    //Painting functionality: ---------------------------------------------------------------------------------------
    /**
     * draw all image elements to the display window
     * @param g graphics context
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // paint score
        g.drawString("Score: " + getScore(), 150, 60);

        // paint Level
        g.drawString("Level: " + getLevelID(), 60, 60);

        //Paint playtime
        g.drawString("Time: " + String.format("%03d" , getPlaytime()), 250, 60);

        // paint entities.PlayerCharacter Image
        int[] pos = convertGridToPixel(playerCharacter.getPosition());
        g.drawImage(playerCharacter.getEntityImg(), pos[0], pos[1], CELL_LENGTH, CELL_LENGTH, this);

        //paint entities
        paintEntities(g,basicObjectives);
        paintEntities(g,bonusObjectives);
        paintEntities(g,stationaryEnemies);
        paintEntities(g,movingEnemies);
  }

    /**
     * helper function for converting grid positions to pixel positions
     * @param pos the position to be converted into pixels
     */
    public int[] convertGridToPixel(Position pos) {
        return new int[]{SIDE_BORDER + CELL_LENGTH * (int)pos.getX(), TOP_BORDER + CELL_LENGTH * (int)pos.getY()};
    }

    /**
     * Helper function to paint ArrayLists of entities
     * @param g the position to be converted into pixels
     */
    public void paintEntities(Graphics g, ArrayList<? extends Entity> entities){
        for (Entity entity : entities) {
            int[] pos = convertGridToPixel(entity.getPosition());
            g.drawImage(entity.getEntityImg(), pos[0], pos[1], CELL_LENGTH, CELL_LENGTH, this);
        }
    }
    //End of painting functionality: ---------------------------------------------------------------------------------

    //Screen Button Listener -----------------------------------------------------------------------------------------
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
            case "Back" -> {
                cl.show(screenContainer, "MENU");
                gameLoop.pause();
            }
            case "Pause" -> {
                cl.show(screenContainer, "PAUSE");
                gameLoop.pause();
            }
        }
    }


    //Getters and Setters --------------------------------------------------------------------------------------------
    /**
     * get current elapsed time in game
     * @return int playtime
     */
    public int getPlaytime() {
        return playtime;
    }


    /**
     * @param score New value of score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return int score.
     */
    public int getScore() {
        return score;
    }

    /**
     * set the level number
     * @param levelID New value of levelID.
     */
    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    /**
     * get the level number
     * @return int levelID.
     */
    public int getLevelID() {
        return levelID;
    }
    /**
     * Sets basicObjective
     */
    public void setBasicObjectives(ArrayList<Objective> basicObjectives) {
        this.basicObjectives = basicObjectives;
    }

    /**
     * Setter for playtime
     * @param playtime the amount of time spent playing
     */
    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    /**
     * Set the player character
     * @param playerCharacter the character object
     */
    public void setPlayerCharacter(PlayerCharacter playerCharacter) {
        this.playerCharacter = playerCharacter;
    }

    /**
     * Set the bonus objective
     * @param bonusObjectives the bonus Objective
     */
    public void setBonusObjectives(ArrayList<BonusObjective> bonusObjectives) {
        this.bonusObjectives = bonusObjectives;
    }

    /**
     * Set the stationary enemies
     * @param stationaryEnemies a list of the stationary enemies
     */
    public void setStationaryEnemies(ArrayList<Enemy> stationaryEnemies) {
        this.stationaryEnemies = stationaryEnemies;
    }

    /**
     * Set the moving enemies
     * @param movingEnemies a list of the moving enemies
     */
    public void setMovingEnemies(ArrayList<MovingEnemy> movingEnemies) {
        this.movingEnemies = movingEnemies;
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
     * @return  pauseButton button object
     */
    public JButton getPauseButton() {
        return pauseButton;
    }
}