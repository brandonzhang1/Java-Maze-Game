package features;

import entities.BonusObjective;
import entities.Objective;
import misc.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectiveTest {
    /**
     * test the two params constructor of Objective
     * test the getScoreReward function in Objective
     */
    @Test
    void testObjective_getScoreReward() {
        Objective obj = new Objective("objective.png", new Position(48, 48));
        int objScoreReward = obj.getScoreReward();

        assertEquals(objScoreReward, 10);
    }

    /**
     * test the three params constructor of Objective
     */
    @Test
    void testObjective_constructor() {
        Objective obj = new Objective("objective.png", new Position (48, 48), 20);
        int objScoreReward = obj.getScoreReward();

        assertEquals(objScoreReward, 20);
    }

    /**
     * test the BonusObjective constructor
     * test the getTimeLimit function in BonusObjective
     */
    @Test
    void testBonusObjective_getTimeLimit() {
        BonusObjective bonusObj = new BonusObjective("bonus.png", new Position (48, 48), 20, 10000);
        int bonusObjTimeLimit = bonusObj.getTimeLimit();

        assertEquals(bonusObjTimeLimit, 10000);
    }


}
