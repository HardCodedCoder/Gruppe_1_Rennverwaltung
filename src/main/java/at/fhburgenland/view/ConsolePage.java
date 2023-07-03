package at.fhburgenland.view;

import at.fhburgenland.enumerations.MenuPages;
import at.fhburgenland.interfaces.IOHandler;
import at.fhburgenland.interfaces.MenuPage;
import at.fhburgenland.menu.MenuItem;

import java.util.List;

/**
 * A Page of a menu which can be rendered to the console.
 */
public class ConsolePage implements MenuPage
{
    /**
     * Holds a List containing all the menu items of the page.
     */
    private final List<MenuItem> menuItems;

    private final MenuPages menuPage;

    /**
     * Initializes a new instance of the ConsolePage class.
     * @param menuItems The selectable menu items of the page.
     * @param menuPage The MenuPage which contains the menu items.
     */
    public ConsolePage(List<MenuItem> menuItems, MenuPages menuPage)
    {
        this.menuItems = menuItems;
        this.menuPage = menuPage;
    }

    /**
     * Gets a list with the menu items of the page.
     *
     * @return A List containing the menu items of the page.
     */
    @Override
    public List<MenuItem> getMenuItems() {
        return this.menuItems;
    }

    /**
     * Selects a specific menu item of the page.
     * @param ioHandler the ioHandler which gets the user selection.
     * @return
     */
    @Override
    public MenuItem getSelected(IOHandler ioHandler)
    {
        return ioHandler.getMenuItemSelection(this);
    }

    /**
     * Gets the type of the menu page.
     *
     * @return The type of the menu page.
     */
    @Override
    public MenuPages getMenuPageType() {
        return this.menuPage;
    }

    /**
     * Renders the renderable object to the user interface.
     *
     * @param ioHandler The ioHandler which renders the object.
     */
    @Override
    public void getRendered(IOHandler ioHandler) {
        ioHandler.printMenuPage(this);
    }
}
