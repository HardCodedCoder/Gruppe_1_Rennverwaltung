package at.fhburgenland.menu.menuObserver;

import at.fhburgenland.RaceManagementService;
import at.fhburgenland.database.entities.*;
import at.fhburgenland.enumerations.ForegroundColor;
import at.fhburgenland.interfaces.ReadEntity;
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
        Vehicle vehicle = this.getHybernateManagedEntity(() -> this.executeReadForVehicle(), Vehicle.class);
        if (vehicle == null)
            return;
        Vehicle newData = this.createVehicleObject();
        vehicle.setBrand(newData.getBrand());
        vehicle.setModel(newData.getModel());
        vehicle.setConstructionYear(newData.getConstructionYear());
        var entityManager = RaceManagementService.getEntityManagerMap().get(Vehicle.class);
        if (entityManager.update(vehicle))
            this.service.getIOHandler().printColoredLn("Fahrzeug erfolgreich bearbeitet.", ForegroundColor.GREEN);
        else
            this.service.getIOHandler().printErrorMessage("Fahrzeug konnte aufgrund eines Fehlers nicht bearbeitet werden!");
    }

    private void updateTeam() {
        Team team = this.getHybernateManagedEntity(() -> this.executeReadForTeam(), Team.class);
        if (team == null)
            return;
        Team newData = this.createTeamObject();
        if (newData == null)
            return;
        team.setName(newData.getName());
        team.setFoundingYear(newData.getFoundingYear());
        team.setSponsorId(newData.getSponsorId());
        this.updateEntity(team, Team.class);
    }

    private void updateSponsor() {
        Sponsor sponsor = this.getHybernateManagedEntity(() -> this.executeReadForSponsor(), Sponsor.class);
        if (sponsor == null)
            return;
        Sponsor newData = this.createSponsorObject();
        sponsor.setName(newData.getName());
        this.updateEntity(sponsor, Sponsor.class);
    }

    private void updateResult() {
        Result result = this.getHybernateManagedEntity(() -> this.executeReadForResult(), Result.class);
        if (result == null)
            return;
        Result newData = this.createResultObject();
        if (newData == null)
            return;
        result.setRaceId(newData.getRaceId());
        result.setFirstId(newData.getFirstId());
        result.setSecondId(newData.getSecondId());
        result.setThirdId(newData.getThirdId());
        Race race = (Race)RaceManagementService.getEntityManagerMap().get(Race.class).read(result.getRaceId());
        result.setFirstDriver(newData.getFirstDriver());
        result.setSecondDriver(newData.getSecondDriver());
        result.setRace(race);
        if (result.getThirdId() != null) {
            result.setThirdDriver(newData.getThirdDriver());
        }
        this.updateEntity(result, Result.class);
    }

    private void updateRaceTrack() {
        RaceTrack raceTrack = this.getHybernateManagedEntity(() -> this.executeReadForRaceTrack(), RaceTrack.class);
        if (raceTrack == null)
            return;
        RaceTrack newData = this.createRaceTrackObject();
        raceTrack.setName(newData.getName());
        raceTrack.setState(newData.getState());
        raceTrack.setCity(newData.getCity());
        updateEntity(raceTrack, RaceTrack.class);
    }

    private void updateRace() {
        Race race = this.getHybernateManagedEntity(() -> this.executeReadForRace(), Race.class);
        if (race == null)
            return;
        Race newData = this.createRaceObject();
        race.setRaceTrackId(newData.getRaceTrackId());
        race.setDate(newData.getDate());
        race.setName(newData.getName());
        updateEntity(race, Race.class);
    }

    private void updateOutage() {
        Outage outage = this.getHybernateManagedEntity(() -> this.executeReadForOutage(), Outage.class);
        if (outage == null)
            return;
        Outage newData = this.createOutageObject();
        outage.setDriverId(newData.getDriverId());
        outage.setRaceId(newData.getRaceId());
        outage.setReason(newData.getReason());
        Driver driverData = (Driver)RaceManagementService.getEntityManagerMap().get(Driver.class).read(outage.getDriverId());
        Race raceData = (Race)RaceManagementService.getEntityManagerMap().get(Race.class).read(outage.getRaceId());
        outage.setDriver(driverData);
        outage.setRace(raceData);
        this.updateEntity(outage, Outage.class);
    }

    private void updateDriver() {
        Driver driver = this.getHybernateManagedEntity(() -> this.executeReadForDriver(), Driver.class);
        if (driver == null)
            return;
        Driver newData = this.createDriverObject();
        if (newData == null)
            return;
        driver.setFirstName(newData.getFirstName());
        driver.setLastName(newData.getLastName());
        driver.setNationality(newData.getNationality());
        driver.setVehicleId(newData.getVehicleId());
        driver.setTeamId(newData.getTeamId());
        this.updateEntity(driver, Driver.class);
    }

    private <T> void updateEntity(T entity, Class clazz) {
        var entityManager = RaceManagementService.getEntityManagerMap().get(clazz);
        if (entityManager.update(entity))
            this.service.getIOHandler().printColoredLn("Eintrag erfolgreich bearbeitet.", ForegroundColor.GREEN);
        else
            this.service.getIOHandler().printErrorMessage("Der Eintrag konnte aufgrund eines Fehlers nicht bearbeitet werden!");

    }

    private <T> T getHybernateManagedEntity(ReadEntity readFunction, Class<T> clazz) {
        readFunction.readEntity();
        int id = this.service.getIOHandler().getNumberFromUser("Bitte geben Sie die ID ein, die sie bearbeiten möchten", false).intValue();
        T entity = (T)RaceManagementService.getEntityManagerMap().get(clazz).read(id);
        if (entity == null) {
            this.service.getIOHandler().printErrorMessage("Eintrag nicht gefunden. Bitte versuchen Sie es erneut!");
            return null;
        }

        return entity;
    }
}
