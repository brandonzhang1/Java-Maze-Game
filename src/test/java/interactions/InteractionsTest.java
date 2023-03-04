package interactions;

import entities.BonusObjective;
import entities.MovingEnemy;
import entities.PlayerCharacter;
import misc.Directions;
import misc.GameLoop;
import misc.Position;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screens.Frame;
import screens.Level;
//import screens.ScreenManager;


import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;



import static org.junit.jupiter.api.Assertions.*;

class InteractionsTest {
    private Frame frame;
    //private ScreenManager screenManager;
    private Level level;
    private Timer timer;
    private GameLoop gameLoop;
    private CountDownLatch latch;
    /**
     * init values
     */
    @BeforeEach
    void init() {
        frame = new Frame();
        //screenManager = new ScreenManager(frame);
        gameLoop = frame.getGameLoop();
        level = new Level(frame);
        timer = new Timer();
        latch = new CountDownLatch(1);
    }
    @AfterEach
    void cancel() {
        gameLoop.pause();
    }
    /**
     * test the bonus objectives disappear after few ticks and player not collect it
     */
    @Test
    void testBonusDisappear() throws InterruptedException {
        gameLoop.initializeLevel(1);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ArrayList<BonusObjective> bonusObjectives = gameLoop.getBonusObjectives();
                assertEquals(bonusObjectives.size(), 0);
                latch.countDown();
                gameLoop.pause();
            }
        }, 40000);
        latch.await();
    }

    /**
     * test the basicObjective is removed after the player gets it
     * @throws InterruptedException
     */
    @Test
    void testPlayerCollectBasicObjectives() throws InterruptedException {
        gameLoop.initializeLevel(1);
        int initSize = gameLoop.getBasicObjectives().size();

        PlayerCharacter playerCharacter = gameLoop.getPlayerCharacter();
        playerCharacter.move(2, Directions.RIGHT);
        playerCharacter.move(1, Directions.DOWN);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 750);
        latch.await();

        assertEquals(initSize-1, gameLoop.getBasicObjectives().size());
    }

    /**
     * test the score is added after the player gets basicObjective
     * @throws InterruptedException
     */
    @Test
    void testPlayerCollectBasicObjectivesScore() throws InterruptedException {
        gameLoop.initializeLevel(1);
        int initScore = level.getScore();

        PlayerCharacter playerCharacter = gameLoop.getPlayerCharacter();
        playerCharacter.move(2, Directions.RIGHT);
        playerCharacter.move(1, Directions.DOWN);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 750);
        latch.await();

        assertEquals(initScore+10, gameLoop.getScore());
    }

    /**
     * test the bonusObjective is removed after the player gets it
     * @throws InterruptedException
     */
    @Test
    void testPlayerCollectBonusObjectives() throws InterruptedException {
        gameLoop.initializeLevel(1);
        PlayerCharacter playerCharacter = gameLoop.getPlayerCharacter();
        Position bonusObjectivePos = gameLoop.getBonusObjectives().get(0).getPosition();
        Position playerCharacterPos = playerCharacter.getPosition();
        int initSize = gameLoop.getBonusObjectives().size();

        playerCharacter.move(bonusObjectivePos.x - playerCharacterPos.x, Directions.RIGHT);
        playerCharacter.move(bonusObjectivePos.y - playerCharacterPos.y, Directions.DOWN);


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 750);
        latch.await();

        assertEquals(initSize-1, gameLoop.getBonusObjectives().size());
    }

    /**
     * test the score is added after the player gets bonusObjective
     * @throws InterruptedException
     */
    @Test
    void testPlayerCollectBonusObjectivesScore() throws InterruptedException {
        gameLoop.initializeLevel(1);
        PlayerCharacter playerCharacter = gameLoop.getPlayerCharacter();
        Position bonusObjectivePos = gameLoop.getBonusObjectives().get(0).getPosition();
        Position playerCharacterPos = playerCharacter.getPosition();
        int initScore = gameLoop.getScore();

        playerCharacter.move(bonusObjectivePos.x - playerCharacterPos.x, Directions.RIGHT);
        playerCharacter.move(bonusObjectivePos.y - playerCharacterPos.y, Directions.DOWN);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 750);
        latch.await();

        assertEquals(initScore+30, gameLoop.getScore());
    }

    /**
     * test the player losing score after the player encounters stationaryEnemy
     * @throws InterruptedException
     */
    @Test
    void testPlayerEncounterStationaryEnemyPunish() throws InterruptedException {
        gameLoop.initializeLevel(1);
        PlayerCharacter playerCharacter = gameLoop.getPlayerCharacter();

        playerCharacter.move(3, Directions.RIGHT);
        playerCharacter.move(2, Directions.DOWN);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 1500);
        latch.await();

        assertEquals(frame.getScreenContainer().getComponent(5).isVisible(), true);
    }

    /**
     * test the player lose when encounters the movingEnemy
     * @throws InterruptedException
     */
    @Test
    void testPlayerEncounterMovingEnemy() throws InterruptedException {
        gameLoop.initializeLevel(1);
        PlayerCharacter playerCharacter = gameLoop.getPlayerCharacter();
        MovingEnemy movingEnemy= gameLoop.getMovingEnemies().get(0);

        playerCharacter.setPosition(8, 7);
        movingEnemy.setPosition(8, 8);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 1500);
        latch.await();

        assertEquals(frame.getScreenContainer().getComponent(5).isVisible(), true);
        //assertArrayEquals(movingEnemy.getPosition(), new int[] {8, 8});
    }

    /**
     * test the player cannot cross walls
     * @throws InterruptedException
     */
    @Test
    void testPlayerAndWall() throws InterruptedException {
        gameLoop.initializeLevel(1);
        PlayerCharacter playerCharacter = gameLoop.getPlayerCharacter();
        /*
        playerCharacter.moveRight(2);
        level.setKeyState(Directions.RIGHT);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                level.pause();
            }
        }, 750);
        latch.await();

        assertArrayEquals(playerCharacter.getPosition(), new int[] {2, 5});
        */
        //test player move right
        playerCharacter.setPosition(4, 4);
        gameLoop.setKeyState(Directions.RIGHT);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 750);
        latch.await();
        assertEquals(playerCharacter.getPosition(), new Position (4, 4));

        //test player move up
        playerCharacter.setPosition(4, 4);
        gameLoop.setKeyState(Directions.UP);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 750);
        latch.await();
        assertEquals(playerCharacter.getPosition(), new Position (4, 4));

        //test player move down
        playerCharacter.setPosition(4, 4);
        gameLoop.setKeyState(Directions.DOWN);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 750);
        latch.await();
        assertEquals(playerCharacter.getPosition(), new Position(4, 4));

        //test player move left
        playerCharacter.setPosition(6, 4);
        gameLoop.setKeyState(Directions.LEFT);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 750);
        latch.await();
        assertEquals(playerCharacter.getPosition(), new Position(6, 4));

    }

    /**
     * test the moving enemy cannot cross walls
     * @throws InterruptedException
     */
    @Test
    void testMovingEnemyAndWall() throws InterruptedException {
        gameLoop.initializeLevel(1);
        PlayerCharacter playerCharacter = gameLoop.getPlayerCharacter();
        MovingEnemy movingEnemy = gameLoop.getMovingEnemies().get(0);

        //test enemy move right
        playerCharacter.setPosition(6, 4);
        movingEnemy.setPosition(4, 4);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 750);
        latch.await();
        assertEquals(movingEnemy.getPosition(), new Position (4, 4));

        //test enemy move up
        playerCharacter.setPosition(4, 2);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 750);
        latch.await();
        assertEquals(movingEnemy.getPosition(), new Position (4, 4));

        //test enemy move down
        playerCharacter.setPosition(4, 6);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 750);
        latch.await();
        assertEquals(movingEnemy.getPosition(), new Position (4, 4));

        //test enemy move left
        playerCharacter.setPosition(4, 4);
        movingEnemy.setPosition(6, 4);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
                gameLoop.pause();
            }
        }, 750);
        latch.await();
        assertEquals(movingEnemy.getPosition(), new Position(6, 4));
    }
}
