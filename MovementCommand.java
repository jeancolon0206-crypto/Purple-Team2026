/**
 * Command class representing the user's navigational movement between locations on campus.
 * Implements the UserInputCommand interface.
 *
 * @author Jean Colon
 * @version 1.0
 * Date: 10/26/2026
 */
public class MovementCommand implements UserInputCommand{
    private String dir;

    /**
     * Constructor that defines the direction of movement.
     *
     * @param dir the cardinal direction string (e.g., "n", "s", "e", "w")
     */
    public MovementCommand(String dir) {
        this.dir = dir;
    }

    String result = null;
    /**
     * Method that executes the movement command, updating the current tour location,
     * marking the new location as visited, and generating its full descriptive output.
     *
     * @return A string detailing the new location name, description, doors, and local items.
     */
    public String carryOut() {
        TourStatus status = TourStatus.getInstance();
        Location currentLocation;
        status.updateTourLocation(this.dir);

        currentLocation = status.getCurrentLocation();
        currentLocation.setHaveVisited(true);

        result = "You are now in " + currentLocation.getName() + ".\n" + currentLocation.describeLocation();

        return result;
    }

}
