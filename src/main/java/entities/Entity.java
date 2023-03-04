package entities;

import misc.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Point;

/** 
 * An entity is an object that participates in game interactions with other entities
 */
abstract public class Entity {
    //the entities image
    private BufferedImage EntityImg;
    //entities position
    protected Position position;
    //entities scoreChange
    private int scoreChange = 0;

    /**
     * path string to image resource files
     */
    private static String path = System.getProperty("user.dir") + "/src/main/resources/";

    /**
     * Constructor of Entity object
     * @param ImgName the Image Name for EntityImg
     * @param pos entity position in level grid
     */
    public Entity(String ImgName, Position pos) {
        try {
            EntityImg = ImageIO.read(new File(path + ImgName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.position = pos;
    }

    /**
     * @param pos array of coordinates {x, y}
     */
    public void setPosition(Position pos){
        this.position = pos;
    }

    /**
     * @param x left-right coordinate
     * @param y up-down coordinate
     */
    public void setPosition(int x, int y){ //??????????????????????????????????
        this.position.setLocation(x,y);

    }

    /**
     * @return BufferedImage EntityImg
     */
    public BufferedImage getEntityImg() {
        return EntityImg;
    }

    /**
     * @return int[] position coordinates {x, y}
     */
    public Position getPosition() { return position; }

    /**
     * @return the score change due to contact with the entity
     */
    public int getScoreChange(){ return scoreChange;}

    /**
     * setter method for score change
     * @param scoreChange score
     */
    public void setScoreChange(int scoreChange){
        this.scoreChange = scoreChange;
    }


}
