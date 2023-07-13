package at.fhburgenland.menu.menuObserver;

import at.fhburgenland.RaceManagementService;
import at.fhburgenland.database.entities.*;
import at.fhburgenland.enumerations.ForegroundColor;
import at.fhburgenland.interfaces.Service;
import at.fhburgenland.enumerations.MenuPages;

public class CreateObserver extends BaseMenuObserver {
    /**
     * Initializes a new instance of the BaseMenuObserver class.
     *
     * @param service The service.
     */
    public CreateObserver(Service service) {
        super(service);
    }

    @Override
    public void update(MenuPages fromMenu) {
        switch (fromMenu) {
            case DRIVER -> createDriver();
            case OUTAGE -> createOutage();
            case RACE -> createRace();
            case RACETRACK -> createRaceTrack();
            case RESULT -> createResult();
            case SPONSOR -> createSponsor();
            case TEAM -> createTeam();
            case VEHICLE -> createVehicle();
        }
    }

    private void createVehicle() {
        var entityManager = RaceManagementService.getEntityManagerMap().get(Vehicle.class);
        if (entityManager.create(this.createVehicleObject()))
            this.service.getIOHandler().printColoredLn("Fahrzeug erfolgreich erstellt.", ForegroundColor.GREEN);
        else
            this.service.getIOHandler().printErrorMessage("Fahrzeug konnte aufgrund eines Fehlers nicht erstellt werden!");
    }

    private void createTeam() {
        var entityManager = RaceManagementService.getEntityManagerMap().get(Team.class);
        Team team = this.createTeamObject();
        if (team == null)
            return;
        if (entityManager.create(team))
            this.service.getIOHandler().printColoredLn("Team erfolgreich erstellt.", ForegroundColor.GREEN);
        else
            this.service.getIOHandler().printErrorMessage("Team konnte aufgrund eines Fehlers nicht erstellt werden!");
    }

    private void createSponsor() {
        var entityManager = RaceManagementService.getEntityManagerMap().get(Sponsor.class);
        if (entityManager.create(this.createSponsorObject()))
            this.service.getIOHandler().printColoredLn("Sponsor erfolgreich erstellt.", ForegroundColor.GREEN);
        else
            this.service.getIOHandler().printErrorMessage("Sponsor konnte aufgrund eines Fehlers nicht erstellt werden!");
    }

    private void createResult() {
        var entityManager = RaceManagementService.getEntityManagerMap().get(Result.class);
        var result = this.createResultObject();
        if (result == null)
            return;
        if (entityManager.create(result))
            this.service.getIOHandler().printColoredLn("Ergebnis erfolgreich erstellt.", ForegroundColor.GREEN);
        else
            this.service.getIOHandler().printErrorMessage("Ergebnis konnte nicht erstellt werden.");
    }

    private void createRaceTrack() {
        var entityManager = RaceManagementService.getEntityManagerMap().get(RaceTrack.class);
        if (entityManager.create(this.createRaceTrackObject()))
            this.service.getIOHandler().printColoredLn("Rennstrecke erfolgreich erstellt.", ForegroundColor.GREEN);
        else
            this.service.getIOHandler().printErrorMessage("Rennstrecke konnte aufgrund eines Fehlers nicht erstellt werden!");
    }

    private void createRace() {
        var entityManager = RaceManagementService.getEntityManagerMap().get(Driver.class);
        Race race = this.createRaceObject();
        if (race == null)
            return;
        if (entityManager.create(race))
        {
            this.service.getIOHandler().printColoredLn("Rennen erfolgreich erstellt.", ForegroundColor.GREEN);
        }
        else
        {
            this.service.getIOHandler().printErrorMessage("Rennen konnte nicht erstellt werden.");
        }
    }

    private void createOutage() {
        var entityManager = RaceManagementService.getEntityManagerMap().get(Outage.class);
        if (entityManager.create(this.createOutageObject()))
        {
            this.service.getIOHandler().printColoredLn("Ausfall erfolgreich erstellt.", ForegroundColor.GREEN);
        }
        else
        {
            this.service.getIOHandler().printErrorMessage("Ausfall konnte nicht erstellt werden.");
        }
    }

    private void createDriver() {
        var entityManager = RaceManagementService.getEntityManagerMap().get(Driver.class);
        Driver driver = this.createDriverObject();
        if (driver == null)
            return;
        if (entityManager.create(driver))
        {
            this.service.getIOHandler().printColoredLn("Fahrer erfolgreich erstellt.", ForegroundColor.GREEN);
        }
        else
        {
            this.service.getIOHandler().printErrorMessage("Fahrer konnte nicht erstellt werden.");
        }
    }
}




