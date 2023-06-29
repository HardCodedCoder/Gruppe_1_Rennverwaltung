package at.fhburgenland.org.lecture.interfaces;

import at.fhburgenland.org.lecture.enumerations.MenuPages;
import at.fhburgenland.org.lecture.menu.MenuItem;

import java.util.List;

public interface MenuPage extends Renderable
{
    /**
     * Gets a list with the menu items of the page.
     * @return A List containing the menu items of the page.
     */
    List<MenuItem> getMenuItems();

    /**
     * Selects a specific menu item of the page.
     * @param ioHandler The ioHandler which gets the selection from the user.
     * @return
     */
    MenuItem  getSelected(IOHandler ioHandler);

    /**
     * Gets the type of the menu page.
     * @return The type of the menu page.
     */
    MenuPages getMenuPageType();
}
