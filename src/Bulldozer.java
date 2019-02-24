import org.newdawn.slick.SlickException;

/**
 * The class for Bulldozer.
 */
public class Bulldozer extends Displacer implements Solid{

    // Constant for Bulldozer speed
    private static final float SPEED = 0.05f;

    /**
     * Instantiates a new Bulldozer.
     *
     * @param imageSrc  the image src
     * @param x         the x position
     * @param y         the y position
     * @param moveRight the boolean for the direction right
     * @throws SlickException the slick exception
     */
    public Bulldozer(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc,x,y,SPEED,moveRight);
    }

    @Override
    public void contactSprite(Sprite other) {
        super.contactSprite(other);

        // Check whether player is outside of the screen
        if (other instanceof Player) {
            if (!World.isInScreen(other)) {
                ((Player) other).playerCollided();
            }
        }
    }
}
