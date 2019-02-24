import org.newdawn.slick.SlickException;

/**
 * The class for Long log.
 */
public class LongLog extends Displacer{

    // Constant for LongLog speed
    private static final float SPEED = 0.07f;

    /**
     * Instantiates a new Long log.
     *
     * @param imageSrc  the image src
     * @param x         the x position
     * @param y         the y position
     * @param moveRight the boolean for the direction right
     * @throws SlickException the slick exception
     */
    public LongLog(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc,x,y,SPEED,moveRight);
    }
}
