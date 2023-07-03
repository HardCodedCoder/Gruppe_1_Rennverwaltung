package at.fhburgenland.view;

import at.fhburgenland.enumerations.MenuPages;
import at.fhburgenland.interfaces.IOHandler;
import at.fhburgenland.interfaces.Menu;
import at.fhburgenland.interfaces.MenuPage;
import at.fhburgenland.validators.IOHandlerValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsoleMenu implements Menu
{
    /**
     * Holds the menu pages of the menu.
     */
    private final List<MenuPage> pages;

    private MenuPage currentPage;
    /**
     * Holds the title of the menu.
     */
    private final String menuTitle;

    /**
     * Initializes a new Instance of the ConsoleMenu class.
     */
    private ConsoleMenu() {
        this.pages = new ArrayList<>();
        this.currentPage = null;
        this.menuTitle = "";
    }
    /**
     * Initializes a new instance of the ConsoleMenu class.
     * @param pages The list of pages which can be displayed in the menu.
     */
    public ConsoleMenu(List<MenuPage> pages, String menuTitle)
    {
        this.pages = pages;
        this.menuTitle = menuTitle;
        this.currentPage = this.pages.get(0);
    }

    /**
     * Renders the renderable object to the user interface.
     * @param ioHandler
     */
    @Override
    public void getRendered(IOHandler ioHandler) {
        IOHandlerValidator validator = new IOHandlerValidator();
        if (validator.validate(ioHandler))
            ioHandler.printMenu(this);
        else
            //TODO: Implement logging!
            System.out.println("Replace me with logging");
    }

    /**
     * Gets the current selected menu page.
     * @return The current selected menu poge.
     */
    @Override
    public MenuPage getCurrent() {
        return this.currentPage;
    }

    /**
     * Selects the current menu page.
     *
     * @param menuPages The menu page to select.
     */
    @Override
    public void selectCurrentPage(MenuPages menuPages) {
        Optional<MenuPage> toSetAsCurrent = this.pages.stream().filter(menuPage -> menuPage.getMenuPageType() == menuPages).findFirst();
        if (toSetAsCurrent.isPresent())
            this.currentPage = toSetAsCurrent.get();
        else
            throw new IllegalStateException("Unexpected state!");
    }

    /**
     * Gets the title of the menu.
     * @return A string representing the title of the menu.
     */
    @Override
    public String getTitle() {
        return this.menuTitle;
    }

    /**
     * Gets the pages of the menu.
     * @return
     */
    public List<MenuPage> getMenuPages() { return this.pages; }
}
