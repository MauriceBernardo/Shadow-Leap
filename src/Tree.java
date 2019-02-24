import org.newdawn.slick.SlickException;

/**
 * The class for Tree.
 */
public class Tree extends Tile implements Solid{

    // Constant for Tree type
    private static final String TYPE = "tree";

    /**
     * Instantiates a new Tree.
     *
     * @param x      the x position
     * @param y      the y position
     * @param imgSrc the image source
     * @throws SlickException the slick exception
     */
    public Tree(float x, float y, String imgSrc) throws SlickException {
        super(x,y,TYPE,imgSrc);
    }

    // Tree tile will affect nothing when in contact with player
    @Override
    public void contactSprite(Sprite other){}

}
