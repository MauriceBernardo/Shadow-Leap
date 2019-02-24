import org.newdawn.slick.SlickException;

/**
 * The type Grass.
 */
public class Grass extends Tile {

    // Constant for Grass type
    private static final String TYPE = "grass";

    /**
     * Instantiates a new Grass.
     *
     * @param x      the x
     * @param y      the y
     * @param imgSrc the img src
     * @throws SlickException the slick exception
     */
    public Grass(float x, float y, String imgSrc) throws SlickException {
        super(x,y,TYPE,imgSrc);
    }

    // Grass tile will affect nothing when in contact with player
    @Override
    public void contactSprite(Sprite other){}

}
