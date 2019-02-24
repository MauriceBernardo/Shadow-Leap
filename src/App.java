/**
 * Sample Project for SWEN20003: Object Oriented Software Development 2018
 * by Eleanor McMurtry, University of Melbourne
 * modified by Maurice Bernardo Edwin, University of Melbourne
 */

import org.newdawn.slick.*;

/**
 * Main class for the game.
 * Handles initialisation, input and rendering.
 */
public class App extends BasicGame {

    // Constant for Screen Sizes and position
    public static final int SCREEN_WIDTH = 1024;
    public static final int SCREEN_HEIGHT = 768;
    public static final int ORIGIN_X = 0;
    public static final int ORIGIN_Y = 0;

    // Attributes for exit status
    private static boolean exit = false;

    // Attributes for world ending status
    private static boolean worldEnd = false;

    // Attributes for the world level
    private static int level = 0;

    // Attributes of app
    private World world;

    /**
     * Instantiates a new App.
     */
    public App() {
        super("Shadow Leap");
    }

    @Override
    public void init(GameContainer gc)
            throws SlickException {
        world = new World("assets/levels/"+ level + ".lvl");

    }

    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();

        // Update the world
        world.update(input, delta);

        // Exit the world if the exit status is true
        if (exit){
            gc.exit();
        }

        // Go to next level and create a new world if the world ended
        if (worldEnd){
            level++;
            if(level == 2){
                gc.exit();
            } else {
                world = new World("assets/levels/" + level + ".lvl");
                worldEnd = false;
            }
        }

    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g)
            throws SlickException {
        world.render(g);
    }

    /**
     * Quit app method
     */
    public static void exit(){
       exit = true;
    }

    /**
     * End the world method
     */
    public static void worldEnd(){
        worldEnd = true;
    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
            throws SlickException {
        AppGameContainer app = new AppGameContainer(new App());
        app.setShowFPS(false);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }

}