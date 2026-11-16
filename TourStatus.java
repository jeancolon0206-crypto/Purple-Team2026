import java.util.*;
/**
 * Singleton class tracking the global state of the campus tour, including the active campus,
 * current player location, and backpack inventory.
 *
 * @author Jean Colon
 * @version 1.0
 * Date: 10/26/2026
 */
public class TourStatus {
    private Campus campus;
    private Location currentLocation;
    private ArrayList<Item> backpack;
    private static TourStatus tourInstance;

    /**
     * Private constructor to enforce the Singleton design pattern.
     */
    private TourStatus() {
        campus = null;
        currentLocation = null;
        backpack = new ArrayList<>();
    }

    /**
     * Method that returns the single shared instance of TourStatus, creating it if it does not already exist.
     *
     * @return the singleton TourStatus instance
     */
    public static synchronized TourStatus getInstance() {
        if (tourInstance == null) {
            tourInstance = new TourStatus();
        }
        return tourInstance;
    }

    /**
     * Method that sets the active campus model for the tour.
     *
     * @param campus the Campus object
     */
    public void setCampus (Campus campus) {
        this.campus = campus;
    }

    /**
     * Method for setting the user's current location on campus.
     *
     * @param loc the Location object
     */
    public void setCurrentLocation (Location loc) {
        this.currentLocation = loc;
    }

    /**
     * Method for getting the active campus model.
     *
     * @return the Campus object
     */
    public Campus getCampus () {
        return campus;
    }

    /**
     * Method for getting the user's current location.
     *
     * @return the current Location object
     */
    public Location getCurrentLocation () {
        return currentLocation;
    }

    /**
     * Method that updates the current tour location based on a directional movement string.
     *
     * @param dir the direction of movement
     */
    public void updateTourLocation (String dir) {
        Location location = currentLocation.leaveLocation(dir);
        if (location == null) {
            System.out.println("You can't leave in that direction. Try going another direction.");
        }
        else {
            this.setCurrentLocation(location);
        }
    }

    /**
     * Method for adding an item to the user's backpack.
     *
     * @param item the Item to add
     */
    public void addToBackpack (Item item) {
        backpack.add(item);
    }

    /**
     * Method that removes and returns a specified item from the backpack by its name (case-insensitive).
     *
     * @param name the name of the item to drop
     * @return the removed Item object, or null if not found
     */
    public Item dropItemFromBackpack (String name) {
        Item item = null;
        for (int i = 0; i < backpack.size(); i++) {
            if (backpack.get(i).getName().equalsIgnoreCase(name)) {
                item = backpack.get(i);
                backpack.remove(i);
                break;
            }
        }
        return item;
    }

    /**
     * Method that picks up an item from the current location, places it into the backpack
     * and removes it from the current location.
     *
     * @param name the name of the item to pick up
     * @return the picked up Item object, or null if not found
     */
    public Item pickupItemFromLocation (String name) {
        Item item = currentLocation.getItemNamed(name);

        if (item != null) {
            addToBackpack(item);
            currentLocation.removeItem(item);
            return item;
        }

        else {
            return null;
        }
    }

    /**
     * Method that generates a formatted string listing all items currently stored in the backpack.
     *
     * @return A string containing the inventory list.
     */
    public String listBackpackItems () {
        String result = "Your backpack contains: ";
        StringBuilder sb = new StringBuilder(result);
        for (int i = 0; i < backpack.size(); i++) {
            sb.append(backpack.get(i).getName()).append("\n");
            result = sb.toString();
        }

        return result;
    }
}