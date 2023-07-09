package at.fhburgenland.enumerations;

public enum OutageReason {
    UNFALL("Unfall"),
    MOTORSCHADEN("Motorschaden");

    private final String reason;

    OutageReason(String reasonString) {
        this.reason = reasonString;
    }

    public String getReason() {
        return this.reason;
    }
}
