package at.fhburgenland.org.lecture.menu.menuObserver;

import at.fhburgenland.org.lecture.BaseEntityManager;
import at.fhburgenland.org.lecture.entities.Driver;
import at.fhburgenland.org.lecture.entities.Race;
import at.fhburgenland.org.lecture.enumerations.MenuPages;
import at.fhburgenland.org.lecture.interfaces.Service;

import java.util.Map;

public class ReadObserver extends BaseMenuObserver {
    /**
     * Initializes a new instance of the BaseMenuObserver class.
     *
     * @param service   The service.
     */
    public ReadObserver(Service service) {
        super(service);

    }

    @Override
    public void update(MenuPages fromMenu) {
        switch (fromMenu)
        {
            case DRIVER -> this.executeReadForDriver();
            case RACE -> this.executeReadForRace();
            default -> System.out.println("Test");
        }
    }

    private void executeReadForRace() {
        var races = entityManagerMap.get(Race.class).readAll();
        this.service.getIOHandler().renderRaceTable(races);
    }

    private void executeReadForDriver() {
       var drivers = entityManagerMap.get(Driver.class).readAll();
        this.service.getIOHandler().renderDriverTable(drivers);
    }
}
