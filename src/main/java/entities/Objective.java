package entities;
import misc.Position;

import java.awt.Point;

/**
 * Entities that add to the player's score when collected
 */
public class Objective extends Entity {
    /**
     * points added to score upon collection
     */
    private int scoreReward;

    /**
     * Constructor of Objective object
     * @param ImgName image file name in /src/main/resources/
     * @param position entity position in level grid
     */
    public Objective(String ImgName, Position position) {
        super(ImgName, position);
        this.scoreReward = 10;
        setScoreChange(10);
    }

    /**
     * Constructor of Objective object
     * @param ImgName image file name in /src/main/resources/
     * @param position entity position in level grid
     * @param scoreReward point value of this objective
     */
    public Objective(String ImgName, Position position, int scoreReward) {
        super(ImgName, position);
        this.scoreReward = scoreReward;
        setScoreChange(scoreReward);
    }

    /**
     * @return int scoreReward
     */
    public int getScoreReward() {
        return scoreReward;
    }
}