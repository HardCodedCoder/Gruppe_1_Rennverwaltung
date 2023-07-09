package at.fhburgenland;

import at.fhburgenland.entities.*;
import at.fhburgenland.interfaces.IOHandler;
import at.fhburgenland.interfaces.Menu;
import at.fhburgenland.interfaces.MenuPage;
import at.fhburgenland.menu.MenuItem;
import at.fhburgenland.enumerations.MenuPages;
import at.fhburgenland.exceptions.InstantiateServiceException;
import at.fhburgenland.interfaces.Service;
import at.fhburgenland.validators.IOHandlerValidator;
import at.fhburgenland.view.ConsoleMenu;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    private static final Map<Class, BaseEntityManager> entityManagerMap = initializeEntityManagerMap();

    // TODO: Does not need to be static anymore as Service exists only once.
    public static Map<Class, BaseEntityManager> getEntityManagerMap() { return entityManagerMap; }

    /**
     * Initializes all entity managers to have them once running in memory. Creating and establishing for each
     * transaction an entity manager costs a lot of performance (time).
     * @return The Initialized entity managers stored in a map.
     */
    private static Map<Class, BaseEntityManager> initializeEntityManagerMap()
    {
        return Map.of(
                Driver.class, new BaseEntityManager<Driver>(
                        Persistence.createEntityManagerFactory("fahrer").createEntityManager(), Driver.class),
                Race.class, new RaceEntityManager(
                        Persistence.createEntityManagerFactory("rennen").createEntityManager()),
                Outage.class, new BaseEntityManager<Outage>(
                        Persistence.createEntityManagerFactory("ausfall").createEntityManager(), Outage.class),
                RaceTrack.class, new BaseEntityManager<RaceTrack>(
                        Persistence.createEntityManagerFactory("rennstrecke").createEntityManager(), RaceTrack.class),
                Result.class, new BaseEntityManager<Result>(
                        Persistence.createEntityManagerFactory("ergebnis").createEntityManager(), Result.class),
                Sponsor.class, new BaseEntityManager<Sponsor>(
                        Persistence.createEntityManagerFactory("sponsor").createEntityManager(), Sponsor.class),
                Team.class, new BaseEntityManager<Team>(
                        Persistence.createEntityManagerFactory("team").createEntityManager(), Team.class),
                Vehicle.class, new BaseEntityManager<Vehicle>(
                        Persistence.createEntityManagerFactory("fahrzeug").createEntityManager(), Vehicle.class)
        );
    }

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

        this.cleanup();
    }

    /**
     * Closes the connection to the database thus cleans the resources used.
     */
    private void cleanup() {
        for (BaseEntityManager entityManager : entityManagerMap.values())
            entityManager.close();
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
