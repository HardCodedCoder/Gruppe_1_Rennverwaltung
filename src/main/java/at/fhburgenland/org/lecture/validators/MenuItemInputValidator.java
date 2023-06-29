package at.fhburgenland.org.lecture.validators;

/**
 * This class can be used to validate user input.
 */
public class MenuItemInputValidator
{
    /**
     * Holds the lowest menu item number.
     */
    private final int lowestMenuItemNumber;

    /**
     * Holds the highest menu item number.
     */
    private final int highestMenuItemNumber;
    /**
     * Initializes a new instance of the InputValidator class.
     */
    public MenuItemInputValidator(int highestMenuItemNumber)
    {
        this.lowestMenuItemNumber = 1;
        this.highestMenuItemNumber = highestMenuItemNumber;
    }

    /**
     * Validates if the user has entered a valid menu item number.
     * @param userSelection The user selection to validate.
     * @return True, if the number entered by the user is correct, otherwise false.
     */
    public boolean validateCorrectMenuSelectionInput(int userSelection)
    {
        if (userSelection >= lowestMenuItemNumber && userSelection <= highestMenuItemNumber)
            return true;
        else
            return false;
    }

    /**
     * Gets the lowest menu item number.
     * @return
     */
    public int getLowestMenuItemNumber() { return this.lowestMenuItemNumber; }
}
