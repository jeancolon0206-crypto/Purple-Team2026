/**
 * Class to define the properties and behaviors of items that can be picked up, carried, and dropped in the campus world.
 *
 * @author Jean Colon
 * @version 1.0
 * Date: 10/26/2026
 */
public class Item {
    private String name;
    private String message;

    /**
     * Default constructor for Item objects.
     */
    public Item() {
        this.name = null;
        this.message = null;
    }

    /**
     * Constructor that defines an item with a specific name and pickup message.
     *
     * @param n the name of the item
     * @param m the message displayed when the item is picked up
     */
    public Item(String n, String m) {
        this.name = n;
        this.message = m;
    }

    /**
     * Method for getting the name of the item.
     *
     * @return the item name
     */
    public String getName() {
        return name;
    }

    /**
     * Method for getting the custom description/message associated with the item.
     *
     * @return the item message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Method for setting the name of the item.
     *
     * @param name the new name for the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for setting the pickup message for the item.
     *
     * @param msg the new message for the item
     */
    public void setMessage(String msg) {
        this.message = msg;
    }
}
