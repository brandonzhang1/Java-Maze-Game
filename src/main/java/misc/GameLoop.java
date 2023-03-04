package misc;

import entities.*;
import screens.Frame;
import screens.Level;
//import screens.ScreenManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Point;

public class GameLoop {
    //--------------------------------------------Constants:----------------------------------------------------

    //Maximum playtime to display on the screen
    public final static int MAX_PLAYTIME = 999;

    //Number of levels currently implemented in the game
    public final static int MAX_LEVEL = 2;

    //--------------------------------------------Game State Attributes:----------------------------------------------------
    //The level number
    private int levelID;

    //The highest level beaten
    private int maxLevel;

    //Player's score from collected rewards
    private int score;

    //Elapsed time
    private int playtime;

    //Game State map
    private int[][] map;

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

    //finish cell position
    private Position finishPosition; //????????????????????

    //describes next player movement
    private Directions keyState;

    //timer for game loop
    private Timer timer;

    //pointer to the frame
    //private Frame frame;
    //pointer to the level screen
    private Level level;

    private Frame manager;

    private Container screenContainer;

    /**
     * Class constructor
     */
    public GameLoop(){
        //initialize components that are common to every level
        maxLevel = 2;
    }

    /**
     * Method used by screen manager to help initialize the class attributes and to add the keyListener
     * @param manager the Screen manager object
     */
    public void addScreenManager(Frame manager) {
        this.manager = manager;
        this.screenContainer = manager.getScreenContainer();
        //this.frame = manager.getFrame();
        this.level = manager.getLevel();

        manager.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 37) {
                    keyState = Directions.LEFT;
                }
                if (e.getKeyCode() == 38) {
                    keyState = Directions.UP;
                }
                if (e.getKeyCode() == 39) {
                    keyState = Directions.RIGHT;
                }
                if (e.getKeyCode() == 40) {
                    keyState = Directions.DOWN;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        manager.setFocusable(true);
    }

    /**
     * Creates entities and resets variable fields to start a new level
     * @param levelID level to load
     */
    public void initializeLevel(int levelID) {
        this.levelID = levelID;
        this.maxLevel = 0;
        this.score = 0;
        this.playtime = 0;
        this.keyState = Directions.STAY;
        this.map = LevelPlan.getLevelPlan(levelID);
        if (levelID == 1) {
            level.setBackGroundImg("screens/map.png");
        } else if (levelID == 2) {
            level.setBackGroundImg("screens/map2.png");
        }
        //initialize arraylists
        stationaryEnemies = new ArrayList<>();
        movingEnemies = new ArrayList<>();
        basicObjectives = new ArrayList<>();
        bonusObjectives = new ArrayList<>();

        //scan level plan for entities to initialize
        for (int j = 0; j < map.length; j++) {
            for (int i = 0; i < map[j].length; i++) {
                switch (map[j][i]) {
                    case 2 ->//stationary enemy
                            stationaryEnemies.add(new Enemy("stationaryEnemy.png", new Position(i,j)));
                    case 3 ->//moving enemy
                            movingEnemies.add(new MovingEnemy("movingEnemy.png", new Position(i,j)));
                    case 4 ->//basic objective
                            basicObjectives.add(new Objective("objective.png", new Position(i,j), 10));
                    case 6 ->//player character
                            playerCharacter = new PlayerCharacter("player.png", new Position(i,j));
                    case 7 -> //finish position
                            finishPosition = new Position(i, j);
                }
            }
        }
        //initialize bonus objective
        Random rn = new Random();
        int randomI =  rn.nextInt(8)+1;
        int randomJ =  rn.nextInt(8)+1;
        while (map[randomJ][randomI] != 0) {
            randomI =  rn.nextInt(8)+1;
            randomJ =  rn.nextInt(8)+1;
        }
        bonusObjectives.add(new BonusObjective("bonus.png", new Position (randomI, randomJ), 30, 40));


        this.start();
    }

    //Game Logic -----------------------------------------------------------------------------------------------------
    /**
     * defines and executes game logic at a regular tick rate
     */
    public void start() {
        this.timer = new Timer();
        TimerTask gameLoop = new TimerTask() {
            @Override
            public void run() {
                //player position before tick move
                Position playerPos = playerCharacter.getPosition();

                //test for win
                testForWin(playerPos);

                //increment timer
                incrementTimer();

                //enemy movement
                moveEnemy(playerPos);

                //player movement
                movePlayer(playerPos);

                //test for lose
                testForLose(playerPos);

                //reset key state for next player movement input
                keyState = Directions.STAY;

                //new player position after tick move
                playerPos = playerCharacter.getPosition();

                //various checks against player's new position
                testForContact(playerPos, stationaryEnemies);
                testForContact(playerPos, basicObjectives);
                testForContact(playerPos, bonusObjectives);

                //check if bonus is still there
                checkBonus();

                // paint new game state
                update();
            }
        };
        timer.schedule(gameLoop, 0, 750);
    }


    /**
     * helper method to update all of the {@link Level } entity objects and to paint them
     */
    private void update() {
        level.setPlaytime(playtime);
        level.setLevelID(levelID);
        level.setScore(score);

        level.setBasicObjectives(basicObjectives);
        level.setBonusObjectives(bonusObjectives);
        level.setMovingEnemies(movingEnemies);
        level.setStationaryEnemies(stationaryEnemies);
        level.setPlayerCharacter(playerCharacter);
        level.repaint();
    }

    //start()'s helpers:-------------------------------
    /**
     * pause timer when pause menu is opened
     */
    public void pause() {
        timer.cancel();
    }

    /**
     * helper method that increments timer
     */
    private void incrementTimer(){
        //increment timer
        if(playtime <= MAX_PLAYTIME) {
            playtime++;
        }
    }

    /**
     * helper method that tests whether the player has won
     * @param playerPos the player's position
     */
    private void testForWin(Position playerPos){
        //test for win
        if (playerPos.compare(finishPosition) && basicObjectives.size() == 0) {
            maxLevel++;
            pause();
            manager.getWinScreen().createWinButtons();
            ((CardLayout)screenContainer.getLayout()).show(screenContainer, "WIN");
        }
    }

    /**
     * helper method that tests whether the player has lost
     * @param playerPos the player's position
     */
    private void testForLose(Position playerPos){
        //tests for lose
        //by contact with a moving enemy
        for (MovingEnemy movingEnemy : movingEnemies) {
            Position currentPos = movingEnemy.getPosition();
            if (currentPos.compare(playerPos)) {
                ((CardLayout) screenContainer.getLayout()).show(screenContainer, "LOSE");
                pause();
            }
        }
        //by negative score
        if (getScore() < 0) {
            pause();
            ((CardLayout)screenContainer.getLayout()).show(screenContainer, "LOSE");
        }
    }

    /**
     * helper method that tests whether the player has made contact with an entity object
     * @param playerPos the player's position
     */
    private void testForContact(Position playerPos, ArrayList<? extends Entity> entities){
        //test whether player has contacted an entity
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            Position currentPos = entity.getPosition();
            if (currentPos.compare(playerPos)) {
                setScore(getScore() + entity.getScoreChange());
                entities.remove(i);
            }
        }
    }

    /**
     * helper method that checks if bonus is still there
     */
    private void checkBonus(){
        //check if bonus objective is still there
        for (int i = 0; i < bonusObjectives.size(); i++) {
            if (playtime >= bonusObjectives.get(i).getTimeLimit()) {
                bonusObjectives.remove(i);
            }
        }
    }

    /**
     * helper method to move the moving enemy
     */
    private void moveEnemy(Position playerPos){
        //enemy movement
        for (MovingEnemy movingEnemy : movingEnemies) {
            //determine which movement directions are valid, and which leads enemy closest to player
            Position enemyPos = movingEnemy.getPosition();

            // check the bestMove Direction of the movingEnemy
            Directions bestMove = checkBestMove(enemyPos, playerPos);

            //check for lose before executing enemy movements
            //calculate next player position
            Position nextPlayerPos = new Position(0, 0);
            nextPlayerPos.setPosition(playerPos);

            WallDetection playerWallDetection = new WallDetection(map,playerPos);

            if(playerWallDetection.validMove(keyState)) {
                nextPlayerPos.move(1,keyState);
            }


            //calculate next enemy position
            Position nextEnemyPos = new Position (0, 0);
            nextEnemyPos.setPosition(enemyPos);

            nextEnemyPos.move(1,bestMove);

            //lose condition
            if (nextPlayerPos.compare(nextEnemyPos)) {
                pause();
                ((CardLayout) screenContainer.getLayout()).show(screenContainer, "LOSE");
                return;
            }

            //execute enemy move
            movingEnemy.move(1,bestMove);
        }
    }

    /**
     * check the bestMove of movingEnemy
     */
    public Directions checkBestMove(Position enemyPos, Position playerPos) {
        int bestDistance = Math.abs(enemyPos.x - playerPos.x) + Math.abs(enemyPos.y - playerPos.y);
        int testDistance = 100;
        Directions bestMove = Directions.STAY;
        WallDetection enemyWallDetection = new WallDetection(map, enemyPos);
        for (Directions d: new Directions[] {Directions.LEFT, Directions.UP, Directions.RIGHT, Directions.DOWN}) {
            switch(d) {
                case LEFT ->
                    testDistance = Math.abs(enemyPos.x - 1 - playerPos.x) + Math.abs(enemyPos.y - playerPos.y);
                case UP ->
                    testDistance = Math.abs(enemyPos.x - playerPos.x) + Math.abs(enemyPos.y - 1 - playerPos.y);
                case RIGHT ->
                    testDistance = Math.abs(enemyPos.x + 1 - playerPos.x) + Math.abs(enemyPos.y - playerPos.y);
                case DOWN ->
                    testDistance = Math.abs(enemyPos.x - playerPos.x) + Math.abs(enemyPos.y + 1 - playerPos.y);
            }
            if (testDistance < bestDistance && enemyWallDetection.validMove(d)) {
                bestMove = d;
                bestDistance = testDistance;
            }
        }
        return  bestMove;
    }

    /**
     * helper method to move player character
     * @param playerPos the player's position
     */
    private void movePlayer(Position playerPos){
        WallDetection playerWallDetection = new WallDetection(map,playerPos);
        if(playerWallDetection.validMove(keyState)) {
            playerCharacter.move(1, keyState);
        }
    }

    //End of Game Logic:----------------------------------------------------------------------------------------------


    /**
     * get grid of entity positions
     * @return int[][] map
     */
    public int[][] getMap() {
        return map;
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

     * @param levelID New value of levelID.
     */
    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    /**
     * @return int levelID.
     */
    public int getLevelID() {
        return levelID;
    }

    /**
     * Gets the highest beaten level
     * @return int maxLevel
     */
    public int getMaxLevel() {
        return maxLevel;
    }

    /**
     * Gets basicObjectives
     * @return ArrayList<Objective> basicObjectives
     */
    public ArrayList<Objective> getBasicObjectives() {
        return basicObjectives;
    }

    /**
     * Gets bonusObjectives
     * @return ArrayList<BonusObjective> bonusObjectives
     */
    public ArrayList<BonusObjective> getBonusObjectives() {
        return bonusObjectives;
    }

    /**
     * Gets movingEnemies
     * @return ArrayList<MovingEnemy> movingEnemies
     */
    public ArrayList<MovingEnemy> getMovingEnemies() {
        return movingEnemies;
    }

    /**
     * Gets playerCharacter
     * @return playerCharacter
     */
    public PlayerCharacter getPlayerCharacter() {
        return playerCharacter;
    }

    /**
     * Sets keyState
     */
    public void setKeyState(Directions d) {
        keyState = d;
    }

    /**
     * Sets basicObjective
     */
    public void setBasicObjectives(ArrayList<Objective> basicObjectives) {
        this.basicObjectives = basicObjectives;
    }
}
