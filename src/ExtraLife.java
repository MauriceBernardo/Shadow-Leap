import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The class for Extra life.
 */
public class ExtraLife extends NonPlayerObject {

    // Constant for extra life
    private static final int MOVE_TIME = 2000;
    private static final boolean MOVERIGHT = true;

    // Attributes of Extra life
    private Displacer log;
    private float xOffset = 0;
    private Time time;

    /**
     * Instantiates a new Extra life.
     *
     * @param imageSrc the image src
     * @param x        the x position
     * @param y        the y position
     * @param log      the object of target log
     * @throws SlickException the slick exception
     */
    public ExtraLife(String imageSrc, float x, float y, Displacer log) throws SlickException {
        super(imageSrc, x, y, MOVERIGHT);
        this.log = log;
        this.time = new Time();
    }

    @Override
    public void update(Input input, int delta){
        this.time.update(delta);
        this.time.updateCounter(delta);

        // Extra life always follow the log
        this.setX(this.getLog().getX() + this.getxOffset());
        // Move mechanism for extralife
        if(this.time.getTimeCounter() >= MOVE_TIME){

            float lastxOffset = this.xOffset;
            this.move(input, delta);

            // Reverse the direction when it will go out of the log
            if (!(this.getBox().intersects(this.log.getBox()))){
                this.setxOffset(lastxOffset);
                this.switchDirection();
                this.move(input,delta);
            }

            this.time.resetCounter();
        }

    }

    @Override
    public void move(Input input, int delta) {
        // Change the x offset of the extra life
        if(this.isMoveRight()){
            xOffset += this.getWidth();
        } else {
            xOffset -= this.getWidth();
        }

        // Move the extra life based on the x offset
        this.setX(this.log.getX() + this.xOffset);
    }

    @Override
    public void render(Graphics g) {
        this.getSpriteImage().drawCentered(this.getX(),this.getY());
    }

    @Override
    public void contactSprite(Sprite other) {
        if (other instanceof Player){
            ((Player) other).gainLife(1);
        }
    }

    /**
     * Gets the x offset.
     *
     * @return the offset
     */
    public float getxOffset() {
        return xOffset;
    }

    /**
     * Sets the x offset.
     *
     * @param xOffset the x offset
     */
    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    /**
     * Gets log.
     *
     * @return the log
     */
    public Displacer getLog() {
        return log;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public Time getTime() {
        return time;
    }
}
