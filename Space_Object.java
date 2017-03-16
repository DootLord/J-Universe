import java.awt.*;

/**
 * TODO: Black Hole Attraction
 * TODO: Documentation
 * TODO: Interface
 */

/**
 * Class Space_Object - an object that exists in a finite Universe. The object bounces off
 * the bottom edge of the universe.
 * 
 * Movement can be initiated by repeated calls to the "move" method.
 * 
 * N.B This class is incomplete and still under development. It will require updating to
 * complete the INTPROG coursework assignment.
 * 
 * @author Robert Topp
 *
 * @version 2016.01.22
 */

public class Space_Object
{
    private String name; // Name of the object. Used in interface.
    private Color color; // Defines Color of object (Overwrited if Sun or Black hole)
    private int diameter; // Size of the object
    private int xPosition; 
    private int yPosition;
    // Area space of the universe the object is in
    private  int groundPosition = 0; 
    private  int roofPosition = 0;
    private  int rightPosition = 0;
    private  int leftPosition = 0;
    private Universe universe; // Universe the object is in
    private int xSpeed;                 // current horizontal speed   (+  moving left to right, - right to left
    private int ySpeed;                // current vertical speed ( + moving down, - moving up)
    private int lifeTime; // time till death
    private boolean isExist = true; // Will be removed if false
    private int objectType; //Will be used for indicating what type of object it is (0 comet, 1 planet, 2 star, 3 blackhole)
    private int cometType;  //Used only for comets. As comets will have unique propertys about them, this value will indicate what properys and features they'll take on.
    private double iterationChance; // Used for deciding the chance of a comet peforming it's unique movement function.

    /**
     * Constructor for objects of class Space_Object
     *
     * @param xPos  the horizontal coordinate of the object
     * @param yPos  the vertical coordinate of the object
     * @param xVel  the horizontal speed of the object
     * @param yVel  the vertical speed of the object
     * @param objectDiameter  the diameter (in pixels) of the object
     * @param objectColor  the color of the object
     * @param type  the type of object it is (0 comet, 1 planet, 2 star, 3 black hole)
     * @param theUniverse  the universe this object is in
     */
    public Space_Object(int xPos, int yPos, int xVel, int yVel, int objectDiameter, Color objectColor, int type, double itrChance , Universe theUniverse, String setName)
    {
        xPosition = xPos;
        yPosition = yPos;
        xSpeed = xVel;
        ySpeed = yVel;
        color = objectColor;
        diameter = objectDiameter;
        universe = theUniverse;
        if(theUniverse != null){
        groundPosition = universe.getGround();
        roofPosition = universe.getRoof();
        rightPosition = universe.getRight();
        leftPosition = universe.getLeft();
       }
        objectType = type;
        iterationChance = itrChance;
        name = setName;
        // Overwriting Colors for objects
        if(type == 0){ //comets
            lifeTime = 500;
        }
        else if(type == 1){ // planets
            lifeTime = 2000;
        }
        else if(type == 2){ // stars
            color = Color.YELLOW;
            xSpeed = 0;
            ySpeed = 0;
            lifeTime = 10000;
        }
        else if(type == 3){ // black holes
            color = Color.BLACK;
            xSpeed = 0;
            ySpeed = 0;
            lifeTime = 100000;
        }
    }

    /**
     * Move this object according to its position and speed and redraw.
     * 
     * If statements apply movement in different ways depending on the Space_Object type
     * Comet types are "0 for default, 1 for Speed and Color and 2 for Direction and Size"
     * Each comet has a defined chance in the constructor to trigger their special property when traveling, and has an alternitive trigger when hitting an edge.
     * Move also handles if an object should exist or not.
     **/
    public void move()
    {
        
        universe.erase(this); // Used in a step for replacing position, as well as removing destoryed objects from the universe.

        if(isExist){
            if(lifeTime <= 0){ // Lifetime Control
                isExist = false;
            }
            else{
                lifeTime--;
            }

            if(objectType == 0){ // Will apply movement only if the object is a comet

                if(cometType == 1 && Math.random() > iterationChance){ //Special movement for comet type 1
                    changeSpeed();
                }
                else if(cometType == 2 && Math.random() > iterationChance){
                    sizeScale();
                    step();
                }
                else{
                   step();
                }

                if(yPosition >= (groundPosition - diameter) && ySpeed > 0) { // This block controls edge control
                    yPosition = groundPosition - diameter; // Blocks are repeated due to unique varables required depending on what wall is touched
                    ySpeed = -ySpeed;
                    if(cometType == 1){
                        colorCycle();
                    }
                    else if (cometType == 2){
                        changeDirection();
                    }
                }

                if(xPosition >= (rightPosition - diameter) && xSpeed > 0) {
                    xPosition = rightPosition - diameter;
                    xSpeed = -xSpeed;
                    if(cometType == 1){
                        colorCycle();
                    }
                    else if (cometType == 2){
                        changeDirection();
                    }
                }

                if(xPosition <= (leftPosition + diameter) && xSpeed < 0) {
                    xPosition = leftPosition + diameter;
                    xSpeed = -xSpeed;
                    if(cometType == 1){
                        colorCycle();
                    }
                    else if (cometType == 2){
                        changeDirection();
                    }
                }

                if(yPosition <= (roofPosition + diameter) && ySpeed < 0) {
                    yPosition = roofPosition + diameter;
                    ySpeed = -ySpeed;
                    if(cometType == 1){
                        colorCycle();
                    }
                    else if (cometType == 2){
                        changeDirection();
                    }
                }
            }

            // draw again at new position
            universe.draw(this);

        }
    }    

    /**
     * To be called when the Space_Object is intended to be a planet.
     * Planets will need to have a parameter passed to them so they know what planet to orbit around
     * 
     * @param Star object that the Space_Object(Planet) will orbit around.
     */
    public void movePlanet(Space_Object star)
    {

        universe.erase(this);

        if(isExist == true){
            int newXPos;
            int newYPos;
            int starX = star.getXPosition();
            int starY = star.getYPosition();
            if(lifeTime <= 0){
                isExist = false;
            }
            else{
                lifeTime--;
            }

            // A mathmatical box around each object effectively.
            newXPos = (int) ( starX +( xPosition - starX ) * Math.cos(0.1) - ( yPosition - starY ) * Math.sin(0.1));
            newYPos = (int) ( starX +( xPosition - starX ) * Math.sin(0.1) + ( yPosition - starY ) * Math.cos(0.1));

            setXPosition(newXPos);
            setYPosition(newYPos);
            // redraw at new position
            universe.draw(this);

        }
    }    

    /**
     * A method called to a Space_Object after creation to give it unique propertys that will take place in the move() method.
     * 0 = No Alterations
     * 1 = Speed will change during travel and colour will change on wall touch.
     * 2 = Direction will constantly change and Size will change on wall touch.
     * 
     */
    public void setCometType(int enteredCometType){
        //Check to see if the object is a comet
        if(objectType == 0){
            //Check to see if the user entered a valid comet type
            if(enteredCometType == 0 || enteredCometType == 1 || enteredCometType == 2){
                cometType = enteredCometType;
            }
            // Check to ensure that a valid comet type has been entered.
            else{
                System.out.println("Please enter a valid comet type. (0 for default, 1 for Speed and Color and 2 for Direction and Size)"); 
            }
        }
    }
    

    /**
     * Cycles through colours for a comet
     */

    public void colorCycle(){
            if(color == Color.RED){
                color = Color.BLUE;
            }
            else if( color == Color.BLUE){
                color = Color.CYAN;
            }
            else if( color == Color.CYAN){
                color = Color.GRAY;
            }
            else if(color == Color.GRAY){
                color = Color.PINK;
            }
            else{
                color = Color.RED;
            }
    }

    /**
     * Generates a new size value for a Space_Object.
     */

    public void sizeScale(){
        if(diameter > 40){
            diameter = 10;
        }
        else{
            diameter = diameter + 5;
        }
    }
    /**
     * Adds the amount of ySpeed and xSpeed to the current position of the movement to make it move
     */
    public void step(){
        yPosition += ySpeed;
        xPosition += xSpeed;
    }
    /**
     * changes the direction of the comet by inverting the direction
     */
    public void changeDirection(){
        int xSpeedOld = xSpeed - xSpeed * 2;
        xSpeed = ySpeed;
        ySpeed = xSpeedOld;
        
        if(Math.random() > 0.5){
            xSpeed = xSpeed + 2;
            ySpeed = ySpeed - 2;
        }
    }
    /**
     * speeds up and slows down the object randomly when called
     */
    public void changeSpeed(){
        xPosition += xSpeed * Math.random() * 5;
        yPosition += ySpeed * Math.random() * 5;
    }

    /**
     * return the horizontal position of this object
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this object
     */
    public int getYPosition()
    {
        return yPosition;
    }

    /**
     * set the horizontal position of the object
     */
    public void setXPosition(int newX){
        xPosition = newX;
    }

    /**
     * set the vertical position of the object
     */
    public void setYPosition(int newY){
        yPosition = newY;
    }

    /**
     * return the colour of this object
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * return the diameter of this object
     */
    public int getDiameter()
    {
        return diameter;
    }

    /**
     * get the Radius of this object
     */
    public int getRadius(){
        return diameter / 2;
    }

    /**
     * get the exist state of this object
     */
    public boolean getExist(){
        return isExist;
    }

    /**
     * set exist state of this object
     */
    public void setExist(boolean existState){
        isExist = existState;
    }

    /**
     * get the object type of this object (Represented as an integer. 0 = comet, 1 = planet, 2 = star , 3 = black hole)
     */
    public int getType(){
        return objectType;
    }

    /**
     * get the horizontal velocity of this object
     */
    public int getXVelocity(){
        return xSpeed;
    }

    /**
     * get the vertical velocity of this object
     */
    public int getYVelocity(){
        return ySpeed;
    }

    /**
     * set horizontal velocity of this object
     */
    public void setXVelocity(int xVel){
        xSpeed = xVel;
    }

    /**
     * set vertical velocity of this object
     */
    public void setYVelocity(int yVel){
        ySpeed = yVel;
    }

    /**
     * set diameter of this object
     */
    public void setDiameter(int setDiameter){
        diameter = setDiameter;
    }

    /**
     * set name of this object
     */

    public void setName(String setName){
        name = setName;
    }

    /**
     * get name of this object
     */

    public String getName(){
        return name;
    }

    /**
     * set lifetime of this object
     */

    public void setLifeTime(int setLife){
        lifeTime = setLife;
    }

    /**
     * get lifetime of this object
     */
    public int getLifeTime(){
        return lifeTime;
    }

    /**
     * returns the comet type
     */
    public int getCometType(){
        return cometType;
    }
}
