/**
 * This class manages the features that players have in the game
 */
public class Player {
    int points;
    String name;

    /**
     * @param points Is an int number containing the player's points
     * @param name Is a string containing the player's name
     */
    public Player(int points, String name) {
        this.points = points;
        this.name = name;
    }

    /**
     * @return The player's points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @return The player's name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param earned the points earned
     */

    public void winPoints(int earned){
        points+=earned;
    }

    /**
     * @param lost the points lost
     */
    public void lossPoints(int lost){
        points-=lost;
    }

}
