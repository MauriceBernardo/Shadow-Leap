import org.newdawn.slick.SlickException;

/**
 * The class for Bus.
 */
public class Bus extends Obstacle {

    // Constant for Bus speed
    private static final float SPEED = 0.15f;

    /**
     * Instantiates a new Bus.
     *
     * @param imageSrc  the image src
     * @param x         the x position
     * @param y         the y position
     * @param moveRight the boolean for the direction right
     * @throws SlickException the slick exception
     */
    public Bus(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc,x,y,SPEED,moveRight);
    }

}
