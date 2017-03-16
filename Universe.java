import java.awt.Color;
import java.util.*;
import java.lang.Math;

/**
 * Class Universe - a universe that is used to contain all the Space_Object objects and allow them to interact with one another via collision and rotation.
 *
 * @author Robert Topp & Shaun Porter
 * @version 2016.3.10
 */

// If comet speed + size is the same then both comets are destroyed
// If comet size is bigger than another, then smaller is destroyed
// Only planets increase their size on absorbing a comet.
// Calculate Speed properly

public class Universe  
{
    private Canvas universe;
    private int leftEdge = 0;     // coordinates of the edges of the universe
    private int topEdge = 0;
    private int rightEdge = 0;
    private int bottomEdge = 0;
    private boolean testMode;
    Universe_Interface universe_interface;

    /**
     * Opens the interface and gives the user the ability to select a universe to run.
     */
    public Universe(){
        int uniSelect;
        universe_interface = new Universe_Interface();
        uniSelect = universe_interface.getSelection();
        switch(uniSelect){
            case 1:
            createUniverseGeneral();
            case 2:
            createUniverseCollision();
            case 3:
            createCometTwoTest();

        }
    }

    /**
     *  Create a universe with given name and size.
     *  
     *  To be used in testing
     *  @param name The name to give the universe
     *  @param rightEdge The maximum x coordinate
     *  @param bottomEdge The maximum y coordinate
     */
    public Universe(String name, int rightEdge, int bottomEdge)
    {
        universe = new Canvas(name, rightEdge, bottomEdge);
        this.rightEdge = rightEdge;
        this.bottomEdge = bottomEdge;
        testMode = true;
    }

    /**
     * Erase an object from the view of the universe
     * 
     * @param spaceObj The object to be erased
     */
    public void erase(Space_Object spaceObj)
    {
        universe.eraseCircle(spaceObj.getXPosition(), spaceObj.getYPosition(), spaceObj.getDiameter());
    }

    /**
     * Draw an object at its current position onto the view of the universe.
     * 
     * @param spaceObj The object to be drawn
     */
    public void draw(Space_Object spaceObj)
    {
        universe.setForegroundColor(spaceObj.getColor());
        universe.fillCircle(spaceObj.getXPosition(), spaceObj.getYPosition(), spaceObj.getDiameter());
    }

    /**
     * Demo for collision between space objects.
     */
    public void createUniverseCollision(){
        ArrayList<Space_Object> objectList = new ArrayList<Space_Object>();
        rightEdge = 600;
        bottomEdge = 600;
        universe = new Canvas("Collison Test", rightEdge, bottomEdge);
        // Creating Comets
        Space_Object comet01 = new Space_Object(50, 400, 0, -2, 20, Color.RED, 0, 0.33,this, "comet01");
        Space_Object comet02 = new Space_Object(50, 200, 0, 2, 20, Color.BLUE, 0, 0.33, this, "comet02");
        Space_Object comet03 = new Space_Object(100, 400, 0, -2, 20, Color.RED, 0, 0.33, this, "comet03");
        Space_Object comet04 = new Space_Object(100, 200, 0, 2, 40, Color.BLUE, 0, 0.33, this,"comet04");
        Space_Object comet05 = new Space_Object(150, 400, 0, -2, 20, Color.RED, 0, 0.33, this, "comet05");
        Space_Object comet06 = new Space_Object(300, 200, 0, 2, 20, Color.GREEN, 0, 0.33, this, "comet06");
        Space_Object blackHole01 = new Space_Object(340, 400, 0 , 0 , 30 , Color.BLUE,  3, 0.33, this, "blackHole01");
        Space_Object comet01T1 = new Space_Object (200, 400, 0, 2, 20, Color.GREEN, 0, 0.33, this, "comet01T1");
        Space_Object comet01T2 = new Space_Object (250, 400, 0, 10, 20, Color.GREEN, 0, 0.33, this, "comet01T2");
        Space_Object star01 = new Space_Object(150, 200, 0, 0, 25, Color.PINK, 2, 0.33, this,  "star01");
        //Assigning comet sub-types
        comet01T1.setCometType(1);
        comet01T2.setCometType(2);
        //Creating Object Arraylist. This will be used later for collison checking
        objectList.add(comet01);
        objectList.add(comet02);
        objectList.add(comet03);
        objectList.add(comet04);
        objectList.add(comet05);
        objectList.add(comet06);
        objectList.add(blackHole01);
        objectList.add(comet01T1);
        objectList.add(comet01T2);
        objectList.add(star01);

        // Calls method to run move frames and to check collision.
        runSim(objectList);
    }

    /**
     * A test universe for testing the functionality of the type two comet.
     */
    public void createCometTwoTest(){
        ArrayList<Space_Object> objectList = new ArrayList<Space_Object>();
        rightEdge = 200;
        bottomEdge = 200;
        universe = new Canvas("Comet Two Test", rightEdge, bottomEdge);
        // Creating Comets
        Space_Object comet01T2 = new Space_Object(50,50, 10,10, 20, Color.RED, 0, 0.33, this, "comet01T2");
        //Assigning comet sub-types
        comet01T2.setCometType(2);
        //Creating Object Arraylist. This will be used later for collison checking
        objectList.add(comet01T2);

        runSim(objectList);
    }

    /**
     * An actual universe, with regular space objects existing all at once.
     */
    public void createUniverseGeneral(){
        rightEdge = 1000;
        bottomEdge = 1000;
        universe = new Canvas("Collison Test", rightEdge, bottomEdge);
        // Creating Comets
        Space_Object comet01 = new Space_Object(100, 200, -2, -0, 10, Color.RED, 0, 0.33,this, "comet01");
        Space_Object comet02 = new Space_Object(100, 400, 2, 1, 15, Color.GREEN, 0, 0.33, this, "comet02");
        Space_Object comet03 = new Space_Object(300, 500, -2, 1, 10, Color.GREEN, 0, 0.33, this, "comet03");
        Space_Object planet01 = new Space_Object(300,380, 0,0, 20, Color.GREEN, 1, 0.33, this, "planet01");
        Space_Object star01 = new Space_Object(300,300,0,0, 30, Color.YELLOW, 2, 0.33, this, "star02");
        Space_Object blackhole01 = new Space_Object(450,550, 0,0, 25, Color.PINK, 3, 0.33, this, "blackhole01");
        //Assigning comet sub-types
        comet01.setCometType(1);
        comet02.setCometType(2);
        //Creating Object Arraylist. This will be used later for collison checking
        ArrayList<Space_Object> objectList = new ArrayList<Space_Object>();
        objectList.add(comet01);
        objectList.add(comet02);
        objectList.add(comet03);
        objectList.add(planet01);
        objectList.add(star01);
        objectList.add(blackhole01);

        runSim(objectList);
    }

    /**
     * starts the simulation.
     * assigns stars to planets in the order they're visited in the ArrayList
     * 
     * @param objectList The objects in a universe to be checked for collision and to run the move or movePlanet method
     */
    public void runSim(ArrayList<Space_Object> objectList){
        boolean finished =  false;
        Space_Object selectStar = null;

        while(!finished) {
            universe.wait(50);
            for(int i = 0; i < objectList.size(); i++){
                Space_Object s = objectList.get(i);
                if(s.getType() == 2){
                    selectStar = s;
                }
                if(s.getType() == 1){
                    if(selectStar != null){
                        s.movePlanet(selectStar);
                    }
                    else if(testMode == true){ // If testmode is true, then a planet does not require a sun.
                        s.move();
                    }
                }
                else{
                    s.move();
                }
            }
            checkCollision(objectList);
            //universe_interface.update(objectList);
        }
    }

    /**
     * Used to check for collision between each object inside the universe and give behaviour to each Space_Object depending on their type when collisions occour.
     * 
     * @param objectList - Each object in the universe comprised of Space_Objects
     */
    public void checkCollision(ArrayList<Space_Object> objectList){
        int compareIndex = 0;
        int obj = 0;
        while(compareIndex != objectList.size()) {
            for(obj = 0;obj < objectList.size();obj++){
                Space_Object selectedObject = objectList.get(compareIndex); //
                Space_Object compareObject = objectList.get(obj);

                int selectedObjectX = selectedObject.getXPosition(); // Getting common infomation about the currently selected object to compare
                int selectedObjectY = selectedObject.getYPosition();
                int selectedObjectRadius = selectedObject.getRadius();
                int selectedObjectType = selectedObject.getType();
                int selObjVel = Math.abs(selectedObject.getXVelocity() + selectedObject.getYVelocity()); // Getting velocity speeds

                int compareObjectX = compareObject.getXPosition(); // Getting common infomation about the next space_object to have the main object compared to 
                int compareObjectY = compareObject.getYPosition();
                int compareObjectRadius = compareObject.getRadius();
                int compareObjectType = compareObject.getType();
                int comObjVel = Math.abs(compareObject.getXVelocity() + compareObject.getYVelocity());

                if(selectedObjectType == 3){  // if a space_object type is a blackhole, it's effective radius is increased by three times
                    selectedObjectRadius = selectedObjectRadius * 3;
                }
                else if(compareObjectType == 3){
                    compareObjectRadius = compareObjectRadius * 3;
                }

                if(compareIndex != obj){ //Checking to make sure that the object won't compare itself.
                    if(selectedObject.getExist() && compareObject.getExist()){ // Checking to see if both objects exist in the universe
                        if(compareObjectSpace(selectedObject, compareObject, selectedObjectRadius, compareObjectRadius )){ // Collision used to check if a Space_Object has touched another Space_Objects collision area.
                            // if in comparing object in collision, if one object is a star/sun. The other object is removed from the universe 
                            if(selectedObjectType == 0 && compareObjectType == 3){ // Blackhole and comets collision
                                suckIntoHole(compareObject,  selectedObject);
                            }
                            else if(selectedObjectType == 2 && compareObjectType == 0){ // Comet to sun collision.
                                compareObject.setExist(false); 
                                selectedObject.setLifeTime(selectedObject.getLifeTime() + (compareObjectRadius / 2)); // Adding diameter to lifetime of Star
                                if(testMode == false){
                                    universe_interface.printCometDestructionStar(selectedObject,compareObject);
                                }
                            }
                            else if(selectedObjectRadius > compareObjectRadius && compareObjectType == 0 && selectedObjectType == 1){ // if one object is larger than another and one object is planet and comet
                                compareObject.setExist(false); // Then remove the smaller object
                                if(testMode == false){
                                    universe_interface.printConsumeInfo(compareObject, selectedObject);
                                }
                                selectedObject.setDiameter(selectedObject.getDiameter() + compareObject.getDiameter());
                            }
                            else if(selectedObjectRadius < compareObjectRadius && selectedObjectType == 0 && compareObjectType == 1){
                                selectedObject.setExist(false);
                                compareObject.setDiameter(selectedObject.getDiameter() + compareObject.getDiameter());
                                if(testMode == false){
                                    universe_interface.printConsumeInfo(selectedObject, compareObject);
                                }

                            } 
                            else if(selectedObjectType == 0 && compareObjectType == 0){

                                if( selectedObject.getCometType() != compareObject.getCometType()){ //If one comet is bigger than the other, smaller one is removed.
                                    if(selectedObjectRadius > compareObjectRadius){
                                        compareObject.setExist(false);
                                        if(testMode == false){
                                            universe_interface.printCometDestroyInfo(selectedObject,compareObject);
                                        }
                                    }
                                    else if(selectedObjectRadius < compareObjectRadius){
                                        selectedObject.setExist(false);
                                        if(testMode == false){
                                            universe_interface.printCometDestroyInfo(compareObject,selectedObject);
                                        }
                                    }
                                    else{
                                        if(selObjVel > comObjVel){
                                            compareObject.setExist(false);
                                            if(testMode == false){
                                                universe_interface.printCometDestroyInfo(selectedObject,compareObject);
                                            }
                                        }
                                        else if(selObjVel < comObjVel){
                                            selectedObject.setExist(false);
                                            if(testMode == false){
                                                universe_interface.printCometDestroyInfo(compareObject,selectedObject);
                                            }
                                        }
                                        else{ // if comets are same size + speed
                                            selectedObject.setExist(false);
                                            compareObject.setExist(false);
                                            if(testMode == false){
                                                universe_interface.printCometDestroyInfo(selectedObject, compareObject);
                                            }
                                        }
                                    }
                                }
                                else if(selectedObject.getCometType() == compareObject.getCometType() ){ // If comets are of the same type
                                    if(testMode == false){
                                        universe_interface.printBounceInfo(selectedObject, compareObject);
                                    }
                                    bounce(selectedObject);
                                    bounce(compareObject);
                                }
                            }
                        }
                    }
                }
            }
            compareIndex += 1;  //Increasing the index that's used to check each object with another
        }
    }

    /**
     * Compares the position of two objects to check for collision.  
     * 
     * @param selectObject the object selected to be collided with
     * @param compareObject the object that will be compared in this collision
     * @param selectedObjectRadiusIn the effective radius of selectedObject
     * @param compareObjectRadiusIn the effective radius of compareObject
     */
    //selectedObjectRadius in and compareObjectRadiusIn are used for blackhole collisions. As their effective radiusis larger than their actual radius
    boolean compareObjectSpace(Space_Object selectObject, Space_Object compareObject, int selectedObjectRadiusIn, int compareObjectRadiusIn){ 
        int selectedObjectRadius = selectedObjectRadiusIn;
        int selectedObjectX = selectObject.getXPosition();
        int selectedObjectY = selectObject.getYPosition();
        int compareObjectRadius = compareObjectRadiusIn;
        int compareObjectX = compareObject.getXPosition();
        int compareObjectY = compareObject.getYPosition();

        // acts as a box around the circle of a space_object. If one box is over another, this will return true.
        if( selectedObjectX + selectedObjectRadius + compareObjectRadius > compareObjectX &&
        selectedObjectX < compareObjectX + selectedObjectRadius + compareObjectRadius &&
        selectedObjectY + selectedObjectRadius + compareObjectRadius > compareObjectY &&
        selectedObjectY < compareObjectY + selectedObjectRadius + compareObjectRadius){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Controls the interaction between comets and blackholes when their effective collision areas overlap.
     * 
     * @param blackHole the blackhole Space_Object that will attract and destory absorbObject
     * @param absorbObject Space_Object that will be abosrbed into black hole
     */
    void suckIntoHole(Space_Object blackHole, Space_Object absorbObject){
        int blackHoleX = blackHole.getXPosition();
        int blackHoleY = blackHole.getYPosition();
        int blackHoleRadius = blackHole.getRadius();
        int abosrbObjectX = absorbObject.getXPosition();
        int absorbObjectY = absorbObject.getYPosition();
        int absorbObjectRadius = absorbObject.getRadius();
        absorbObject.setCometType(0);

        if(abosrbObjectX > blackHoleX){
            absorbObject.setXVelocity(-2);
        }
        else if(abosrbObjectX < blackHoleX){
            absorbObject.setXVelocity(2);
        }

        if(absorbObjectY > blackHoleY){
            absorbObject.setYVelocity(-2);
        }
        else if(absorbObjectY < blackHoleY){
            absorbObject.setYVelocity(2);
        }

        if(compareObjectSpace(blackHole, absorbObject,blackHoleRadius,absorbObjectRadius)){
            absorbObject.setExist(false);
            if(testMode != true){
                universe_interface.printBlackConsumeInfo(blackHole,absorbObject);
            }
        }

    }

    /**
     * Used to check if two Space_Objects are moving at the same speed
     * 
     * @param spaceObjOne a Space_Object that will be compared to spaceObjTwo in terms of speed 
     * @param spaceObjTwo a Space_Object that will be compared to spaceObjOne in terms of speed 
     */
    boolean sameVelocity(Space_Object spaceObjOne, Space_Object spaceObjTwo){
        int objOneVel = Math.abs(spaceObjOne.getXVelocity() + spaceObjOne.getYVelocity());
        int objTwoVel = Math.abs(spaceObjTwo.getXVelocity() + spaceObjTwo.getYVelocity());
        if(objOneVel == objTwoVel){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Return the y cordinate of the bottom of the universe
     */
    public int getGround()
    {
        return bottomEdge;
    }

    /**
     * Return the y cordinate of the top of the universe
     */
    public int getRoof(){
        return topEdge;
    }

    /**
     * Return the x cordinate of the right side of the universe
     */
    public int getRight(){
        return rightEdge;
    }

    /**
     * Return the x cordinate of the left side of the universe
     */
    public int getLeft(){
        return leftEdge;
    }

    /**
     * Bounces the Space_Object off somthing by inverting it's velocity
     */
    public void bounce(Space_Object selectedObject){
        selectedObject.setXVelocity(-selectedObject.getXVelocity());
        selectedObject.setYVelocity(selectedObject.getYVelocity()  - selectedObject.getYVelocity() * 2);
        selectedObject.move();
    }
}
