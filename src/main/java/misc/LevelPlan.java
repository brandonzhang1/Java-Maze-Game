package misc;
/** 
 * A level plan encodes a matrix for the floor plan of the map.
 * It contains information on what entities are on the map and where
 */
public abstract class LevelPlan {
    //Level plan of level one
    public static final int[][] levelOne = {
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

    //Level plan of level two
    public static final int[][] levelTwo = {
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

    /**
     * Static method to return reference to corresponding level plan
     * @param n level number
     * @return int[][] grid containing positions of walls and entities
     */
    public static int[][] getLevelPlan(int n) {
        if (n == 1) {
            return levelOne;
        } else if (n == 2) {
            return levelTwo;
        }
        return null;
    }
}