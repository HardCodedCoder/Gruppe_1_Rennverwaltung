package at.fhburgenland.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Ausfall")
@Table(name = "ausfall")
@ToString
public class Outage {
    @Id
    @Column(name = "fahrer_id", updatable = false, nullable = false, unique = true)
    private int driverId;

    @Id
    @Column(name = "rennen_id", updatable = false, nullable = false, unique = true)
    private int raceId;

    @Column(name = "ausfallszenario", nullable = false)
    private String reason;

    @ManyToOne
    @JoinColumn(name = "rennen_id", insertable = false, updatable = false)
    private Race race;

    @OneToOne
    @JoinColumn(name = "fahrer_id", insertable = false, updatable = false)
    private Driver driver;

    public Outage() {

    }

    public Outage(int driverId, int raceId) {
        this.driverId = driverId;
        this.raceId = raceId;
    }
    public Outage(int driverId, int raceId, String reason) {
        this.driverId = driverId;
        this.raceId = raceId;
        this.reason = reason;
    }
}
