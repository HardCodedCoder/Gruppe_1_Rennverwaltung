package at.fhburgenland.org.lecture.menu.menuObserver;

import at.fhburgenland.org.lecture.BaseEntityManager;
import at.fhburgenland.org.lecture.entities.*;
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

    private void executeReadForVehicle() {
        var vehicles = entityManagerMap.get(Vehicle.class).readAll();
        this.service.getIOHandler().renderVehicleTable(vehicles);
    }

    private void executeReadForTeam() {
        var teams = entityManagerMap.get(Team.class).readAll();
        this.service.getIOHandler().renderTeamTable(teams);
    }

    private void executeReadForSponsor() {
        var sponsors = entityManagerMap.get(Sponsor.class).readAll();
        this.service.getIOHandler().renderSponsorTable(sponsors);
    }

    private void executeReadForResult() {
        var results = entityManagerMap.get(Result.class).readAll();
        this.service.getIOHandler().renderResultTable(results);
    }

    private void executeReadForRaceTrack() {
        var raceTracks = entityManagerMap.get(RaceTrack.class).readAll();
        this.service.getIOHandler().renderRaceTrackTable(raceTracks);
    }

    private void executeReadForOutage() {
        var outages = entityManagerMap.get(Outage.class).readAll();
        this.service.getIOHandler().renderOutageTable(outages);
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
