import org.newdawn.slick.SlickException;

/**
 * The class for Log.
 */
public class Log extends Displacer {

    // Constant for Log speed
    private static final float SPEED = 0.1f;

    /**
     * Instantiates a new Log.
     *
     * @param imageSrc  the image src
     * @param x         the x position
     * @param y         the y position
     * @param moveRight the boolean for the direction right
     * @throws SlickException the slick exception
     */
    public Log(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc,x,y,SPEED,moveRight);
    }
}
