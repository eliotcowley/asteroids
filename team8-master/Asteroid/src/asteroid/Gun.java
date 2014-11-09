/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroid;

import java.awt.Color;
import java.util.ArrayList;
import javalib.worldimages.CircleImage;
import javalib.worldimages.DiskImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

/**
 *
 * @author meebs
 */

/**
 * Class containing methods pertaining to laser cannon object, which the player shoots with
 * <spacebar>, and fly out of the ship
 */
public class Gun {
    
    //class constants
    public static final int resolution = 16;    //the number of possible rotations the gun can occupy around the ship
    public static final int gunradius = 3; // the radius of the gun orb
    Posn gunloc; // location of gun
    Color color; //color of the ship's gun
    int curpos; // index value of gun in ArrayList positions
    int shipradius; // radius of ship
    ArrayList<Posn> positions; // positions around circle (ship)

    /**
     * Constructor for ship's Gun
     *
     * @param center Location of the laser
     * @param radius Direction of the laser
     */
    public Gun(Posn center, int radius) {
        this.curpos = 0;
        this.color = Color.RED;
        this.positions = PointsOnCircle.getNPointsOnCircle(center, (radius + gunradius), resolution);
        this.shipradius = radius;
        this.gunloc = positions.get(curpos);
    }

    /**
     * Draw gun image
     * @return WorldImage of gun
     */
    public WorldImage gunImage() {
        return new DiskImage(this.gunloc, gunradius, this.color);
    }

    /**
     * Drift the gun with the ship
     * @param center Posn of center of ship
     * @param dx X direction of gun's movement
     * @param dy Y direction of gun's movement
     */
    public void drift(Posn center, int dx, int dy) {
        //apply respective velocities to the current position
        this.gunloc = new Posn((this.gunloc.x + dx),
                               (this.gunloc.y + dy));
        this.positions = PointsOnCircle.getNPointsOnCircle(center, (shipradius + gunradius), resolution);
    }

    /**
     * Reposition the gun (used in places like wrapping)
     * @param center Center of the ship
     */
    public void reGun(Posn center){
        this.positions = PointsOnCircle.getNPointsOnCircle(center, (shipradius + gunradius), resolution);
        this.gunloc = this.positions.get(curpos);
    }
    
    /**
     * Turn the gun right around the ship
     */
    public void turnRight() {
        this.curpos = ((this.curpos + 1) % resolution);
        this.gunloc = this.positions.get(curpos);
    }

    /**
     * Turn the gun left around the ship
     */
    public void turnLeft() {
        if (this.curpos == 0) {
            this.curpos = resolution - 1;
        } else {
            this.curpos = ((this.curpos - 1) % resolution);
        }
        this.gunloc = this.positions.get(curpos);
    }
    
    /** Wrap the gun around the screen */
    void wrap() {
        if (this.gunloc.x > AsteroidGame.width) {
            this.gunloc.x = 0;
        }
        else if (this.gunloc.x < 0) {
            this.gunloc.x = AsteroidGame.width;
        }
        if (this.gunloc.y < 0) {
            this.gunloc.y = AsteroidGame.height;
        }
        else if (this.gunloc.y > AsteroidGame.height) {
            this.gunloc.y = 0;
        }
    }
}