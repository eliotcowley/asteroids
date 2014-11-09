/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroid;
import javalib.colors.IColor;
import javalib.worldimages.*;

/**
 *
 * @author elcowley
 */

/** Generic asteroid */
public class Asteroid {
    //class constants
    private final int small = AsteroidGame.small;   // Small asteroid
    private final int medium = AsteroidGame.medium; // Medium asteroid
    private final int large = AsteroidGame.large;   // Large asteroid
    /** fields */
    private int size;     // Size of asteroid
    public Posn location; // Coordinates of asteroid
    private IColor color;      // Color of asteroid
    private int xVelocity;     // X velocity
    private int yVelocity;        // Y velocity;
    
    
    /** Constructor for the Asteroid class
     * @param size Size of the asteroid
     * @param location Location of the asteroid
     * @param color Color of the asteroid
     * @param xVelocity X velocity of the asteroid
     * @param yVelocity Y velocity of the asteroid
     */
    public Asteroid(int size, Posn location, IColor color, int xVelocity, int yVelocity) {
        // Initialize fields
        this.size = size;
        this.location = location;
        this.color = color;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }
    
    /** Move the asteroid */
    public void drift() {
        location = new Posn(location.x+xVelocity, location.y+yVelocity);
    }
    
    /** Display the asteroid image
     * @return WorldImage of asteroid
     */
    public WorldImage asteroidImage() {
        return new DiskImage(location, size, color);
    }
    
    /** Check if the asteroid collides with another object
     * @param loc Location of the other object
     * @param radius Radius of the other object
     * @return Boolean, true if the asteroid collides with the other object, 
     *          false otherwise
     */
    public boolean collide(Posn loc, int radius) {
        return (Math.sqrt(Math.pow((loc.x-this.location.x), 2) + 
                Math.pow((loc.y-this.location.y), 2)) < 
                (radius + this.size));
    }
    
    /** Explode the asteroid: if it is a small asteroid, remove it; if it is a
     * medium asteroid, create two small ones; if it is a large asteroid,
     * create two medium ones
     */
    public void explode() {
        switch(this.size) {
            case small: AsteroidGame.asteroids.remove(this); break;
            case medium: 
                Asteroid a1 = new Asteroid(small, 
                                            location, 
                                            color, 
                                            RandomVelocityGenerator.randomVel(), 
                                            RandomVelocityGenerator.randomVel());
                Asteroid a2 = new Asteroid(small, 
                                            location, 
                                            color, 
                                            RandomVelocityGenerator.randomVel(), 
                                            RandomVelocityGenerator.randomVel());
                AsteroidGame.asteroids.add(a1);
                AsteroidGame.asteroids.add(a2);
                AsteroidGame.asteroids.remove(this);
                break;
            case large: 
                Asteroid a3 = new Asteroid(medium, 
                                            location, 
                                            color, 
                                            RandomVelocityGenerator.randomVel(), 
                                            RandomVelocityGenerator.randomVel());
                Asteroid a4 = new Asteroid(medium, 
                                            location, 
                                            color, 
                                            RandomVelocityGenerator.randomVel(), 
                                            RandomVelocityGenerator.randomVel());
                AsteroidGame.asteroids.add(a3);
                AsteroidGame.asteroids.add(a4);
                AsteroidGame.asteroids.remove(this);
                break;
        }
    }
    
    /** Wrap the asteroid around the screen */
    public void wrap() {
        if (this.location.x > AsteroidGame.width) {
            this.location.x = 0;
        }
        else if (this.location.x < 0) {
            this.location.x = AsteroidGame.width;
        }
        if (this.location.y < 0) {
            this.location.y = AsteroidGame.height;
        }
        else if (this.location.y > AsteroidGame.height) {
            this.location.y = 0;
        }
    }
}
