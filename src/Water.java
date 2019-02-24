import org.newdawn.slick.SlickException;

/**
 * The class for Water.
 */
public class Water extends Tile {

    // Constant for type of water
    private static final String TYPE = "water";

    /**
     * Instantiates a new Water.
     *
     * @param x      the x position
     * @param y      the y position
     * @param imgSrc the image source
     * @throws SlickException the slick exception
     */
    public Water(float x, float y, String imgSrc) throws SlickException {
        super(x,y,TYPE,imgSrc);
    }

    @Override
    public void contactSprite(Sprite other){
        // Do the collided mechanism of the player
        if(other instanceof Player){
            ((Player) other).playerCollided();
        }
    }

}
