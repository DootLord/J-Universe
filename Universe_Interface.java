import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Universe_Interface{
    ArrayList<Space_Object> objectList = new ArrayList<Space_Object>();
    int universeSelection = 0;
    Calendar cal;
    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
    Scanner in = new Scanner(System.in);

    /**
     * Calls the startMenu method to start the menu graphics.
     */
    Universe_Interface(){
        startMenu();
    }

    /**
     * Gives the interface the latest details on the objects in the universe.
     */
    void update(ArrayList<Space_Object> objectListInput){
        objectList = objectListInput;
    }

    /**
     * Gives the user an interface to select what type of universe they wish to run.
     */
    void startMenu(){
        System.out.println("*------------------------------------*");
        System.out.println("|                                    |");
        System.out.println("|         Universe Interface         |");
        System.out.println("|                                    |"); 
        System.out.println("*------------------------------------*");
        System.out.println();
        System.out.println("*-----------Universe Select----------*");
        System.out.println("1) General Universe");
        System.out.println("2) Collision Test Universe");
        System.out.println("3) Comet 02 Test Universe");
        System.out.println("4) More Infomation");
        
        switch(in.nextInt()){
            case 1: universeSelection = 1;
            break;
            case 2: universeSelection = 2;
            break;
            case 3: universeSelection = 3;
            break;
            case 4: printMoreInfo();
            break;
            default: System.out.println("Invalid Selection");
            startMenu();
            break;
        }
    }

    /**
     * Prints out details about the options available to the user.
     */
    void printMoreInfo(){
        System.out.println("This is to act as an interface between the universe and");
        System.out.println("you, so that you don't have to constantly interact with bluej.");
        System.out.println();
        System.out.println("General Universe (1) creates a universe that looks much like a regular");
        System.out.println("universe, with planets, stars, blackholes and comets going about and doing their thing");
        System.out.println();
        System.out.println("Collision Test Universe is used to show off the collision interactions between objects");
        System.out.println();
        System.out.println("Comet 02 Test Universe is use to see how that comets behaves upon interaction of edges,");
        System.out.println("as it's hard to observe in a regular universe due to the comet rarely touching an edge ");
        System.out.println("due to it's behaviour");
        System.out.println("After selecting a universe, logging mode will start showing collisions and interactions between objects");
        in.next();
        startMenu();
    }

    /**
     * Prints messages to do with comets being absorbed by stars
     * 
     * @param Star Space_Object that abosrbed the comet
     * @param Comet Space_object that has just been aborbed by the star
     */
    void printCometDestructionStar(Space_Object Star, Space_Object Comet){
        Calendar cal = Calendar.getInstance();

        System.out.println(time.format(cal.getTime()) + ": " + Comet.getName() + " was abosrbed by the star " + Star.getName());
        System.out.println();
    }

    /**
     * Prints messages to do with comets bouncing off other comets
     */
    void printBounceInfo(Space_Object mainObject, Space_Object compareObject){
        Calendar cal = Calendar.getInstance();

        System.out.println(time.format(cal.getTime()) + ": " + mainObject.getName() + " and " + compareObject.getName() + " bounced off eachother");
        System.out.println();
    }

    /**
     * Prints message to the user to do with comets absorbing other comets
     * 
     * @param destoryedComet a Space_Object that has just been destroyed and will be absorbed by absorbObject
     * @param absorbObject a Space_Object that will increase its mass by the diameter of the Space_Object
     */

    void printConsumeInfo(Space_Object destroyedComet, Space_Object absorbObject){
        Calendar cal = Calendar.getInstance();
        int diameterAdded = destroyedComet.getDiameter();

        System.out.println(time.format(cal.getTime()) + ": " + destroyedComet.getName() + " was destroyed by " + absorbObject.getName() + " causing it's diameter to increase by ");
        System.out.println(destroyedComet.getDiameter() + " giving it a new diamter of " + (absorbObject.getDiameter() + destroyedComet.getDiameter()));
        System.out.println();
    }

    /**
     * Prints a message to the user about a comet being absorbed by blackHole Space_Object
     * 
     * @param blackHole the Space_Object that has just abosrbed absorbObject
     * @param aborbObject the Space_Object that has just been destroyed by blackHole
     * 
     */
    void printBlackConsumeInfo(Space_Object blackHole, Space_Object absorbObject){
        Calendar cal = Calendar.getInstance();

        System.out.println(time.format(cal.getTime()) + ": " + blackHole.getName() + " absorbed " + absorbObject.getName());
        System.out.println();

    }

    /**
     * Prints a message to the user about a comet being destroyed by another comet
     * 
     * @param powerComet the more powerful comet, determened by speed or size
     * @param destroyedComet the less powerful comet, that was just destroyed
     */
    void printCometDestroyInfo(Space_Object powerComet, Space_Object destroyedComet){
        Calendar cal = Calendar.getInstance();

        System.out.println(time.format(cal.getTime()) + ": " + powerComet.getName() + " destroyed " + destroyedComet.getName());
        System.out.println();
    }

    /**
     * Prints a message to the user when two comets have just destroyed eachother
     */
    void printCometDestroyEachInfo(Space_Object cometOne, Space_Object cometTwo){
        Calendar cal = Calendar.getInstance();

        System.out.println(time.format(cal.getTime()) + ": " + cometOne.getName() + " and " + cometTwo.getName() + " destroyed eachother");
        System.out.println();
    }

    /**
     * Returns the selection the user made when the menu was presented
     */
    int getSelection(){
        return universeSelection;
    }
}