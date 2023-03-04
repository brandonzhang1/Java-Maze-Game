package entities;
import java.awt.Point;

import misc.Directions;
import misc.Position;

/**
 * The entity corresponding to the player
 */
public class PlayerCharacter extends Entity{
    /**
     * Constructor of entities.PlayerCharacter from entities.Entity
     * @param ImgName image file name in /src/main/resources/
     * @param position entity position in level grid
     */
    public PlayerCharacter(String ImgName, Position position) {
        super(ImgName, position);
    }

    /**
     * @param n number of grid positions to move
     */
    public void move(int n, Directions d) {
        position.move(n,d);
    }
}
