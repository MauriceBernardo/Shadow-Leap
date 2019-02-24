import org.newdawn.slick.SlickException;

/**
 * The class for Car.
 */
public class Car extends Obstacle {

    // Constant for Car speed
    private static final float SPEED = 0.5f;

    /**
     * Instantiates a new Car.
     *
     * @param imageSrc  the image src
     * @param x         the x position
     * @param y         the y position
     * @param moveRight the boolean for the direction right
     * @throws SlickException the slick exception
     */
    public Car(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc,x,y,SPEED,moveRight);
    }

}
