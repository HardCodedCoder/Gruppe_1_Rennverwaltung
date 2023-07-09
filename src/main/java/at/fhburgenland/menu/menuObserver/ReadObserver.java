package at.fhburgenland.menu.menuObserver;

import at.fhburgenland.RaceManagementService;
import at.fhburgenland.entities.*;
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

    private void executeReadForSponsor() {
        var sponsors = RaceManagementService.getEntityManagerMap().get(Sponsor.class).readAll();
        this.service.getIOHandler().renderSponsorTable(sponsors);
    }

    private void executeReadForRaceTrack() {
        var raceTracks = RaceManagementService.getEntityManagerMap().get(RaceTrack.class).readAll();
        this.service.getIOHandler().renderRaceTrackTable(raceTracks);
    }

    private void executeReadForOutage() {
        var outages = RaceManagementService.getEntityManagerMap().get(Outage.class).readAll();
        this.service.getIOHandler().renderOutageTable(outages);
    }
}
