/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package asteroid;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javalib.worldimages.Posn;

/**
 *
 * @author mibeauregard
 */

/** Generate a random velocity for asteroids */
public class RandomVelocityGenerator {
   
    private static Random generator = new Random();
    private static final int ASTEROID_SPEED = 9;

    /**
     * Return a random velocity for a new asteroid
     * @return A random int between -10 and 10 (but not 0)
     */ 
    public static int randomVel() {
        boolean neg = generator.nextBoolean();
        if (neg) {
            return -1*(generator.nextInt(ASTEROID_SPEED)+1);
        }
        else {
            return (generator.nextInt(ASTEROID_SPEED)+1);
        }
    }

}
