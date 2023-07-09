package at.fhburgenland.menu.menuObserver;

import at.fhburgenland.RaceManagementService;
import at.fhburgenland.entities.Vehicle;
import at.fhburgenland.enumerations.ForegroundColor;
import at.fhburgenland.interfaces.Service;
import at.fhburgenland.enumerations.MenuPages;

public class UpdateObserver extends BaseMenuObserver{
    /**
     * Initializes a new instance of the BaseMenuObserver class.
     *
     * @param service The service.
     */
    public UpdateObserver(Service service) {
        super(service);
    }

    @Override
    public void update(MenuPages fromMenu) {
        switch (fromMenu) {
            case DRIVER -> updateDriver();
            case OUTAGE -> updateOutage();
            case RACE -> updateRace();
            case RACETRACK -> updateRaceTrack();
            case RESULT -> updateResult();
            case SPONSOR -> updateSponsor();
            case TEAM -> updateTeam();
            case VEHICLE -> updateVehicle();
        }
    }

    private void updateVehicle() {
        this.executeReadForVehicle();
        int vehicleId = this.service.getIOHandler().getNumberFromUser("Bitte geben Sie die ID des Fahrzeugs ein, dass sie bearbeiten möchten", false).intValue();
        Vehicle vehicle = (Vehicle)RaceManagementService.getEntityManagerMap().get(Vehicle.class).read(vehicleId);
        if (vehicle == null) {
            this.service.getIOHandler().printErrorMessage("Fahrzeug nicht gefunden. Bitte versuchen Sie es erneut!");
            return;
        }
        String vehicleBrand = this.service.getIOHandler().askUserForInput("Bitte gib eine neue Fahrzeugmarke ein", true);
        String vehicleModel = this.service.getIOHandler().askUserForInput("Bitte gib eine neue Modellbezeichnung ein", true);
        int creationYear = this.service.getIOHandler().getNumberFromUser("Bitte gib ein neues Baujahr ein",false).intValue();
        vehicle.setBrand(vehicleBrand);
        vehicle.setModel(vehicleModel);
        vehicle.setConstructionYear(creationYear);
        var entityManager = RaceManagementService.getEntityManagerMap().get(Vehicle.class);
        if (entityManager.update(vehicle))
            this.service.getIOHandler().printColoredLn("Fahrzeug erfolgreich bearbeitet.", ForegroundColor.GREEN);
        else
            this.service.getIOHandler().printErrorMessage("Fahrzeug konnte aufgrund eines Fehlers nicht bearbeitet werden!");
    }

    private void updateTeam() {

    }

    private void updateSponsor() {

    }

    private void updateResult() {
        // 1. Ergebnie anzeigen
        // 2. Die Primary keys eingeben lassen (zum Beispiel in form rennen_id, erster, zweiter, dritter (optional))
        // 3. Mit den eingegeben Daten ein Result erzeugen.
        // 4. Mit dem Result ein Result suchen lassen und in variable abspeichern.
        // 5. Gefundenes Result modifizieren.
        // 6. Update ausführen.
    }

    private void updateRaceTrack() {

    }

    private void updateRace() {

    }

    private void updateOutage() {

    }

    private void updateDriver() {

    }
}
