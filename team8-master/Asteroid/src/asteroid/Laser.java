/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package asteroid;

import javalib.colors.IColor;
import javalib.worldimages.CircleImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

/**
 *
 * @author meebs
 */

/** Class containing methods pertaining to Laser object
 * <spacebar>, and fly out of the ship
 */
public class Laser {
    //laser parameters
    double shotvelocity = 1; // Speed of laser
    Posn location;  // Location of laser
    int xVelocity;  // X Velocity of laser
    int yVelocity;  // Y Velocity of laser
    int radius;     // Radius of laser

    /** Constructor for Lasers
     * @param loc Location of the laser
     * @param dx X velocity of the laser
     * @param dy Y velocity of the laser
     */
    public Laser(Posn loc, int dx, int dy){
        this.location = loc;
        this.yVelocity = (int) (dy * shotvelocity);
        this.xVelocity = (int) (dx * shotvelocity);
        this.radius = 3;
    }

    /** Draws image of laser on screen
     * @return WorldImage of laser
     */
    public WorldImage laserImage(){
        return new CircleImage(this.location, this.radius, RandomColorGenerator.randomColor());
    }

    /** Drift the laser */
    public void drift(){
        //apply respective velocities to the current position
        this.location = new Posn((this.location.x + this.xVelocity),
                                 (this.location.y + this.yVelocity));
    }
    
    /** Destroy the laser */
    public void destroy(){
        AsteroidGame.lasers.remove(this);
        Ship.shots++;
    }
    
    /** Check if the laser is outside of the screen, and if it is, destroy it */
    public void outsideOfScreen() {
        if (location.x < 0 || location.x > AsteroidGame.width || location.y < 0 
                || location.y > AsteroidGame.height) {
            destroy();
        }
    }
}
