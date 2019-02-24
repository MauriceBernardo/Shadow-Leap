import org.newdawn.slick.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class representing the game world
 *
 * @author Maurice
 */
public class World {

    // Constant for the Player
    private static final String PLAYER_IMG_SRC = "assets/frog.png";
    private static final float PLAYER_INITIAL_X = 512;
    private static final float PLAYER_INITIAL_Y = 720;
    private static final int PLAYER_INITIAL_LIVES = 3;

    // Constant for the Lives
    private static final String LIVES_IMG_SRC = "assets/lives.png";
    private static final float LIVES_INITIAL_X = 24;
    private static final float LIVES_INITIAL_Y = 744;
    private static final float LIVES_IMAGE_GAP = 32;

    // Constant for the FinishBox
    private static final float FINISH_BOX_INITIAL_X = 120;
    private static final float FINISH_BOX_INITIAL_Y = 48;
    private static final float FINISH_BOX_GAP = 192;
    private static final int DEFAULT_COMPLETED = 0;

    // Constant for the ExtraLife
    private static final String EXTRA_LIFE_IMG_SRC = "assets/extralife.png";
    private static final int RANDOM_TIME_DIFFERENCE = 10000;
    private static final int RANDOM_TIME_START = 25000;
    private static final int DISAPPEAR_TIME = 14000;

    // Attributes of the world
    private ArrayList<Sprite> spriteSet;
    private Player player;
    private ExtraLife extraLife;
    private Time time;
    private int completedFinish = 0;
    private int numberOfFinish;

    // Attributes of the world specialised for maintaining the ExtraLife
    private int randomTime;
    private boolean isExtraLifeExist = false;

    /**
     * Instantiates a new World.
     *
     * @param lvlText the lvl CSV file
     * @throws SlickException the slick exception
     */
    public World(String lvlText) throws SlickException{

        // Initialise the Attributes
        player = new Player(PLAYER_IMG_SRC,PLAYER_INITIAL_X,PLAYER_INITIAL_Y,PLAYER_INITIAL_LIVES);
        time = new Time();

        // Initialise the tile, obstacle, and displacer ArrayList
        TextHandler.initialiseWorld(this,lvlText);

        // Initialise the FinishBox
        this.initialiseFinishBox();

        // Get a random time for the ExtraLife
        Random rand = new Random();
        randomTime = rand.nextInt(RANDOM_TIME_DIFFERENCE) + RANDOM_TIME_START + time.getTimeElapsed();

	}


    /**
     * Update the world
     *
     * @param input the input
     * @param delta The number of milliseconds since the last frame is passed
     * @throws SlickException the slick exception
     */
    public void update(Input input, int delta) throws SlickException {
		// Update the time elapsed
        time.update(delta);

        // Game over if the player died
        if(player.getLiveCount() == 0){
            App.exit();
        }

        // Reinitialise player if it has collided
        if(player.isCollided()){
            player.playerReinitialise(PLAYER_INITIAL_X,PLAYER_INITIAL_Y);
        }

        // Update the player condition
        player.update(input,delta);

        // Player can't move to solid object
        for (Sprite solid : spriteSet){
            if (solid.getBox().intersects(player.getBox())){
                player.contactSprite(solid);
            }
        }

        // Initialise the extra life
        if (time.getTimeElapsed() >= randomTime && !isExtraLifeExist){
            Displacer randomLog = getRandomLog();
            extraLife = new ExtraLife(EXTRA_LIFE_IMG_SRC,randomLog.getX(),randomLog.getY(),randomLog);
            isExtraLifeExist = true;
        }

        // Mechanism if the ExtraLife exist in the world
        if (isExtraLifeExist){

            // Update and move the ExtraLife
            extraLife.update(input, delta);

            // Check the collision between ExtraLife and player
            if(player.getBox().intersects(extraLife.getBox())){
                extraLife.contactSprite(player);
                isExtraLifeExist = false;
            }

            // The action when the extra life has to disappear
            if(extraLife.getTime().getTimeElapsed() >= DISAPPEAR_TIME || !isExtraLifeExist){
                Random rand = new Random();
                randomTime = rand.nextInt(RANDOM_TIME_DIFFERENCE) + RANDOM_TIME_START + time.getTimeElapsed();
                isExtraLifeExist = false;
            }

        }

        // update all obstacles
        for (Sprite sprite : spriteSet){
            sprite.update(input, delta);
        }

        // Check collision with Displacer and Obstacle
        for (Sprite sprite : spriteSet){
            if(sprite.getBox().intersects(player.getBox())) {
                if (sprite instanceof Displacer || sprite instanceof Obstacle) {
                    sprite.contactSprite(player);
                }
            }
            if (sprite instanceof FinishBox) {
                if (sprite.getBox().intersects(player.getBox())) {
                    // Execute the collided mechanism if the FinishBox is occupied
                    if (((FinishBox) sprite).isCompleted()) {
                        player.playerCollided();

                    } else {
                        sprite.contactSprite(player);
                        player.playerReinitialise(PLAYER_INITIAL_X, PLAYER_INITIAL_Y);
                    }
                }

                // Check the number of completed box
                if (((FinishBox) sprite).isCompleted()) {
                    completedFinish++;
                }
            }
        }

        // If it's riding a log or others ignore the effect of the tiles
        if(!player.isRiding()) {
            // Check for collision between player and tile
            for (Sprite sprite : spriteSet) {
                if (sprite.getBox().intersects(player.getBox()) && sprite instanceof Water) {
                    sprite.contactSprite(player);
                    // to ensure it doesn't lose 2 live because of intersection with 2 tiles
                    break;
                }
            }
        }

        // Check whether the level has completed or not
        if(completedFinish == this.numberOfFinish){
            // End the world
            App.worldEnd();
        } else {
            // Reinitialise the number of finished box
            completedFinish = DEFAULT_COMPLETED;
        }


        // player can't move outside of the screen
        if(!World.isInScreen(player)){
            player.setX(player.getLastX());
            player.setY(player.getLastY());
        }
	}

    /**
     * Render the world
     *
     * @param g the object of Graphics class of Slick
     * @throws SlickException the slick exception
     */
    public void render(Graphics g) throws SlickException {

        // Render the sprites
        for (Sprite sprite: spriteSet){
            sprite.render(g);
        }

        // Draw life
        drawLives(player);

        // Draw extra life
        if(isExtraLifeExist){
            extraLife.render(g);
        }

        // Draw player
        player.render(g);

    }


    /**
     * Sets sprite set.
     *
     * @param spriteSet the sprite set
     */
    public void setSpriteSet(ArrayList<Sprite> spriteSet) {
        this.spriteSet = spriteSet;
    }

    /**
     * Check whether the Sprite is completely inside the screen
     *
     * @param player the player
     * @return the boolean
     */
    public static boolean isInScreen(Sprite player){
        if(player.getX() + player.getWidth()/2 <= App.SCREEN_WIDTH && player.getX() - player.getWidth()/2 >= App.ORIGIN_X){
            if (player.getY() + player.getHeight()/2 <= App.SCREEN_HEIGHT && player.getY() - player.getHeight()/2 >= App.ORIGIN_Y){
                return true;
            }
        }
        return false;
    }

    // Draw Player lives.
    private static void drawLives(Player player) throws SlickException {
        Image live;
        for (int i = 0; i < player.getLiveCount(); i++){
            live = new Image(LIVES_IMG_SRC);
            live.drawCentered(LIVES_INITIAL_X + i*LIVES_IMAGE_GAP,LIVES_INITIAL_Y);
        }
    }

    // Initialise finish boxes of the world.
    private void initialiseFinishBox(){
        ArrayList<FinishBox> boxes = new ArrayList<>();
        this.numberOfFinish = 0;
        for(float i = FINISH_BOX_INITIAL_X; i < App.SCREEN_WIDTH; i += FINISH_BOX_GAP){
            boxes.add(new FinishBox(i,FINISH_BOX_INITIAL_Y));
            numberOfFinish++;
        }
        this.spriteSet.addAll(boxes);
    }

    // Function for getting random Log
    private Displacer getRandomLog(){
        // Generate random number
        Random rand = new Random();
        int randomIndex = rand.nextInt(spriteSet.size());
        Sprite randomLog = spriteSet.get(randomIndex);

        // Check until we get a Log or LongLog
        while (!(randomLog instanceof Log) && !(randomLog instanceof LongLog)){
            randomIndex = rand.nextInt(spriteSet.size());
            randomLog = spriteSet.get(randomIndex);
        }

        return (Displacer) this.spriteSet.get(randomIndex);
    }
}
