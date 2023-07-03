package at.fhburgenland.enumerations;

public enum MenuPages {
    MAIN("Hauptmenü"),
    DRIVER("Fahrer verwalten"),
    OUTAGE("Ausfälle verwalten"),
    RACE("Rennen verwalten"),
    RACETRACK("Rennstrecken verwalten"),
    RESULT("Ergebnisse verwalten"),
    SPONSOR("Sponsoren verwalten"),
    TEAM("Teams verwalten"),
    VEHICLE("Fahrzeuge verwalten"),
    QUERY("Spezielle abfragen"),
    EXIT("Beenden");

    private String label;

    MenuPages(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
