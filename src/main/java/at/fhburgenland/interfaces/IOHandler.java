package at.fhburgenland.interfaces;

import at.fhburgenland.database.entities.*;
import at.fhburgenland.enumerations.BackgroundColor;
import at.fhburgenland.enumerations.ForegroundColor;
import at.fhburgenland.menu.MenuItem;

import java.time.LocalDate;
import java.util.List;

public interface IOHandler {
    /**
     * Prints the menu to the user interface.
     */
    void printMenu(Menu toPrint);

    /**
     * Prints an error message in a specific format to the user interface.
     * @param toPrint The message to print.
     */
    void printErrorMessage(String toPrint);

    /**
     * Prints a message to the user interface.
     * @param toPrint The message to print.
     */
    void println(String toPrint);

    /**
     * Prints the menu-page to the user interface.
     * @param consolePage The page to render.
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
     * Prints a text to the output.
     * @param toPrint The text to print
     */
    void print(String toPrint);

    void drawLine(int numberOfSigns);

    Number getNumberFromUser(String prompt, boolean skipValueCheck);

    void renderDriverTable(List<Driver> data);

    String askUserForInput(String prompt, boolean forceInput);

    boolean askToContinue(String prompt);

    void renderRaceTable(List<Race> races);

    void renderOutageTable(List<Outage> outages);

    void renderRaceTrackTable(List<RaceTrack> raceTracks);

    void renderVehicleTable(List<Vehicle> vehicles);

    void renderTeamTable(List<Team> teams);

    void renderSponsorTable(List<Sponsor> sponsors);

    void renderResultTable(List<Result> results);

    LocalDate getDateFromUser(String prompt);

    int getEntityIdFromUser(ReadEntity readEntityFunctionInterface,Class clazz, String prompt, String errorMessage, Service service);

    /**
     * Prints all values of an enumeration to the console.
     */
    <T extends Enum<T>> void printEnumValues(Class<T> enumClass);

    Enum promptForEnumValue(Class clazz);
}
