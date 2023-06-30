package at.fhburgenland.org.lecture.entities;

import at.fhburgenland.org.lecture.entities.Vehicle;
import at.fhburgenland.org.lecture.entities.Team;

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
    @Column(name = "fahrzeug_ID", nullable = false, insertable = false, updatable = false)
    private int vehicleId;

    /**
     * Holds the team ID
     */
    @Column(name = "team_ID", nullable = false, insertable = false, updatable = false)
    private int teamId;

    @OneToOne
    @JoinColumn(name = "fahrzeug_ID", insertable = false, updatable = false)
    private Vehicle vehicle;

    @OneToOne
    @JoinColumn(name = "team_ID", updatable = false, insertable = false)
    private Team team;

    /**
     * Initializes a new instance of the Driver class.
     */
    public Driver() {

    }

    /**
     * Initializes a new instance of the Driver class.
     * @param firstName The first name of the driver.
     * @param lastName The last name of the driver.
     * @param nationality The nationality of the driver.
     * @param vehicleId The vehicle ID.
     * @param teamId The Team ID.
     */
    public Driver(String firstName, String lastName, String nationality, int vehicleId, int teamId)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.vehicleId = vehicleId;
        this.teamId = teamId;
    }
}
