package at.fhburgenland.org.lecture.utility;

import at.fhburgenland.org.lecture.interfaces.Menu;
import at.fhburgenland.org.lecture.interfaces.MenuPage;
import at.fhburgenland.org.lecture.menu.MenuItem;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public final class Utility
{
    /**
     * Calculates the longest width of the menu items to calculate the menu width.
     * @param menu The menu to calculate the width for.
     * @return The total width of the menu.
     */
    public static int calculatePageWidth(Menu menu)
    {
        int width = menu.getTitle().length();

        MenuPage page = menu.getCurrent();

        for (MenuItem menuItem : page.getMenuItems())
        {
            // <Number> - <label --> + 3 signs for " - "
            int menuItemWidth = menuItem.getLabel().length() + Integer.toString(menuItem.getNumber()).length() + 3;
            if (menuItemWidth > width)
                width = menuItemWidth;
        }

        // Add Padding
        return width;
    }

    /**
     * Formats the datetime String to format: dd.MM.yyyy HH:mm:ss.
     * @param unformatted A datetime String.
     * @return If successfull, a datetime String of format: yyyy-MM-dd'T'HH:mm:ss,
     *          otherwise the original passed string.
     */
    public static String getFormattedDate(String unformatted)
    {
        // I know, that's hacky but a rushed fix.
        if (unformatted.split(":").length == 2)
            unformatted += ":00";

        var tmpPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String dateTime = unformatted;
        try {
            LocalDateTime tmp = LocalDateTime.parse(unformatted, tmpPattern);
            var pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            dateTime = pattern.format(tmp);
        }
        catch (DateTimeParseException e)
        {
            log.warn(e.getMessage());
        }

        return dateTime;
    }
}
