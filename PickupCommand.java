/**
 * Command class representing the user's request to pick up an item from the current location and add it to their backpack.
 * Implements the UserInputCommand interface.
 *
 * @author Jean Colon
 * @version 1.0
 * Date: 10/26/2026
 */
public class PickupCommand implements UserInputCommand {
    private String itemName;

    /**
     * Constructor that defines the name of the item to pick up.
     *
     * @param itemName the name of the item
     */
    public PickupCommand(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Method that executes the pickup command by attempting to retrieve the item from the current location,
     * transferring it to the backpack inventory, and returning its pickup message.
     *
     * @return A success message with the item description, or an error message if the item is not present.
     */
    public String carryOut() {
        TourStatus status = TourStatus.getInstance();
        Location currentLocation = status.getCurrentLocation();
        Item item = status.pickupItemFromLocation(itemName);

        if (item == null) {
            return "There isn't any item with that name in this location.";
        }
        else {
            return "You picked up " + item.getName() + " from " + currentLocation.getName() + ".\n" + item.getMessage();
        }

    }
}