package at.fhburgenland.org.lecture.interfaces;

import at.fhburgenland.org.lecture.entities.*;
import at.fhburgenland.org.lecture.enumerations.BackgroundColor;
import at.fhburgenland.org.lecture.enumerations.ForegroundColor;
import at.fhburgenland.org.lecture.menu.MenuItem;

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
}
