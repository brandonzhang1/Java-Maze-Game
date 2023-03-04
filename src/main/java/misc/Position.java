package misc;

import java.awt.*;

/**
 * Object of this class encodes the position of an entity
 */
public class Position extends Point {

    /**
     * Class constructor to set the coordinates
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Position(int x, int y) {
        super(x,y);
    }

    /**
     * Method to move the position in a specific direction by a certain amount
     * @param n the amount moved
     * @param d the direction
     */
    public void move(int n, Directions d){
        switch(d) {
            case LEFT:
                this.x = this.x - n;
                break;
            case UP:
                this.y = this.y - n;
                break;
            case RIGHT:
                this.x = this.x + n;
                break;
            case DOWN:
                this.y = this.y + n;
                break;
        }
    }

    /**
     * Method to determine whether two positions are the same
     * @param other the position being compared against
     * @return Boolean whether the position is the same or not
     */
    public Boolean compare(Position other){
        return (this.x == other.x && this.y==other.y);
    }

    /**
     * Method to set the position from an existing position object
     * @param position the new position
     */
    public void setPosition(Position position){
        this.setLocation(position.getLocation());
    }

}
