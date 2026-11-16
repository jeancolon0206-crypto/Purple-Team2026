import java.util.*;
import java.io.*;
/**
 * Main class which reads in a file to create the virtual campus. It then allows the user to move throughout the campus.
 * @author Jean Colon
 * @version 1.0
 * Date: 10/26/2026
 */
public class TourUMW {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner stdin = new Scanner(System.in);

        Campus campus = setUpCampus(stdin);
        TourStatus status = TourStatus.getInstance();
        status.setCampus(campus);
        status.setCurrentLocation(campus.getStartingLocation());

        if (status.getCurrentLocation() == null) {
            System.out.println("Error: Starting location could not be loaded.");
            return;
        }

        System.out.println("\n--- " + campus.getCampusName() + " ---");

        Location current = status.getCurrentLocation();
        System.out.println("\n-----------------------------------------------------------------------------");
        current.setHaveVisited(true);
        System.out.println("Current Location: " + current.getName());
        System.out.println(current.describeLocation());

        while (true) {
            System.out.print("\nWhere would you like to do? Enter commands such as movements (n, s, e, or w) or actions (pickup _Item Name_, drop _Item Name_, or backpack) to continue, or q to quit. ");
            String input = stdin.nextLine().trim();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("Thank you for exploring campus! Goodbye.");
                break;
            }
            UserInputCommand command = parseInput(input);
            String result = command.carryOut();
            System.out.println(result);
        }
    }

    /**
     * Method for setting up the campus by reading from a setup file.
     * @param s the scanner
     * @return returns a new campus from the instructions of the setup file
     */
    public static Campus setUpCampus(Scanner s) {
        System.out.println("Welcome to the Virtual Campus Exploration Program!");
        System.out.print("Enter the campus file name (e.g. umw_campus.txt): ");
        String campusFileName = s.nextLine().trim();

        return new Campus(campusFileName);
    }

    /**
     * Method that parses raw user input strings, separating command verbs from optional arguments,
     * and instantiates and returns the corresponding UserInputCommand object.
     *
     * @param input the raw string entered by the user at the console prompt
     * @return A UserInputCommand implementation (MovementCommand, PickupCommand, DropCommand, BackPackCommand, or InvalidCommand)
     */
    public static UserInputCommand parseInput(String input) {
        String[] parts = input.split("\\s+", 2);
        String verb = parts[0].toLowerCase();
        String argument = (parts.length > 1) ? parts[1].trim() : "";

        switch (verb) {
            case "n":
            case "s":
            case "e":
            case "w":
                return new MovementCommand(verb);
            case "backpack":
            case "b":
                return new BackPackCommand();
            case "pickup":
            case "p":
                return new PickupCommand(argument);
            case "drop":
            case "d":
                return new DropCommand(argument);
            default:
                return new InvalidCommand(input);
        }
    }
}