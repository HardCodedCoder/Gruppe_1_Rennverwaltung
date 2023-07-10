package at.fhburgenland.menu.menuObserver;
import at.fhburgenland.interfaces.Service;
import at.fhburgenland.enumerations.MenuPages;

public class ReadObserver extends BaseMenuObserver {
    /**
     * Initializes a new instance of the BaseMenuObserver class.
     *
     * @param service The service.
     */
    public ReadObserver(Service service) {
        super(service);

    }

    @Override
    public void update(MenuPages fromMenu) {
        switch (fromMenu) {
            case DRIVER -> this.executeReadForDriver();
            case OUTAGE -> this.executeReadForOutage();
            case RACE -> this.executeReadForRace();
            case RACETRACK -> this.executeReadForRaceTrack();
            case RESULT -> this.executeReadForResult();
            case SPONSOR -> this.executeReadForSponsor();
            case TEAM -> this.executeReadForTeam();
            case VEHICLE -> this.executeReadForVehicle();
            default -> System.out.println("Test");
        }
    }
}
