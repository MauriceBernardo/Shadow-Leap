import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The class for Player.
 */
public class Player extends Sprite implements Movable{

    // Constant for how much life will be lost
    private static final int LIFELOST = 1;

    // Attributes of Player
    private int liveCount;
    private boolean isCollided = false;
    private float lastX;
    private float lastY;
    private boolean isRiding;

    /**
     * Instantiates a new Player.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param lifeCount the life count
     * @throws SlickException the slick exception
     */
    public Player(String imageSrc, float x, float y, int lifeCount) throws SlickException {
        super(imageSrc,x,y);
        this.liveCount = lifeCount;
        this.lastX = x;
        this.lastY = y;
    }

    @Override
    public void update(Input input, int delta){
        this.lastX = this.getX();
        this.lastY = this.getY();
        this.move(input, delta);
        this.isRiding = false;
    }

    @Override
    public void move(Input input, int delta){
        // Move depending on the input key
        if (input.isKeyPressed(Input.KEY_UP)) {
            this.setY(this.getY() - this.getHeight());
        }

        if (input.isKeyPressed(Input.KEY_DOWN)) {
            this.setY(this.getY() + this.getHeight());
        }

        if (input.isKeyPressed(Input.KEY_RIGHT)) {
            this.setX(this.getX() + this.getWidth());
        }

        if (input.isKeyPressed(Input.KEY_LEFT)) {
            this.setX(this.getX() - this.getWidth());
        }
    }

    @Override
    public void contactSprite(Sprite other){
        // Go back to the last position before moving if contacted with Solid (can't move to Solid object)
        if(other instanceof Solid){
            this.setX(lastX);
            this.setY(lastY);
        }
    }

    /**
     * Reinitialise the player at specified x and y
     *
     * @param x the x
     * @param y the y
     */
    public void playerReinitialise(float x, float y){
        this.setX(x);
        this.setY(y);
        this.setCollided(false);
    }

    /**
     * The effect of player being collided
     */
    public void playerCollided(){
        this.loseLife(LIFELOST);
        this.setCollided(true);
    }

    /**
     * Lose life.
     *
     * @param numberOfLive the number of live
     */
    public void loseLife(int numberOfLive){
        this.liveCount = this.getLiveCount() - numberOfLive;
    }

    /**
     * Gain life.
     *
     * @param numberOfLive the number of live
     */
    public void gainLife(int numberOfLive){
        this.liveCount = this.getLiveCount() + numberOfLive;
    }

    /**
     * Get the live count.
     *
     * @return the live count
     */
    public int getLiveCount() {
        return liveCount;
    }

    /**
     * Set the collided boolean;
     *
     * @param collided the collided
     */
    public void setCollided(boolean collided) {
        isCollided = collided;
    }

    /**
     * Gets last x position.
     *
     * @return the last x
     */
    public float getLastX() {
        return lastX;
    }

    /**
     * Gets last y position.
     *
     * @return the last y
     */
    public float getLastY() {
        return lastY;
    }

    /**
     * Getter for collided boolean.
     *
     * @return the boolean
     */
    public boolean isCollided() {
        return isCollided;
    }

    /**
     * Is riding boolean.
     *
     * @return the boolean
     */
    public boolean isRiding() {
        return isRiding;
    }

    /**
     * Sets riding.
     *
     * @param riding the riding
     */
    public void setRiding(boolean riding) {
        isRiding = riding;
    }
}

