package features;

import entities.BonusObjective;
import entities.PlayerCharacter;
import misc.Directions;
import misc.GameLoop;
import misc.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import screens.Frame;
import screens.Level;
//import screens.ScreenManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerCharacterTest {
    private Frame frame;
    //private ScreenManager screenManager;
    private Level level;
    private Timer timer;
    private CountDownLatch latch;
    private PlayerCharacter player;
    private GameLoop gameLoop;
    /**
     * init values
     */
    @BeforeEach
    void init() {
        frame = new Frame();
        //screenManager = new ScreenManager(frame);
        gameLoop = frame.getGameLoop();
        gameLoop.initializeLevel(1);
        timer = new Timer();
        latch = new CountDownLatch(1);
        player = gameLoop.getPlayerCharacter();
    }
    @AfterEach
    void cancel() {
        gameLoop.pause();
    }
    /**
     * moveLeft of PlayerCharacter based on the keyState
     */
    @Test
    void testPlayerCharacter_moveLeft() throws InterruptedException {
        player.move(1, Directions.RIGHT);
        gameLoop.setKeyState(Directions.LEFT);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 900);
        latch.await();
        Position playerPos = player.getPosition();
        assertEquals(playerPos, new Position (0, 5));
    }
    /**
     * moveUp of PlayerCharacter based on keyState
     */
    @Test
    void testPlayerCharacter_moveUp() throws InterruptedException {
        player.move(1, Directions.RIGHT);
        gameLoop.setKeyState(Directions.UP);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 900);
        latch.await();
        Position playerPos = player.getPosition();
        assertEquals(playerPos, new Position (1, 4));
    }
    /**
     * moveRight of PlayerCharacter based on keyState
     */
    @Test
    void testPlayerCharacter_moveRight() throws InterruptedException {
        gameLoop.setKeyState(Directions.RIGHT);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 900);
        latch.await();
        Position playerPos = player.getPosition();
        assertEquals(playerPos, new Position (1, 5));
    }
    /**
     * moveDown of PlayerCharacter based on keyState
     */
    @Test
    void testPlayerCharacter_moveDown() throws InterruptedException {
        player.move(1, Directions.RIGHT);
        gameLoop.setKeyState(Directions.DOWN);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 900);
        latch.await();
        Position playerPos = player.getPosition();
        assertEquals(playerPos, new Position(1, 6));
    }
}
