package asteroid;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.InputEvent;
import java.awt.Color;
import java.util.ArrayList;
import javalib.impworld.World;
import javalib.worldimages.*;

/**
 *
 * @author mibeauregard
 */

/**
 * Class containing methods for setting up the main menu
 */
public class MainMenu extends World {
    // class constants
    public int width = 800; //width of the canvas
    public int height = 800; //height of the canvas
    public int midheight = height/2;
    public int midwidth = width/2;
    public Posn midposn = new Posn((width/2), (height/2));
    private AsteroidGame world; // Represents the game world
    private static final double speed = .1; // Clock speed (how often it ticks)
    public ArrayList<Asteroid> asteroids;  // the Asteroid objects
    
    /** Constructor for MainMenu object */
    public MainMenu(){
  
    }
    
    /** Draws the images on the main menu
     * @return WorldImage
     */
    public WorldImage makeImage(){
        // List of controls to add to screen
        ArrayList<String> directions = new ArrayList<>();
            directions.add("RIGHT & LEFT: pivot laser cannon around ship");
            directions.add("DOWN: fire rear thruster to move forward");
            directions.add("UP: fire forward thruster to move backward");
            directions.add("SPACEBAR: fire laser cannon");
            directions.add("P: pause");
            directions.add("Q: quit game");
            directions.add("WATCH OUT: when you hyperjump across sides,");
            directions.add("the laser cannon will jump seperate from the ship");

        WorldImage text = new TextImage(new Posn(midwidth, (600)), "OBJECTIVE: destroy all the asteroids, don't let them hit your ship", 20, Color.gray);
        int spacing = 610;
        for (int i = 0; i < directions.size(); i++){
            spacing = spacing + 21;
            text = new OverlayImages(text, new TextImage(new Posn(midwidth, spacing), directions.get(i), 20, Color.gray));
        }
        WorldImage eblock = new OverlayImages(new RectangleImage(new Posn(midwidth, (midheight - 100)), (160), 60, Color.gray),
                                              new RectangleImage(new Posn(midwidth, (midheight - 100)), (150), 50, Color.green));
        WorldImage easy = new OverlayImages(eblock,
                    new TextImage(new Posn(midwidth, (midheight - 100)), "Easy", 30, Color.gray));

        WorldImage mblock = new OverlayImages(new RectangleImage(new Posn(midwidth, (midheight)), (160), 60, Color.gray),
                                              new RectangleImage(new Posn(midwidth, midheight), (150), 50, Color.blue));
        WorldImage medium = new OverlayImages(mblock,
                    new TextImage(new Posn(midwidth, (midheight + 5)), "Medium", 30, Color.gray));

        WorldImage hblock = new OverlayImages(new RectangleImage(new Posn(midwidth, (midheight + 100)), (160), 60, Color.gray),
                                              new RectangleImage(new Posn(midwidth, (midheight + 100)), (150), 50, Color.red));
        WorldImage hard = new OverlayImages(hblock,
                    new TextImage(new Posn((midwidth), (midheight + 105)), "Hard", 30, Color.gray));
        WorldImage title = new TextImage(new Posn(midwidth, (midheight - 200)), "Asteroids", 90, Color.gray);
        WorldImage background = new RectangleImage(new Posn(midwidth, midheight), width, height, Color.black);
        return new OverlayImages(background,
                new OverlayImages(title,
                new OverlayImages(medium,
                new OverlayImages(easy,
                new OverlayImages(hard, text)))));
    }
    
    /**
     * On mouse click go to easy, medium, or hard mode
     * @param loc Location of mouse
     */
    public void onMouseClicked(Posn loc) {
        //easy
        if ( (loc.x < (midwidth + 50)) && (loc.x > (midwidth - 50))
                && (loc.y < (midheight - 100 + 25)) && (loc.y > (midheight - 100 - 25))){
            this.world = new AsteroidGame(2);
            this.world.bigBang(width, height, speed);
        //medium
        } else if ((loc.x < (midwidth + 50)) && (loc.x > (midwidth - 50))
                && (loc.y < (midheight + 25)) && (loc.y > (midheight - 25))){
            this.world = new AsteroidGame(3);
            this.world.bigBang(width, height, speed);
        //hard
        } else if ((loc.x < (midwidth + 50)) && (loc.x > (midwidth - 50))
                && (loc.y < (midheight + 100 + 25)) && (loc.y > (midheight + 100 - 25))){
            this.world = new AsteroidGame(4);
            this.world.bigBang(width, height, speed);
        }
    }
    
}
