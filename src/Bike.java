import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The class for Bike.
 */
public class Bike extends Obstacle{

    // Constant for Bike speed
    private static final float SPEED = 0.2f;

    /**
     * Instantiates a new Bike.
     *
     * @param imageSrc  the image src
     * @param x         the x position
     * @param y         the y position
     * @param moveRight the boolean for the direction right
     * @throws SlickException the slick exception
     */
    public Bike(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc,x,y,SPEED,moveRight);
    }

    @Override
    public void update(Input input, int delta) {
        // Move the bike
        this.move(input, delta);

        // Calculate the right and left border of the Bike
        float right = this.getX() + this.getWidth()/2;
        float left = this.getX() - this.getWidth()/2;

        // Re-initialise the position depending on the direction
        if(this.isMoveRight()) {
            if (right >= App.SCREEN_WIDTH) {
                this.setMoveRight(false);
            }
        } else {
            if (left <= App.ORIGIN_X) {
                this.setMoveRight(true);
            }
        }
    }
}
