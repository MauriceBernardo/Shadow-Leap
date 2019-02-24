import org.newdawn.slick.Input;

/**
 * The interface Movable.
 */
public interface Movable {
    /**
     * Move
     *
     * @param input the input
     * @param delta the delta
     */
    public void move(Input input, int delta);
}
