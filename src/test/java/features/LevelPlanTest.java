package features;

import misc.LevelPlan;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LevelPlanTest {
    /**
     * test getLevelPlan function
     */
    @Test
    void testGetLevelPlanOne() {
        int[][] levelOne = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 2, 2, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 1, 0, 1, 0, 1},
                {1, 0, 0, 1, 1, 1, 0, 1, 0, 7},
                {1, 0, 0, 0, 4, 1, 0, 1, 0, 1},
                {6, 0, 0, 1, 1, 1, 2, 1, 3, 1},
                {1, 0, 4, 4, 4, 0, 0, 1, 0, 1},
                {1, 0, 4, 2, 4, 0, 0, 1, 0, 1},
                {1, 0, 4, 4, 4, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

        assertArrayEquals(levelOne, LevelPlan.getLevelPlan(1));
    }

    /**
     * test getLevelPlan function
     */
    @Test
    void testGetLevelPlanTwo() {
        int[][] levelTwo = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 4, 0, 0, 5, 0, 2, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 0, 1, 4, 1},
                {1, 0, 1, 1, 0, 1, 1, 5, 0, 7},
                {1, 0, 1, 1, 2, 1, 0, 0, 0, 1},
                {6, 0, 4, 0, 0, 0, 0, 4, 0, 1},
                {1, 0, 1, 1, 1, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 0, 2, 3, 1},
                {1, 0, 0, 4, 1, 0, 0, 0, 4, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

        assertArrayEquals(levelTwo, LevelPlan.getLevelPlan(2));
    }
    /**
     * test getLevelPlan function
     */
    @Test
    void testGetLevelPlanNull() {
        int[][] wrongLevel = LevelPlan.getLevelPlan(3);
        assertNull(wrongLevel);
    }
}
