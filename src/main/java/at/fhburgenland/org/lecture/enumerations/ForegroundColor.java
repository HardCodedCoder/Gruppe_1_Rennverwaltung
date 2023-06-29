package at.fhburgenland.org.lecture.enumerations;

/**
 *  * This enum provides several ConsoleColors with their corresponding ANSI Color codes.
 */
public enum ForegroundColor
{
    BLACK("\u001b[30m"),
    BLUE("\u001b[34m"),
    CYAN("\u001b[36m"),
    GREEN("\u001b[32m"),
    MAGENTA("\u001b[35m"),
    RED("\u001b[31m"),
    WHITE("\u001b[37m"),
    YELLOW("\u001b[33m"),
    BRIGHT_BLACK("\u001b[30;1m"),
    BRIGHT_BLUE("\u001b[34;1m"),
    BRIGHT_CYAN("\u001b[36;1m"),
    BRIGHT_GREEN("\u001b[32;1m"),
    BRIGHT_MAGENTA("\u001b[35;1m"),
    BRIGHT_RED("\u001b[31;1m"),
    BRIGHT_WHITE("\u001b[37;1m"),
    BRIGHT_YELLOW("\u001b[33;1m");

    /**
     * Holds the ANSI Color code.
     */
    private final String code;

    /**
     * Initializes a new "instance" of the ForegroundColor enum value.
     * @param code The color code of the color.
     */
    ForegroundColor(String code) {
        this.code = code;
    }

    /**
     * Gets the ANSI Code of the color.
     * @return The ANSI code of the color as string.
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets the next foreground color.
     * @return A value of the ForegroundColor enumeration.
     */
    public ForegroundColor next() {
        return values()[(ordinal() + 1) % values().length];
    }

    /**
     * Gets the previous foreground color.
     * @return A value of the ForegroundColor enumeration.
     */
    public ForegroundColor previous() {
        int ordinal = this.ordinal() - 1;
        if (ordinal < 0)
            ordinal = ForegroundColor.values().length - 1;
        return values()[ordinal];
    }
}
