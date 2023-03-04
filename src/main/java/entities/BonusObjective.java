package entities;
import misc.Position;

import java.awt.Point;

/**
 * Objective with higher score reward and a time limit
 */
public class BonusObjective extends Objective {
    /**
     * time at which this bonus objective dissapears
     */
    private int timeLimit;
    /**
     * BasicObjective Constructor
     *
     * @param ImgName image file name in /src/main/resources/
     * @param position entity position in level grid
     * @param scoreReward the scoreReward of this Objective
     * @param timeLimit time at which this bonus objective dissapears
     */
    public BonusObjective(String ImgName, Position position, int scoreReward, int timeLimit) {
        super(ImgName, position, scoreReward);
        this.timeLimit = timeLimit;
    }

    /**
     * @return int timeLimit;
     */
    public int getTimeLimit() {
        return timeLimit;
    }
}