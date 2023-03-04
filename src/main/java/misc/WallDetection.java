package misc;

/**
 * An object of Wall detection can be used to test the position of an entity against a map of wall objects
 */
public class WallDetection {
    //the map
    private final int[][] map;

    //the position of the entity you are verifying
    private final Position position;

    /**
     * Class constructor
     * @param map the map which encodes the wall positions
     * @param position the position of the entity to be tested
     */
    public WallDetection(int[][] map, Position position){
        this.map = map;
        this.position = position;
    }

    /**
     * Method to test if the cell below the given position is a wall
     * @return a boolean whether there is a wall or not
     */
    public Boolean validMove(Directions d) {
        switch (d) {
            case LEFT:
                return position.x - 1 >= 0 && map[position.y][position.x - 1] != 1;
            case UP:
                return position.y - 1 >= 0 && map[position.y - 1][position.x] != 1;
            case RIGHT:
                return position.x + 1 < map[0].length && map[position.y][position.x + 1] != 1;
            case DOWN:
                return position.y + 1 < map.length && map[position.y + 1][position.x] != 1;
            default:
                return true;
        }
    }
}

