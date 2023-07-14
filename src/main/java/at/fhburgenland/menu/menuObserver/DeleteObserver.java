package at.fhburgenland.menu.menuObserver;

import at.fhburgenland.RaceManagementService;
import at.fhburgenland.database.entities.*;
import at.fhburgenland.enumerations.ForegroundColor;
import at.fhburgenland.interfaces.ReadEntity;
import at.fhburgenland.interfaces.Service;
import at.fhburgenland.enumerations.MenuPages;

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
        this.deleteEntity(() -> this.executeReadForOutage(), Outage.class);
    }

    private void deleteResult() {
        deleteEntity(() -> this.executeReadForResult(), RaceTrack.class);
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
