package at.fhburgenland.menu.menuObserver;

import at.fhburgenland.RaceManagementService;
import at.fhburgenland.entities.*;
import at.fhburgenland.menu.MenuItem;
import at.fhburgenland.BaseEntityManager;
import at.fhburgenland.enumerations.MenuPages;
import at.fhburgenland.interfaces.Menu;
import lombok.extern.slf4j.Slf4j;
import at.fhburgenland.interfaces.Service;
import at.fhburgenland.interfaces.MenuObserver;
import at.fhburgenland.interfaces.IOHandler;

import javax.persistence.Persistence;
import java.util.Map;

@Slf4j
public abstract class BaseMenuObserver implements MenuObserver {

    /**
     * Holds a reference to the race management service.
     */
    protected final Service service;

    /**
     * Initializes a new instance of the BaseMenuObserver class.
     * @param service The service.
     */
    public BaseMenuObserver(Service service) {
        this.service = service;
    }



    public void handleSubMenu(MenuPages toHandle)
    {
        Menu menu = this.service.getMenu();
        IOHandler ioHandler = this.service.getIOHandler();
        menu.selectCurrentPage(toHandle);
        while (!this.service.isReturningToMain()) {
            menu.getRendered(ioHandler);
            MenuItem item = menu.getCurrent().getSelected(ioHandler);
            item.notifyObservers();
            ioHandler.print("");
        }
        menu.selectCurrentPage(MenuPages.MAIN);
    }

    protected void executeReadForVehicle() {
        var vehicles = RaceManagementService.getEntityManagerMap().get(Vehicle.class).readAll();
        this.service.getIOHandler().renderVehicleTable(vehicles);
    }


    protected void executeReadForTeam() {
        var teams = RaceManagementService.getEntityManagerMap().get(Team.class).readAll();
        this.service.getIOHandler().renderTeamTable(teams);
    }
}
