/**
 * Command class handling unrecognized or invalid user inputs during the simulation.
 * Implements the UserInputCommand interface to provide help instructions.
 *
 * @author Jean Colon
 * @version 1.0
 * Date: 10/26/2026
 */
public class InvalidCommand implements UserInputCommand {
    private String InvalidCommand;

    /**
     * Constructor that accepts the invalid input string entered by the user.
     *
     * @param invalidCommand the raw string entered by the user that failed to match any valid command
     */
    public InvalidCommand(String invalidCommand) {
        this.InvalidCommand = invalidCommand;
    }

    String result = null;
    /**
     * Method that executes the invalid command handler, returning an error feedback message along with a list of valid commands.
     *
     * @return A formatted help string detailing valid movement, quit, and action commands.
     */
    public String carryOut() {

        result = "You Typed: " + InvalidCommand + "." + " That's An Invalid Command."
                + "\n" + "Try Again With a Valid Command."
                + "\n" + "Here is a list of valid commands:"
                + "\n" + "Movement Commands, such as n, s, e, or w."
                + "\n" + "The Quit Command, q."
                + "\n" + "Action Commands, such as backpack, pickup (Item Name), or drop (Item Name).";
        return result;
    }
}
