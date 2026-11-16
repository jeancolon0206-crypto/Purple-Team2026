/**
 * Interface defining the standard contract for all user input command objects in the application.
 *
 * @author Jean Colon
 * @version 1.0
 * Date: 10/26/2026
 */
public interface UserInputCommand {

    /**
     * Method that executes the specific behavior associated with the user command.
     *
     * @return A string result resulting from command execution.
     */
    String carryOut();
}
