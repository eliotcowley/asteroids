/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package asteroid;

import java.util.*;
import javalib.worldimages.Posn;

/** Generates a random Posn */
public class RandomPosnGenerator {
   
    private static Random generator = new Random();

    private static List <Posn> posns =
            Arrays.asList(new Posn(200, 200),
                          new Posn(200, 600),
                          new Posn(600, 600),
                          new Posn(600, 200));
    
    /**
     * Return a random Posn for a new Asteroid
     * @return a random Posn
     */
    public static Posn randomPosn() {
        Posn posn = posns.get( generator.nextInt( posns.size() ) );
        return posn;
    }

}


