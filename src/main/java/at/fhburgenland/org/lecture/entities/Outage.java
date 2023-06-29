package at.fhburgenland.org.lecture.entities;


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
    @Column(name = "fahrerID", updatable = false, nullable = false, unique = true)
    private int driverId;

    @Id
    @Column(name = "rennenID", updatable = false, nullable = false, unique = true)
    private int raceId;

    @ManyToOne
    @JoinColumn(name = "rennenID", insertable = false, updatable = false)
    private Race race;

    @OneToOne
    @JoinColumn(name = "fahrerID", insertable = false, updatable = false)
    private Driver driver;
}
