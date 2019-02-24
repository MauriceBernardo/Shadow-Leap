import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * The class for Sprite.
 */
public abstract class Sprite{

	// Attributes of Sprite
	private float x;
	private float y;
	private Image spriteImage;
	private float width;
	private float height;
	private BoundingBox box;


	/**
	 * Instantiates a new Sprite.
	 *
	 * @param imageSrc the image src
	 * @param x        the x position
 	 * @param y        the y position
	 * @throws SlickException the slick exception
	 */
	public Sprite(String imageSrc, float x, float y) throws SlickException {
		this.spriteImage = new Image(imageSrc);
		this.width = this.spriteImage.getWidth();
		this.height = this.spriteImage.getHeight();
		this.x = x;
		this.y = y;
		this.box = new BoundingBox(spriteImage,this.x,this.y);
	}

	/**
	 * Instantiates a new Sprite.
	 *
	 * @param x      the x position
	 * @param y      the y position
	 * @param width  the width of the Sprite
	 * @param height the height of the Sprite
	 */
	public Sprite(float x, float y,  float width, float height){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.box = new BoundingBox(x, y, width, height);
	}


	/**
	 * Update the Sprite
	 *
	 * @param input the input
	 * @param delta The number of milliseconds since the last frame is passed
	 */
	public abstract void update(Input input, int delta);

	/**
	 * Contact sprite.
	 *
	 * @param other the other Sprite
	 */
	public abstract void contactSprite(Sprite other);

	/**
	 * Render the Sprite
	 *
	 * @param g the Graphics class from Slick
 	 */
	public void render(Graphics g) {
		this.spriteImage.drawCentered(this.x,this.y);
	}

	/**
	 * Get the bounding box.
	 *
	 * @return the bounding box
	 */
	public BoundingBox getBox(){
		return this.box;
	}

	/**
	 * Get the width of the Sprite.
	 *
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * Get the height of the Sprite.
	 *
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * Get the x position.
	 *
	 * @return the x
	 */
	public float getX() {
		return this.x;
	}

	/**
	 * Get the y position.
	 *
	 * @return the y
	 */
	public float getY() {
		return this.y;
	}

	/**
	 * Set the x position.
	 *
	 * @param x the x
	 */
	public void setX(float x) {
		this.x = x;
		this.box.setX(x);
	}

	/**
	 * Set the y position.
	 *
	 * @param y the y
	 */
	public void setY(float y) {
		this.y = y;
		this.box.setY(y);
	}

	/**
	 * Get the sprite image.
	 *
	 * @return the sprite image
	 */
	public Image getSpriteImage() {
		return spriteImage;
	}

}
