import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The Class for Non player object.
 */
public abstract class NonPlayerObject extends Sprite implements Movable {

    // Attributes of NonPlayerObject
    private boolean moveRight;
    private float speed;

    /**
     * Instantiates a new Non player object.
     *
     * @param imageSrc  the image src
     * @param x         the x position
     * @param y         the y position
     * @param moveRight the boolean for the direction right
     * @throws SlickException the slick exception
     */
    public NonPlayerObject(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc,x,y);
        this.moveRight = moveRight;
    }

    // Abstract method of contactSprite
    public abstract void contactSprite(Sprite other);

    /**
     * Method for Switching direction.
     */
    public void switchDirection(){
        this.moveRight = !this.moveRight;
    }

    @Override
    public void update(Input input, int delta){
        // Move the object
        this.move(input, delta);

        // Calculate the right and left border of the object
        float right = this.getX() + this.getWidth()/2;
        float left = this.getX() - this.getWidth()/2;

        // Re-initialise the position depending on the direction
        if(this.isMoveRight()) {
            if (left > App.SCREEN_WIDTH) {
                this.setX(App.ORIGIN_X - this.getWidth() / 2);
            }
        } else {
            if (right < App.ORIGIN_X) {
                this.setX(App.SCREEN_WIDTH + this.getWidth() / 2);
            }
        }
    }

    @Override
    public void move(Input input, int delta){
        // Change the position depending on the direction
        if(this.isMoveRight()) {
            this.setX(this.getX() + delta * speed);
        } else {
            this.setX(this.getX() - delta * speed);
        }
    }

    /**
     * Getter for moveRight boolean.
     *
     * @return the moveRight boolean
     */
    public boolean isMoveRight() {
        return moveRight;
    }

    /**
     * Sets the moveRight boolean.
     *
     * @param moveRight the moveRight boolean
     */
    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    /**
     * Set speed.
     *
     * @param speed the speed
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Get speed.
     *
     * @return the speed
     */
    public float getSpeed() {
        return speed;
    }
}
