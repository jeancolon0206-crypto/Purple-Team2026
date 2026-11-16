import java.util.*;
/**
 * Class that defines a specific room or coordinate point on campus, including its description, doors, and contained items.
 *
 * @author Jean Colon
 * @version 1.0
 * Date: 10/26/2026
 */
public class Location {
    private String name;
    private String description;
    private Boolean haveVisited;
    protected ArrayList<Door> doors;
    private ArrayList<Item> items;

    /**
     * Default constructor for Location objects.
     */
    public Location () {
        name = null;
        description = null;
        haveVisited = false;
        doors = new ArrayList<>();
        items = new ArrayList<>();
    }

    /**
     * Constructor that defines a location with a name and description text.
     *
     * @param name the name of the location
     * @param desc the narrative description of the location
     */
    public Location (String name, String desc) {
        this();
        this.name = name;
        this.description = desc;
    }

    /**
     * Method for getting the name of the location.
     *
     * @return the location name
     */
    public String getName () {
        return name;
    }

    /**
     * Method for getting the description of the location.
     *
     * @return the location description
     */
    public String getDescription () {
        return description;
    }

    /**
     * Method for stating whether the player has previously visited this location.
     *
     * @return returns either true or false
     */
    public boolean getHaveVisited () {
        return haveVisited;
    }

    /**
     * Method for setting the name of the location.
     *
     * @param n the location name
     */
    public void setName (String n) {
        this.name = n;
    }

    /**
     * Method for setting the description of the location.
     *
     * @param d the description text
     */
    public void setDescription (String d) {
        this.description = d;
    }

    /**
     * Method for setting the HaveVisited status flag for the location.
     *
     * @param v the true or false flag
     */
    public void setHaveVisited (boolean v) {
        this.haveVisited = v;
    }

    /**
     * Describes all available doors leading out from this location by delegating to each door object.
     *
     * @return A formatted string detailing available exit directions and destinations.
     */
    public String describeDoors() {
        StringBuilder description = new StringBuilder();
        for (Door d : doors) {
            description.append(d.describe()).append("\n");
        }
        return description.toString();
    }

    /**
     * Method that returns a comprehensive text block containing the location's description, available exits, and local items.
     *
     * @return A formatted string representing the full state of the location.
     */
    public String describeLocation() {
        String itemSection;
        if (items.isEmpty()) {
            itemSection = "Items in this location: None\n";
        } else {
            itemSection = "Items in this location: " + getItemsInLocation() + "\n";
        }

        return this.description + "\n" + this.describeDoors() + "\n" + itemSection;
    }

    /**
     * Method that determines and returns the destination location when moving in a specified direction.
     *
     * @param dir the cardinal direction string (e.g., "n", "s", "e", "w")
     * @return The destination Location object, or null if no door exists in that direction.
     */
    public Location leaveLocation (String dir) {
        Location destination = null;
        for (int index = 0; index < doors.size(); index++) {
            Door d = doors.get(index);
            if (d.getDirection().equalsIgnoreCase(dir)) {
                destination = d.getEntering();
            }
        }
        return destination;
    }

    /**
     * Method for adding an exit door to the location.
     *
     * @param door the Door object to be added
     */
    public void addDoor (Door door) {
        this.doors.add(door);
    }

    /**
     * Method for adding an item to the current location's floor inventory.
     *
     * @param item the Item object to be added
     */
    public void addItem (Item item) {
        this.items.add(item);
    }

    /**
     * Method for removing an item from the current location's floor inventory.
     *
     * @param item the Item object to be removed
     */
    public void removeItem (Item item) {
        this.items.remove(item);
    }

    /**
     * Method that searches for an item by its name (case-insensitive) in the current location and retrieves its name if found.
     *
     * @param name the name of the item to find
     * @return the matching Item object, or null if not found
     */
    public Item getItemNamed (String name) {
        Item item = null;
        for (Item value : items) {
            if (value.getName().equalsIgnoreCase(name)) {
                item = value;
            }
        }
        return item;
    }

    /**
     * Method that returns a comma-separated string of all items present in this location without a trailing comma.
     *
     * @return A formatted string of item names.
     */
    public String getItemsInLocation() {
        if (items.isEmpty()) {
            return "";
        }

        StringBuilder itemsInLocation = new StringBuilder();
        for (Item item : items) {
            itemsInLocation.append(item.getName()).append(", ");
        }

        itemsInLocation.setLength(itemsInLocation.length() - 2);

        return itemsInLocation.toString();
    }
}