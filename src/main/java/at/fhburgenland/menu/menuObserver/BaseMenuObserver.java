package at.fhburgenland.menu.menuObserver;

import at.fhburgenland.RaceEntityManager;
import at.fhburgenland.RaceManagementService;
import at.fhburgenland.entities.*;
import at.fhburgenland.enumerations.OutageReason;
import at.fhburgenland.menu.MenuItem;
import at.fhburgenland.enumerations.MenuPages;
import at.fhburgenland.interfaces.Menu;
import lombok.extern.slf4j.Slf4j;
import at.fhburgenland.interfaces.Service;
import at.fhburgenland.interfaces.MenuObserver;
import at.fhburgenland.interfaces.IOHandler;

import java.time.LocalDate;
import java.util.Collections;


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
        Collections.sort(vehicles);
        this.service.getIOHandler().renderVehicleTable(vehicles);
    }


    protected void executeReadForTeam() {
        var teams = RaceManagementService.getEntityManagerMap().get(Team.class).readAll();
        Collections.sort(teams);
        this.service.getIOHandler().renderTeamTable(teams);
    }

    protected void executeReadForDriver()  {
        var drivers = RaceManagementService.getEntityManagerMap().get(Driver.class).readAll();
        Collections.sort(drivers);
        this.service.getIOHandler().renderDriverTable(drivers);
    }

    protected void executeReadForRace() {
        var races = RaceManagementService.getEntityManagerMap().get(Race.class).readAll();
        Collections.sort(races);
        this.service.getIOHandler().renderRaceTable(races);
    }

    protected void executeReadForResult() {
        var results = RaceManagementService.getEntityManagerMap().get(Result.class).readAll();
        Collections.sort(results);
        this.service.getIOHandler().renderResultTable(results);
    }

    protected void executeReadForSponsor() {
        var sponsors = RaceManagementService.getEntityManagerMap().get(Sponsor.class).readAll();
        Collections.sort(sponsors);
        this.service.getIOHandler().renderSponsorTable(sponsors);
    }

    protected void executeReadForRaceTrack() {
        var raceTracks = RaceManagementService.getEntityManagerMap().get(RaceTrack.class).readAll();
        Collections.sort(raceTracks);
        this.service.getIOHandler().renderRaceTrackTable(raceTracks);
    }

    protected void executeReadForOutage() {
        var outages = RaceManagementService.getEntityManagerMap().get(Outage.class).readAll();
        Collections.sort(outages);
        this.service.getIOHandler().renderOutageTable(outages);
    }


    protected Team createTeamObject() {
        String name = this.service.getIOHandler().askUserForInput("Bitte gebe einen Namen für das Team ein", true);
        int foundingYear = 0;
        do
        {
            foundingYear = this.service.getIOHandler().getNumberFromUser("Bitte gib das Gründungsjahr des Teams ein",false).intValue();
            if (foundingYear < 1900)
                this.service.getIOHandler().printErrorMessage("Das angegebene Gründungsjahr des Teams ist nicht gültig! Bitte" +
                        "geben Sie eine Zahl nach dem Jahr 1900 ein");
        } while (foundingYear < 1900);
        boolean exit = false;
        int sponsorId = 0;
        while (!exit) {
            this.executeReadForSponsor();
            sponsorId = this.service.getIOHandler().getNumberFromUser(
                    "Bitte geben Sie eine bestehende Sponsor-ID ein", true).intValue();
            if (RaceManagementService.getEntityManagerMap().get(Sponsor.class).read(sponsorId) == null) {
                this.service.getIOHandler().printErrorMessage("Der angegebene Sponsor ist nicht vorhanden." +
                        "Wählen Sie bitte eine der folgenden Sponsor-IDs aus:");
                this.service.getIOHandler().println("");
            } else {
                exit = true;
            }
        }

        return new Team(name, foundingYear, sponsorId);
    }

    protected RaceTrack createRaceTrackObject() {
        String name = this.service.getIOHandler().askUserForInput("Bitte gib einen Namen für Rennstrecke ein:", true);
        String state = this.service.getIOHandler().askUserForInput("Bitte gib das Bundesland ein, wo sich die Rennstrecke befindet:", true);
        String city = this.service.getIOHandler().askUserForInput("Bitte gib die Stadt ein, wo sich die Rennstrecke befindet:", true);
        return new RaceTrack(name, state, city);
    }

    protected Sponsor createSponsorObject() {
        return new Sponsor(this.service.getIOHandler().askUserForInput("Bitte gebe einen Namen für den Sponsor ein:", true));
    }

    protected Result createResultObject() {
        int raceId = this.service.getIOHandler().getEntityIdFromUser(
                () -> this.executeReadForRace(),
                Race.class,
                "Bitte geben Sie eine bestehende Rennen-ID ein",
                "Das angegebene Rennen ist nicht vorhanden. Wählen Sie bitte eines der folgenden Rennen IDs aus",
                this.service);
        boolean exists = ((RaceEntityManager)RaceManagementService.getEntityManagerMap().get(Race.class)).doesResultExistForRace(raceId);
        if (exists) {
            this.service.getIOHandler().printErrorMessage("Für das Rennen mit der Id " + raceId + " gibt es bereits ein Ergebnis!");
            return null;
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

        if (!thirdExists)
            return new Result(raceId, firstId, secondId);
        else
            return new Result(raceId, firstId, secondId, thirdId);
    }

    protected Vehicle createVehicleObject() {
        String vehicleBrand = this.service.getIOHandler().askUserForInput("Bitte gib eine Fahrzeugmarke ein", true);
        String vehicleModel = this.service.getIOHandler().askUserForInput("Bitte gib eine Fahrzeugmodell ein", true);
        int creationYear = this.service.getIOHandler().getNumberFromUser("Bitte gib das Baujahr ein",false).intValue();
        return new Vehicle(vehicleBrand, vehicleModel, creationYear);
    }

    protected Race createRaceObject() {
        String name = this.service.getIOHandler().askUserForInput("Bitte gib einen Namen für das Rennen ein:", true);
        LocalDate date = this.service.getIOHandler().getDateFromUser("Bitte gib ein Datum für das Rennen ein: ");
        boolean exit = false;
        int raceTrackId = 0;
        while (!exit) {
            raceTrackId = this.service.getIOHandler().getNumberFromUser(
                    "Bitte geben Sie eine bestehende Rennstrecken-ID ein:", true).intValue();
            if (RaceManagementService.getEntityManagerMap().get(RaceTrack.class).read(raceTrackId) == null) {
                this.service.getIOHandler().printErrorMessage("Die eingegebene Rennstrecke ist nicht vorhanden." +
                        "Wählen Sie bitte eine der folgenden Rennstrecken-IDs aus:");
                this.service.getIOHandler().println("");
                this.executeReadForVehicle();
            } else {
                exit = true;
            }
        }

        return new Race(name, date, raceTrackId);
    }

    protected Outage createOutageObject() {
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
        return new Outage(driverId, raceId, reason);
    }

    protected Driver createDriverObject() {
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
        return new Driver(forename, lastname, nationality, vehicleId, teamId);
    }
}
