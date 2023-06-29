package at.fhburgenland.org.lecture.interfaces;

import at.fhburgenland.org.lecture.enumerations.MenuPages;
import at.fhburgenland.org.lecture.interfaces.Renderable;

public interface Menu extends Renderable {
    /**
     * Gets the current selected menu page.
     * @return The current selected menu poge.
     */
    MenuPage getCurrent();

    /**
     * Selects the current menu page.
     * @param menuPages The menu page to select.
     */
    void selectCurrentPage(MenuPages menuPages);

    /**
     * Gets the title of the menu.
     * @return A string representing the title of the menu.
     */
    String getTitle();
}
