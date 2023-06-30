package at.fhburgenland.org.lecture.menu.menuObserver;

import at.fhburgenland.org.lecture.BaseEntityManager;
import at.fhburgenland.org.lecture.entities.*;
import at.fhburgenland.org.lecture.enumerations.MenuPages;
import at.fhburgenland.org.lecture.interfaces.Menu;
import at.fhburgenland.org.lecture.menu.MenuItem;
import lombok.extern.slf4j.Slf4j;
import at.fhburgenland.org.lecture.interfaces.Service;
import at.fhburgenland.org.lecture.interfaces.MenuObserver;
import at.fhburgenland.org.lecture.interfaces.IOHandler;

import javax.persistence.Persistence;

import java.util.Map;

@Slf4j
public abstract class BaseMenuObserver implements MenuObserver {
    protected static final Map<Class, BaseEntityManager> entityManagerMap = initializeEntityManagerMap();

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

    private static Map<Class, BaseEntityManager> initializeEntityManagerMap()
    {
        return Map.of(
                Driver.class, new BaseEntityManager<Driver>(
                        Persistence.createEntityManagerFactory("fahrer").createEntityManager(), Driver.class),
                Race.class, new BaseEntityManager<Race>(
                        Persistence.createEntityManagerFactory("rennen").createEntityManager(), Race.class),
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
        var vehicles = entityManagerMap.get(Vehicle.class).readAll();
        this.service.getIOHandler().renderVehicleTable(vehicles);
    }


    protected void executeReadForTeam() {
        var teams = entityManagerMap.get(Team.class).readAll();
        this.service.getIOHandler().renderTeamTable(teams);
    }
}
