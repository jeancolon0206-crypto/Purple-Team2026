/**
 * Class to define the different information variables of the doors of each location in the campus.
 * @author Jean Colon
 * @version 1.0
 * Date: 10/26/2026
 */
public class Door {
    private String direction;
    private Location leavingLocation;
    private Location enteringLocation;

    /**
     * Default constructor for door objects.
     */
    public Door () {
        direction = null;
        leavingLocation = null;
        enteringLocation = null;
    }

    /**
     * Constructor that defines the different variables of a door.
     * @param dir the direction
     * @param leave the leaving location
     * @param enter the entering location
     */
    public Door (String dir, Location leave, Location enter) {
        this.direction = dir;
        this.leavingLocation = leave;
        this.enteringLocation = enter;
    }

    /**
     * Method for describing a location's door(s) based on the user's direction.
     * @return Returns a string with a description of the locations the user can reach based on the available directions
     */
    public String describe() {
        return "You can go " + getDirection() + " to get to " + getEntering().getName() + ".";
    }

    /**
     * Method for getting the destination location.
     * @return returns the location the user is going to
     */
    public Location getLeaving () {
        return leavingLocation;
    }

    /**
     * Method for getting the departing location.
     * @return returns the location being left
     */
    public Location getEntering () {
        return enteringLocation;
    }

    /**
     * Method for getting the user's heading direction.
     * @return returns the direction the user is heading
     */
    public String getDirection () {
        return direction;
    }

    /**
     * Method for setting the departing location.
     * @param leave the location being left
     */
    public void setLeaving (Location leave) {
        this.leavingLocation = leave;
    }

    /**
     * Method for setting the destination location.
     * @param enter the location the user is going to
     */
    public void setEntering (Location enter) {
        this.enteringLocation = enter;
    }

    /**
     * Method for setting the user's heading direction.
     * @param dir the direction the user is heading
     */
    public void setDirection (String dir) {
        this.direction = dir;
    }
}