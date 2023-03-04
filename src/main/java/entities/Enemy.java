package entities;
import misc.Position;

import java.awt.Point;

/**
 * Entities that subtract from the player's score when contacted
 */
public class Enemy extends Entity{
    /**
     * points removed from score upon contact
     */
    protected static int scorePunishment;

    /**
     * Constructor of Enemy object
     * @param ImgName image file name in /src/main/resources/
     * @param position entity position in level grid
     */
    public Enemy(String ImgName, Position position) {
        super(ImgName, position);
        scorePunishment = 10;
        setScoreChange(-10);
    }

    /**
     * Constructor of Enemy object
     * @param ImgName image file name in /src/main/resources/
     * @param position entity position in level grid
     * @param scoreChange the scoreChange of the enemy
     */
    public Enemy(String ImgName, Position position, int scoreChange) {
        super(ImgName, position);
        scorePunishment = scoreChange;
        setScoreChange(-scorePunishment);
    }

    /**
     * @return int scorePunishment
     */
    public int getScorePunishment() {
        return scorePunishment;
    }
}

