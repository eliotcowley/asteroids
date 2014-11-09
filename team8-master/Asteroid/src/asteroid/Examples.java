/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroid;

import java.awt.Color;
import java.util.ArrayList;
import javalib.colors.*;
import javalib.impworld.World;
import javalib.worldimages.*;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;
import tester.Tester;

/**
 *
 * @author elcowley
 */
public class Examples {
    // class constants
    public int width = 800; //width of the canvas
    public int height = 800; //height of the canvas
    public int midheight = height / 2;
    public int midwidth = width / 2;
    public Posn midposn = new Posn((width / 2), (height / 2));
    private final int small = 5;
    private final int medium = 10;
    private final int large = 20;
    
    // Example data for tests
    Posn center = new Posn(midwidth, midheight);
    Posn origin = new Posn(0, 0);
    
    IColor black = new Black();
    
    Asteroid a1 = new Asteroid(small, origin, black, 1, 1);
    Asteroid a2 = new Asteroid(small, new Posn(1, 1), black, 1, 1);
    Asteroid a3 = new Asteroid(small, new Posn(2, 2), black, 1, 1);
    Asteroid a4 = new Asteroid(small, origin, black, 1, 0);
    Asteroid a5 = new Asteroid(small, new Posn(1, 0), black, 1, 0);
    Asteroid a6 = new Asteroid(small, new Posn(width + 10, 0), black, 0, 0);
    Asteroid a7 = new Asteroid(small, new Posn(0, height + 10), black, 0, 0);
    Asteroid a8 = new Asteroid(small, new Posn(-10, 0), black, 0, 0);
    Asteroid a9 = new Asteroid(small, new Posn(0, -10), black, 0, 0);
    
    WorldImage a1image = new DiskImage(origin, small, black);
    WorldImage a2image = new DiskImage(new Posn(1, 1), small, black);
    WorldImage a3image = new DiskImage(new Posn(2, 2), small, black);
    
    AsteroidGame agame = new AsteroidGame(2);
    AsteroidGame agametick = new AsteroidGame(2);
    AsteroidGame ashotfired = new AsteroidGame(2);
    AsteroidGame agunright = new AsteroidGame(2);
    AsteroidGame agunleft = new AsteroidGame(2);
    AsteroidGame ashipfor = new AsteroidGame(2);
    AsteroidGame ashipback = new AsteroidGame(2);
    AsteroidGame aplayinggame = new AsteroidGame(2);
    AsteroidGame apausedgame = new AsteroidGame(2);
    AsteroidGame aquitgame = new AsteroidGame(2);
    
    Ship ship0 = new Ship();
    Ship ship1 = new Ship();
    Ship ship15 = new Ship();
    Ship ship0r = new Ship();
    Ship ship0l = new Ship();
    Ship ship0f = new Ship();
    Ship ship0b = new Ship();
    Ship shipfor = new Ship();
    Ship shipback = new Ship();
    Ship shipdrift = new Ship();
    Ship shipdrift2 = new Ship();

    Gun gun0 = new Gun(center, Ship.radius);
    Gun gun1 = new Gun(center, Ship.radius);
    Gun gun15 = new Gun(center, Ship.radius);
  
    Laser laser0 = new Laser(new Posn(423, 400), 23, 0);
    Laser laser1 = new Laser(new Posn(421, 409), 23, 0);
    Laser laser15 = new Laser(new Posn(421, 391), 23, 0);
    
    WorldImage laserImage = new CircleImage(new Posn(423, 400), 3, new Green());
    WorldImage laserImage2 = new CircleImage(new Posn(423, 400), 3, new Blue());
    WorldImage laserImage3 = new CircleImage(new Posn(423, 400), 3, new Yellow());
    WorldImage laserImage4 = new CircleImage(new Posn(423, 400), 3, new Red());
    
    /**
     * Reset the example data
     */
    void reset() {
        
        Posn center = new Posn(midwidth, midheight);

        a1 = new Asteroid(small, origin, black, 1, 1);
        a2 = new Asteroid(small, new Posn(1, 1), black, 1, 1);
        a3 = new Asteroid(small, new Posn(2, 2), black, 1, 1);
        a4 = new Asteroid(small, origin, black, 1, 0);
        a5 = new Asteroid(small, new Posn(1, 0), black, 1, 0);

        a1image = new DiskImage(origin, small, black);
        a2image = new DiskImage(new Posn(1, 1), small, black);
        a3image = new DiskImage(new Posn(2, 2), small, black);

        agame = new AsteroidGame(2);
        
        this.agametick = this.agame;
            this.agametick.ship.drift();
            for (int i = 0; i < this.agametick.asteroids.size(); i++) {
                this.agametick.asteroids.get(i).drift();
                this.agametick.asteroids.get(i).wrap();
            }
            for (int i = 0; i < this.agametick.lasers.size(); i++) {
                this.agametick.lasers.get(i).drift();
                this.agametick.lasers.get(i).outsideOfScreen();
            }
            //slow down the ship when the player isn't pressing a key
            if ( this.agametick.ship.xVelocity > 0) {
                 this.agametick.ship.xVelocity -= 1;
            }
            else if ( this.agametick.ship.xVelocity < 0) {
                 this.agametick.ship.xVelocity += 1;
            }
            if ( this.agametick.ship.yVelocity > 0) {
                 this.agametick.ship.yVelocity -= 1;
            }
            else if ( this.agametick.ship.yVelocity < 0) {
                 this.agametick.ship.yVelocity += 1;
            }
  
        this.ship0r = new Ship();
        this.ship0l = new Ship();
        this.shipdrift = new Ship();
        this.shipdrift.xVelocity = 4;
        this.shipdrift.location = new Posn (404, 400);
        this.shipdrift.gun.gunloc = new Posn(427, 400);
        this.shipdrift.gun.reGun(this.shipdrift.location);
        this.shipdrift2.location = new Posn(396, 396);
        ship0 = new Ship();
        ship1 = new Ship();
        ship15 = new Ship();
        ship0f = new Ship();
        ship0b = new Ship();
        shipfor = new Ship();
        shipback = new Ship();
      
        this.shipfor.gun.reGun(this.shipfor.location);
        this.shipfor.xVelocity = 4;
        this.shipfor.yVelocity = (int) (this.shipfor.yVelocity - ((this.shipfor.location.y - this.shipfor.gun.gunloc.y) * Ship.accel));
            
        this.shipback.xVelocity = -4;
        this.shipback.yVelocity = (int) (this.shipback.yVelocity + ((center.y - this.shipback.gun.gunloc.y) * Ship.accel));           
            
        this.gun0 = gun0;
            this.gun0.curpos = 0;
        this.gun1 = gun1;
            this.gun1.curpos = 1;
            this.gun1.gunloc = this.gun0.positions.get(this.gun1.curpos);
        this.gun15 = gun15;
            this.gun15.curpos = 15;
            this.gun15.gunloc = this.gun0.positions.get(this.gun15.curpos);

        this.laser0 = new Laser(new Posn(423, 400), 23, 0);
        this.laser1 = new Laser(new Posn(221, 209), 21, 9);
        this.laser15 = new Laser(new Posn(121, 491), 21, -9);
        
        ship1.location = new Posn(200, 200);
            ship1.gun = this.gun1;
            ship1.gun.reGun(this.ship1.location);
        ship15.location = new Posn(100, 500);
            ship15.gun = this.gun15;
            ship15.gun.reGun(this.ship15.location);
        
        this.ashotfired = new AsteroidGame(2);
            this.ashotfired.lasers.add(laser0);
        this.agunright = new AsteroidGame(2);
            this.agunright.ship.gun.gunloc = new Posn(421, 409);
            this.agunright.ship.gun.curpos = 1;
        this.agunleft = new AsteroidGame(2);
            this.agunleft.ship.gun.gunloc = new Posn(421, 391);
            this.agunleft.ship.gun.curpos = 15;
        this.ashipfor = new AsteroidGame(2);
            this.ashipfor.ship = this.shipfor;
        this.ashipback = new AsteroidGame(2);
            this.ashipback.ship = this.shipback;
        this.apausedgame = new AsteroidGame(2);
            this.apausedgame.pause = true;
            this.apausedgame.resume = false;
        this.aquitgame = new AsteroidGame(2);
            this.aquitgame.quit = true;
    }

    /*
     * ASTEROIDGAME TESTS
     */
    
    /**
     * Test the makeImage method in the AsteroidGame class
     */
    void testmakeImage(Tester t) {
        reset();
        WorldImage space = new RectangleImage(new Posn(agame.midwidth, agame.midheight), agame.width, agame.height, Color.black);
        for (int i = 0; i < agame.lasers.size(); i++) {
            space = new OverlayImages(space, agame.lasers.get(i).laserImage());
        }
        space = new OverlayImages(space, agame.ship.shipImage());
        for (int i = 0; i < agame.asteroids.size(); i++) {
            space = new OverlayImages(space, agame.asteroids.get(i).asteroidImage());
        }
        for (int i = 0; i < agame.health; i++){
            space = new OverlayImages(space, 
                        new RectangleImage(new Posn(15+i*11, 15),
                            10, 10, new Green()));
        }
        t.checkExpect(agame.makeImage(), space, "test makeImage - initial rendering");
        
        reset();
        space = new RectangleImage(new Posn(agametick.midwidth, agametick.midheight), agametick.width, agametick.height, Color.black);
        for (int i = 0; i < agametick.lasers.size(); i++) {
            space = new OverlayImages(space, agametick.lasers.get(i).laserImage());
        }
        space = new OverlayImages(space, agametick.ship.shipImage());
        for (int i = 0; i < agametick.asteroids.size(); i++) {
            space = new OverlayImages(space, agametick.asteroids.get(i).asteroidImage());
        }
        for (int i = 0; i < agametick.health; i++){
            space = new OverlayImages(space, 
                        new RectangleImage(new Posn(15+i*11, 15),
                            10, 10, new Green()));
        }
        t.checkExpect(agametick.makeImage(), space, "test makeImage - rendering after one tick");
        
    }
    
    /**
     * Test the onTick method in the AsteroidGame class
     */
    void testonTick(Tester t) {
        reset();
        agame.onTick();
        t.checkExpect(agame, agametick, "test onTick - one tick after initial state");
    }
    
    /**
     * Test the lastImage method in the AsteroidGame class
     */
    void testlastImage(Tester t) {
        reset();
        t.checkExpect(this.agame.lastImage("GAME OVER", 40, Color.RED), 
                      new OverlayImages(agame.makeImage(), new TextImage(midposn,"GAME OVER", 40, Color.RED)), "test lastImage - hit q in initial scene");
        
        reset();
        t.checkExpect(this.agame.lastImage("GAME OVER", 40, Color.RED), 
                      new OverlayImages(agame.makeImage(), new TextImage(midposn,"GAME OVER", 40, Color.RED)), "test lastImage - ship runs out of hitpoints");
        
        reset();
        t.checkExpect(this.agame.lastImage("YOU WIN", 80, Color.CYAN), 
                      new OverlayImages(agame.makeImage(), new TextImage(midposn,"YOU WIN", 80, Color.CYAN)), "test lastImage - user wins");
    }
    /**
     * Test the worldEnds method in the AsteroidGame class
     */
    void testworldEnds(Tester t) {
        reset();
        this.agame.quit = true;
        t.checkExpect(this.agame.worldEnds(),
                new WorldEnd(true, agame.lastImage("GAME OVER", 40, Color.RED)), "test worldEnds - hit q in initial scene");
        
        reset();
        this.agame.health = 0;
        t.checkExpect(this.agame.worldEnds(),
                new WorldEnd(true, agame.lastImage("GAME OVER", 50, Color.RED)), "test worldEnds - no hitpoints left");
        
        reset();
        this.agame.asteroids.clear();
        t.checkExpect(this.agame.worldEnds(),
                new WorldEnd(true, agame.lastImage("YOU WIN", 80, Color.CYAN)), "test worldEnds - clear space, you win");
    }    
    
    /**
     * Test the onKeyEvent method in the AsteroidGame class
     */        
    void testonKeyEvent(Tester t) {
        this.reset();
        this.agame.onKeyEvent("right");
        t.checkExpect(this.agame,
                this.agunright, "test onKeyEvent - right " + "\n");
        
        this.reset();
        this.agame.onKeyEvent("left");
        t.checkExpect(this.agame,
                this.agunleft, "test onKeyEvent - left " + "\n");
        
        this.reset();
        this.agame.onKeyEvent("down");
        t.checkExpect(this.agame, 
                this.ashipfor, "test onKeyEvent-thrustForward-down " + "\n");
        
        this.reset();
        this.agame.onKeyEvent("up");
        t.checkExpect(this.agame,
                this.ashipback, "test onKeyEvent-thrustBackward- up " + "\n");
        
        this.reset();
        this.agame.onKeyEvent("p");
        t.checkExpect(this.agame,
                this.apausedgame, "test onKeyEvent - pause " + "\n");
        
        this.reset();
        this.apausedgame.onKeyEvent("p");
        t.checkExpect(this.apausedgame,
                this.agame, "test onKeyEvent - resume " + "\n");
        
        this.reset();
        this.agame.onKeyEvent("q");
        t.checkExpect(this.agame,
                this.aquitgame, "test onKeyEvent - quit " + "\n");
        
        this.reset();
        this.agame.onKeyEvent("");
        t.checkExpect(this.agame,
                this.ashotfired, "test onKeyEvent - spacebar " + "\n");
    } 
        
    /**
     * Test the removeAsteroid method in the AsteroidGame class
     */
    void testRemoveAsteroid(Tester t) {
        reset();
        agame.removeAsteroid(agame.asteroids.get(0));
        t.checkExpect(agame.asteroids.size(), 1);
        
        reset();
        agame.removeAsteroid(agame.asteroids.get(1));
        agame.removeAsteroid(agame.asteroids.get(0));
        t.checkExpect(agame.asteroids.size(), 0 );
    }
    
    
    /*
     * ASTEROID TESTS
     */
    
    /**
     * Test the drift method in the Asteroid class
     */
    void testDrift(Tester t) {
        reset();
        a1.drift();
        t.checkExpect(a1, a2, "test drift\n");

        reset();
        a2.drift();
        t.checkExpect(a2, a3, "test drift\n");

        reset();
        a4.drift();
        t.checkExpect(a4, a5, "test drift\n");
    }

    /**
     * Test the asteroidImage method in the Asteroid class
     */
    void testAsteroidImage(Tester t) {
        reset();
        t.checkExpect(a1.asteroidImage(), a1image, "test asteroidImage\n");

        reset();
        t.checkExpect(a2.asteroidImage(), a2image, "test asteroidImage\n");

        reset();
        t.checkExpect(a3.asteroidImage(), a3image, "test asteroidImage\n");
    }

    /**
     * Test the collide method in the Asteroid class
     */
    void testCollide(Tester t) {
        reset();
        t.checkExpect(a1.collide(new Posn(0, 4), small), true, "test collide\n");

        reset();
        t.checkExpect(a1.collide(new Posn(0, 10), small), false, "test collide\n");

        reset();
        t.checkExpect(a1.collide(a2.location, small), true, "test collide\n");
    }

    /**
     * Test the outsideOfScreen method in the Asteroid class
     */
    void testExplode(Tester t) {
        reset();
        agame.asteroids.get(0).explode();
        t.checkExpect(agame.asteroids.size(), 3, "test explode\n");

        reset();
        agame.asteroids.get(0).explode();
        agame.asteroids.get(0).explode();
        t.checkExpect(agame.asteroids.size(), 4, "test explode\n");

        reset();
        agame.asteroids.get(0).explode();
        agame.asteroids.get(0).explode();
        agame.asteroids.get(0).explode();
        t.checkExpect(agame.asteroids.size(), 5, "test explode\n");
    }

    void testWrap(Tester t) {
        reset();
        a6.wrap();
        t.checkExpect(a6.location, new Posn(0, 0),
                "test wrap horizontal, right side of screen\n");

        reset();
        a7.wrap();
        t.checkExpect(a7.location, new Posn(0, 0),
                "test wrap vertical, bottom of screen\n");

        reset();
        a8.wrap();
        t.checkExpect(a8.location, new Posn(width, 0),
                "test wrap horizontal, left side of screen\n");

        reset();
        a9.wrap();
        t.checkExpect(a9.location, new Posn(0, height),
                "test wrap vertical, top of screen\n");
    }


    /*
     * SHIP TESTS
     */

    /**
     * Test the shipImage method in the Ship class
     */
    void testshipImage(Tester t) {
        reset();
        t.checkExpect(this.ship0.shipImage(), 
                new OverlayImages(
                    new OverlayImages(new DiskImage(
                        new Posn(midwidth, midheight), 
                        20, Color.LIGHT_GRAY),
                    new RectangleImage(new Posn(midwidth, midheight), 
                    10, 10, Color.CYAN)), new DiskImage(
                        ship0.gun.gunloc, 3, Color.RED)),
                "test shipImage method in Ship class");
    }

    /**
     * Test the drift method in the Ship class
     */
    void testshipdrift(Tester t) {
        reset();
        this.ship0.xVelocity = 4;
        this.ship0.drift();
        t.checkExpect(this.ship0, this.shipdrift, "test Ship Class drift, one tick of forward movement");
        
        reset();
        this.ship0.xVelocity = -4;
        this.ship0.yVelocity = -4;
        this.ship0.drift();
        t.checkExpect(this.ship0.location, this.shipdrift2.location, "test Ship Class drift, stationary object remains so");
    }
    /**
     * Test the shoot method in the Ship class
     */
    void testshoot(Tester t) {
        reset();
        t.checkExpect(this.ship0.shoot(), this.laser0, "test Ship Class shoot, from initial position\n");
        
        reset();
        t.checkExpect(this.ship1.shoot(), this.laser1, "test Ship Class shoot, from ship 1\n");
        
        reset();
        t.checkExpect(this.ship15.shoot(), this.laser15, "test Ship Class shoot, from ship 15\n");
    }

    /**
     * Test the thrustForward method in the Ship class
     */
    void testthrustForward(Tester t) {
        reset();
        this.ship0.thrustForward();
        t.checkExpect(this.ship0, this.shipfor, "test Ship Class thrustForward, from initial position\n");
        
    }

    /**
     * Test the thrustBackward method in the Ship class
     */
    void testthrustBackward(Tester t) {
        reset();
        this.ship0.thrustBackward();
        t.checkExpect(this.ship0, this.shipback, "test Ship Class thrustBackward, from initial position\n");
    }



    /*
     * GUN TESTS
     */
    /**
     * Test the gunImage method in the Gun class
     */
    void testgunImage(Tester t) {
        reset();
        t.checkExpect(this.gun0.gunImage(), new DiskImage(this.gun0.gunloc, this.gun0.gunradius, Color.RED), "test Gun Class gunImage, from initial position\n");
    }

    /**
     * Test the drift method in the Gun class
     */
    void testgundrift(Tester t) {
        reset();
        this.ship0.gun.drift(midposn, 4, 0);
        this.ship0.gun.reGun(this.shipdrift.location);
        t.checkExpect(this.ship0.gun, this.shipdrift.gun, "test Ship Class drift, from initial position");

    }
    /**
     * Test the reGun method in the Gun class
     */
    void testreGun(Tester t) {
        reset();
        this.gun0.reGun(this.shipdrift.location);
        t.checkExpect(this.gun0, this.shipdrift.gun, "test Gun Class reGun, from initial position\n");
    }
    /**
     * Test the turnLeft method in the Gun class
     */
    void testturnRight(Tester t) {
        reset();
        this.gun0.turnRight();
        this.gun0.reGun(this.midposn);
        this.gun1.reGun(this.midposn);
        t.checkExpect(this.gun0, this.gun1, "test Gun Class turnRight, from initial position\n");
    }
    /**
     * Test the turnLeft method in the Gun class
     */
    void testturnLeft(Tester t) {
        reset();
        this.gun0.turnLeft();
        this.gun0.reGun(this.midposn);
        this.gun15.reGun(this.midposn);
        t.checkExpect(this.gun0, this.gun15, "test Gun Class turnLeft, from initial position\n");
    }

    /** LASER TESTING METHODS */
    
    void testLaserImage(Tester t) {
        reset();
        t.checkOneOf(this.laser0.laserImage(),
            laserImage, laserImage2, laserImage3, laserImage4,
            "test Laser Class laserImage method\n");
    }
    
    void testLaserDrift(Tester t) {
        reset();
        laser0.xVelocity = 4;
        laser0.yVelocity = 4;
        laser0.drift();
        t.checkExpect(laser0.location, new Posn((427), (404)), "test Laser Class drift method\n");
    }
    
    void testLaserDestroy(Tester t) {
        reset();
        agame.lasers.add(laser0);
        agame.ship = ship0;
        laser0.destroy();
        t.checkExpect(agame.lasers.size(), 0, "test Laser class destroy method\n");
        t.checkExpect(agame.ship.shots, 6, "test Laser class destroy method\n");
    }
    
    void testLaserOutsideScreen(Tester t) {
        reset();
        agame.lasers.add(laser0);
        laser0.location = new Posn(-100, 0);
        laser0.outsideOfScreen();
        t.checkExpect(agame.lasers.size(), 0, "test Laser class outsideOfScreen method\n");
    }
    
    /** RandomColorGenerator class method testing */
    
    void testRandomColor(Tester t) {
        reset();
        t.checkOneOf(RandomColorGenerator.randomColor(), new Green(), new Blue(), new Red(), new Yellow(), 
                "test RandomColorGenerator class randomColor method");
    }
    
    /** RandomPosnGenerator class method testing */
    
    void testRandomPosn(Tester t) {
        reset();
        t.checkOneOf(RandomPosnGenerator.randomPosn(), new Posn(200, 200),
                          new Posn(200, 600),
                          new Posn(600, 600),
                          new Posn(600, 200), "test RandomPosnGenerator class randomPosn method\n");
    }
    
    /** RandomVelocityGenerator class method testing */
    
    void testRandomVel(Tester t) {
        reset();
        int randVel = RandomVelocityGenerator.randomVel();
        t.checkNumRange(randVel, -10, 10, "test RandomVelocityGenerator class randomVel method, check that value is between -10 and 10\n");
        t.checkFail(randVel, 0, "test RandomVelocityGenerator class randomVel method, check that value is not 0\n");
    }
    
    /** PointsOnCircle class method testing */
    
    void testGetNPointsOnCircle(Tester t) {
        reset();
        ArrayList<Posn> points = new ArrayList<Posn>();
        points.add(new Posn(420, 400));
        points.add(new Posn(400, 420));
        points.add(new Posn(380, 400));
        points.add(new Posn(400, 380));
        t.checkExpect(PointsOnCircle.getNPointsOnCircle(midposn, 20, 4),
                points,
                "test getNPointsOnCircle method in PointsOnCircle class\n");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Examples b = new Examples();
        Tester.runReport(b, true, false);
        MainMenu w = new MainMenu();
        //AsteroidGame w = new AsteroidGame(2);
        w.bigBang(800, 800, 0.1);

    }
}
