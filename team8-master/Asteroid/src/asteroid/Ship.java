/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package asteroid;
import java.awt.Color;
import java.sql.Array;
import java.util.ArrayList;
import javalib.colors.IColor;
import javalib.worldimages.*;

/**
 *
 * @author meebs
 */

/** Ship that the player controls */
public class Ship {

    // class constants
    public static final int width = 800; // width of the canvas
    public static final int height = 800; //height of the canvas
    public final int midheight = height/2;
    public final int midwidth = width/2;
    public final Posn midposn = new Posn((width/2), (height/2));
    public static int radius = 20;    //radius of the ship
    public static int cockpit = 10; //size of the cockpit rectangle
    public static final double accel = 0.2; //the higher the value, the faster the ship moves
    public static final int MAX_SPEED = 20; //the maximum speed the ship can move

    /** fields */
    public Boolean invincible;  //true if ship has just been hit
    public Color color;      // Color of Ship
    public Color ccolor;     // Color of ship cockpit
    public static int shots;     // a counter to limit 3 live shots at any given time
    public int xVelocity;     // X velocity
    public int yVelocity;     // Y velocity;
    public Posn location;  // location of the ship
    public Gun gun;

    /** Constructor for the Ship class */
    public Ship(){
        this.invincible = false;
        this.color = Color.LIGHT_GRAY;
        this.ccolor = Color.CYAN;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.location = new Posn(midwidth, midheight);
        this.gun = new Gun(this.location, radius);
        this.shots = 5;
    }

    /** Draw the ship image
     * @return WorldImage representing the ship
     */
    public WorldImage shipImage(){
         WorldImage shipimg =
                new OverlayImages(new OverlayImages(new DiskImage(this.location, radius, color),
                                  new RectangleImage(this.location, cockpit, cockpit, ccolor)), this.gun.gunImage());
         return shipimg;
    }

    /** Drift the ship by its current x and y velocities */
    public void drift(){
        //apply respective velocities to the current position
        this.location = new Posn((this.location.x + this.xVelocity),
                                 (this.location.y + this.yVelocity));
        //check for wrap scenarios, adjusts ship and gun position accordingly.
        if (this.location.x > AsteroidGame.width) {
            this.location.x = 0;
            this.gun.reGun(this.location);
        }
        else if (this.location.x < 0) {
            this.location.x = AsteroidGame.width;
            this.gun.reGun(this.location);
        }
        if (this.location.y < 0) {
            this.location.y = AsteroidGame.height;
            this.gun.reGun(this.location);
        }
        else if (this.location.y > AsteroidGame.height) {
            this.location.y = 0;
            this.gun.reGun(this.location);
        }
        
        //apply respective velocities to gun object
        this.gun.drift(this.location, this.xVelocity, this.yVelocity);
    }
   
    /** Shoot a laser from the ship's gun
     * @return A Laser at the Ship's current location, with the Ship's x and y velocities
     */
    public Laser shoot(){
        int dy = (this.gun.gunloc.y - this.location.y);
        int dx = (this.gun.gunloc.x - this.location.x);
        return new Laser(this.gun.gunloc, dx, dy);
    }

    /** Move the Ship in the direction opposite the Gun */
    public void thrustBackward(){
        int dy = (this.location.y - this.gun.gunloc.y);
        int dx = (this.location.x - this.gun.gunloc.x);
        if (Math.abs(xVelocity) < MAX_SPEED) {
            this.xVelocity = (int) (this.xVelocity + (dx * accel));
        }
        if (Math.abs(yVelocity) < MAX_SPEED) {
            this.yVelocity = (int) (this.yVelocity + (dy * accel));
        }
    }
    
    /** Move the Ship in the direction of the Gun */
    public void thrustForward(){
        int dy = (this.location.y - this.gun.gunloc.y);
        int dx = (this.location.x - this.gun.gunloc.x);
        if (Math.abs(xVelocity) < MAX_SPEED) {
            this.xVelocity = (int) (this.xVelocity - (dx * accel));
        }
        if (Math.abs(yVelocity) < MAX_SPEED) {
            this.yVelocity = (int) (this.yVelocity - (dy * accel));
        }
    }  
    
    /** Turn the Gun counterclockwise around the Ship */
    public void turnLeft(){
        //turns gun left, thrusters turn accordingly
        this.gun.turnLeft();
    }
    
    /** Turn the Gun clockwise around the Ship */
    public void turnRight(){
        //turns gun right, thrusters turn accordingly
        this.gun.turnRight();
    }
   
   
}