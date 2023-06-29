package at.fhburgenland.org.lecture.enumerations;

/**
 * This enum provides several ConsoleColors with their corresponding ANSI Color codes.
 */
public enum BackgroundColor
{
    BLACK("\u001b[40m"),
    BLUE("\u001b[44m"),
    CYAN("\u001b[46m"),
    GREEN("\u001b[42m"),
    MAGENTA("\u001b[45m"),
    RED("\u001b[41m"),
    WHITE("\u001b[47m"),
    YELLOW("\u001b[43m"),
    BRIGHT_BLACK("\u001b[40;1m"),
    BRIGHT_BLUE("\u001b[44;1m"),
    BRIGHT_CYAN("\u001b[46;1m"),
    BRIGHT_GREEN("\u001b[42;1m"),
    BRIGHT_MAGENTA("\u001b[45;1m"),
    BRIGHT_RED("\u001b[41;1m"),
    BRIGHT_WHITE("\u001b[47;1m"),
    BRIGHT_YELLOW("\u001b[43;1m");

    /**
     * Holds the foreground color code.
     */
    private final String code;

    /**
     * Initializes a new "instance" of the BackgroundColor enum value.
     * @param code The color code of the color.
     */
    BackgroundColor(String code) {
        this.code = code;
    }

    /**
     * Gets the ANSI Code of the Background Color.
     * @return
     */
    public String getCode() {
        return code;
    }
}
