package features;

import entities.Enemy;
import entities.MovingEnemy;
import misc.Directions;
import misc.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    private MovingEnemy mEne;
    /**
     * init values
     */
    @BeforeEach
    void init() {
        mEne = new MovingEnemy("movingEnemy.png", new Position(48,48));
    }
    /**
     * test the two params constructor of Enemy
     * test the getScorePunishment function in Enemy
     */
    @Test
    void testEnemy_getScorePunishment() {
        Enemy ene = new Enemy("stationaryEnemy.png", new Position(48, 48));
        int eneScorePunishment = ene.getScorePunishment();

        assertEquals(eneScorePunishment, 10);
    }

    /**
     * test the three params constructor of Enemy
     */
    @Test
    void testEnemy_constructor() {
        Enemy ene = new Enemy("stationaryEnemy.png", new Position(48,48), 20);
        int eneScorePunishment = ene.getScorePunishment();

        assertEquals(eneScorePunishment, 20);
    }

    /**
     * test the moveLeft function in MovingEnemy
     */
    @Test
    void testMovingEnemy_moveLeft() {
        mEne.move(10, Directions.LEFT);
        Position enePos = mEne.getPosition();
        assertEquals(enePos, new Position(38,48));
    }

    /**
     * test the moveUp function in MovingEnemy
     */
    @Test
    void testMovingEnemy_moveUp() {
        mEne.move(10, Directions.UP);
        Position enePos = mEne.getPosition();
        assertEquals(enePos, new Position(48,38));
    }

    /**
     * test the moveRight function in MovingEnemy
     */
    @Test
    void testMovingEnemy_moveRight() {
        mEne.move(10, Directions.RIGHT);
        Position enePos = mEne.getPosition();
        assertEquals(enePos, new Position(58,48));
    }

    /**
     * test the moveDown function in MovingEnemy
     */
    @Test
    void testMovingEnemy_moveDown() {
        mEne.move(10, Directions.DOWN);
        Position enePos = mEne.getPosition();
        assertEquals(enePos, new Position(48,58));
    }
}
