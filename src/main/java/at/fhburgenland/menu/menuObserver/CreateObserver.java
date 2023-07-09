package at.fhburgenland.menu.menuObserver;

import at.fhburgenland.RaceEntityManager;
import at.fhburgenland.RaceManagementService;
import at.fhburgenland.entities.*;
import at.fhburgenland.enumerations.ForegroundColor;
import at.fhburgenland.enumerations.OutageReason;
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
        String vehicleBrand = this.service.getIOHandler().askUserForInput("Bitte gib eine Fahrzeugmarke ein", true);
        String vehicleModel = this.service.getIOHandler().askUserForInput("Bitte gib eine Fahrzeugmodell ein", true);
        int creationYear = this.service.getIOHandler().getNumberFromUser("Bitte gib das Baujahr ein",false).intValue();
        var entityManager = RaceManagementService.getEntityManagerMap().get(Vehicle.class);
        if (entityManager.create(new Vehicle(vehicleBrand, vehicleModel, creationYear)))
            this.service.getIOHandler().printColoredLn("Fahrzeug erfolgreich erstellt.", ForegroundColor.GREEN);
        else
            this.service.getIOHandler().printErrorMessage("Fahrzeug konnte aufgrund eines Fehlers nicht erstellt werden!");
    }

    private void createTeam() {

    }

    private void createSponsor() {

    }

    private void createResult() {
        int raceId = this.service.getIOHandler().getEntityIdFromUser(
                () -> this.executeReadForRace(),
                Race.class,
                "Bitte geben Sie eine bestehende Rennen-ID ein",
                "Das angegebene Rennen ist nicht vorhanden. Wählen Sie bitte eines der folgenden Rennen IDs aus",
                this.service);
        boolean exists = ((RaceEntityManager)RaceManagementService.getEntityManagerMap().get(Race.class)).doesResultExistForRace(raceId);
        if (exists) {
            this.service.getIOHandler().printErrorMessage("Für das Rennen mit der Id " + raceId + " gibt es bereits ein Ergebnis!");
            return;
        }

        int firstId = this.service.getIOHandler().getEntityIdFromUser(
                () -> this.executeReadForDriver(),
                Driver.class,
                "Bitte geben Sie eine bestehende Fahrer-ID für den ersten Platz ein",
                "Der angegebene Fahrer ist nicht vorhanden. Wählen Sie bitte eines der folgenden Fahrer IDs aus",
                this.service);
        boolean exit = false;
        int secondId = 0;
        do {
            secondId = this.service.getIOHandler().getEntityIdFromUser(
                    () -> this.executeReadForDriver(),
                    Driver.class,
                    "Bitte geben Sie eine bestehende Fahrer-ID für den zweiten Platz ein",
                    "Der angegebene Fahrer ist nicht vorhanden. Wählen Sie bitte eines der folgenden Fahrer IDs aus",
                    this.service);
            if (secondId != firstId)
                exit = true;
            else
                this.service.getIOHandler().printErrorMessage("Fahrer mit der Id " + secondId + " kann nicht erster UND zweiter sein!");
        } while (!exit);

        boolean thirdExists = this.service.getIOHandler().askToContinue("Gibt es einen dritten Platz?");
        int thirdId = 0;
        exit = false;
        if (thirdExists) {
            do {
                thirdId = this.service.getIOHandler().getEntityIdFromUser(
                        () -> this.executeReadForDriver(),
                        Driver.class,
                        "Bitte geben Sie eine bestehende Fahrer-ID für den dritten Platz ein",
                        "Der angegebene Fahrer ist nicht vorhanden. Wählen Sie bitte eines der folgenden Fahrer IDs aus",
                        this.service);
                if (thirdId != firstId && thirdId != secondId)
                    exit = true;
                this.service.getIOHandler().printErrorMessage("Fahrer mit der Id " + thirdId + " wurde bereits für ersten oder zweiten Platz angegeben!");
            } while (!exit);
        }

        var entityManager = RaceManagementService.getEntityManagerMap().get(Result.class);
        boolean success = true;

        if (!thirdExists)
            success = entityManager.create(new Result(raceId, firstId, secondId));
        else
            success = entityManager.create(new Result(raceId, firstId, secondId, thirdId));

        if (success)
            this.service.getIOHandler().printColoredLn("Ergebnis erfolgreich erstellt.", ForegroundColor.GREEN);
        else
            this.service.getIOHandler().printErrorMessage("Ergebnis konnte nicht erstellt werden.");
    }

    private void createRaceTrack() {

    }

    private void createRace() {

    }

    private void createOutage() {

        int driverId = this.service.getIOHandler().getEntityIdFromUser(
                () -> this.executeReadForDriver(),
                Driver.class,
                "Bitte geben Sie eine bestehende Fahrer-ID ein",
                "Der angegebene Fahrer ist nicht vorhanden. Wählen Sie bitte eines der folgenden Fahrer IDs aus",
                this.service);
        int raceId = this.service.getIOHandler().getEntityIdFromUser(
                () -> this.executeReadForRace(),
                Race.class,
                "Bitte geben Sie eine bestehende Rennen-ID ein",
                "Das angegebene Rennen ist nicht vorhanden. Wählen Sie bitte eines der folgenden Rennen IDs aus",
                this.service);
        String reason = ((OutageReason)this.service.getIOHandler().promptForEnumValue(OutageReason.class)).getReason();
        var entityManager = RaceManagementService.getEntityManagerMap().get(Outage.class);
        if (entityManager.create(new Outage(driverId, raceId, reason)))
        {
            this.service.getIOHandler().printColoredLn("Ausfall erfolgreich erstellt.", ForegroundColor.GREEN);
        }
        else
        {
            this.service.getIOHandler().printErrorMessage("Ausfall konnte nicht erstellt werden.");
        }
    }

    private void createDriver() {
        String forename = this.service.getIOHandler().askUserForInput("Bitte gebe einen Vornamen ein", true);
        String lastname = this.service.getIOHandler().askUserForInput("Bitte gebe einen Nachnamen ein", true);
        String nationality = this.service.getIOHandler().askUserForInput("Bitte gebe die Nationalität ein", true);
        int vehicleId = this.service.getIOHandler().getEntityIdFromUser(
                () -> this.executeReadForVehicle(),
                Vehicle.class,
                "Bitte geben Sie eine bestehende Fahrzeug-ID ein",
                "Das angegebene Fahrzeug ist nicht vorhanden. Wählen Sie bitte eines der folgenden Fahrzeug-IDs aus",
                this.service);
        int teamId = this.service.getIOHandler().getEntityIdFromUser(
                () -> this.executeReadForTeam(),
                Team.class,
                "Bitte geben Sie eine bestehende Team-ID ein",
                "Das angegebene Team ist nicht vorhanden. Wählen Sie bitte eine der folgenden Team-IDs aus",
                this.service);
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


