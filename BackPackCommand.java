/**
 * Command class representing the user's request to view the contents of their backpack.
 * Implements the UserInputCommand interface to carry out the backpack listing action.
 *
 * @author Jean Colon
 * @version 1.0
 * Date: 10/26/2026
 */
public class BackPackCommand implements UserInputCommand {

    /**
     * Default constructor for BackPackCommand objects.
     */
    public BackPackCommand() {
    }

    /**
     * Method that executes the backpack command by retrieving and listing all items currently held in the user's backpack.
     *
     * @return A string containing the list of items stored in the backpack.
     */
    public String carryOut() {
        return TourStatus.getInstance().listBackpackItems();
    }
}
