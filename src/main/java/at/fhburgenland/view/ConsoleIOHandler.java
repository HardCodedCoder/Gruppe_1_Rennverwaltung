package at.fhburgenland.view;

import at.fhburgenland.RaceManagementService;
import at.fhburgenland.entities.*;
import at.fhburgenland.enumerations.BackgroundColor;
import at.fhburgenland.enumerations.ForegroundColor;
import at.fhburgenland.interfaces.*;
import at.fhburgenland.menu.MenuItem;
import at.fhburgenland.utility.Utility;
import at.fhburgenland.validators.MenuItemInputValidator;
import at.fhburgenland.view.table.Column;
import at.fhburgenland.view.table.TableRenderer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

@Slf4j
/**
 * This class manages the input and output from and to the console and decouples UI from the Business Logic.
 */
public class ConsoleIOHandler implements IOHandler {
    /**
     * Color code to reset the color.
     */
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * Holds the padding needed to draw the menu.
     */
    private static final int MENU_WIDTH_PADDING = 5;

    /**
     * Holds the scanner instance reading input from the console.
     */
    private final Scanner inputScanner;

    /**
     * Initializes a new instance of the ConsoleIOHandler class.
     */
    public ConsoleIOHandler() {
        this.inputScanner = new Scanner(System.in);
    }

    /**
     * Asks the user to input a number.
     *
     * @param prompt The prompt to show to the user.
     * @return An integer.
     */
    @Override
    public Number getNumberFromUser(String prompt, boolean skipValueCheck) {
        boolean correctInput = false;
        Number result = null;

        while (!correctInput) {
            this.print(prompt + ": ");
            String userinput = "";
            try {
                userinput = this.inputScanner.nextLine();
            } catch (NoSuchElementException e) {
                this.printErrorMessage("Unzulässiges Format! Bitte gib eine Zahl ein.");
            }

            try {
                double doubleInput = Double.parseDouble(userinput);
                if (doubleInput == (int) doubleInput) {
                    result = (int) doubleInput;
                } else {
                    result = doubleInput;
                }
                correctInput = true;
            } catch (NumberFormatException e) {
                if (skipValueCheck) {
                    this.println();
                    return 0;
                }
                this.printErrorMessage("Unzulässiges Format! Bitte gib eine Zahl ein.");
            }
        }

        System.out.println();
        return result;
    }

    public void renderDriverTable(List<Driver> data) {
        System.out.println();
        TableRenderer<Driver> driverTableRenderer = new TableRenderer<>();
        TableColumn<Driver> driverIdColumn = new Column<>("ID", Driver::getDriverId);
        TableColumn<Driver> driverNameColumn = new Column<>("VORNAME", Driver::getFirstName);
        TableColumn<Driver> driverLastNameColumn = new Column<>("NACHNAME", Driver::getLastName);
        TableColumn<Driver> driverNationalityColumn = new Column<>("NATIONALITÄT", Driver::getNationality);
        TableColumn<Driver> driverVehicleColumn = new Column<>("VEHICLE ID", Driver::getVehicleId);
        TableColumn<Driver> driverTeamColumn = new Column<>("TEAM ID", Driver::getTeamId);
        driverTableRenderer.renderTable(data, driverIdColumn, driverNameColumn, driverLastNameColumn,
                driverNationalityColumn, driverVehicleColumn, driverTeamColumn);
        System.out.println();
    }

    /**
     * Gets the menu item the user wants to select.
     *
     * @param consolePage The page containing all the items.
     * @return The selected menu item.
     */
    @Override
    public MenuItem getMenuItemSelection(MenuPage consolePage) {
        boolean correctInput = false;
        int highestMenuItemNumber = consolePage.getMenuItems().get(consolePage.getMenuItems().size() - 1).getNumber();
        MenuItemInputValidator validator = new MenuItemInputValidator(highestMenuItemNumber);
        MenuItem item = null;

        while (!correctInput) {
            int userInput = getNumberFromUser("Please select a menuitem-number from the list above", false).intValue();
            if (validator.validateCorrectMenuSelectionInput(userInput)) {
                correctInput = true;
                for (MenuItem menuItem : consolePage.getMenuItems()) {
                    if (menuItem.getNumber() == userInput) {
                        item = menuItem;
                        break;
                    }
                }
            } else {
                this.printErrorMessage(
                        MessageFormat.format("You can not select any number than from {0} to {1}",
                                validator.getLowestMenuItemNumber(),
                                highestMenuItemNumber)
                );
            }
        }

        return item;
    }

    /**
     * Prints the menu to the user interface.
     */
    @Override
    public void printMenu(Menu toPrint) {
        int menuWidth = Utility.calculatePageWidth(toPrint) + MENU_WIDTH_PADDING;
        this.drawLine(menuWidth);
        this.printColoredLn(toPrint.getCurrent().getMenuPageType().getLabel(), ForegroundColor.MAGENTA, BackgroundColor.BLACK);
        this.drawLine(menuWidth);
        this.printMenuPage(toPrint.getCurrent());
        this.drawLine(menuWidth);
    }


    /**
     * Prints an error message in a specific format to the console.
     *
     * @param toPrint The error message to print to the console.
     */
    @Override
    public void printErrorMessage(String toPrint) {
        this.println();
        this.printColored("[ERROR]: ", ForegroundColor.RED);
        this.println(toPrint);
        this.waitForConfirmation();
    }


    /**
     * Prints a message to the user interface.
     *
     * @param toPrint The message to print.
     */
    @Override
    public void println(String toPrint) {

        System.out.println(toPrint);
    }

    /**
     * Prints the menupage to the user interface.
     *
     * @param consolePage The console page to print.
     */
    @Override
    public void printMenuPage(MenuPage consolePage) {
        ForegroundColor color = ForegroundColor.BLUE;
        for (MenuItem item : consolePage.getMenuItems()) {
            String toPrint = item.toString();
            this.printColored(Character.toString(toPrint.charAt(0)), color);
            System.out.println(toPrint.substring(1));
            color = color.next();
        }
    }

    /**
     * This method draws a line to the console
     *
     * @param numberOfSigns The total number of signs to draw.
     */
    public void drawLine(int numberOfSigns) {
        System.out.println("━".repeat(numberOfSigns));
    }

    /**
     * Prints a text to the console.
     *
     * @param toPrint         The text to print.
     * @param foregroundColor The foreground color of the text.
     */
    private void printColored(String toPrint, ForegroundColor foregroundColor) {
        System.out.print(foregroundColor.getCode() + toPrint + ANSI_RESET);
    }

    /**
     * Prints a colored text to the console followed by a newline char.
     *
     * @param toPrint         The text to print.
     * @param foregroundColor The foreground color of the text.
     * @param backgroundColor The background color of the text.
     */
    public void printColoredLn(String toPrint, ForegroundColor foregroundColor, BackgroundColor backgroundColor) {
        System.out.println(foregroundColor.getCode() + backgroundColor.getCode() + toPrint + ANSI_RESET);
    }

    /**
     * Prints a colored text to the console followed by a newline char.
     *
     * @param toPrint         The text to print.
     * @param foregroundColor The foreground color of the text.
     */
    public void printColoredLn(String toPrint, ForegroundColor foregroundColor) {
        System.out.println(foregroundColor.getCode() + toPrint + ANSI_RESET);
    }

    /**
     * Prints the prompt to the user interface and returns a string containing the input.
     *
     * @param prompt     The text to print to the user interface.
     * @param forceInput True, if the user needs to enter something which is not empty.
     * @return The input of the user.
     */
    @Override
    public String askUserForInput(String prompt, boolean forceInput) {
        boolean exit = !forceInput;
        String input;
        do {
            System.out.print(prompt + ": ");
            input = this.inputScanner.nextLine();
            if (forceInput && input.isEmpty()) {
                this.printErrorMessage("Your input must not be empty! Please try again!");
            } else {
                exit = true;
            }
        } while (!exit);

        this.println();
        return input;
    }

    @Override
    public boolean askToContinue(String prompt) {
        boolean exit = false;
        while (!exit) {
            String input = this.askUserForInput(prompt, true);
            switch (input) {
                case "y" -> {
                    return true;
                }
                case "n" -> {
                    return false;
                }
                default -> this.printErrorMessage("Invalid input. Please enter y or n.");
            }

        }
        return false;
    }

    @Override
    public void renderRaceTable(List<Race> races) {
        System.out.println();
        TableRenderer<Race> raceTableRenderer = new TableRenderer<>();
        TableColumn<Race> raceIdColumn = new Column<>("ID", Race::getRaceId);
        TableColumn<Race> raceNameColumn = new Column<>("NAME", Race::getName);
        TableColumn<Race> raceDateColumn = new Column<>("DATUM", Race::getDate);
        TableColumn<Race> raceTrackIdColumn = new Column<>("RENNSTRECKEN ID", Race::getRaceTrackId);
        raceTableRenderer.renderTable(races, raceIdColumn, raceNameColumn, raceDateColumn, raceTrackIdColumn);
        System.out.println();
    }

    @Override
    public void renderOutageTable(List<Outage> outages) {
        System.out.println();
        TableRenderer<Outage> outageTableRenderer = new TableRenderer<>();
        TableColumn<Outage> driverId = new Column<>("FAHRER ID", Outage::getDriverId);
        TableColumn<Outage> raceId = new Column<>("RENNEN ID", Outage::getRaceId);
        TableColumn<Outage> reasonColumn = new Column<>("AUSFALLSZENARIO", Outage::getReason);
        outageTableRenderer.renderTable(outages, driverId, raceId, reasonColumn);
        System.out.println();
    }

    @Override
    public void renderRaceTrackTable(List raceTracks) {
        System.out.println();
        TableRenderer<RaceTrack> raceTrackTableRenderer = new TableRenderer<>();
        TableColumn<RaceTrack> raceTrackColumn = new Column<>("RENNSTRECKEN ID", RaceTrack::getRaceTrackId);
        TableColumn<RaceTrack> raceTrackNameColumn = new Column<>("NAME", RaceTrack::getName);
        TableColumn<RaceTrack> raceTrackStateColumn = new Column<>("BUNDESLAND", RaceTrack::getState);
        TableColumn<RaceTrack> raceTrackCityColumn = new Column<>("STADT", RaceTrack::getCity);
        raceTrackTableRenderer.renderTable(raceTracks, raceTrackColumn, raceTrackNameColumn, raceTrackStateColumn, raceTrackCityColumn);
        System.out.println();
    }

    @Override
    public void renderVehicleTable(List vehicles) {
        System.out.println();
        TableRenderer<Vehicle> vehicleTableRenderer = new TableRenderer<>();
        TableColumn<Vehicle> vehicleIdColumn = new Column<>("ID", Vehicle::getVehicleId);
        TableColumn<Vehicle> vehicleBrandColumn = new Column<>("MARKE", Vehicle::getBrand);
        TableColumn<Vehicle> vehicleModelColumn = new Column<>("MODELL", Vehicle::getModel);
        TableColumn<Vehicle> vehicleManufactDateColumn = new Column<>("BAUJAHR", Vehicle::getConstructionYear);
        vehicleTableRenderer.renderTable(vehicles, vehicleIdColumn, vehicleBrandColumn, vehicleModelColumn, vehicleManufactDateColumn);
        System.out.println();
    }

    @Override
    public void renderTeamTable(List teams) {
        System.out.println();
        TableRenderer<Team> teamTableRenderer = new TableRenderer<>();
        TableColumn<Team> teamIdColumn = new Column<>("ID", Team::getTeamId);
        TableColumn<Team> teamNameColumn = new Column<>("NAME", Team::getName);
        TableColumn<Team> teamFoundingYearColumn = new Column<>("GRÜNDUNGSJAHR", Team::getFoundingYear);
        TableColumn<Team> teamSponsorColumn = new Column<>("SPONSOR ID", Team::getSponsorId);
        teamTableRenderer.renderTable(teams, teamIdColumn, teamNameColumn, teamFoundingYearColumn, teamSponsorColumn);
        System.out.println();
    }

    @Override
    public void renderSponsorTable(List sponsors) {
        System.out.println();
        TableRenderer<Sponsor> sponsorTableRenderer = new TableRenderer<>();
        TableColumn<Sponsor> sponsorIdColumn = new Column<>("ID", Sponsor::getSponsorId);
        TableColumn<Sponsor> sponsorNameColumn = new Column<>("NAME", Sponsor::getName);
        sponsorTableRenderer.renderTable(sponsors, sponsorIdColumn, sponsorNameColumn);
        System.out.println();
    }

    @Override
    public void renderResultTable(List results) {
        System.out.println();
        TableRenderer<Result> resultTableRenderer = new TableRenderer<>();
        TableColumn<Result> raceIdColumn = new Column<>("RENNEN_ID", Result::getRaceId);
        TableColumn<Result> driverIdColumn = new Column<>("ERSTER", Result::getFirstId);
        TableColumn<Result> secondDriverIdColumn = new Column<>("ZWEITER", Result::getSecondId);
        TableColumn<Result> thirdDriverIdColumn = new Column<>("DRITTER", Result::getThirdId);
        resultTableRenderer.renderTable(results, raceIdColumn, driverIdColumn, secondDriverIdColumn, thirdDriverIdColumn);
        System.out.println();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void waitForConfirmation() {
        System.out.print("Press any key to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            this.printErrorMessage(e.getMessage());
        }

        System.out.println();
    }

    /**
     * Prints all values of an enumeration to the console.
     */
    public <T extends Enum<T>> void printEnumValues(Class<T> enumClass) {
        String header = enumClass.getSimpleName().toUpperCase() + "-VALUES:";
        this.printColoredLn(header, ForegroundColor.BLACK, BackgroundColor.MAGENTA);
        this.drawLine(header.length());
        ForegroundColor color = ForegroundColor.MAGENTA;
        int counter = 1;
        for (T enumValue : enumClass.getEnumConstants()) {
            this.printColoredLn(enumValue.toString(), color);
            if (counter % 2 != 0)
                color = color.next();
            else
                color = color.previous();
            counter++;
        }
        this.drawLine(header.length());
        this.println();
    }


    /**
     * Prints a newline sign to the console to switch to the next line.
     */
    private void println() {
        System.out.println();
    }

    /**
     * Prints a message to the console without newline.
     *
     * @param toPrint The text to print.
     */
    public void print(String toPrint) {
        System.out.print(toPrint);
    }

    public int getEntityIdFromUser(ReadEntity readEntityFunctionInterface, Class clazz, String prompt, String errorMessage, Service service) {
        boolean exit = false;
        int id = 0;
        while (!exit) {
            readEntityFunctionInterface.readEntity();
            id = this.getNumberFromUser(prompt, true).intValue();
            if (RaceManagementService.getEntityManagerMap().get(clazz).read(id) == null) {
                service.getIOHandler().printErrorMessage(errorMessage);
                this.println("");
            } else {
                exit = true;
            }
        }

        return id;
    }

    public Enum promptForEnumValue(Class clazz) {
        boolean exit = false;
        Enum value = null;
        while (!exit) {
            this.printEnumValues(clazz);
            String tmp = this.askUserForInput("Please enter a value from the list above", true).toUpperCase();
            try {
                value = this.createEnumValueFromInput(tmp, clazz);
                exit = true;
            } catch (IllegalArgumentException e) {
                this.printErrorMessage(e.getMessage() + " Please try again!");
            }
        }
        return value;
    }

    private Enum createEnumValueFromInput(String input, Class clazz) throws IllegalArgumentException {
        if (input == null || input.isEmpty())
            throw new IllegalArgumentException("The entered input mustn't be null or empty!");
        return Enum.valueOf(clazz, input);
    }

    public void printWarningMessage(String toPrint) {
        this.println();
        this.printColored("[WARNING]: ", ForegroundColor.YELLOW);
        this.println(toPrint);
        this.println();
    }

}
