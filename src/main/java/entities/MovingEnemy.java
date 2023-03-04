package entities;
import java.awt.Point;

import misc.Directions;
import misc.Position;

/**
 * Moving enemies that cause the player to lose the game when contacted
 */
public class MovingEnemy extends Enemy{
    /**
     * Constructor of MovingEnemy object
     * @param ImgName image file name in /src/main/resources/
     * @param position entity position in level grid
     */
    public MovingEnemy(String ImgName, Position position) {
        super(ImgName, position);
    }

    /**
     * @param n number of grid positions to move
     */
    public void move(int n, Directions d) {
        position.move(n,d);
    }

}
