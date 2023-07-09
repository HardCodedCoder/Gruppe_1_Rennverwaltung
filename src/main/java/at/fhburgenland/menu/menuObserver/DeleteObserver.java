package at.fhburgenland.menu.menuObserver;

import at.fhburgenland.RaceEntityManager;
import at.fhburgenland.RaceManagementService;
import at.fhburgenland.entities.Outage;
import at.fhburgenland.entities.Result;
import at.fhburgenland.entities.Vehicle;
import at.fhburgenland.enumerations.ForegroundColor;
import at.fhburgenland.interfaces.Service;
import at.fhburgenland.enumerations.MenuPages;

import javax.persistence.criteria.CriteriaBuilder;

public class DeleteObserver extends BaseMenuObserver{
    /**
     * Initializes a new instance of the BaseMenuObserver class.
     *
     * @param service The service.
     */
    public DeleteObserver(Service service) {
        super(service);
    }

    @Override
    public void update(MenuPages fromMenu) {
        switch (fromMenu) {
            case DRIVER -> deleteDriver();
            case OUTAGE -> deleteOutage();
            case RACE -> deleteRace();
            case RACETRACK -> deleteRaceTrack();
            case RESULT -> deleteResult();
            case SPONSOR -> deleteSponsor();
            case TEAM -> deleteTeam();
            case VEHICLE -> deleteVehicle();
        }
    }

    private void deleteVehicle() {
        this.executeReadForVehicle();
        int toDelete = this.service.getIOHandler().getNumberFromUser("Bitte geben Sie die Id des Fahrzeuges ein, welches Sie löschen möchten", false).intValue();
        var entityManager = RaceManagementService.getEntityManagerMap().get(Vehicle.class);
        Vehicle vehicle = (Vehicle)entityManager.read(toDelete);
        if (vehicle == null) {
            this.service.getIOHandler().printErrorMessage("Das angegebene Fahrzeug mit der id: " + toDelete + " existiert nicht! Versuchen Sie den Vorgang nochmal");
            return;
        }
        if (this.service.getIOHandler().askToContinue("Möchten Sie das Fahrzeug wirklich löschen? Der Vorgang kann nicht rückgängig gemacht werden! (y/n)"))
            if (entityManager.delete(vehicle))
                this.service.getIOHandler().printColoredLn("Fahrzeug wurde erfolgreich gelöscht.", ForegroundColor.GREEN);
            else
                this.service.getIOHandler().printErrorMessage("Das angegebene Fahrzeug mit der id: " + toDelete + " konnte nicht gelöscht werden");
    }

    private void deleteTeam() {

    }

    private void deleteSponsor() {

    }

    private void deleteRace() {

    }


    private void deleteOutage() {
        String userInput = this.service.getIOHandler().askUserForInput("Bitte geben Sie einen Ausfall " +
                "in Form von <fahrer_id>, <rennen_id> ein: ", true);
        var split = userInput.split(",");
        if (!(split.length == 2)) {
            this.service.getIOHandler().printErrorMessage("Bitte geben Sie einen Ausfall " +
                    "in Form von <fahrer_id>, <rennen_id> ein: ");
            return;
        }
        int driverId = Integer.parseInt(split[0]);
        int raceId = Integer.parseInt(split[1]);
        Outage unmanagedOutage = new Outage(driverId, raceId);
        Outage managedOutage = (Outage) RaceManagementService.getEntityManagerMap().get(Outage.class).read(unmanagedOutage);
        if (RaceManagementService.getEntityManagerMap().get(Outage.class).delete(managedOutage))
            this.service.getIOHandler().printColoredLn("Ausfall wurde erfolgreich gelöscht.", ForegroundColor.GREEN);
        else
            this.service.getIOHandler().printErrorMessage("Der angegebene Ausfall konnte nicht gelöscht werden");
    }

    private void deleteResult() {
       String userInput = this.service.getIOHandler().askUserForInput("Bitte geben Sie ein Ergebnis " +
               "in Form <rennen_id>, <erster_id>, <zweiter_id>, <dritter_id> (optional) ein: ", true);
       var split = userInput.split(",");
       if (!(split.length >= 3 && split.length <= 4)) {
           this.service.getIOHandler().printErrorMessage("Bitte geben Sie ein Ergebnis im Format " +
                   "<rennen_id>, <erster_id>, <zweiter_id>, <dritter_id> (optional) ");
           return;
       }
       int rennenId = Integer.parseInt(split[0]);
       int firstId = Integer.parseInt(split[1]);
       int secondId = Integer.parseInt(split[2]);
       Integer thirdId = null;
       if (split.length == 4) {
           thirdId = Integer.parseInt(split[3]);
       }
       Result unmanagedResult = new Result(rennenId, firstId, secondId, thirdId);
       Result managedResult = (Result) RaceManagementService.getEntityManagerMap().get(Result.class).read(unmanagedResult);
       if (RaceManagementService.getEntityManagerMap().get(Result.class).delete(managedResult))
           this.service.getIOHandler().printColoredLn("Ergebnis wurde erfolgreich gelöscht.", ForegroundColor.GREEN);
       else
           this.service.getIOHandler().printErrorMessage("Das angegebene Ergebnis konnte nicht gelöscht werden");
    }

    private void deleteRaceTrack() {

    }

    private void deleteDriver() {

    }
}
