import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The class for Displacer.
 */
public class Displacer extends NonPlayerObject {

    // Attributes of Displacer
    private float xDisplacement;

    /**
     * Instantiates a new Displacer.
     *
     * @param imageSrc  the image src
     * @param x         the x position
     * @param y         the y position
     * @param speed     the speed
     * @param moveRight the boolean for the direction right
     * @throws SlickException the slick exception
     */
    public Displacer(String imageSrc, float x, float y, float speed, boolean moveRight) throws SlickException {
        super(imageSrc,x,y,moveRight);
        this.setSpeed(speed);
    }

    @Override
    public void update(Input input, int delta) {
        // update using the superclass update
        super.update(input, delta);

        // update the displacement for each update
        this.xDisplacement = this.getSpeed()*delta;
    }

    @Override
    public void contactSprite(Sprite other) {
        // Move the other sprite with the same displacement as Displacer
        if (this.isMoveRight()) {
            other.setX(other.getX() + this.xDisplacement);
        } else {
            other.setX(other.getX() - this.xDisplacement);
        }
        if (other instanceof Player && !(this instanceof Bulldozer)){
            ((Player) other).setRiding(true);
        }
    }
}
