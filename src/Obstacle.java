import org.newdawn.slick.SlickException;

/**
 * The class for Obstacle.
 */
public class Obstacle extends NonPlayerObject{

    /**
     * Instantiates a new Obstacle.
     *
     * @param imageSrc  the image src
     * @param x         the x position
     * @param y         the y position
     * @param speed     the speed
     * @param moveRight the boolean for the direction right
     * @throws SlickException the slick exception
     */
    public Obstacle(String imageSrc, float x, float y, float speed, boolean moveRight) throws SlickException {
        super(imageSrc,x,y,moveRight);
        this.setSpeed(speed);
    }

    @Override
    public void contactSprite(Sprite other) {
        // Do the collided mechanism of the player
        if(other instanceof Player){
            ((Player) other).playerCollided();
        }
    }
}