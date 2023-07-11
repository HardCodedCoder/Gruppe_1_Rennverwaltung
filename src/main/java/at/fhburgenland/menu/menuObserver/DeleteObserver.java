package at.fhburgenland.menu.menuObserver;

import at.fhburgenland.RaceEntityManager;
import at.fhburgenland.RaceManagementService;
import at.fhburgenland.entities.*;
import at.fhburgenland.enumerations.ForegroundColor;
import at.fhburgenland.interfaces.ReadEntity;
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
        this.deleteEntity(() -> this.executeReadForVehicle(), Vehicle.class);
    }

    private void deleteTeam() {
       this.deleteEntity(() -> this.executeReadForTeam(), Team.class);
    }

    private void deleteSponsor() {
        this.deleteEntity(() -> this.executeReadForSponsor(), Sponsor.class);
    }

    private void deleteRace() {
        this.deleteEntity(() -> this.executeReadForRace(), Race.class);
    }


    private void deleteOutage() {
        this.executeReadForOutage();
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
        this.executeReadForResult();
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
        deleteEntity(() -> this.executeReadForRaceTrack(), RaceTrack.class);
    }

    private void deleteDriver() {
        deleteEntity(() -> this.executeReadForDriver(), Driver.class);
    }

    private <T> void  deleteEntity(ReadEntity readEntityFunction, Class clazz) {
        readEntityFunction.readEntity();
        int toDelete = this.service.getIOHandler().getNumberFromUser("Bitte geben Sie die Id die Sie löschen möchten", false).intValue();
        var entityManager = RaceManagementService.getEntityManagerMap().get(clazz);
        T entity = (T) entityManager.read(toDelete);
        if (entity == null) {
            this.service.getIOHandler().printErrorMessage("Die eingegebene id: " + toDelete + " existiert nicht! Versuchen Sie den Vorgang nochmal");
            return;
        }
        if (this.service.getIOHandler().askToContinue("Möchten Sie den Eintrag wirklich löschen? Der Vorgang kann nicht rückgängig gemacht werden! (y/n)"))
            if (entityManager.delete(entity))
                this.service.getIOHandler().printColoredLn("Eintrag wurde erfolgreich gelöscht.", ForegroundColor.GREEN);
            else
                this.service.getIOHandler().printErrorMessage("Der Eintrag mit der id: " + toDelete + " konnte nicht gelöscht werden");
    }
}
