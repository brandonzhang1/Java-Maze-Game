package features;

import entities.Enemy;
import entities.Objective;
import misc.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class EnetityTest {
    private Objective obj;
    /**
     * init values
     */
    @BeforeEach
    void init() {
        obj = new Objective("objective.png", new Position(48, 48));
    }

    /**
     * test the getPosition function in Entity
     */
    @Test
    void testEntity_getPosition() {
        Position objPosition = obj.getPosition();

        assertEquals(objPosition, new Position(48, 48));
    }

//    /**
//     * test the getEntityImg function in Entity
//     */
//    @Test
//    public void testEntity_getEntityImg() throws IOException {
//        Objective obj = new Objective("objective.png", new int[]{48, 48});
//        BufferedImage EntityImg = ImageIO.read(new File("src/main/resources/objective.png"));
//
//        assertArrayEquals(EntityImg, obj.getEntityImg());
//    }

    /**
     * test setPosition(int[] pos) in Entity
     */
    @Test
    void testEntity_setPosition1() {
        obj.setPosition(new Position(50, 50));
        Position objPosition = obj.getPosition();

        assertEquals(objPosition, new Position(50, 50));
    }

    /**
     * test setPosition(int x, int y) in Entity
     */
    @Test
    public void testEntity_setPosition2() {
        obj.setPosition(50, 50);
        Position objPosition = obj.getPosition();

        assertEquals(objPosition, new Position(50, 50));
    }


    /**
     * test getScoreChange() in Entity
     */
    @Test
    public void testEntity_getScoreChange() {
  //      obj.setScoreChange(0);
        int scoreChange = obj.getScoreChange();

        assertEquals(scoreChange, 10);
    }

    /**
     * test setScoreChange() in Entity
     */
    @Test
    public void testEntity_setScoreChange() {
        obj.setScoreChange(20);
        int scoreChange = obj.getScoreChange();

        assertEquals(scoreChange, 20);
    }
}
