

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.awt.Color;

/**
 * The test class UniverseTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class UniverseTest
{
    /**
     * Default constructor for test class UniverseTest
     */
    public UniverseTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /**
     * Shows how LifeSpan works by creating a single comet and showing it disspear from the universe
     */
    @Test
    public void testLifeSpan(){
        ArrayList<Space_Object> objectList = new ArrayList<Space_Object>();
        Universe universe = new Universe("Lifespan Test", 200, 200);
        Space_Object comet01 = new Space_Object(100, 100, 0, 0, 20, Color.GREEN, 0,0, universe,  "comet01");
        
        for(int i = 0;i > 50;i++){
            comet01.move();
            assertEquals(0, comet01.getLifeTime());
        }
        
        
    }
    
    /**
     * Demonstrates collision between two comets of the same comet type
     */
    @Test
    public void testTwoCometsSameType(){
        ArrayList<Space_Object> objectList = new ArrayList<Space_Object>();
        Universe universe = new Universe("Comet Test", 200, 200);
        Space_Object comet01 = new Space_Object(50, 50, 0, 2, 20, Color.GREEN, 0,0, universe,  "cometGREEN");
        Space_Object comet02 = new Space_Object(50, 150, 0, -2, 20, Color.RED, 0,0, universe, "cometRED");
        
        objectList.add(comet01);
        objectList.add(comet02);
        universe.runSim(objectList);
    }
    /**
     * Demonstrates collision between different comet types of the same mass and speed
     */
    @Test
    public void testTwoCometDiffType(){
        ArrayList<Space_Object> objectList = new ArrayList<Space_Object>();
        Universe universe = new Universe("Comet Speed Test", 200, 200);
        Space_Object comet01 = new Space_Object(50, 50, 0, 2, 20, Color.GREEN, 0,0, universe,  "cometGREEN");
        Space_Object comet02 = new Space_Object(50, 150, 0, -2, 20, Color.RED, 0,0.5, universe,  "cometRED");
        
        comet02.setCometType(1);
        
        objectList.add(comet01);
        objectList.add(comet02);
        universe.runSim(objectList);
    }
    /**
     * Demonstrates collision between different comet types, with one being larger than the other
     */
    @Test
    public void testTwoCometDiffTypeDiffSize(){
        ArrayList<Space_Object> objectList = new ArrayList<Space_Object>();
        Universe universe = new Universe("Comet Speed Test", 200, 200);
        Space_Object comet01 = new Space_Object(50, 50, 0, 2, 25, Color.GREEN, 0,0, universe,  "cometGREEN");
        Space_Object comet02 = new Space_Object(50, 150, 0, -2, 20, Color.RED, 0,0.5, universe,  "cometRED");
        
        comet02.setCometType(1);
        
        objectList.add(comet01);
        objectList.add(comet02);
        universe.runSim(objectList);
    }
    /**
     * Demonstrates collision between different comet types, with one being faster than the other, but of both equal mass.
     */
    @Test
    public void testTwoCometDiffTypeDiffSpeed(){
        ArrayList<Space_Object> objectList = new ArrayList<Space_Object>();
        Universe universe = new Universe("Comet Speed Test", 200, 200);
        Space_Object comet01 = new Space_Object(50, 50, 0, 4, 20, Color.GREEN, 0,0, universe,  "cometGREEN");
        Space_Object comet02 = new Space_Object(50, 150, 0, -2, 20, Color.RED, 0,0.5, universe,  "cometRED");
        
        comet02.setCometType(1);
        
        objectList.add(comet01);
        objectList.add(comet02);
        universe.runSim(objectList);
    }
    /**
     * Demonstrates collision between a comet and a planet and the change in size
     */
    @Test
    public void testPlanetCollision(){
        ArrayList<Space_Object> objectList = new ArrayList<Space_Object>();
        Universe universe = new Universe("Comet Speed Test", 200, 200);
        Space_Object planet01 = new Space_Object(50, 50, 0, 0, 20, Color.GREEN, 1,0, universe,  "planetGREEN");
        Space_Object comet01 = new Space_Object(50, 150, 0, -2, 10, Color.RED, 0,0, universe, "comet01");
        
        objectList.add(planet01);
        objectList.add(comet01);
        universe.runSim(objectList);
    }
    /**
     * Demonstrates collision between a comet and a star
     */
    @Test
    public void testSunCollision(){
        ArrayList<Space_Object> objectList = new ArrayList<Space_Object>();
        Universe universe = new Universe("Comet Speed Test", 200, 200);
        Space_Object sun01 = new Space_Object(50, 50, 0, 0, 20, Color.GREEN, 2,0, universe,  "sunGREEN");
        Space_Object comet01 = new Space_Object(50, 150, 0, -2, 10, Color.RED, 0,0, universe, "comet01");
        
        objectList.add(sun01);
        objectList.add(comet01);
        universe.runSim(objectList);
    }
    /**
     * Demonstrates the interaction between a black hole and a comet
     */
    @Test
    public void testBlackHoleCollision(){
        ArrayList<Space_Object> objectList = new ArrayList<Space_Object>();
        Universe universe = new Universe("Comet Speed Test", 200, 200);
        Space_Object blackHole01 = new Space_Object(50, 50, 0, 0, 20, Color.GREEN, 3, 0, universe, "blackHoleGREEN");
        Space_Object comet01 = new Space_Object(80, 150, 0, -2, 10, Color.RED, 0, 0, universe, "comet01");
        
        objectList.add(blackHole01);
        objectList.add(comet01);
        universe.runSim(objectList);
    }
    /**
     * Demonstrates the type one comets and it's interaction when moving and touching a wall
     */
    @Test
    public void cometTypesOne(){
        ArrayList<Space_Object> objectList = new ArrayList<Space_Object>();
        Universe universe = new Universe("Comet Speed Test", 200, 200);
        Space_Object comet01 = new Space_Object(50, 50, 2, 0, 20, Color.GREEN, 0, 0.5, universe, "cometTypeOne");
        
        comet01.setCometType(1);
        
        objectList.add(comet01);
        universe.runSim(objectList);
    }
    /**
     * Demonstrates the type tw comets and it's interaction when moving and touching a wall
     */
    @Test
    public void cometTypesTwo(){
        ArrayList<Space_Object> objectList = new ArrayList<Space_Object>();
        Universe universe = new Universe("Comet Speed Test", 200, 200);
        Space_Object comet01 = new Space_Object(50, 50, 5, 0, 20, Color.GREEN, 0, 0.5,universe, "cometTypeTwo");
        
        comet01.setCometType(2);
        
        objectList.add(comet01);
        universe.runSim(objectList);
    }
    
}

