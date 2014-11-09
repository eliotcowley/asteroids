/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package asteroid;

import java.util.*;
import javalib.colors.Blue;
import javalib.colors.Green;
import javalib.colors.IColor;
import javalib.colors.Red;
import javalib.colors.Yellow;

/**
 * Represents a random color
 */
public class RandomColorGenerator {

    private static Random generator = new Random();

    private static List <IColor> colors =
            Arrays.asList(new Red(), new Yellow(), new Blue(), new Green());
    
    /**
     * Return a random color
     * @return a random IColor (red, yellow, blue, green)
     */
    public static IColor randomColor() {
        return colors.get( generator.nextInt( colors.size() ) );
    }

}
