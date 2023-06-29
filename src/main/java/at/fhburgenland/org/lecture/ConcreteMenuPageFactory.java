package at.fhburgenland.org.lecture;

import at.fhburgenland.org.lecture.enumerations.CRUD;
import at.fhburgenland.org.lecture.enumerations.MenuPages;
import at.fhburgenland.org.lecture.interfaces.MenuPage;
import at.fhburgenland.org.lecture.interfaces.MenuPageFactory;
import at.fhburgenland.org.lecture.interfaces.Service;
import at.fhburgenland.org.lecture.menu.MenuItem;
import at.fhburgenland.org.lecture.menu.menuObserver.*;
import at.fhburgenland.org.lecture.view.ConsolePage;

import java.util.List;

public class ConcreteMenuPageFactory implements MenuPageFactory {
    private final Service hostingService;

    private final List<MenuItem> mainMenuItems;

    private final List<MenuItem> driverMenuItems;

    private final List<MenuItem> outageMenuItems;

    private final List<MenuItem> raceMenuItems;
    private final List<MenuItem> racetrackMenuItems;
    private final List<MenuItem> resultMenuItems;
    private final List<MenuItem> sponsorMenuItems;
    private final List<MenuItem> teamMenuItems;
    private final List<MenuItem> vehicleMenuItems;

    private final CreateObserver createObserver;

    private final UpdateObserver updateObserver;

    private final DeleteObserver deleteObserver;

    private final ReadObserver readObserver;

    private final ReturnToPreviousPageObserver returnToPreviousPageObserver;

    public ConcreteMenuPageFactory(Service hostingService) {
        this.hostingService = hostingService;
        this.mainMenuItems = this.createMainMenuItems();
        this.createObserver = new CreateObserver(this.hostingService);
        this.updateObserver = new UpdateObserver(this.hostingService);
        this.deleteObserver = new DeleteObserver(this.hostingService);
        this.readObserver = new ReadObserver(this.hostingService);
        this.returnToPreviousPageObserver = new ReturnToPreviousPageObserver(this.hostingService);
        this.driverMenuItems = this.createMenuItems(MenuPages.DRIVER);
        this.outageMenuItems = this.createMenuItems(MenuPages.OUTAGE);
        this.raceMenuItems = this.createMenuItems(MenuPages.RACE);
        this.racetrackMenuItems = this.createMenuItems(MenuPages.RACETRACK);
        this.resultMenuItems = this.createMenuItems(MenuPages.RESULT);
        this.sponsorMenuItems = this.createMenuItems(MenuPages.SPONSOR);
        this.teamMenuItems =  this.createMenuItems(MenuPages.TEAM);
        this.vehicleMenuItems = this.createMenuItems(MenuPages.VEHICLE);
    }

    private List<MenuItem> createMenuItems(MenuPages menuPage) {
        return List.of(
                new MenuItem(CRUD.READ.getLabel(), 1, this.readObserver, menuPage),
                new MenuItem(CRUD.UPDATE.getLabel(), 2, this.updateObserver, menuPage),
                new MenuItem(CRUD.CREATE.getLabel(), 3, this.createObserver, menuPage),
                new MenuItem(CRUD.DELETE.getLabel(), 4, this.deleteObserver, menuPage),
                new MenuItem("Zurück", 5, this.returnToPreviousPageObserver, menuPage)
        );
    }

    private List<MenuItem> createMainMenuItems() {
        return List.of(
                new MenuItem(MenuPages.DRIVER.getLabel(), 1, new ShowDriverMenuObserver(this.hostingService), MenuPages.MAIN),
                new MenuItem(MenuPages.OUTAGE.getLabel(), 2, new ShowOutageMenuObserver(this.hostingService), MenuPages.MAIN),
                new MenuItem(MenuPages.RACE.getLabel(), 3, new ShowRaceMenuObserver(this.hostingService), MenuPages.RACE),
                new MenuItem(MenuPages.RACETRACK.getLabel(), 4, new ShowRaceTrackMenuObserver(this.hostingService), MenuPages.RACETRACK),
                new MenuItem(MenuPages.RESULT.getLabel(), 5, null, MenuPages.RESULT),
                new MenuItem(MenuPages.SPONSOR.getLabel(), 6, null, MenuPages.SPONSOR),
                new MenuItem(MenuPages.TEAM.getLabel(), 7, null, MenuPages.TEAM),
                new MenuItem(MenuPages.VEHICLE.getLabel(), 8, null, MenuPages.VEHICLE),
                new MenuItem(MenuPages.QUERY.getLabel(), 9, null, MenuPages.QUERY),
                new MenuItem(MenuPages.EXIT.getLabel(), 10, new TerminateApplicationObserver(this.hostingService), MenuPages.EXIT)
        );
    }
    @Override
    public MenuPage createMenuPage(MenuPages menuPageToCreate) {
        return switch (menuPageToCreate) {
            case MAIN -> new ConsolePage(mainMenuItems, MenuPages.MAIN);
            case DRIVER -> new ConsolePage(driverMenuItems, MenuPages.DRIVER);
            case OUTAGE -> new ConsolePage(outageMenuItems, MenuPages.OUTAGE);
            case RACE -> new ConsolePage(raceMenuItems, MenuPages.RACE);
            case RACETRACK -> new ConsolePage(racetrackMenuItems, MenuPages.RACETRACK);
            case RESULT -> new ConsolePage(resultMenuItems, MenuPages.RESULT);
            case SPONSOR -> new ConsolePage(sponsorMenuItems, MenuPages.SPONSOR);
            case TEAM -> new ConsolePage(teamMenuItems, MenuPages.TEAM);
            case VEHICLE -> new ConsolePage(vehicleMenuItems, MenuPages.VEHICLE);
            default -> throw new IllegalStateException("Unknown menu page type: " + menuPageToCreate);
        };
    }
}
