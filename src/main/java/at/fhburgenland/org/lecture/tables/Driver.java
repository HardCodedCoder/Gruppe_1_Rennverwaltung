package at.fhburgenland.org.lecture.tables;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Fahrer")
@Table(name = "fahrer")
@ToString
public class Driver {
    /**
     * Holds the id of the driver
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fahrer_ID", updatable = false, nullable = false, unique = true)
    private int driverId;

    /**
     * Holds the first name of the driver
     */
    @Column(name = "vorname", nullable = false, length =50)
    private String firstName;

    /**
     * Holds the last name of the driver
     */
    @Column(name = "nachname", nullable = false, length =50)
    private String lastName;

    /**
     * Holds the nationality of the driver
     */
    @Column(name = "nationalitaet", nullable = false, length =50)
    private String nationality;

    /**
     * Holds the vehicle ID
     */
    @Column(name = "fahrzeug_ID", nullable = false, updatable = false, insertable = false)
    private int vehicleId;

    /**
     * Holds the team ID
     */
    @Column(name = "team_ID", nullable = false, updatable = false, insertable = false)
    private int teamId;
}
