package screens;

import misc.GameLoop;

import javax.imageio.ImageIO;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

/**
 * A Screen object is a JPanel from the Java Swing library.
 * Screens are the images, buttons etc. displayed at a time.
 * They will be displayed on the {@link Frame} window.
 * Each screen will be listening for user actions such as pressing buttons.
 */
abstract public class Screen extends JPanel implements ActionListener {

    // background image
    private BufferedImage backGroundImg;

    //Pointer to the frame on which the screens will be displayed
    protected Frame manager;

    //Pointer to the ScreenManager has a container for all the created strings.
    //protected ScreenManager manager;

    //Pointer to a container for all the strings
    protected Container screenContainer;

    //Pointer to a level
    protected Level level;

    protected GameLoop gameLoop;

    /**
     * Constructs screen
     * @param manager pointer to the container for the created strings
     */
    public Screen(Frame manager) {
        this.manager = manager;   //initialize frame
        this.screenContainer = manager.getScreenContainer();    //initialize the container
        this.level = manager.getLevel(); //initialize the level pointer
        this.gameLoop = manager.getGameLoop();

        this.setLayout(null);   //set the layout to null for all Screens
    }

    /**
     * Helper method to create a JButton object
     * @param name The title of the button
     * @param file the filepath of the button image
     * @return JButton newButton
     */
    public JButton createButtons(String name, String file){
        String path = System.getProperty("user.dir") + "/src/main/resources/";
        Icon buttonIcon = new ImageIcon(path + file);
        JButton newButton = new JButton(buttonIcon);  //declare a new JButton
        newButton.setOpaque(false); //transparent background
        newButton.setContentAreaFilled(false); //transparent background
        newButton.setBorderPainted(false); //transparent background
        newButton.setActionCommand(name); //set the name of the button

        return  newButton;
    }

    /**
     * Helper method to set the position and size of the button
     * @param newButton the button
     * @param x the X-coordinate of the button
     * @param y the Y-Coordinate of the button
     * @param width the Width of the button
     * @param height the Height of the button
     * @return JButton newButton
     */
    public JButton setButtonBounds(JButton newButton,  int x, int y, int width, int height){
        newButton.setBounds(x, y, width, height);   //set the location and size of button
        newButton.setVisible(true);     //set the visibility to true
        newButton.setFocusPainted(false); //transparent background
        newButton.setOpaque(false); //transparent background
        newButton.addActionListener(this);      //if player clicks button, action will have a result
        this.add(newButton);            //add the button to the panel
        return newButton;
    }

    /**
     * backgroundImage is set for the Screen object
     * @param fileName the String file name
     */
    public void setBackGroundImg(String fileName) {
        String path = System.getProperty("user.dir") + "/src/main/resources/";
        try {
            this.backGroundImg = ImageIO.read(new File(path + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }

    /**
     * This method allows drawing the background image on the {@link Screen}.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Set the font type, color and size for the subclasses
        Font f = new Font("Bauhaus 93", Font.BOLD, 16);
        g.setFont(f);
        g.setColor(Color.ORANGE);
        //draw the background
        g.drawImage(backGroundImg, 0, 0, Frame.FRAME_WIDTH, Frame.FRAME_HEIGHT, null);
    }

    /**
     * This method is called when the user presses one of the buttons on screen.
     * Pressing the buttons will cause the screen to change.
     * @param e The button-pressing event
     */
    @Override
    abstract public void actionPerformed(ActionEvent e);

}
