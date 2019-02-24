import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The type Tile.
 */
public abstract class Tile extends Sprite{

    // Attributes for Tile
    private String type;

    /**
     * Instantiates a new Tile.
     *
     * @param x      the x position
     * @param y      the y position
     * @param type   the type of the tile
     * @param imgSrc the image source
     * @throws SlickException the slick exception
     */
    public Tile (float x, float y, String type, String imgSrc) throws SlickException {
        super(imgSrc,x,y);
        this.setType(type);
    }

    /**
     * Instantiates a new Tile.
     *
     * @param x      the x position
     * @param y      the y position
     * @param width  the width of the tile
     * @param height the height of the tile
     * @param type   the type of the tile
     */
    public Tile(float x, float y,  float width, float height, String type){
        super(x,y,width,height);
        this.setType(type);
    }

    // All tiles can have an update but it is not needed in this version of the game
    public void update(Input input, int delta){}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type of the tile
	 */
	public void setType(String type) {
		this.type = type;
	}
}
