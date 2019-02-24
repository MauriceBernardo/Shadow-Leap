/**
 * The Class for Time.
 */
public class Time {

    // Attributes for Time
    private int timeElapsed;
    private int timeCounter;

    /**
     * Instantiates a new Time object.
     */
    public Time(){
        this.timeElapsed = 0;
        this.timeCounter = 0;
    }

    /**
     * Update the Time elapsed
     *
     * @param delta the delta
     */
    public void update(int delta){
        this.timeElapsed += delta;
    }

    /**
     * Update the Time counter.
     *
     * @param delta the delta
     */
    public void updateCounter(int delta){
        this.timeCounter += delta;
    }

    /**
     * Reset the Time counter.
     */
    public void resetCounter(){
        this.timeCounter = 0;
    }

    /**
     * Get the time elapsed.
     *
     * @return the time elapsed
     */
    public int getTimeElapsed() {
        return timeElapsed;
    }

    /**
     * Get the time counter.
     *
     * @return the time counter
     */
    public int getTimeCounter() {
        return timeCounter;
    }
}
