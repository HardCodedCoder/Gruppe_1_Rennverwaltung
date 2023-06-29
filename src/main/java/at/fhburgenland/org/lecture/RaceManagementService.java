package at.fhburgenland.org.lecture;

import at.fhburgenland.org.lecture.enumerations.MenuPages;
import at.fhburgenland.org.lecture.exceptions.InstantiateServiceException;
import at.fhburgenland.org.lecture.interfaces.IOHandler;
import at.fhburgenland.org.lecture.interfaces.Menu;
import at.fhburgenland.org.lecture.interfaces.MenuPage;
import at.fhburgenland.org.lecture.interfaces.Service;
import at.fhburgenland.org.lecture.menu.MenuItem;
import at.fhburgenland.org.lecture.validators.IOHandlerValidator;
import at.fhburgenland.org.lecture.view.ConsoleMenu;
import at.fhburgenland.org.lecture.view.ConsolePage;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class RaceManagementService implements Service {
    /**
     * Signals whether the service should exit or not.
     */
    private boolean exitServiceLoop = false;

    /**
     * Holds whether the business logic should return to the main menu or not.
     */
    private boolean returningToMain = false;

    /**
     * Holds the menu of the service.
     */
    private final Menu menu;

    /**
     * Holds an object interacting with the user interface.
     */
    private final IOHandler ioHandler;

    /**
     * Holds the factory creating the desired menu pages.
     */
    private final ConcreteMenuPageFactory menuPageFactory;

    /**
     * Creates a new instance of the service.
     * @param ioHandler The IO handler taking care of the user interaction.
     */
    public RaceManagementService(IOHandler ioHandler) throws InstantiateServiceException {
        IOHandlerValidator validator = new IOHandlerValidator();
        if (!validator.validate(ioHandler))
            throw new InstantiateServiceException("The passed IOHandler is invalid.");
        this.ioHandler = ioHandler;
        this.menuPageFactory = new ConcreteMenuPageFactory(this);
        this.menu = createMenu(ioHandler);
    }

    private Menu createMenu(IOHandler ioHandler) {
        log.info("Creating the main menu...");
        List<MenuPage> menuPages = Arrays.asList(
                this.menuPageFactory.createMenuPage(MenuPages.MAIN),
                this.menuPageFactory.createMenuPage(MenuPages.DRIVER),
                this.menuPageFactory.createMenuPage(MenuPages.OUTAGE),
                this.menuPageFactory.createMenuPage(MenuPages.RACE),
                this.menuPageFactory.createMenuPage(MenuPages.RACETRACK),
                this.menuPageFactory.createMenuPage(MenuPages.RESULT),
                this.menuPageFactory.createMenuPage(MenuPages.SPONSOR),
                this.menuPageFactory.createMenuPage(MenuPages.TEAM),
                this.menuPageFactory.createMenuPage(MenuPages.VEHICLE)
        );
        Menu menu = new ConsoleMenu(menuPages, MenuPages.MAIN.getLabel());
        return menu;
    }

    /**
     * Runs the service which hosts the business logic.
     */
    @Override
    public void runService() {
        while (!exitServiceLoop)
        {
            this.menu.getRendered(this.ioHandler);
            MenuItem item = this.menu.getCurrent().getSelected(this.ioHandler);
            item.notifyObservers();
            this.ioHandler.println("");
            if (this.returningToMain)
                this.returningToMain = false;
        }
    }

    /**
     * Signals the service to exit.
     */
    @Override
    public void signalExit() {
        this.exitServiceLoop = true;
    }

    /**
     * Gets the IOHandler.
     *
     * @return
     */
    @Override
    public IOHandler getIOHandler() {
        return this.ioHandler;
    }

    /**
     * Gets the entire menu of the service.
     *
     * @return
     */
    @Override
    public Menu getMenu() {
        return this.menu;
    }

    /**
     * Checks weather the service should return to the main menu or not.
     */
    @Override
    public boolean isReturningToMain() {
        return this.returningToMain;
    }

    /**
     * Signals the service to return to the main menu.
     */
    @Override
    public void signalReturningToMain() {
        this.returningToMain = true;
    }


}
