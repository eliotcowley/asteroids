package asteroid;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.awt.Color;
import javalib.colors.Black;
import javalib.colors.Blue;
import javalib.colors.Green;
import javalib.colors.White;
import javalib.colors.Yellow;
import javalib.worldimages.*;
import javalib.impworld.World;


/**
 *
 * @author meebs
 */

/** The AsteroidGame World class */
public class AsteroidGame extends World {
    // class constants
    public static final int width = 800; //width of the canvas
    public static final int height = 800; //height of the canvas
    public int midheight = height / 2;
    public int midwidth = width / 2;
    public Posn midposn = new Posn((width / 2), (height / 2));
    public static final int small = 10; // small asteroid
    public static final int medium = 20;    // medium asteroid
    public static final int large = 40; // large asteroid
    
    //fields
    public int difficulty; // the number of asteroids which the game begins with
    public int health = 3;  // represents the ship's hit points
    public Ship ship; // the instance of the Ship
    public static ArrayList<Asteroid> asteroids;  // the Asteroid objects
    public static ArrayList<Laser> lasers;  // the Laser objects
    public boolean pause;   // true, if the game is paused
    public boolean resume;  // true, if game is being played
    public boolean quit;    // true, if game is quit
    private RandomPosnGenerator posnGenerator;
    
    /** Constructor for the AsteroidGame class
     * @param dif Difficulty: 0, 1, or 2
     */
    public AsteroidGame(int dif) {
        this.difficulty = dif;
        this.health = health;
        this.ship = new Ship();
        this.asteroids = new ArrayList<Asteroid>();
        for (int i = 0; i < this.difficulty; i++) {
            this.asteroids.add(new Asteroid(large,
                                            posnGenerator.randomPosn(),
                                            RandomColorGenerator.randomColor(),
                                            RandomVelocityGenerator.randomVel(), 
                                            RandomVelocityGenerator.randomVel()));
        }
        this.lasers = new ArrayList<Laser>(3);
        this.pause = false;
        this.resume = true;
        this.quit = false;
        
    }

    /** Draw the world
     * @return WorldImage
     */
    public WorldImage makeImage() {
        //Image accumulator
        WorldImage space = new RectangleImage(new Posn(midwidth, midheight), width, height, Color.black);
        //laser shot render
        for (int i = 0; i < lasers.size(); i++) {
            space = new OverlayImages(space, this.lasers.get(i).laserImage());
        }
        //ship render
        space = new OverlayImages(space, this.ship.shipImage());
        //asteroid render
        for (int i = 0; i < asteroids.size(); i++) {
            space = new OverlayImages(space, this.asteroids.get(i).asteroidImage());
        }
        //health meter
        for (int i = 0; i < this.health; i++){
            space = new OverlayImages(space, 
                        new RectangleImage(new Posn(15+i*11, 15),
                            10, 10, new Green()));
        }
        return space;
    }

    /** Update the game */
    public void onTick() {
        //if paused, hold the ship, asteroids, and lasers in position, and disable turning
        if (!pause) {
            boolean[] ast = new boolean[this.asteroids.size()]; // Array of asteroids to be marked for removal
            boolean[] laz = new boolean[this.lasers.size()]; // Array of lasers to be marked for removal
            // Drift the ship, check if it should wrap around the screen
            this.ship.drift();
            for (int i = 0; i < this.asteroids.size(); i++) {
                // Drift the asteroids, check if they collide with the ship
                this.asteroids.get(i).drift();
                this.asteroids.get(i).wrap();
                /** If the ship collides with an asteroid, decrease the health,
                 * turn the ship black, set its x and y velocities to 0, make it 
                 * invincible, and set a timer to return it to normal in 3 
                 * seconds */
                if(!this.ship.invincible){
                if (this.asteroids.get(i).collide(this.ship.location, this.ship.radius)) {
                    health -= 1;
                    this.ship.invincible = true;
                    this.ship.color = Color.BLACK;
                    this.ship.ccolor = Color.BLACK;
                    this.ship.gun.color = Color.BLACK;
                    this.ship.xVelocity = 0;
                    ship.yVelocity = 0;
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            //ship.invincible = false;
                            ship.color = Color.LIGHT_GRAY;
                            ship.ccolor = Color.CYAN;
                            ship.gun.color = Color.RED;
                            ship.location = midposn;
                            ship.gun.reGun(ship.location);
                            ship.xVelocity = 0;
                            ship.yVelocity = 0;
                            // Give the player one more second of invincibility
                            // so he doesn't immediately die when he reappears
                            // in the center of the screen
                            Timer timer2 = new Timer();
                            timer2.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    ship.invincible = false;
                                }
                            },
                                1*1000);
                        }
                    }, 3*1000);
                }
            }
                for (int j = 0; j < this.lasers.size(); j++) {
                    // Check if the asteroids collide with lasers
                    if (this.asteroids.get(i).collide
                        (this.lasers.get(j).location, this.lasers.get(j).radius)) {
                            // Mark the asteroid and laser for removal
                            ast[i] = true;
                            laz[j] = true;
                    }
                }
            }
            // Remove the marked asteroids
            for (int a = 0; a < ast.length; a++) {
                if (ast[a]) {
                    this.asteroids.get(a).explode();
                }
            }
            // Remove the marked lasers
            for (int l = 0; l < laz.length; l++) {
                if (laz[l]) {
                    this.lasers.get(l).destroy();
                }
            }
            // Drift the lasers and check if they're outside the screen
            for (int i = 0; i < this.lasers.size(); i++) {
                this.lasers.get(i).drift();
                this.lasers.get(i).outsideOfScreen();
            }
            //slow down the ship when the player isn't pressing a key
            if (ship.xVelocity > 0) {
                ship.xVelocity -= 1;
            }
            else if (ship.xVelocity < 0) {
                ship.xVelocity += 1;
            }
            if (ship.yVelocity > 0) {
                ship.yVelocity -= 1;
            }
            else if (ship.yVelocity < 0) {
                ship.yVelocity += 1;
            }
        }
    }
    /**
     * key event handler:
     * right arrow = turns the ship right (1 rotation unit)
     * left arrow = turns the ship left (1 rotation unit)
     * down arrow = fires thrustBackward thruster
     * up arrow = fires thrustForward thruster
     * q = ends the game
     * p = a pause button (1st push pauses game, 2nd push resumes)
     * <space> = fires a laser shot from the ship
     * @param ke Key input
     */
    public void onKeyEvent(String ke) {
        if (ke.equals("right")) {
            this.ship.turnRight();
        } else if (ke.equals("left")) {
            this.ship.turnLeft();
        } else if (ke.equals("down")) {
            this.ship.thrustForward();
        } else if (ke.equals("up")) {
            this.ship.thrustBackward();
        } else if (ke.equals("q")) {
            this.quit = true;
        } else if (ke.equals("p") && this.resume == true) {
            this.pause = true;
            this.resume = false;
        } else if (ke.equals("p") && this.resume == false) {
            this.pause = false;
            this.resume = true;
        } else if (ke.equals(" ") && (this.ship.shots > 0)) {
            this.lasers.add(this.ship.shoot());
            this.ship.shots--;
        }
    }

    /** renders the image to be rendered at the end of the game.
     * @param s     Text to be shown at the end of the game
     * @param size  Size of the text image
     * @param color Color of the text image
     * @return the WorldImage to be rendered at the end of the game.
     */
    public WorldImage lastImage(String s, int size, Color color) {
        return new OverlayImages(this.makeImage(),
                //change size to get right
                new TextImage(midposn, s, size, color));
    }
    
    /**
     * creates the World End when game over conditions are met
     * @return the World End when game over conditions are met
     */
    @Override
    public WorldEnd worldEnds() {
        if (this.asteroids.isEmpty()){
            return new WorldEnd(true, this.lastImage("YOU WIN", 80, Color.CYAN));
        } else if(this.health == 0) {
            return new WorldEnd(true, this.lastImage("GAME OVER", 50, Color.RED));
        } else if (this.quit == true) {
            return new WorldEnd(true, this.lastImage("GAME OVER", 40, Color.RED));
        } else {
            return new WorldEnd(false, this.makeImage());
        }
    }
    
    /** Remove an asteroid from the array and thus the world
     * @param asteroid The asteroid to be removed
     */
    public void removeAsteroid(Asteroid asteroid) {
        asteroids.remove(asteroid);
    }
}
