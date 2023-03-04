package features;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {
    /**
     * load image function
     */
    BufferedImage loadImage(String name) throws IOException {
        BufferedImage image = ImageIO.read(new File(System.getProperty("user.dir") + "/src/main/resources/" + name));
        return image;
    }

    /**
     * load buttons/about.png
     */
    @Test
    void testAbout() throws IOException {
        BufferedImage image = loadImage("buttons/about.png");
        assertNotNull(image);
    }
    /**
     * load buttons/back.png
     */
    @Test
    void testBack() throws IOException {
        BufferedImage image = loadImage("buttons/back.png");
        assertNotNull(image);
    }
    /**
     * load buttons/home.png
     */
    @Test
    void testHome() throws IOException {
        BufferedImage image = loadImage("buttons/home.png");
        assertNotNull(image);
    }
    /**
     * load buttons/house.png
     */
    @Test
    void testHouse() throws IOException {
        BufferedImage image = loadImage("buttons/house.png");
        assertNotNull(image);
    }
    /**
     * load buttons/instructions.png
     */
    @Test
    void testInstructions() throws IOException {
        BufferedImage image = loadImage("buttons/instructions.png");
        assertNotNull(image);
    }
    /**
     * load buttons/levels.png
     */
    @Test
    void testLevels() throws IOException {
        BufferedImage image = loadImage("buttons/levels.png");
        assertNotNull(image);
    }
    /**
     * load buttons/nextLevel.png
     */
    @Test
    void testNextLevel() throws IOException {
        BufferedImage image = loadImage("buttons/nextLevel.png");
        assertNotNull(image);
    }
    /**
     * load buttons/pause.png
     */
    @Test
    void testPause() throws IOException {
        BufferedImage image = loadImage("buttons/pause.png");
        assertNotNull(image);
    }
    /**
     * load buttons/play.png
     */
    @Test
    void testPlay() throws IOException {
        BufferedImage image = loadImage("buttons/play.png");
        assertNotNull(image);
    }
    /**
     * load buttons/restart.png
     */
    @Test
    void testRestart() throws IOException {
        BufferedImage image = loadImage("buttons/restart.png");
        assertNotNull(image);
    }
    /**
     * load buttons/resume.png
     */
    @Test
    void testResume() throws IOException {
        BufferedImage image = loadImage("buttons/resume.png");
        assertNotNull(image);
    }

    /**
     * load screens/credits.png
     */
    @Test
    void testCredits() throws IOException {
        BufferedImage image = loadImage("screens/credits.png");
        assertNotNull(image);
    }
    /**
     * load screens/help.png
     */
    @Test
    void testHelp() throws IOException {
        BufferedImage image = loadImage("screens/help.png");
        assertNotNull(image);
    }
    /**
     * load screens/help-2.png
     */
    @Test
    void testHelp2() throws IOException {
        BufferedImage image = loadImage("screens/help-2.png");
        assertNotNull(image);
    }
    /**
     * load screens/lost.png
     */
    @Test
    void testLost() throws IOException {
        BufferedImage image = loadImage("screens/lost.png");
        assertNotNull(image);
    }
    /**
     * load screens/map.png
     */
    @Test
    void testMap() throws IOException {
        BufferedImage image = loadImage("screens/map.png");
        assertNotNull(image);
    }
    /**
     * load screens/map2.png
     */
    @Test
    void testMap2() throws IOException {
        BufferedImage image = loadImage("screens/map2.png");
        assertNotNull(image);
    }
    /**
     * load screens/menu.png
     */
    @Test
    void testMenu() throws IOException {
        BufferedImage image = loadImage("screens/menu.png");
        assertNotNull(image);
    }
    /**
     * load screens/menu-2.png
     */
    @Test
    void testMenu2() throws IOException {
        BufferedImage image = loadImage("screens/menu-2.png");
        assertNotNull(image);
    }
    /**
     * load screens/paused.png
     */
    @Test
    void testPaused() throws IOException {
        BufferedImage image = loadImage("screens/paused.png");
        assertNotNull(image);
    }
    /**
     * load screens/won.png
     */
    @Test
    void testWon() throws IOException {
        BufferedImage image = loadImage("screens/won.png");
        assertNotNull(image);
    }

    /**
     * load bonus.png
     */
    @Test
    void testBonus() throws IOException {
        BufferedImage image = loadImage("bonus.png");
        assertNotNull(image);
    }
    /**
     * load movingEnemy.png
     */
    @Test
    void testMovingEnemy() throws IOException {
        BufferedImage image = loadImage("movingEnemy.png");
        assertNotNull(image);
    }
    /**
     * load objective.png
     */
    @Test
    void testObjective() throws IOException {
        BufferedImage image = loadImage("objective.png");
        assertNotNull(image);
    }
    /**
     * load player.png
     */
    @Test
    void testPlayer() throws IOException {
        BufferedImage image = loadImage("player.png");
        assertNotNull(image);
    }
    /**
     * load stationaryEnemy.png
     */
    @Test
    void testStationaryEnemy() throws IOException {
        BufferedImage image = loadImage("stationaryEnemy.png");
        assertNotNull(image);
    }
    /**
     * load wall.png
     */
    @Test
    void testWall() throws IOException {
        BufferedImage image = loadImage("wall.png");
        assertNotNull(image);
    }
}
