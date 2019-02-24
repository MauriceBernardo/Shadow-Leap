import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The class for Turtle.
 */
public class Turtle extends Displacer {

    // Constant for turtle object
    private static final int APPEAR_DURATION = 7000;
    private static final int SUBMERGE_DURATION = 2000;
    private static final float SPEED = 0.085f;

    // Attributes of Turtle
    private boolean isUnderwater = false;
    private Time time;

    /**
     * Instantiates a new Turtle.
     *
     * @param imageSrc  the image src
     * @param x         the x position
     * @param y         the y position
     * @param moveRight the boolean for the direction right
     * @throws SlickException the slick exception
     */
    public Turtle(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc,x,y,SPEED,moveRight);
        this.time = new Time();
    }

    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);

        // Update the timeElapsed and TimeCounter
        this.time.update(delta);
        this.time.updateCounter(delta);

        // Change the state when the time has reached
        this.stateDecision(time);
    }

    @Override
    public void render(Graphics g) {
        // Draw the turtle if it is not underwater
        if (!this.isUnderwater()){
            super.render(g);
        }
    }

    @Override
    public void contactSprite(Sprite other) {
        // Check the state of the turtle
        if (!this.isUnderwater()){
            super.contactSprite(other);
        }
    }

    /**
     * Change the state of the turtle when the criteria has fulfilled
     *
     * @param time the time
     */
    public void stateDecision(Time time){

        // Mechanism for deciding the state base on the time counter
        if(!this.isUnderwater && (time.getTimeCounter() >= APPEAR_DURATION)){
            this.isUnderwater = true;
            this.time.resetCounter();
        } else if (this.isUnderwater && (time.getTimeCounter() >= SUBMERGE_DURATION)){
            this.isUnderwater = false;
            this.time.resetCounter();
        }

    }

    /**
     * Get the isUnderwater boolean.
     *
     * @return the boolean
     */
    public boolean isUnderwater() {
        return isUnderwater;
    }
}
