package at.fhburgenland.org.lecture.enumerations;

public enum CRUD
{
    CREATE("Anlegen"),
    READ("Anzeigen"),
    UPDATE("Bearbeiten"),
    DELETE("Löschen");

    private String label;

    CRUD(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
