package at.fhburgenland.org.lecture.interfaces;

import at.fhburgenland.org.lecture.entities.Driver;
import at.fhburgenland.org.lecture.entities.Race;
import at.fhburgenland.org.lecture.enumerations.BackgroundColor;
import at.fhburgenland.org.lecture.enumerations.ForegroundColor;
import at.fhburgenland.org.lecture.menu.MenuItem;

import java.util.List;

public interface IOHandler {
    /**
     * Prints the menu to the user interface.
     * @return
     */
    void printMenu(Menu toPrint);

    /**
     * Prints an error message in a specific format to the user interface.
     * @param toPrint The message to print.
     */
    void printErrorMessage(String toPrint);

    /**
     * Prints a warning message in a specific format to the user interface.
     * @param toPrint The message to print.
     */
    void printWarningMessage(String toPrint);

    /**
     * Prints a message to the user interface.
     * @param toPrint The message to print.
     */
    void println(String toPrint);

    /**
     * Prints the menupage to the user interface.
     * @param consolePage
     */
    void printMenuPage(MenuPage consolePage);

    /**
     * Gets the menu item the user wants to select.
     * @param consolePage The page containing all the items.
     * @return The selected menu item.
     */
    MenuItem getMenuItemSelection(MenuPage consolePage);

    /**
     * Waits for the user to press a key or similar.
     */
    void waitForConfirmation();

    /**
     * Prints a colored text to the console followed by a newline char.
     * @param toPrint The text to print.
     * @param foregroundColor The foreground color of the text.
     */
    void printColoredLn(String toPrint, ForegroundColor foregroundColor);

    /**
     * Prints a colored text to the console followed by a newline char.
     * @param toPrint The text to print.
     * @param foregroundColor The foreground color of the text.
     * @param backgroundColor The background color of the text
     */
    void printColoredLn(String toPrint, ForegroundColor foregroundColor, BackgroundColor backgroundColor);

    /**
     * Prints all values of an enumeration to the console.
     */
    <T extends Enum<T>> void printEnumValues(Class<T> enumClass);

    /**
     * Prints a text to the output.
     * @param toPrint The text to print
     */
    void print(String toPrint);

    void drawLine(int numberOfSigns);

    Number getNumberFromUser(String prompt, boolean skipValueCheck);

    public void renderDriverTable(List<Driver> data);

    String askUserForInput(String prompt, boolean forceInput);

    boolean askToContinue(String prompt);

    void renderRaceTable(List<Race> races);
}
