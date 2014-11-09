/*
 * Program that computes the points on a circle.
 * Author: Marc Smith
 * For: Mike Beauregard and Eliot Cowley
 * Spring 2013: cs203 
 * 
 * Based on algorithm found at URL:
 * http://board.flashkit.com/board/showthread.php?773919-trying-to-find-coordinates-for-points-around-a-circle
 * 
 */
package asteroid;


import javalib.worldimages.Posn;
import java.util.ArrayList;


class PointsOnCircle {
  
  /**
   * Computes the coordinates of the given n evenly spaced points, 
   * around a circle of the given radius, at the given center coordinates.
   * @param center Center of the circle (ship)
   * @param radius Radius of the circle (ship)
   * @param n How many evenly-spaced points around the circle you want (resolution)
   * @return ArrayList<Posn> of the points on the circle (ship)
   */
  public static ArrayList<Posn> getNPointsOnCircle(Posn center, int radius, int n)
  {
    ArrayList<Posn> points = new ArrayList<Posn>();    
    double alpha = Math.PI * 2 / n;
    
    int i = -1;
    while( ++i < n )
    {
      double theta = alpha * i;
      int x = (int) Math.round( Math.cos( theta ) * radius );
      int y = (int) Math.round( Math.sin( theta ) * radius );
      Posn pointOnCircle = new Posn( center.x + x, center.y + y );
      points.add( pointOnCircle );
    }
    
    return points;
    
  }
  

}