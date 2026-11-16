import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/**
 * Class to define the different information variables of the campus.
 * @author Jean Colon
 * @version 1.0
 * Date: 10/26/2026
 */
public class Campus {
    private String campusName;
    private Hashtable<String, Location> locations;
    private ArrayList<String> locationNames;
    private Location startingLocation;
    private String filename;

    /**
     * Default constructor for campus objects.
     */
    public Campus() {
        campusName = null;
        locations = new Hashtable<>();
        locationNames = new ArrayList<>();
        startingLocation = null;
        filename = null;
    }

    /**
     * Constructor that defines the different information variables of a campus. Input file data is also prepped here
     * to be parsed.
     * @param name the name of the input file
     */
    public Campus(String name) {
        this();
        setFilename(name);
        File inFile = new File(filename);

        try (Scanner scanFileIn = new Scanner(inFile)) {
            // Read entire file content into a StringBuilder
            StringBuilder fileContent = new StringBuilder();
            while (scanFileIn.hasNextLine()) {
                fileContent.append(scanFileIn.nextLine()).append("\n");
            }

            // Split into main sections separated by *****
            String[] sections = fileContent.toString().split("\\*\\*\\*\\*\\*");

            // Section 0: Campus Name
            setCampusName(sections[0].trim());

            // Section 1: Locations
            if (sections.length > 1) {
                parseLocationsSection(sections[1].trim());
            }

            // Section 2: Doors
            if (sections.length > 2) {
                parseDoorsSection(sections[2].trim());
            }

            // Section 3: Items
            if (sections.length > 3) {
                parseItemsSection(sections[3].trim());
            }

            // Set starting location to the first location added
            if (!locationNames.isEmpty()) {
                String startingLocationName = locationNames.get(0);
                setStartingLocation(locations.get(startingLocationName));
            }

        } catch (FileNotFoundException e) {
            System.err.println("File Not Found: " + name);
        }
    }

    /**
     * Method for parsing the locations section of the input file separated by delimiters.
     * @param sectionText the sections of text from the locations part of the input file
     */
    private void parseLocationsSection(String sectionText) {
        // Split section by lines, dropping header "Locations:" if present
        String[] blocks = sectionText.split("\\+\\+\\+");

        for (String block : blocks) {
            String trimmedBlock = block.trim();
            if (trimmedBlock.startsWith("Locations:")) {
                trimmedBlock = trimmedBlock.replace("Locations:", "").trim();
            }
            if (trimmedBlock.isEmpty()) continue;

            // Split into name (line 1) and description (rest of block)
            String[] lines = trimmedBlock.split("\n", 2);
            String locName = lines[0].trim();
            String locDescription = (lines.length > 1) ? lines[1].trim() : "";

            Location location = new Location(locName, locDescription);
            addLocation(location);
            locationNames.add(locName);
        }
    }

    /**
     * Method for passing the door section of the input file separated by delimiters.
     * @param sectionText the sections of text from the doors part of the input file
     */
    private void parseDoorsSection(String sectionText) {
        String[] blocks = sectionText.split("\\+\\+\\+");

        for (String block : blocks) {
            String trimmedBlock = block.trim();
            if (trimmedBlock.startsWith("Doors:")) {
                trimmedBlock = trimmedBlock.replace("Doors:", "").trim();
            }
            if (trimmedBlock.isEmpty()) continue;

            // File order for doors:
            // Line 1: Origin / Leaving Location Name
            // Line 2: Direction (n, s, e, w)
            // Line 3: Destination / Entering Location Name
            String[] lines = trimmedBlock.split("\r?\n");
            if (lines.length >= 3) {
                String leavingName = lines[0].trim();
                String direction = lines[1].trim();
                String arrivingName = lines[2].trim();

                Location leavingLoc = locations.get(leavingName);
                Location arrivingLoc = locations.get(arrivingName);

                if (leavingLoc != null && arrivingLoc != null) {
                    Door door = new Door(direction, leavingLoc, arrivingLoc);
                    leavingLoc.addDoor(door);
                }
            }
        }
    }

    private void parseItemsSection(String sectionText) {
        String[] blocks = sectionText.split("\\+\\+\\+");

        for (String block : blocks) {
            String trimmedBlock = block.trim();
            if (trimmedBlock.startsWith("Items:")) {
                trimmedBlock = trimmedBlock.replace("Items:", "").trim();
            }
            if (trimmedBlock.isEmpty()) continue;

            String[] lines = trimmedBlock.split("\r?\n");
            if (lines.length >= 3) {
                String itemName = lines[0].trim();
                String locName = lines[1].trim();

                // Handle potential multi-line item messages safely
                StringBuilder msgBuilder = new StringBuilder();
                for (int i = 2; i < lines.length; i++) {
                    msgBuilder.append(lines[i].trim()).append(" ");
                }
                String itemMessage = msgBuilder.toString().trim();

                Item item = new Item(itemName, itemMessage);
                Location loc = locations.get(locName);
                if (loc != null) {
                    loc.addItem(item);
                }
            }
        }
    }

    /**
     * Method for adding locations.
     * @param location the location being added
     */
    public void addLocation(Location location) {
        this.locations.put(location.getName(), location);
    }

    /**
     * Method for getting a location by name.
     * @param name the name of the location being retrieved
     * @return the location being retrieved
     */
    public Location getLocation(String name) {
        return locations.get(name);
    }

    /**
     * Method for setting the name of the campus.
     * @param n the name of the campus
     */
    public void setCampusName(String n) {
        this.campusName = n;
    }

    /**
     * Method for getting the name of the campus.
     * @return the name of the campus
     */
    public String getCampusName() {
        return campusName;
    }

    /**
     * Method for setting the starting location.
     * @param start the location to be set as the starting location
     */
    public void setStartingLocation(Location start) {
        this.startingLocation = start;
    }

    /**
     * Method for getting the starting location.
     * @return the starting location
     */
    public Location getStartingLocation() {
        return startingLocation;
    }

    /**
     * Method for setting the name of the input file.
     * @param f the name to be set for the input file
     */
    public void setFilename(String f) {
        this.filename = f;
    }

    /**
     * Method for getting the name of the input file.
     * @return the name of the input file
     */
    public String getFilename() {
        return filename;
    }
}