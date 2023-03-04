package features;

import entities.BonusObjective;
import entities.Objective;
import misc.Directions;
import misc.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import screens.Frame;
import screens.Level;
//import screens.ScreenManager;

import misc.GameLoop;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {
    private Frame frame;
    //private ScreenManager screenManager;
    private Level level;
    private Timer timer;
    private CountDownLatch latch;
    private GameLoop gameLoop;

    /**
     * init values
     */
    @BeforeEach
    void init() {
        frame = new Frame();
        //screenManager = new ScreenManager(frame);
        level = frame.getLevel();
        gameLoop = frame.getGameLoop();
        gameLoop.initializeLevel(1);
        timer = new Timer();
        latch = new CountDownLatch(1);
    }

    /**
     * test convertGridToPixel function in Level
     */
    @Test
    void testConvertGridToPixel() {
        int[] pixel = level.convertGridToPixel(new Position(1, 1));

        assertArrayEquals(pixel, new int[] {108, 142});
    }
//    /**
//     * test getPlaytime function in Level
//     */
//    @Test
//    void testGetPlayTime() throws InterruptedException {
//        level.start();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                //level.getPlaytime();
//                latch.countDown();
//                System.out.println(level.getPlaytime());
//                level.pause();
//            }
//        }, 2000);
//        latch.await();
//    }
    /**
     * test getMap function in Level
     */
    @Test
    void testGetMap() {
        int[][] map = gameLoop.getMap();
         assertArrayEquals(map, new int[][]{
                 {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                 {1, 0, 2, 2, 0, 0, 0, 0, 0, 1},
                 {1, 0, 0, 0, 0, 1, 0, 1, 0, 1},
                 {1, 0, 0, 1, 1, 1, 0, 1, 0, 7},
                 {1, 0, 0, 0, 4, 1, 0, 1, 0, 1},
                 {6, 0, 0, 1, 1, 1, 2, 1, 3, 1},
                 {1, 0, 4, 4, 4, 0, 0, 1, 0, 1},
                 {1, 0, 4, 2, 4, 0, 0, 1, 0, 1},
                 {1, 0, 4, 4, 4, 0, 0, 0, 0, 1},
                 {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}});
    }
    /**
     * test getScore function in Level
     */
    @Test
    void testGetScore() {
        int score = gameLoop.getScore();
        assertEquals(score, 0);
    }

    /**
     * test setScore function in Level
     */
    @Test
    void testSetScore() {
        level.setScore(10);
        int score = level.getScore();
        assertEquals(score, 10);
    }

    /**
     * test getLevelID function in Level
     */
    @Test
    void testGetLevelID() {
        int levelID = gameLoop.getLevelID();
        assertEquals(levelID, 1);
    }
    /**
     * test setLevelID function in Level
     */
    @Test
    void testSetLevelID() {
        gameLoop.setLevelID(3);
        int levelID = gameLoop.getLevelID();
        assertEquals(levelID, 3);
    }
    /**
     * test getMaxLevel function in Level
     */
    @Test
    void testGetMaxLevel() {
        int maxLevel = gameLoop.getMaxLevel();
        assertEquals(maxLevel, 0);
    }
    /**
     *  test the testForWin function in Level
     */
    @Test
    void testTestForWin() throws InterruptedException {
        gameLoop.setBasicObjectives(new ArrayList<Objective>());
        gameLoop.getPlayerCharacter().move(9, Directions.RIGHT);
        gameLoop.getPlayerCharacter().move(2, Directions.UP);
        latch = new CountDownLatch(1);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 1500);
        latch.await();
        assertEquals(frame.getScreenContainer().getComponent(6).isVisible(), true);
    }
}