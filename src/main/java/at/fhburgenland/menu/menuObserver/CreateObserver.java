package at.fhburgenland.menu.menuObserver;

import at.fhburgenland.RaceManagementService;
import at.fhburgenland.enumerations.ForegroundColor;
import at.fhburgenland.interfaces.Service;
import at.fhburgenland.entities.Driver;
import at.fhburgenland.entities.Team;
import at.fhburgenland.entities.Vehicle;
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
    }

    private void createTeam() {

    }

    private void createSponsor() {

    }

    private void createResult() {

    }

    private void createRaceTrack() {

    }

    private void createRace() {

    }

    private void createOutage() {

    }

    private void createDriver() {
        String forename = this.service.getIOHandler().askUserForInput("Bitte gebe einen Vornamen ein:", true);
        String lastname = this.service.getIOHandler().askUserForInput("Bitte gebe einen Nachnamen ein:", true);
        String nationality = this.service.getIOHandler().askUserForInput("Bitte gebe die Nationalität ein:", true);
        int vehicleId = 0;
        int teamId = 0;
        boolean exit = false;
        while (!exit) {
            vehicleId = this.service.getIOHandler().getNumberFromUser(
                    "Bitte geben Sie eine bestehende Fahrzeug-ID ein:", true).intValue();
            if (RaceManagementService.getEntityManagerMap().get(Vehicle.class).read(vehicleId) == null) {
                this.service.getIOHandler().printErrorMessage("Die eingegebene Fahrzeug ist nicht vorhanden." +
                        "Wählen Sie bitte eine der folgenden Fahrzeug-IDs aus:");
                this.service.getIOHandler().println("");
                this.executeReadForVehicle();
            } else {
                exit = true;
            }
        }
        exit = false;
        while (!exit)
        {
            teamId = this.service.getIOHandler().getNumberFromUser(
                    "Bitte geben Sie eine bestehende Team-ID ein:", true).intValue();
            if (RaceManagementService.getEntityManagerMap().get(Team.class).read(teamId) == null) {
                this.service.getIOHandler().printErrorMessage("Das angegebene Team ist nicht vorhanden." +
                        "Wählen Sie bitte eine der folgenden Team-IDs aus:");
                this.service.getIOHandler().println("");
                this.executeReadForTeam();
            } else {
                exit = true;
            }
        }

        var entityManager = RaceManagementService.getEntityManagerMap().get(Driver.class);
        if (entityManager.create(new Driver(forename, lastname, nationality, vehicleId, teamId)))
        {
            this.service.getIOHandler().printColoredLn("Fahrer erfolgreich erstellt.", ForegroundColor.GREEN);
        }
        else
        {
            this.service.getIOHandler().printErrorMessage("Fahrer konnte nicht erstellt werden.");
        }
    }
}

