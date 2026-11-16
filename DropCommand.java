/**
 * Command class representing the user's request to drop an item from their backpack into their current location.
 * Implements the UserInputCommand interface.
 *
 * @author Jean Colon
 * @version 1.0
 * Date: 10/26/2026
 */
public class DropCommand implements UserInputCommand {
    private String itemName;
    private TourStatus tourStatus;

    /**
     * Default constructor for DropCommand objects.
     */
    public DropCommand() {
        itemName = null;
        tourStatus = TourStatus.getInstance();
    }

    /**
     * Constructor that defines the item to be dropped.
     *
     * @param itemName the name of the item being dropped from the backpack
     */
    public DropCommand(String itemName) {
        this();
        this.itemName = itemName;
    }

    String result = null;
    /**
     * Method that executes the drop command by removing the specified item from the backpack
     * and adding it to the user's current location if present.
     *
     * @return A confirmation message indicating the item was dropped, or an error message if not found.
     */
    public String carryOut() {
        Location currentLocation = tourStatus.getCurrentLocation();
        if (currentLocation != null) {
            Item droppedItem = tourStatus.dropItemFromBackpack(itemName);
            if (droppedItem != null) {
                currentLocation.addItem(droppedItem);
                result = "The " + itemName + " has been dropped in " + currentLocation.getName() + ".";
            }

            else {
                result = "You don't seem to have " + itemName + " in your backpack.";
            }

        }

        return result;
    }
}