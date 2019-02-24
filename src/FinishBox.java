import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * The class for Finish box.
 */
public class FinishBox extends Tile{

    // Constant for The size of the finish box is 2 tree tiles in width and 1 tree tiles in height
    private static final float WIDTH = 96;
    private static final float HEIGHT = 48;
    private static final String TYPE = "finishbox";

    // Attributes of FinishBox
    private boolean completed = false;
    private Image centerImage = null;


    /**
     * Instantiates a new Finish box.
     *
     * @param x the x position
     * @param y the y position
     */
    public FinishBox(float x, float y){
        super(x,y,WIDTH,HEIGHT,TYPE);
    }

    @Override
    public void render(Graphics g){
        // Render the player image if it's completed
        if (centerImage != null) {
            centerImage.drawCentered(this.getX(), this.getY());
        }
    }

    @Override
    public void contactSprite(Sprite other){

        // Get the player image if it is in contact with player
        if(other instanceof Player){
            completed = true;
            centerImage = other.getSpriteImage();
        }
    }

    /**
     * Getter for completed boolean
     *
     * @return the completed boolean
     */
    public boolean isCompleted() {
        return completed;
    }

}
